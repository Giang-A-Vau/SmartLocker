package com.intel.smartlockers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button[] buttons = new Button[21];

    ArrayList<Lockers> list;
    LockersDatabase database;
    private String msRFIDTagCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        database = new LockersDatabase(this);

        initView();

        setData();
    }

    private void setData() {
        list = database.getListLocker();
        for (int i=0; i<buttons.length; i++){
            buttons[i].setBackgroundResource(R.drawable.bg_normal);
            buttons[i].setText("TỦ TRỐNG");
        }
        setStateLock();
    }

    private void setStateLock() {
        for (int i=0; i<list.size(); i++){
            Log.d("myLog", list.get(i).toString());
            for (int j=0; j<buttons.length; j++){
                if (list.get(i).getLockerId() == j){
                    Lockers lockers = list.get(i);
                    buttons[j].setText(getString(R.string.format_label, lockers.getNumber(), lockers.getLabel()));
                    if (lockers.getStatus() == 1) buttons[j].setBackgroundResource(R.drawable.bg_register);
                    else if (lockers.getStatus() == 2) buttons[j].setBackgroundResource(R.drawable.bg_use);
                    else buttons[j].setBackgroundResource(R.drawable.bg_normal);
                }
            }
        }
    }

    private void initView() {
        buttons[0] = findViewById(R.id.bt1);
        buttons[1] = findViewById(R.id.bt2);
        buttons[2] = findViewById(R.id.bt3);
        buttons[3] = findViewById(R.id.bt4);
        buttons[4] = findViewById(R.id.bt5);
        buttons[5] = findViewById(R.id.bt6);
        buttons[6] = findViewById(R.id.bt7);
        buttons[7] = findViewById(R.id.bt8);
        buttons[8] = findViewById(R.id.bt9);
        buttons[9] = findViewById(R.id.bt10);
        buttons[10] = findViewById(R.id.bt11);
        buttons[11] = findViewById(R.id.bt12);
        buttons[12] = findViewById(R.id.bt13);
        buttons[13] = findViewById(R.id.bt14);
        buttons[14] = findViewById(R.id.bt15);
        buttons[15] = findViewById(R.id.bt16);
        buttons[16] = findViewById(R.id.bt17);
        buttons[17] = findViewById(R.id.bt18);
        buttons[18] = findViewById(R.id.bt19);
        buttons[19] = findViewById(R.id.bt20);
        buttons[20] = findViewById(R.id.bt21);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(this);
        }
    }

    private void buttonClick(int pos){
        Lockers lockers = null;
        for (int i=0; i<list.size(); i++){

            if (list.get(i).getLockerId() == pos){
                lockers = list.get(i);
            }
        }

        if (lockers == null){
            showDialogRegister(pos);
            return;
        }else if (lockers.getStatus() == 1){
            showDialogUse(lockers);
            return;
        }else if (lockers.getStatus() == 2){
            showDialogLock(lockers);
            return;
        }
    }

    Button btUse;
    private void showDialogUse(final Lockers lockers) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_use, null);

        tvNumber =view.findViewById(R.id.tvNumber);
        tvLabel =view.findViewById(R.id.tvLabel);
        btUse =view.findViewById(R.id.btUse);
        btDelete =view.findViewById(R.id.btDelete);

        tvNumber.setText(lockers.getNumber()+"");
        tvLabel.setText(lockers.getLabel());

        btUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockers.setStatus(2);

                database.updateLocker(lockers);

                setData();
                dialog.dismiss();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteOneLocker(lockers);

                setData();
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    Button btLock, btDelete;
    TextView tvNumber, tvLabel;
    private void showDialogLock(final Lockers lockers) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_lock, null);

        tvNumber =view.findViewById(R.id.tvNumber);
        tvLabel =view.findViewById(R.id.tvLabel);
        btLock =view.findViewById(R.id.btLock);
        btDelete =view.findViewById(R.id.btDelete);

        tvNumber.setText(lockers.getNumber()+"");
        tvLabel.setText(lockers.getLabel());

        btLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockers.setStatus(1);

                database.updateLocker(lockers);

                setData();
                dialog.dismiss();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteOneLocker(lockers);

                setData();
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    AlertDialog dialog = null;
    EditText edtNumber, edtLabel;
    Button btRegister;
    private void showDialogRegister(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_register, null);

        edtNumber =view.findViewById(R.id.edtEnterNumber);
        edtLabel =view.findViewById(R.id.edtEnterLabel);
        btRegister =view.findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = edtNumber.getText().toString();
                String label = edtLabel.getText().toString();

                if (!number.equals("") && !label.equals("")){
                    Lockers lockers = new Lockers();
                    lockers.setLockerId(pos);
                    lockers.setNumber(Integer.parseInt(number));
                    lockers.setLabel(label);
                    lockers.setStatus(1);

                    database.addNewLocker(lockers);

                    setData();
                    dialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, "Please enter params!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                buttonClick(0);
                break;
            case R.id.bt2:
                buttonClick(1);
                break;
            case R.id.bt3:
                buttonClick(2);
                break;
            case R.id.bt4:
                buttonClick(3);
                break;
            case R.id.bt5:
                buttonClick(4);
                break;
            case R.id.bt6:
                buttonClick(5);
                break;
            case R.id.bt7:
                buttonClick(6);
                break;
            case R.id.bt8:
                buttonClick(7);
                break;
            case R.id.bt9:
                buttonClick(8);
                break;
            case R.id.bt10:
                buttonClick(9);
                break;
            case R.id.bt11:
                buttonClick(10);
                break;
            case R.id.bt12:
                buttonClick(11);
                break;
            case R.id.bt13:
                buttonClick(12);
                break;
            case R.id.bt14:
                buttonClick(13);
                break;
            case R.id.bt15:
                buttonClick(14);
                break;
            case R.id.bt16:
                buttonClick(15);
                break;
            case R.id.bt17:
                buttonClick(16);
                break;
            case R.id.bt18:
                buttonClick(17);
                break;
            case R.id.bt19:
                buttonClick(18);
                break;
            case R.id.bt20:
                buttonClick(19);
                break;
            case R.id.bt21:
                buttonClick(20);
                break;
        }
    }

    //Đọc thẻ RFID
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("KeyEvent:", event.getKeyCode() + "   " + event.getUnicodeChar());
        if(event.getAction() == KeyEvent.ACTION_UP)
        {
            int mRFIDCodeNum = 0;
            byte[] mbRFIDTagCode = new byte[0];

            if((byte)event.getUnicodeChar() != 13 && (byte)event.getUnicodeChar() != 10 && (byte)event.getUnicodeChar() != 0) {
                mbRFIDTagCode[mRFIDCodeNum] = (byte) event.getUnicodeChar();
                mRFIDCodeNum++;
            }

            //Nếu reader không đọc được Enter thì đọc quá 10 byte là reset lại buffer
            if ((byte)event.getUnicodeChar() == 13 || (byte)event.getUnicodeChar() == 10 || mRFIDCodeNum > 10) {

                String strCode = "";
                for (int i = 0; i < mRFIDCodeNum; i++) {
                    strCode = strCode + String.format("%02X", mbRFIDTagCode[i]);
                }

                Log.d("KeyEvent:", "RFID tage code: " + strCode);
                mRFIDCodeNum = 0;
                this.msRFIDTagCode = strCode;

                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}

