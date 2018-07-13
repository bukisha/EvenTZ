package com.example.bookee.eventz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EventsDatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "EventsDatabaseHelper";
    private static final String DATABASE_NAME = "followedEvents.db";
    public static final String TABLE_NAME = "followedEvents";
    private static String COLUMN_EVENT_NAME = "name";
    private static String COLUMN_EVENT_ID = "eventId";
    public static final String COLUMN_EVENT_CANCEL_ID = "cancelId";
    private static final int DATABASE_VERSION = 1;
    private static EventsDatabaseHelper helperInstance;
    private SQLiteDatabaseRepository attachedRepository;

    public static EventsDatabaseHelper getInstance(Context context) {
        if (helperInstance == null) {
            helperInstance = new EventsDatabaseHelper(context.getApplicationContext());
        }
        return helperInstance;
    }

    private EventsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "EventsDatabaseHelper: creating helper class instance");

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: ");
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER, " + COLUMN_EVENT_NAME + " TEXT," + COLUMN_EVENT_ID + " TEXT PRIMARY KEY UNIQUE)";
        Log.d(TAG, "onCreate: ===========================" + createTable);
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void attachRepository(SQLiteDatabaseRepository sqLiteDatabaseRepository) {
        attachedRepository = sqLiteDatabaseRepository;
    }

    public SQLiteDatabaseRepository getAttachedRepository() {
        return attachedRepository;
    }

    public static   String getColumnEventName() {
        return COLUMN_EVENT_NAME;
    }

    public static String getColumnEventId() {
        return COLUMN_EVENT_ID;
    }
}
