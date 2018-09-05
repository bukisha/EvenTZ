package com.example.bookee.eventz.followed;

import android.content.Context;

import com.example.bookee.eventz.data.EventsDatabaseHelper;
import com.example.bookee.eventz.data.SQLiteDatabaseRepository;

import java.util.ArrayList;
import java.util.List;

class Model implements MvpContract.Model {
    private EventsDatabaseHelper databaseHelper;
    private SQLiteDatabaseRepository repository;

    public Model(Context context) {
        databaseHelper = EventsDatabaseHelper.getInstance(context);
        repository = new SQLiteDatabaseRepository(databaseHelper);
    }

    @Override
    public void getFollowedEventNames(MvpContract.GetEventsNamesListCallback callback) {
        callback.onSuccess(prepareListOfNames(repository.getEventsIds()));
    }

    public void closeDatabase() {
        databaseHelper.close();
    }

    @Override
    public void getIdForName(String name, MvpContract.GetEventIdCallback idCallback) {
        idCallback.onSuccess(repository.getEventIdForName(name));
    }

    private List<String> prepareListOfNames(List<String> eventIds) {
        List<String> eventNames = new ArrayList<>();
        for (int i = 0; i < eventIds.size(); i++) {
            String name = repository.getEventNameForId(eventIds.get(i));
            eventNames.add(name);
        }
        return eventNames;
    }
}
