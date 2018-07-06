package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.pojos.Event;

import java.util.List;

public interface EventDataStorage {

    void addEvent(Event event);

    String getEventIdForName(String name);

    String getEventNameForId(String id);

    List<String> getEventsIds();

    void closeDataSource();

    void removeEventWithId(String id);
}
