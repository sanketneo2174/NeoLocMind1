package com.example.android.neolocmind1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sanket.ashok.nerella on 30/10/17.
 */

public class ReminderDbHelper extends SQLiteOpenHelper {
    //Database name
    private static final String DATABASE_NAME = "reminder.db";

    private static final int DATABASE_VERSION = 1;

    public ReminderDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        Log.i("sdsdsdsds","reached oncreate");
        final String SQL_CREATE_REMINDER_TABLE = "CREATE TABLE " + ReminderContract.ReminderEntry.TABLE_NAME + " (" +
                ReminderContract.ReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ReminderContract.ReminderEntry.COLUMN_REMINDER_TITLE + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_REMINDER_DESCRIPTION + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_REMINDER_ENABLED + " TEXT NOT NULL," +
                ReminderContract.ReminderEntry.COLUMN_REMINDER_LATITUDE + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_REMINDER_LONGITUDE + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_REMINDER_IMAGEPATH + " TEXT NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_REMINDER_TABLE);
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReminderContract.ReminderEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
