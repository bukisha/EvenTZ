package com.example.bookee.eventz.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookee.eventz.data.pojos.Event;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseRepository implements EventDataStorage {
    private EventsDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public SQLiteDatabaseRepository(EventsDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        databaseHelper.attachRepository(this);
    }

    @Override
    public void addEvent(Event event) {
        //databaseHelper.addEvent(event);
        database = databaseHelper.getWritableDatabase();
        ContentValues addDatabaseRow = new ContentValues();
        addDatabaseRow.put(EventsDatabaseHelper.getColumnEventName(), event.getName().getText());
        addDatabaseRow.put(EventsDatabaseHelper.getColumnEventId(), event.getId());

        database.insert(EventsDatabaseHelper.TABLE_NAME, null, addDatabaseRow);
    }

    @Override
    public String getEventIdForName(String eventName) {

        String selection = EventsDatabaseHelper.getColumnEventName() + " =?";
        String[] selectionArgs = {eventName};
        String idToReturn = null;
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(EventsDatabaseHelper.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            String selectedEventName = cursor.getString(cursor.getColumnIndexOrThrow(EventsDatabaseHelper.getColumnEventName()));
            String selectedEventId = cursor.getString(cursor.getColumnIndexOrThrow(EventsDatabaseHelper.getColumnEventId()));
            if (selectedEventName.equals(eventName)) {
                idToReturn = selectedEventId;
                break;
            }
        }
        cursor.close();
        return idToReturn;
        //return databaseHelper.getEventIdForName(name);
    }

    @Override
    public String getEventNameForId(String eventId) {
        String projections[] = {EventsDatabaseHelper.getColumnEventName(), EventsDatabaseHelper.getColumnEventId()};
        String selection = EventsDatabaseHelper.getColumnEventId() + " =?";
        String[] selectionArgs = {eventId};
        String returnEventName = null;
        database = databaseHelper.getReadableDatabase();
        Cursor returnCursor = database.query(
                EventsDatabaseHelper.TABLE_NAME,
                projections,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        while (returnCursor.moveToNext()) {
            String selectedEventName = returnCursor.getString(returnCursor.getColumnIndexOrThrow(EventsDatabaseHelper.getColumnEventName()));
            String selectedEventId = returnCursor.getString(returnCursor.getColumnIndexOrThrow(EventsDatabaseHelper.getColumnEventId()));
            if (selectedEventId.equals(eventId)) {
                returnEventName = selectedEventName;
                break;
            }
        }
        returnCursor.close();
        return returnEventName;
        //return databaseHelper.getEventNameForId(id);
    }

    @Override
    public List<String> getEventsIds() {
        List<String> followedIds = new ArrayList<>();
        String[] projections = {EventsDatabaseHelper.getColumnEventId()};
        database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                EventsDatabaseHelper.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(EventsDatabaseHelper.getColumnEventId()));
            followedIds.add(id);
        }
        cursor.close();
        return followedIds;
        //return databaseHelper.getAllEventsIds();
    }

    @Override
    public void closeDataSource() {
        databaseHelper.close();
    }

    @Override
    public void removeEventWithId(String deleteId) {
        String selection = EventsDatabaseHelper.getColumnEventId() + " LIKE ?";
        String[] selectionArgs = {deleteId};
        databaseHelper.getReadableDatabase().delete(EventsDatabaseHelper.TABLE_NAME, selection, selectionArgs);
        //databaseHelper.deleteRowWithId(id);
    }
}
