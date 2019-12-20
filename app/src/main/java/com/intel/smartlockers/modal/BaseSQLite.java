package com.intel.smartlockers.modal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Date;
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
        db.execSQL(String.format("CREATE TABLE [Histories] ([ID] INTEGER  PRIMARY KEY NOT NULL,[lockerID] INTEGER  NOT NULL,[employeeID] INTEGER  NOT NULL,[date] TIMESTAMP  NOT NULL,[status] INTEGER DEFAULT '0' NULL)"));
        db.execSQL(String.format("CREATE TABLE [LockerGroups] ([ID] INTEGER  NOT NULL PRIMARY KEY,[name] TEXT  NULL,[description] TEXT  NULL);"));
        db.execSQL(String.format("CREATE TABLE [Lockers] ([ID] INTEGER  PRIMARY KEY NOT NULL,[groupLockerID] INTEGER  NOT NULL,[name] TEXT  NULL,[data] TEXT  NULL,[status] INTEGER DEFAULT '0' NULL);"));

        insertNewData_LookerGroups(db);
        insertNewData_Lookers(db);
        insertNewData_EmployeeGroups(db);
        insertNewData_Employee(db);
    }

    private void insertNewData_EmployeeGroups(SQLiteDatabase db) {
        Log.w("TAG_LOG", "insertNewData_EmployeeGroups");
        ArrayList<EmployeeGroup> employeeGroups = new ArrayList<EmployeeGroup>();
        employeeGroups.add(new EmployeeGroup(1, "Nhóm user Giáo viên", "Đây là nhóm user của Giáo viên"));
        employeeGroups.add(new EmployeeGroup(2, "Nhóm user Học sinh", "Đây là nhóm user của Học sinh"));
        employeeGroups.add(new EmployeeGroup(3, "Nhóm user Bảo vệ", "Đây là nhóm user của Bảo vệ"));

        for(int i = 0; i < employeeGroups.size(); i++){
            ContentValues values = new ContentValues();
            values.put("name", employeeGroups.get(i).getName());
            values.put("description", employeeGroups.get(i).getDescription());

            db.insert("EmployeeGroups", null, values);
        }
    }

    private void insertNewData_LookerGroups(SQLiteDatabase db) {
        Log.w("TAG_LOG", "insertNewData_LookerGroups");
        ArrayList<LockerGroup> lockerGroups = new ArrayList<LockerGroup>();
        lockerGroups.add(new LockerGroup(1, "Nhóm tủ Giáo viên", "Đây là nhóm tủ dành cho Giáo viên"));
        lockerGroups.add(new LockerGroup(2, "Nhóm tủ Học sinh", "Đây là nhóm tủ dành cho Học sinh"));
        lockerGroups.add(new LockerGroup(3, "Nhóm tủ Bảo vệ", "Đây là nhóm tủ dành cho Bảo vệ"));

        for(int i = 0; i < lockerGroups.size(); i++){
            db.insert("LockerGroups", null, lockerGroups.get(i).getValues());
        }
    }

    private void insertNewData_Employee(SQLiteDatabase db) {
        Log.w("TAG_LOG", "insertNewData_Lookers");
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1, 1, "0610788460", "Đinh Thanh Thịnh", 1));
        employees.add(new Employee(2, 1, "0610788666", "Nguyễn Văn A", 0));

        for(int i = 0; i < employees.size(); i++){
            db.insert("Employees", null, employees.get(i).getValues());
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
        lockers.add(new Lockers(19, 1, "G17", "", 0));
        lockers.add(new Lockers(20, 1, "G18", "", 0));
        lockers.add(new Lockers(21, 1, "G19", "", 0));
        lockers.add(new Lockers(22, 1, "G110", "", 0));

        for(int i = 0; i < lockers.size(); i++){
            db.insert("Lockers", null, lockers.get(i).getValues());
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

    public ArrayList<LockerGroup> getAllLockerGroups (){
        ArrayList<LockerGroup> lockerGroups = new ArrayList<LockerGroup>();

        String query = "SELECT * FROM LockerGroups";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            lockerGroups.add(new LockerGroup(cursor));
            cursor.moveToNext();
        }

        return lockerGroups;
    }

    public Employee getEmployee(String code){
        String query = "SELECT * FROM Employees WHERE code='"+ code +"'";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return new Employee(cursor);
        }return  null;
    }

    public boolean updateLocker(Lockers lockers){
        SQLiteDatabase database = this.getWritableDatabase();
        database.update("Lockers", lockers.getValues(), "ID=" +lockers.getID(), null);

        return true;
    }

    public Lockers getLocker(int lockerID){
        String query = "SELECT * FROM Lockers WHERE ID="+ lockerID;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return new Lockers(cursor);
        }
        return null;
    }


    public boolean createHistory(History history){
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert("Histories", null, history.getValues());
        return true;
    }

    public ArrayList<History> getHistoryForEmployee(int employeeID){
        ArrayList<History> histories = new ArrayList<History>();

        String query = "SELECT * FROM Histories WHERE employeeID="+ employeeID;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToLast();

        while(cursor.isBeforeFirst() == false) {
            histories.add(new History(cursor));
            cursor.moveToPrevious();
        }


        return  histories;
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
