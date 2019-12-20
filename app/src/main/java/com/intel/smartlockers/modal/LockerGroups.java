package com.intel.smartlockers.modal;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class LockerGroups {
    private int ID;
    private String name;
    private String description;
    private ArrayList<Lockers> lockers = new ArrayList<Lockers>();

    public LockerGroups() {
    }

    public LockerGroups(Cursor cursor) {
        try {
            this.ID = cursor.getInt(0);
            this.name = cursor.getString(1);
            this.description = cursor.getString(2);
        }catch (Exception e){
            Log.w("TAG_LOG", "Convert DB to LockerGroups error");
        }
    }

    public LockerGroups(int ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Lockers> getLockers() {
        return lockers;
    }

    public void setLockers(ArrayList<Lockers> lockers) {
        this.lockers = lockers;
    }

    @Override
    public String toString() {
        return "LockerGroups{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean saveInDataBase(){
        return false;
    }

    public boolean deleteInDataBase(){
        return false;
    }
}
