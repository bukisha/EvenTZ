package com.example.bookee.eventz.events;

import com.example.bookee.eventz.data.callbacks.FetchEventsForQueryCallback;
import com.example.bookee.eventz.data.pojos.Event;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        void fetchEventsForCategory(String categoryName, FetchEventsForCategoryCallback callback);
        String getIdForName(String eventName);

        void fetchEventsForQuery(String query, FetchEventsForQueryCallback callback);
    }

    interface Presenter {
        void fetchEventsForCategory(String categoryName);
        String getIdOfClickedItem(String eventName);
        void attachView(EventsListActivity eventsListActivity);
        void detachView();
        void launchFollowedEvents();
        void fetchEventsForQuery(String query);
    }

    interface View {
        void launchFollowedActivity();
        void populateEventListActivity(ArrayList<Event> eventNames);
        void displayError(String errorMessage);
    }

    interface FetchEventsForCategoryCallback {
        void onSuccess(ArrayList<Event> events);
        void onFailure(Throwable t);
    }
}
