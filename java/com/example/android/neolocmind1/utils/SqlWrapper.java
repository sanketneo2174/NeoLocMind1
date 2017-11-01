package com.example.android.neolocmind1.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.neolocmind1.data.ReminderContract;
import com.example.android.neolocmind1.data.ReminderDbHelper;

/**
 * Created by sanket.ashok.nerella on 01/11/17.
 */

public class SqlWrapper {
    public static Context context;
    public static SQLiteDatabase mDb;
    public static Cursor mCursor;
    public static boolean cursorChanged = false;
    public static void getAllReminders(){
        mCursor = mDb.query(
                ReminderContract.ReminderEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    public static void initDB(Context context){
        mDb = (new ReminderDbHelper(context)).getWritableDatabase();
    }
    public static long insertReminder(String title, String description, String latitude, String longitude, String mode, String imagePath){
        ContentValues cv = new ContentValues();
        cv.put(ReminderContract.ReminderEntry.COLUMN_REMINDER_TITLE, title);
        cv.put(ReminderContract.ReminderEntry.COLUMN_REMINDER_DESCRIPTION, description);
        cv.put(ReminderContract.ReminderEntry.COLUMN_REMINDER_ENABLED, mode);
        cv.put(ReminderContract.ReminderEntry.COLUMN_REMINDER_LATITUDE, latitude);
        cv.put(ReminderContract.ReminderEntry.COLUMN_REMINDER_LONGITUDE, longitude);
        cv.put(ReminderContract.ReminderEntry.COLUMN_REMINDER_IMAGEPATH, imagePath);
        return mDb.insert(ReminderContract.ReminderEntry.TABLE_NAME, null, cv);
    }
}
