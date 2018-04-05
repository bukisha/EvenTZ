package com.example.bookee.eventz.eventlist;

import android.util.Log;

import com.example.bookee.eventz.data.Event;

import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter{
    private static final String TAG = "Presenter";
    private MvpContract.View view;
    private MvpContract.Model model;

    Presenter(MvpContract.View view,MvpContract.Model model) {
        this.view=view;
        this.model=model;
    }

    @Override
    public void fetchEventsForCategory( String categoryName) {
        Log.d(TAG, "fetchEventsForCategory: "+ categoryName);
        model.fetchEventsForCategory(categoryName, new MvpContract.FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                view.openEventListActivity(extractEventNames(events));
            }

            @Override
            public void onFailure() {
                view.displayError();
            }
        });
    }

    private ArrayList<String> extractEventNames( ArrayList<Event> events) {
        ArrayList<String> eventNames = new ArrayList<>();
        for (Event e: events) {
            eventNames.add(e.getName().getText());
        }
        return eventNames;
    }
}
