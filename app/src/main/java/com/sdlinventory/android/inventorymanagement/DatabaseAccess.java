package com.sdlinventory.android.inventorymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    ArrayList list = new ArrayList();
    ArrayList list1 = new ArrayList();

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }


    //to open the database

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    //to close the database

    public void close(){
        this.db.close();
    }

    //method to login into the database
    //passing employee id and password as arguments

    //Employee Login begins here

    public List<String> getProducts(String table_name, int deptID) {
        ArrayList list= new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT prodName FROM '" + table_name + "' WHERE deptID='"+ deptID +"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex("prodName")));
            cursor.moveToNext();
        }
        cursor.close();
        for (int i=0;i<list.size();i++)
        {
            Log.i("mYapp",list.get(i).toString());
        }

        return list;
        }

    public List<String> getProductPrice(String table_name, int deptID) {
        ArrayList list= new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT basePrice FROM '" + table_name + "' WHERE deptID='"+ deptID +"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex("basePrice")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getBrand(String table_name, int deptID) {
        ArrayList list= new ArrayList<>();
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT brand FROM '" + table_name + "' WHERE deptID='"+ deptID +"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex("brand")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void insertWishlist(String product, int quantity){

        db = openHelper.getWritableDatabase();
        String query = "INSERT INTO Wishlist VALUES ('"+product+"',"+quantity+");";
        db.execSQL(query);
    }

    public void displayWishlist() {
        db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Wishlist", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex("prodName")));
            list1.add(cursor.getString(cursor.getColumnIndex("quantity")));
            cursor.moveToNext();
        }
        cursor.close();
    }


}

