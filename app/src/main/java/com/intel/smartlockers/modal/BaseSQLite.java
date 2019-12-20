package com.intel.smartlockers.modal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseSQLite extends SQLiteOpenHelper {
    public BaseSQLite(@Nullable Context context) {
        super(context, "DB_SmartLocker", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w("TAG_LOG", "creating DB");
        db.execSQL(String.format("CREATE TABLE [EmployeeGroups] ([ID] INTEGER  NOT NULL PRIMARY KEY,[name] TEXT  NULL,[description] TEXT  NULL);"));
        db.execSQL(String.format("CREATE TABLE [Employees] ([ID] INTEGER  PRIMARY KEY NOT NULL,[groupEmployeeID] INTEGER  NOT NULL,[code] TEXT  UNIQUE NOT NULL,[name] TEXT  NULL,[gender] INTEGER DEFAULT '0' NULL,[brithDay] DATE  NULL)"));
        db.execSQL(String.format("CREATE TABLE [Histories] ([ID] INTEGER  NOT NULL PRIMARY KEY,[lockerID] INTEGER  NOT NULL,[employeeID] INTEGER  NOT NULL,[date] TIMESTAMP  NOT NULL);"));
        db.execSQL(String.format("CREATE TABLE [LockerGroups] ([ID] INTEGER  NOT NULL PRIMARY KEY,[name] TEXT  NULL,[description] TEXT  NULL);"));
        db.execSQL(String.format("CREATE TABLE [Lockers] ([ID] INTEGER  PRIMARY KEY NOT NULL,[groupLockerID] INTEGER  NOT NULL,[name] TEXT  NULL,[data] TEXT  NULL,[status] INTEGER DEFAULT '0' NULL);"));

        insertNewData_LookerGroups(db);
        insertNewData_Lookers(db);
    }

    private void insertNewData_LookerGroups(SQLiteDatabase db) {
        Log.w("TAG_LOG", "insertNewData_LookerGroups");
        ArrayList<LockerGroups> lockerGroups = new ArrayList<LockerGroups>();
        lockerGroups.add(new LockerGroups(1, "Nhóm tủ Giáo viên", "Đây là nhóm tủ dành cho Giáo viên"));
        lockerGroups.add(new LockerGroups(2, "Nhóm tủ Học sinh", "Đây là nhóm tủ dành cho Học sinh"));
        lockerGroups.add(new LockerGroups(3, "Nhóm tủ Bảo vệ", "Đây là nhóm tủ dành cho Bảo vệ"));

        for(int i = 0; i < lockerGroups.size(); i++){
            ContentValues values = new ContentValues();
            values.put("name", lockerGroups.get(i).getName());
            values.put("description", lockerGroups.get(i).getDescription());
            db.insert("LockerGroups", null, values);
        }
    }

    private void insertNewData_Lookers(SQLiteDatabase db) {
        Log.w("TAG_LOG", "insertNewData_Lookers");
        ArrayList<Lockers> lockers = new ArrayList<Lockers>();
        lockers.add(new Lockers(1, 1, "G11", "", 0));
        lockers.add(new Lockers(2, 1, "G12", "", 0));
        lockers.add(new Lockers(3, 1, "G13", "", 0));
        lockers.add(new Lockers(4, 1, "G14", "", 0));
        lockers.add(new Lockers(5, 1, "G15", "", 0));
        lockers.add(new Lockers(6, 1, "G16", "", 0));
        lockers.add(new Lockers(7, 2, "G21", "", 0));
        lockers.add(new Lockers(8, 2, "G22", "", 0));
        lockers.add(new Lockers(9, 2, "G23", "", 0));
        lockers.add(new Lockers(10, 2, "G24", "", 0));
        lockers.add(new Lockers(11, 2, "G25", "", 0));
        lockers.add(new Lockers(12, 2, "G26", "", 0));
        lockers.add(new Lockers(13, 3, "G31", "", 0));
        lockers.add(new Lockers(14, 3, "G32", "", 0));
        lockers.add(new Lockers(15, 3, "G33", "", 0));
        lockers.add(new Lockers(16, 3, "G34", "", 0));
        lockers.add(new Lockers(17, 3, "G35", "", 0));
        lockers.add(new Lockers(18, 3, "G36", "", 0));

        for(int i = 0; i < lockers.size(); i++){
            ContentValues values = new ContentValues();
            values.put("name", lockers.get(i).getName());
            values.put("groupLockerID", lockers.get(i).getGroupLockerID());
            values.put("name", lockers.get(i).getName());
            values.put("data", lockers.get(i).getData());
            values.put("status", lockers.get(i).getStatus());
            db.insert("Lockers", null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS EmployeeGroups"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Employees"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Histories"));
        db.execSQL(String.format("DROP TABLE IF EXISTS LockerGroups"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Lockers"));
    }

    public ArrayList<LockerGroups> getAllLockerGroups (){
        ArrayList<LockerGroups> lockerGroups = new ArrayList<LockerGroups>();

        String query = "SELECT * FROM LockerGroups";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            lockerGroups.add(new LockerGroups(cursor));
            cursor.moveToNext();
        }

        return lockerGroups;
    }

    public ArrayList<Lockers> getAllLockers (){
        ArrayList<Lockers> lockers = new ArrayList<Lockers>();

        String query = "SELECT * FROM Lockers";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            lockers.add(new Lockers(cursor));
            cursor.moveToNext();
        }

        return lockers;
    }
}
