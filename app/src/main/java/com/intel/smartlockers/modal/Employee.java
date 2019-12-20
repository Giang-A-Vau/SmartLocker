package com.intel.smartlockers.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class Employee {
    private int ID;
    private int groupEmployeeID;
    private String code;
    private String name;
    private int gender;

    public Employee() {
    }

    public Employee(Cursor cursor) {
        try {
            this.ID = cursor.getInt(0);
            this.groupEmployeeID = cursor.getInt(1);
            this.code = cursor.getString(2);
            this.name = cursor.getString(3);
            this.gender = cursor.getInt(4);
        }catch (Exception e){
            Log.w("TAG_LOG", "Convert DB to EmployeeGroups error");
        }
    }

    public Employee(int ID, int groupEmployeeID, String code, String name, int gender) {
        this.ID = ID;
        this.groupEmployeeID = groupEmployeeID;
        this.code = code;
        this.name = name;
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int groupEmployeeID() {
        return groupEmployeeID;
    }

    public void setGroupEmployeeID(int groupEmployeeId) {
        this.groupEmployeeID = groupEmployeeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "ID=" + ID +
                ", groupEmployeeID=" + groupEmployeeID +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        values.put("groupEmployeeID", this.name);
        values.put("code", this.code);
        values.put("name", this.name);
        values.put("gender", this.gender);
        return values;
    }
}
