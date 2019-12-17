package com.intel.smartlockers;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class LockersDatabase extends SQLiteOpenHelper {
    private static final String DATANAME = "smartlocker.db";
    private static final String TABLE = "lockers";
    private static final String ID = "ID";
    private static final String LOCKER_ID = "LockersId";
    private static final String NUMBER = "Number";
    private static final String LABEL = "Label";
    private static final String STATUS = "Status";

    public LockersDatabase(Context context) {
        super(context, DATANAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table
        String sql = "create table " + TABLE + "(" +
                ID + " integer primary key autoincrement, " +
                LOCKER_ID + " integer unique," +
                NUMBER + " integer," +
                LABEL + " text," +
                STATUS + " integer" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    //add new Lockers
    public void addNewLocker(Lockers locker) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "insert into " + TABLE + "(" +LOCKER_ID+","+ NUMBER + "," + LABEL + "," + STATUS  + ") values ('" +
                    locker.getLockerId()+"','"+locker.getNumber() + "','" + locker.getLabel() + "','" + locker.getStatus() + "')";
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("myLog", "add locker err");
        }
    }

    //update Lockers
    public void updateLocker(Lockers locker) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "update " + TABLE + " set " + LOCKER_ID + "='" + locker.getLockerId() + "',"+NUMBER + "='" + locker.getNumber() + "',"+
                    LABEL + "='" + locker.getLabel() +"',"+STATUS + "='" + locker.getStatus() +"' where " +
                    ID + "='" + locker.getId() + "'";
            db.execSQL(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get ont Locker
    public Lockers getLocker(String lockerId) {
        Lockers locker = new Lockers();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "";
            sql = "select * from " + TABLE + " where "+LOCKER_ID+"='"+lockerId+"'";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    locker.setId(cursor.getInt(0));
                    locker.setLockerId(cursor.getInt(1));
                    locker.setNumber(cursor.getInt(2));
                    locker.setLabel(cursor.getString(3));
                    locker.setStatus(cursor.getInt(4));
                } while (cursor.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locker;
    }

    // get list Lockers
    public ArrayList<Lockers> getListLocker() {
        ArrayList<Lockers> list = new ArrayList<Lockers>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from " + TABLE + " order by " + ID + " asc";

            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    Lockers locker = new Lockers();
                    locker.setId(cursor.getInt(0));
                    locker.setLockerId(cursor.getInt(1));
                    locker.setNumber(cursor.getInt(2));
                    locker.setLabel(cursor.getString(3));
                    locker.setStatus(cursor.getInt(4));

                    list.add(locker);
                } while (cursor.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // delete 1 Lockers
    public void deleteOneLocker(Lockers locker) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "delete from " + TABLE + " where " + LOCKER_ID + "='" + locker.getLockerId() + "'";
            db.execSQL(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete All Lockers
    public void deleteAllLocker() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "delete from " + TABLE;
            db.execSQL(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
