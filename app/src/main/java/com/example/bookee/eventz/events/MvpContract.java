package com.example.bookee.eventz.events;

import com.example.bookee.eventz.data.pojos.Event;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        void fetchEventsForCategory(String categoryName, FetchEventsForCategoryCallback callback);
        String getIdForName(String eventName);
    }

    interface Presenter {
        void fetchEventsForCategory(String categoryName);
        String getIdOfClickedItem(String eventName);
        void attachView(EventsListActivity eventsListActivity);
        void detachView();
        void launchFollowedEvents();
    }

    interface View {
        void populateEventListActivity(ArrayList<Event> eventNames);
        void displayError();
    }

    interface FetchEventsForCategoryCallback {
        void onSuccess(ArrayList<Event> events);
        void onFailure(Throwable t);
    }
}
