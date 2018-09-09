package com.example.bookee.eventz.followed;

import java.util.List;

interface MvpContract {

    interface Model {
        /**
         * gets names of followed events from data source
         * @param callback callback so that model can handle result of fetch operation
         */
        void getFollowedEventNames(GetEventsNamesListCallback callback);

        /**
         * closes data source
         */
        void closeDatabase();

        /**
         * gets id of an event for a given name from data source
         * @param name name of event whose id we want
         * @param idCallback callback so that model can handle result of the operation
         */
        void getIdForName(String name, GetEventIdCallback idCallback);
    }

    interface Presenter {
        /**
         * signals to model that we want all names of followed events
         */
        void getFollowedEventNames();

        /**
         * attach parent view to presenter
         * @param view parent view
         */
        void attachView(MvpContract.View view);

        /**
         * detach view from presenter to prevent mem leak
         */
        void detachView();

        /**
         * launches DetailsActivity for event with given name
         * @param name name of event we want to display in DetailsActivity
         */
        void launchDetailsActivityForEvent(String name);
    }

    interface View {
        /**
         * displays list of followed events
         * @param names list of followed events
         */
        void displayFollowedEvents(List<String> names);

        /**
         * displays error
         */
        void displayError();

        /**
         * launches DetailsActivity for event with given id
         * @param id id of event we want to display in DetailsActivity
         */
        void launchDetailsActivity(String id);
    }

    interface GetEventIdCallback {
        void onSuccess(String id);
        void onFailure(Throwable throwable);
    }

    interface GetEventsNamesListCallback {
        void onSuccess(List<String> names);
        void onFailure(Throwable throwable);
    }
}
