package com.intel.smartlockers.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.sql.Date;

public class History {
    private int ID;
    private int lockerID;
    private int employeeID;
    private String date;
    private int status;

    public History() {
    }

    public History(int ID, int lockerID, int employeeID, String date, int status) {
        this.ID = ID;
        this.lockerID = lockerID;
        this.employeeID = employeeID;
        this.date = date;
        this.status = status;
    }

    public History(Cursor cursor) {
        try {
            this.ID = cursor.getInt(0);
            this.lockerID = cursor.getInt(1);
            this.employeeID = cursor.getInt(2);
            this.date = cursor.getString(3);
            this.status = cursor.getInt(4);
        }catch (Exception e){
            Log.w("TAG_LOG", "Convert DB to EmployeeGroups error");
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLockerID() {
        return lockerID;
    }

    public void setLockerID(int lockerID) {
        this.lockerID = lockerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "History{" +
                "ID=" + ID +
                ", lockerID=" + lockerID +
                ", employeeID=" + employeeID +
                ", date='" + date + '\'' +
                ", status=" + status +
                '}';
    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        values.put("lockerID", this.lockerID);
        values.put("employeeID", this.employeeID);
        values.put("date", this.date);
        values.put("status", this.status);
        return values;
    }
}
