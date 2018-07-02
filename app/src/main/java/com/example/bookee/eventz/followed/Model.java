package com.example.bookee.eventz.followed;

import com.example.bookee.eventz.details.EventsDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Model implements MvpContract.Model {
    private EventsDatabaseHelper databaseHelper;

    public Model(final EventsDatabaseHelper helper) {
        this.databaseHelper = helper;
    }

    @Override
    public void getFollowedEventNames(MvpContract.GetDatabaseEventsCallback callback) {
        callback.onSuccess(prepareListOfNames(databaseHelper.getAllFollowedEventsIds()));
    }

    private List<String> prepareListOfNames(List<String> eventIds) {
        List<String> eventNames = new ArrayList<>();
        for (int i = 0; i < eventIds.size(); i++) {
            String name = databaseHelper.getEventNameForId(eventIds.get(i));
            eventNames.add(name);
        }
        return eventNames;
    }
}
