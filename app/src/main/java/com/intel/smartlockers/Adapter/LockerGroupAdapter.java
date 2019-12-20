package com.intel.smartlockers.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.intel.smartlockers.R;
import com.intel.smartlockers.modal.LockerGroup;

import java.util.ArrayList;

public class LockerGroupAdapter extends RecyclerView.Adapter<LockerGroupAdapter.MyViewHolder> {
    private ArrayList<LockerGroup> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public LockerGroupAdapter(Context context, ArrayList<LockerGroup> listData) {
        Log.w("TAG_LOG", "In LockerGroupAdapter" + listData.toString());
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_description;
        public RecyclerView rec_listData;

        public MyViewHolder(View itemView) {
            super(itemView);
            addControls();
            addEvents();
        }

        private void addControls() {
            txt_name = itemView.findViewById(R.id.txt_lockergroup_name);
            txt_description = itemView.findViewById(R.id.txt_lockergroup_description);
            rec_listData = itemView.findViewById(R.id.recy_lockergroup_data);
        }

        private void addEvents() {
        }


    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_locker_group, parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LockerGroup lockerGroups = listData.get(position);

        holder.txt_name.setText(lockerGroups.getName());
        holder.txt_description.setText(lockerGroups.getDescription());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.rec_listData.setLayoutManager(mLayoutManager);
        LockerAdapter lockerAdapter = new LockerAdapter(context, lockerGroups.getLockers());

        holder.rec_listData.setLayoutManager(new GridLayoutManager(context, 4));


        holder.rec_listData.setItemAnimator(new DefaultItemAnimator());
        holder.rec_listData.setAdapter(lockerAdapter);
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }
}
