package com.example.m03_bounce;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Database helper class for managing ball data.
 */
public class DBClass extends SQLiteOpenHelper implements DB_Interface {

    public static final int DATABASE_VERSION = 9; // Incremented due to schema changes
    public static final String DATABASE_NAME = "Bounce_DB_Name.db";

    // Table and column names
    private static final String TABLE_NAME = "balls_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_X = "X";
    private static final String COLUMN_Y = "Y";
    private static final String COLUMN_DX = "DX";
    private static final String COLUMN_DY = "DY";
    private static final String COLUMN_COLOR = "COLOR";

    // SQL statements
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_X + " FLOAT, " +
                    COLUMN_Y + " FLOAT, " +
                    COLUMN_DX + " FLOAT, " +
                    COLUMN_DY + " FLOAT, " +
                    COLUMN_COLOR + " INTEGER)";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    /**
     * Called when the database is created for the first time.
     */
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBClass", "Creating database table with SQL: " + SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    /**
     * Called when the database needs to be upgraded.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBClass", "Upgrading database from version " + oldVersion + " to " + newVersion);
        // For simplicity, we'll drop the table and recreate it. In a production app, consider altering the table instead.
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    /////////// Implement Interface ///////////////////////////

    @Override
    public int count() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        db.close();
        Log.v("DBClass", "getCount=" + cnt);
        return cnt;
    }

    @Override
    public int save(DataModel dataModel) {
        Log.v("DBClass", "Saving dataModel: " + dataModel.toString());

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, dataModel.getName());
        values.put(COLUMN_X, dataModel.getX());
        values.put(COLUMN_Y, dataModel.getY());
        values.put(COLUMN_DX, dataModel.getDx());
        values.put(COLUMN_DY, dataModel.getDy());
        values.put(COLUMN_COLOR, dataModel.getColor());

        // Insert the new row
        long id = db.insert(TABLE_NAME, null, values);
        db.close();

        // Return the new row ID as an integer
        return (int) id;
    }

    @Override
    public int update(DataModel dataModel) {
        // Not implemented in this example
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        // Not implemented in this example
        return 0;
    }

    @Override
    public List<DataModel> findAll() {
        List<DataModel> dataList = new ArrayList<>();

        // Build the query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        // Get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Iterate over each row and create DataModel objects
        if (cursor.moveToFirst()) {
            do {
                DataModel data = new DataModel(
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_X)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_Y)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_DX)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_DY)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COLOR))
                );
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.v("DBClass", "findAll=> " + dataList.toString());

        // Return the list of DataModel objects
        return dataList;
    }

    @Override
    public String getNameById(Long id) {
        // Not implemented in this example
        return null;
    }

    // Method to clear all entries in the table
    public void clearAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
        Log.v("DBClass", "All entries deleted from the database.");
    }
}
