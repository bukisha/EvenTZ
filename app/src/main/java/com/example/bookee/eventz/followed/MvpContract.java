package com.example.bookee.eventz.followed;

import java.util.List;

interface MvpContract {

    interface Model {
        void getFollowedEventNames(GetDatabaseEventsCallback callback);
    }

    interface Presenter {
        void getFollowedEventNames();
        void attachView(MvpContract.View view);
        void detachView();
    }

    interface View {
        void displayFollowedEvents(List<String> names);
        void displayError();
    }

    interface GetDatabaseEventsCallback {
        void onSuccess(List<String> names);
        void onFailure(Throwable throwable);
    }
}
