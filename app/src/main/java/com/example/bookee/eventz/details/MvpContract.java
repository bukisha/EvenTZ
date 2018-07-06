package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.pojos.Event;

interface MvpContract {

    interface Model {
        void fetchEventForId(String id,FetchEventForIdCallback callback);
        void removeEventWithId(String id);
        void addFollowedEvent(Event event);
        void checkFollowButton(String eventId,CheckFollowedStatusCallback statusCallback);
        void closeDataSource();
    }

    interface Presenter {
        void fetchEventForId(String id);
        void attachView(DetailsActivity detailsActivity);
        void detachView();
        void followClicked();
        void launchFollowedEventsActivity();
        void removeRowWithId(String id);
        void addFollowedEvent(Event event);
        void checkFollowButton(String id);
        void closeDataSource();
    }

    interface View {
        void displayError();
        void displayEvent(String title,String name,String date,String description,String logoUrl);
        void displayEventWithoutLogo(String title, String name, String date, String description);
        void setFollowUncheck(Event event);
        void setFollowChecked(Event event);
        void setupFollowButton(boolean isFollowed);
        void launchFollowedEventActivity();
    }

    interface FetchEventForIdCallback {
       void onSuccess(Event event);
       void onFailure(Throwable t);
    }

    interface CheckFollowedStatusCallback {
        void onSuccess(Boolean status);
    }
}
