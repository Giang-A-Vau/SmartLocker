package com.intel.smartlockers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class FunctionSystem {
    private Context context;
    private AlertDialog alertDialogLoading = null;

    public FunctionSystem(final Context context){
        this.context = context;
    }

    public DateFormat dateOnlyFormat = new SimpleDateFormat("dd/MM/yyyy");
    public DateFormat timeOnLyFormat = new SimpleDateFormat("HH:mm");
    public DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public DecimalFormat formatMoney = new DecimalFormat("###,###,###");


    public String getAddress(){
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return null;
    }




    public void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) this.context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public void showKeyboardFrom(View view){
        InputMethodManager imm = (InputMethodManager) this.context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

//    public void showDialogSuccess(final String value){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
//        final LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();
//        final View view = inflater.inflate(R.layout.layout_dialog_success, null);
//        builder.setView(view);
//
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//        ((TextView) view.findViewById(R.id.txt_dialog_success)).setText(value);
//        view.findViewById(R.id.btn_dialog_success).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//            alertDialog.dismiss();
//            }
//        });
//    }

//    public void showDialogError(final String value){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
//        final LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();
//        final View view = inflater.inflate(R.layout.layout_dialog_error, null);
//        builder.setView(view);
//
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//        ((TextView) view.findViewById(R.id.txt_dialog_error)).setText(value != null ? value: context.getString(R.string.check_error_server));
//        view.findViewById(R.id.btn_ticketvote_ok).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                alertDialog.dismiss();
//            }
//        });
//    }

//    public void showLoading(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
//        LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();
//        View view = inflater.inflate(R.layout.layout_loading, null);
//
//        alertDialogLoading = builder.create();
//        alertDialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialogLoading.setView(view, 0,0,0,0);
//
//        alertDialogLoading.setCanceledOnTouchOutside(false);
//        alertDialogLoading.setCancelable(false);
//        alertDialogLoading.show();
//    }

    public void hideLoading(){
        if(alertDialogLoading != null){
            alertDialogLoading.dismiss();
            alertDialogLoading = null;
        }
    }


}
