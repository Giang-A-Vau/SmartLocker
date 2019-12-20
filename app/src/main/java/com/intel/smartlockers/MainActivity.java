package com.intel.smartlockers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.intel.smartlockers.fragment.fragmentHistory;
import com.intel.smartlockers.fragment.fragmentHome;
import com.intel.smartlockers.modal.BaseSQLite;
import com.intel.smartlockers.modal.Employee;
import com.intel.smartlockers.modal.History;
import com.intel.smartlockers.modal.Lockers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    private BottomNavigationView navView;
    public static BaseSQLite baseSQLite;
    public static boolean isOpenLooker = false;
    public static String codeRFID = ""; //Ma dung: 0610788460
    public static Employee employeeIndex = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseSQLite = new BaseSQLite(this);

        addControls();
        addEvents();
        changeFragment(new fragmentHome(baseSQLite));
    }

    private void addControls() {
        navView = findViewById(R.id.nav_view);
    }

    private void addEvents() {
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navi_home:
                        changeFragment(new fragmentHome(baseSQLite));
                        return true;
                    case R.id.navi_history:
                        changeFragment(new fragmentHistory());
                        return true;
                }
                return false;
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_constraint_layout, fragment)
                .commit();
    }

    //Đọc thẻ RFID
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_UP){
            if(event.getKeyCode() != KeyEvent.KEYCODE_ENTER){
                codeRFID += (char) event.getUnicodeChar();
            } else{//Đã đọc xong mã codeRFID

                employeeIndex =  baseSQLite.getEmployee(codeRFID);//Lấy thông tin employee theo mã codeRFID
                if(employeeIndex != null){//Kết quả trả về != null => Có employee này trong DB
                    Toast.makeText(this, employeeIndex.getName() + " - " + employeeIndex.getCode(), Toast.LENGTH_LONG).show();

                    ArrayList<History> histories = baseSQLite.getHistoryForEmployee(employeeIndex.getID());//Lấy lịch sử  truy cập hệ thống theo employee
                    Log.w("TAG_HIS", histories.toString() + "");

                    if(histories.size() != 0){//Có lịch sử trả về
                        Lockers lockers = baseSQLite.getLocker(histories.get(0).getLockerID());//Lấy thông tin tủ theo lịch sử gần nhất
                        if(lockers != null && lockers.getStatus() == 1){//Có thông tin tủ trả về và trạng thái đang là có người sử dụng. Tức là employee đang sử dụng tủ này
                            openLocker(lockers);//Thực hiện mở tủ trên giao diện main
                        }else{//Không có thông tin tủ trả về, hoặc trạng thái của tủ trả về là trống (chưa có ai sử dụng)
                            isOpenLooker = true;//Cho phép người dùng click vào để mở tủ
                            Toast.makeText(this, "Hãy mở 1 tủ trống bất kỳ để đựng đồ", Toast.LENGTH_LONG).show();
                            changeFragment(new fragmentHome(baseSQLite));//Làm mới lại giao diện
                        }
                    }else {//Không có lịch sử trả về
                        isOpenLooker = true;//Cho phép người dùng click vào để mở tủ
                        Toast.makeText(this, "Hãy mở 1 tủ trống bất kỳ để đựng đồ", Toast.LENGTH_LONG).show();
                        changeFragment(new fragmentHome(baseSQLite));//Làm mới lại giao diện
                    }
                }else{//Thẻ này không có employee nào sở hữu
                    Toast.makeText(this, "Thẻ của bạn chưa được đăng ký", Toast.LENGTH_LONG).show();
                }

                codeRFID = "";
            }
        }
        return  false;
    }

    private void openLocker(final Lockers lockers) {
        Log.w("TAG_LOG", lockers.toString());

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog_locker, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();

        ((TextView) view.findViewById(R.id.txt_info_locker_name)).setText("THÔNG TIN TỦ: " + lockers.getName());
        ((EditText) view.findViewById(R.id.edit_info_locker_data)).setText(lockers.getData());
        ((Button) view.findViewById(R.id.btn_info_looker_done)).setText("Đóng tủ");
        ((Button) view.findViewById(R.id.btn_info_locker_back)).setText("Trả tủ");

        final EditText edit_data = view.findViewById(R.id.edit_info_locker_data);

        //Khi click vào nút Trả tủ
        view.findViewById(R.id.btn_info_locker_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MainActivity.isOpenLooker = false;
                lockers.setStatus(0);
                if(baseSQLite.updateLocker(lockers)){//Cập nhật lại trạng thái của tủ về 0
                    //Tạo mới lịch sử
                    baseSQLite.createHistory(new History(1,
                            lockers.getID(), MainActivity.employeeIndex.getID(),
                            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date()), 1));

                    Toast.makeText(view.getContext(), "Trả tủ thành công", Toast.LENGTH_LONG).show();
                    changeFragment(new fragmentHome(baseSQLite));//Làm mới giao diện
                }else {
                    Toast.makeText(view.getContext(), "Trả tủ thất bại", Toast.LENGTH_LONG).show();
                }

                alertDialog.dismiss();
            }
        });

        //Khi click vào nút Đóng tủ
        view.findViewById(R.id.btn_info_looker_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MainActivity.isOpenLooker = false;//Khóa người dùng không cho phép mở những tủ khác

                //Ghi lại lịch sử
                baseSQLite.createHistory(new History(1,
                        lockers.getID(), MainActivity.employeeIndex.getID(),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date()), 2));

                lockers.setData(edit_data.getText().toString());
                baseSQLite.updateLocker(lockers);//Cập nhật lại trạng thái tủ
                alertDialog.dismiss();
            }
        });
    }
}

