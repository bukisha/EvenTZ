package com.example.bookee.eventz.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EventsDatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "EventsDatabaseHelper";
    private static final String DATABASE_NAME = "followedEvents.db";
    public static final String TABLE_NAME = "followedEvents";
    public static final String COLUMN_EVENT_NAME = "name";
    public static final String COLUMN_EVENT_ID = "eventId";
    private static final int DATABASE_VERSION=1;
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
        Log.d(TAG, "onCreate: ==========================="+createTable);
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void attachRepository(SQLiteDatabaseRepository sqLiteDatabaseRepository) {
        attachedRepository=sqLiteDatabaseRepository;
    }

    public SQLiteDatabaseRepository getAttachedRepository() {
        return attachedRepository;
    }

    //    public void addEvent(Event event) {
//        database = getWritableDatabase();
//        ContentValues addDatabaseRow = new ContentValues();
//        addDatabaseRow.put(COLUMN_EVENT_NAME, event.getName().getText());
//        addDatabaseRow.put(COLUMN_EVENT_ID, event.getId());
//
//        database.insert(TABLE_NAME, null, addDatabaseRow);
//        Log.d(TAG, "addEvent: "+this.toString());
//    }

//    public String getEventIdForName(String eventName) {
//        String selection=COLUMN_EVENT_NAME+" =?";
//        String[] selectionArgs={eventName};
//        String idToReturn=null;
//        database=getReadableDatabase();
//        Cursor cursor=database.query(TABLE_NAME,
//                null,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//        while (cursor.moveToNext()) {
//            String selectedEventName=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENT_NAME));
//            String selectedEventId=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENT_ID));
//            if(selectedEventName.equals(eventName)) {
//                idToReturn=selectedEventId;
//                break;
//            }
//        }
//        cursor.close();
//        return idToReturn;
//    }

//    public String getEventNameForId(String eventId) {
//        String projections[]={COLUMN_EVENT_NAME,COLUMN_EVENT_ID};
//        String selection=COLUMN_EVENT_ID+" =?";
//        String[] selectionArgs={eventId};
//        String returnEventName=null;
//        database=getReadableDatabase();
//        Cursor returnCursor=database.query(
//                TABLE_NAME,
//                projections,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//        while (returnCursor.moveToNext()) {
//           String selectedEventName=returnCursor.getString(returnCursor.getColumnIndexOrThrow(COLUMN_EVENT_NAME));
//           String selectedEventId=returnCursor.getString(returnCursor.getColumnIndexOrThrow(COLUMN_EVENT_ID));
//           if(selectedEventId.equals(eventId)) {
//                returnEventName = selectedEventName;
//                break;
//          }
//        }
//        returnCursor.close();
//        return returnEventName;
//    }

//    public List<String> getAllEventsIds() {
//        List<String> followedIds=new ArrayList<>();
//            String[] projections={COLUMN_EVENT_ID};
//            database =getReadableDatabase();
//            Cursor cursor=database.query(
//                    TABLE_NAME,
//                    projections,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//            while (cursor.moveToNext()) {
//                String id=cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENT_ID));
//                followedIds.add(id);
//            }
//        cursor.close();
//        return followedIds;
//    }

/*    public void deleteRowWithId(String deleteId) {
        String selection=COLUMN_EVENT_ID+" LIKE ?";
        String[] selectionArgs={deleteId};
        this.getReadableDatabase().delete(TABLE_NAME,selection,selectionArgs);
    }*/

}
