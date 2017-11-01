package com.example.android.neolocmind1.data;

import android.provider.BaseColumns;

/**
 * Created by sanket.ashok.nerella on 30/10/17.
 */

public class ReminderContract {
    public static final class ReminderEntry implements BaseColumns {
        public static final String TABLE_NAME = "reminder";
        public static final String COLUMN_REMINDER_TITLE = "title";
        public static final String COLUMN_REMINDER_DESCRIPTION = "description";
        public static final String COLUMN_REMINDER_ENABLED = "enabled";
        public static final String COLUMN_REMINDER_LATITUDE = "latitude";
        public static final String COLUMN_REMINDER_LONGITUDE = "longitude";
        public static final String COLUMN_REMINDER_IMAGEPATH = "imagepath";
    }
}
