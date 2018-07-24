package com.example.bookee.eventz.events;

import android.util.Log;

import com.example.bookee.eventz.data.callbacks.FetchEventsForQueryCallback;
import com.example.bookee.eventz.data.pojos.Event;

import java.util.ArrayList;

class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.View view;
    private MvpContract.Model model;

    Presenter(MvpContract.View view, MvpContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void fetchEventsForCategory(String categoryName) {
        Log.d(TAG, "fetchEventsForCategory: " + categoryName);
        MvpContract.FetchEventsForCategoryCallback modelCallback = new MvpContract.FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                if (notViewExists()) return;
                view.populateEventListActivity(events);
            }

            @Override
            public void onFailure(Throwable t) {
                if (notViewExists()) return;
                view.displayError("error happened");
            }
        };
        model.fetchEventsForCategory(categoryName, modelCallback);
    }

    private boolean notViewExists() {
        return this.view == null;
    }

    @Override
    public String getIdOfClickedItem(String eventName) {
        return model.getIdForName(eventName);
    }

    @Override
    public void attachView(EventsListActivity eventsListActivity) {
        this.view = eventsListActivity;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void launchFollowedEvents() {
        if (notViewExists()) return;
        view.launchFollowedActivity();
    }

    @Override
    public void fetchEventsForQuery(String query) {
        FetchEventsForQueryCallback callback=new FetchEventsForQueryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> list) {
                if(notViewExists()) return;
                view.populateEventListActivity(list);
            }

            @Override
            public void onFailure(Throwable t) {
                if(notViewExists()) return;;
                view.displayError("No event for query");
            }
        };
        model.fetchEventsForQuery(query,callback);

    }
}

