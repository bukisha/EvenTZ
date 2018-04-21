package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.Event;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        void fetchEventsForCategory(String categoryName, FetchEventsForCategoryCallback callback);
        String getIdForName(String eventName);
    }

    interface Presenter {
        void fetchEventsForCategory(String categoryName);
        String getIdOfClickedItem(String eventName);
        void attachView(EventListActivity eventListActivity);
        void detachView();
    }

    interface View {
        void populateEventListActivity(ArrayList<String> eventNames);
        void displayError();
    }

    interface FetchEventsForCategoryCallback {
        void onSuccess(ArrayList<Event> events);
        void onFailure();
    }
}
