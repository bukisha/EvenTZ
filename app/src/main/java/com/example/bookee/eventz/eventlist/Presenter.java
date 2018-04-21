package com.example.bookee.eventz.eventlist;

import android.util.Log;

import com.example.bookee.eventz.data.Event;

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
                view.populateEventListActivity(extractEventNames(events));
            }

            @Override
            public void onFailure() {
                if (notViewExists()) return;
                view.displayError();
            }
        };
        model.fetchEventsForCategory(categoryName, modelCallback);
    }

    private boolean notViewExists() {
       return this.view!=null;
    }

    @Override
    public String getIdOfClickedItem(String eventName) {
        return model.getIdForName(eventName);
    }

    @Override
    public void attachView(EventListActivity eventListActivity) {
        this.view = eventListActivity;
    }

    @Override
    public void detachView() {
        this.view = null;
    }


    private ArrayList<String> extractEventNames(ArrayList<Event> events) {
        ArrayList<String> eventNames = new ArrayList<>();
        for (Event e : events) {
            eventNames.add(e.getName().getText());
        }
        return eventNames;
    }
}
