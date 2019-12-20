package com.intel.smartlockers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.intel.smartlockers.fragment.fragmentHistory;
import com.intel.smartlockers.fragment.fragmentHome;
import com.intel.smartlockers.modal.BaseSQLite;

public class MainActivity extends AppCompatActivity{
    private BottomNavigationView navView;
    private BaseSQLite baseSQLite;

    public static boolean isOpenLooker = false;
    public static String codeRFID = ""; //Ma dung: 0610788460

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseSQLite = new BaseSQLite(this);

//        baseSQLite.getReadableDatabase().isOpen();

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
            } else{

                Toast.makeText(this, codeRFID, Toast.LENGTH_SHORT).show();
                isOpenLooker = true;
                codeRFID = "";
                changeFragment(new fragmentHome(baseSQLite));
            }
        }
        return  false;
    }
}

