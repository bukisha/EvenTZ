package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.pojos.Event;

import java.util.List;

public interface EventDataStorage {
    /**
     * inserts event into data storage
     * @param event event which should be inserted into storage
     */
    void addEvent(Event event);

    /**
     * queries data storage for event id for event with name
     * @param name name of event
     * @return id of event with name
     */
    String getEventIdForName(String name);

    /**
     * queries data storage for event name of an event with given id
     * @param id id of event which name we want
     * @return name of event with given id
     */
    String getEventNameForId(String id);

    /**
     *
     * @return list of ids of all events in data storage
     */
    List<String> getEventsIds();

    /**
     * closes data storage
     */
    void closeDataSource();

    /**
     * removes event with given id from data storage
     * @param id id of event which we want to be removed
     */
    void removeEventWithId(String id);
}
