package com.example.bookee.eventz.events;

import android.util.Log;

import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;

import java.util.ArrayList;
import java.util.HashMap;

class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private RetrofitEventsRepository repository;
    private MvpContract.FetchEventsForCategoryCallback presenterCallback;
    private HashMap<String,String> nameToIdHash;

    public Model(RetrofitEventsRepository repository) {
        this.repository = repository;
        nameToIdHash=new HashMap<>(50);
    }

    @Override
    public void fetchEventsForCategory(String categoryId, MvpContract.FetchEventsForCategoryCallback callback) {
        presenterCallback = callback;
        FetchEventsForCategoryCallback repositoryCallback = new FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                populateHash(events);
                presenterCallback.onSuccess(events);
            }

            @Override
            public void onFailure(Throwable t) {
                presenterCallback.onFailure(t);
            }
        };
        repository.fetchEventsForCategory(categoryId, repositoryCallback);
    }

    @Override
    public String getIdForName(String eventName) {
        Log.d(TAG, "getIdForName: "+eventName);
        return   nameToIdHash.get(eventName);
    }

    private void populateHash(ArrayList<Event> events) {
        for(Event e : events) {
            String eventName=e.getName().getText();
            String eventId=e.getId();
            nameToIdHash.put(eventName,eventId);
        }
    }
}
