package com.intel.smartlockers.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.intel.smartlockers.Adapter.LockerGroupAdapter;
import com.intel.smartlockers.R;
import com.intel.smartlockers.modal.BaseSQLite;
import com.intel.smartlockers.modal.LockerGroup;
import com.intel.smartlockers.modal.Lockers;

import java.util.ArrayList;

public class fragmentHome extends Fragment implements View.OnClickListener{
    BaseSQLite baseSQLite;
    ArrayList<LockerGroup> lockerGroups = new ArrayList<LockerGroup>();
    ArrayList<Lockers> lockers = new ArrayList<Lockers>();


    RecyclerView recyclerView;
    SwipeRefreshLayout srl_data;

    public fragmentHome(BaseSQLite baseSQLite){
        this.baseSQLite = baseSQLite;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.w("TAG_LOG", "In HOME");
        View view = inflater.inflate(R.layout.fragment_home,null);

        lockerGroups = baseSQLite.getAllLockerGroups();
        lockers = baseSQLite.getAllLockers();

        //Thuc hiện tìm con của LockerGroup
        for(int i = 0; i < lockerGroups.size(); i++){
            for(int j = 0; j < lockers.size(); j++){
                if(lockers.get(j).getGroupLockerID() == lockerGroups.get(i).getID()){
                    lockerGroups.get(i).getLockers().add(lockers.get(j));
                }
            }
        }

        addControls(view);
        addEvents(view);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        LockerGroupAdapter lockerGroupAdapter = new LockerGroupAdapter(view.getContext(), lockerGroups);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lockerGroupAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    private void addEvents(View view) {
    }

    private void addControls(View view) {
        recyclerView = view.findViewById(R.id.recy_home_data);


    }



}
