package com.intel.smartlockers.modal;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class EmployeeGroup {
    private int ID;
    private String name;
    private String description;
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public EmployeeGroup() {
    }

    public EmployeeGroup(Cursor cursor) {
        try {
            this.ID = cursor.getInt(0);
            this.name = cursor.getString(1);
            this.description = cursor.getString(2);
        }catch (Exception e){
            Log.w("TAG_LOG", "Convert DB to EmployeeGroups error");
        }
    }

    public EmployeeGroup(int ID, String name, String description) {
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

    public ArrayList<Employee> getLockers() {
        return employees;
    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        values.put("name", this.name);
        values.put("description", this.description);
        return values;
    }


    @Override
    public String toString() {
        return "EmployeeGroups{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }

    public boolean saveInDataBase(){
        return false;
    }

    public boolean deleteInDataBase(){
        return false;
    }


}
