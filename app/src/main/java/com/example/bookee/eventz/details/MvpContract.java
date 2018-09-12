package com.example.bookee.eventz.details;

import android.content.Context;

import com.example.bookee.eventz.data.pojos.Event;

interface MvpContract {

    interface Model {
        /**
         * fetches event for a given id from api
         * @param id String representation of integer value of event id
         * @param callback callback to model so that it cna handle response
         */
        void fetchEventForId(String id,FetchEventForIdCallback callback);

        /**
         * remove event with given id from list of followed events
         * @param id id of event to be removed(un-followed)
         */
        void removeEventWithId(String id);

        /**
         * add event to list of followed events
         * @param event event we want to start following
         */
        void addFollowedEvent(Event event);

        /**
         *checks data source for event with given id so that we cna determine if event is followed
         * or not and set follwo button accordingly
         * @param eventId id of event we are checking
         * @param statusCallback callback to model so that it can react
         */
        void checkFollowButton(String eventId,CheckFollowedStatusCallback statusCallback);

        /**
         * closes data Source
         */
        void closeDataSource();
    }

    interface Presenter {
        /**
         * passes id of event we want to display to model
         * @param id string representation of integer id of event we want to display
         */
        void fetchEventForId(String id);

        /**
         *  attaches parent view to presenter
         * @param view parent view
         */
        void attachView(View view);

        /**
         * detaches parent view from presenter
         */
        void detachView();

        /**
         * signals to model that follow button is clicked
         */
        void followClicked();

        /**
         * launches followEventActivity
         */
        void launchFollowedEventsActivity();

        /**
         * start following of event
         * @param event event we want to start following
         * @param context context of parent view so we can set alarm,and pending intents for broadcast
         */
        void startFollowingEvent(Event event,Context context);

        /**
         * cancellation of event following
         * @param event event we do not want to follow any more
         * @param context context of parent view so we can cancel alarms
         */
        void stopFollowingEvent(Event event,Context context);

        /**
         * checks status of follow button for event with given id
         * @param id id of event which follow button status we are checking
         */
        void checkFollowButton(String id);

        /**
         * signal to model it should close data source to prevent mem leak
         */
        void closeDataSource();
    }

    interface View {
        /**
         * displays various errors
         */
        void displayError();

        /**
         * display event parameters
         * @param title title of event
         * @param name name of event
         * @param date date of event
         * @param description description of event
         * @param logoUrl logo of event
         */
        void displayEvent(String title,String name,String date,String description,String logoUrl);

        /**
         * displays event but without logo adds custom logo image
         * @param title title of event
         * @param name name of event
         * @param date date of event
         * @param description description of event
         */
        void displayEventWithoutLogo(String title, String name, String date, String description);

        /**
         * sets follow button to unchecked state
         * @param event event whose follow button we are setting to unchecked state
         */
        void setFollowUncheck(Event event);

        /**
         * sets follow button to checked state
         * @param event event whose follow button we are setting to check state
         */
        void setFollowChecked(Event event);

        /**
         * sets up follow button of displayed event
         * @param isFollowed flag that indicates is displayed event followed or not
         */
        void setupFollowButton(boolean isFollowed);

        /**
         * launches followedEventsActivity
         */
        void launchFollowedEventActivity();

        /**
         * util method to display Toast in view
         * @param toastStartedFollowing toast message
         */
        void showToast(String toastStartedFollowing);
    }

    interface FetchEventForIdCallback {
       void onSuccess(Event event);
       void onFailure(Throwable t);
    }

    interface CheckFollowedStatusCallback {
        void onSuccess(Boolean status);//todo sta se zbilo sa onFailure()?
    }
}
