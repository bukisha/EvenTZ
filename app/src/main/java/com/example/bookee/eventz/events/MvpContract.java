package com.example.bookee.eventz.events;

import com.example.bookee.eventz.data.callbacks.FetchEventsForQueryCallback;
import com.example.bookee.eventz.data.pojos.Event;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        /**
         * fetch events for given category name from api
         * @param categoryName name of category whose list of events we want
         * @param callback callback so that model can get data back from repository
         */
        void fetchEventsForCategory(String categoryName, FetchEventsForCategoryCallback callback);

        /**
         *
         * @param eventName name of event whose id we want
         * @return id of event for given name
         */
        String getIdForName(String eventName);

        /**
         * fetches list of events for a given query
         * @param query our query
         * @param callback callback so that result can be returned to model from repository
         */
        void fetchEventsForQuery(String query, FetchEventsForQueryCallback callback);
    }

    interface Presenter {
        /**
         * passes category whose list of events we want to model
         * @param categoryName name of category whose list of events we want
         */
        void fetchEventsForCategory(String categoryName);

        /**
         * returns id of clicked item in
         * @param eventName name of clicked event
         * @return id of clicked event
         */
        String getIdOfClickedItem(String eventName);

        /**
         * attaches parent view to presenter
         * @param view parent view
         */
        void attachView(MvpContract.View view);

        /**
         * detaches view from presenter
         */
        void detachView();

        /**
         * signals to presente that view wants to launch FollowedEventsActivity
         */
        void launchFollowedEvents();

        /**
         * signals to presenter we want to get events for query
         * @param query query we want events for
         */
        void fetchEventsForQuery(String query);
    }

    interface View {
        /**
         * launches FollowedEventsActivity
         */
        void launchFollowedActivity();

        /**
         * populates list of Events that are displayed to user
         * @param eventNames list of events we want to display
         */
        void populateEventListActivity(ArrayList<Event> eventNames);

        /**
         * displays error message
         * @param errorMessage message to display
         */
        void displayError(String errorMessage);
    }

    interface FetchEventsForCategoryCallback {
        void onSuccess(ArrayList<Event> events);
        void onFailure(Throwable t);
    }
}
