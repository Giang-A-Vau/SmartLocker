package com.intel.smartlockers.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intel.smartlockers.MainActivity;
import com.intel.smartlockers.R;
import com.intel.smartlockers.modal.History;
import com.intel.smartlockers.modal.Lockers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LockerAdapter extends RecyclerView.Adapter<LockerAdapter.MyViewHolder> {
    private ArrayList<Lockers> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public LockerAdapter(Context context, ArrayList<Lockers> listData) {
        Log.w("TAG_LOG", "In LockerAdapter" + listData.toString());
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            addControls();
            addEvents();
        }

        private void addControls() {
            txt_name = itemView.findViewById(R.id.txt_locker_name);
        }

        private void addEvents() {
            txt_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoLocker(getAdapterPosition());
                }
            });
        }

        private void showInfoLocker(int position){
            final Lockers lockers = listData.get(position);

            if(lockers.getStatus() == 0 && MainActivity.isOpenLooker){
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                final View view = inflater.inflate(R.layout.layout_dialog_locker, null);
                builder.setView(view);

                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);
                alertDialog.show();

                ((TextView) view.findViewById(R.id.txt_info_locker_name)).setText("THÔNG TIN TỦ: " + lockers.getName());
                ((Button) view.findViewById(R.id.btn_info_looker_done)).setText("Nhận tủ");
                final EditText edit_data = view.findViewById(R.id.edit_info_locker_data);

                view.findViewById(R.id.btn_info_locker_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        MainActivity.isOpenLooker = false;
                        alertDialog.dismiss();
                    }
                });

                view.findViewById(R.id.btn_info_looker_done).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        lockers.setStatus(1);
                        lockers.setData(edit_data.getText().toString());

                         notifyDataSetChanged();

                        MainActivity.isOpenLooker = false;
                        MainActivity.baseSQLite.createHistory(new History(1,
                                lockers.getID(), MainActivity.employeeIndex.getID(),
                                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date()), 0));

                        if(MainActivity.baseSQLite.updateLocker(lockers)){
                            Toast.makeText(view.getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(view.getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }

                        alertDialog.dismiss();
                    }
                });
            }
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_locker, parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Lockers lockers = listData.get(position);
        holder.txt_name.setText(lockers.getName());

        if(lockers.getStatus() == 1){
            holder.txt_name.setBackgroundResource(R.drawable.background_locker_used);
            holder.txt_name.setTextColor(Color.WHITE);
        }else if(lockers.getStatus() == 0){
            holder.txt_name.setBackgroundResource(R.drawable.background_locker_unused);
            holder.txt_name.setTextColor(Color.BLACK);
        }
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }
}
