package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.pojos.Event;

import java.util.List;

public class SQLiteDatabaseRepository implements EventDataStorage {
    private EventsDatabaseHelper databaseHelper;

    public SQLiteDatabaseRepository(EventsDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void addEvent(Event event) {
        databaseHelper.addEvent(event);
    }

    @Override
    public String getEventIdForName(String name) {
        return databaseHelper.getEventIdForName(name);
    }

    @Override
    public String getEventNameForId(String id) {
        return databaseHelper.getEventNameForId(id);
    }

    @Override
    public List<String> getEventsIds() {
        return databaseHelper.getAllEventsIds();
    }

    @Override
    public void closeDataSource() {
        databaseHelper.close();
    }

    @Override
    public void removeEventWithId(String id) {
        databaseHelper.deleteRowWithId(id);
    }
}
