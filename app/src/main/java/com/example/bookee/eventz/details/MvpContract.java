package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.Event;

interface MvpContract {

    interface Model {
        void fetchEventForId(String id,FetchEventForIdCallback callback);
    }

    interface Presenter {
        void fetchEventForId(String id);
        void attachView(DetailsActivity detailsActivity);
        void detachView();
        void followClicked();

        void displayFollowedEventsDialog();
    }

    interface View {
        void displayError();
        void displayEvent(String title,String name,String date,String description,String logoUrl);
        void displayEventWithoutLogo(String title, String name, String date, String description);
        void setFollowUncheck(Event event);
        void setFollowChecked(Event event);
    }

    interface FetchEventForIdCallback {
       void onSuccess(Event event);
       void onFailure(Throwable t);
    }
}
