package com.example.bookee.eventz.eventlist;

import android.util.Log;

import com.example.bookee.eventz.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.Event;

import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter{
    private static final String TAG = "Presenter";
    private MvpContract.View view;
    private MvpContract.Model model;


    Presenter(MvpContract.View view) {
        this.view=view;
        model=new Model();
    }

    @Override
    public void fetchEventsForCategory( String categoryName) {
        Log.d(TAG, "fetchEventsForCategory: "+ categoryName);
        model.fetchEventsForCategory(categoryName, new FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                view.displayEvents(extractEventNames(events));
            }

            @Override
            public void onFailure(Throwable t) {
                view.displayError(t);
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