package com.intel.smartlockers.modal;

import android.database.Cursor;
import android.util.Log;

public class Lockers {
    private int ID;
    private int groupLockerID;
    private String name;
    private String data;
    private int status; //0: Trong, 1: Da co nguoi dung

    public Lockers() {
    }

    public Lockers(int ID, int groupLockerID, String name, String data, int status) {
        this.ID = ID;
        this.groupLockerID = groupLockerID;
        this.name = name;
        this.data = data;
        this.status = status;
    }

    public Lockers(Cursor cursor) {
        try {
            this.ID = cursor.getInt(0);
            this.groupLockerID = cursor.getInt(1);
            this.name = cursor.getString(2);
            this.data = cursor.getString(3) ;
            this.status = cursor.getInt(4);
        }catch (Exception e){
            Log.w("TAG_LOG", "Convert DB to LockerGroups Lockers");
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGroupLockerID() {
        return groupLockerID;
    }

    public void setGroupLockerID(int groupLockerID) {
        this.groupLockerID = groupLockerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lockers{" +
                "ID=" + ID +
                ", groupLockerID=" + groupLockerID +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", status=" + status +
                '}';
    }
}
