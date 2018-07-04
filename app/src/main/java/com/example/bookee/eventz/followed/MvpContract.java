package com.example.bookee.eventz.followed;

import java.util.List;

interface MvpContract {

    interface Model {
        void getFollowedEventNames(GetEventsNamesListCallback callback);
        void closeDatabase();
        void getIdForName(String name, GetEventIdCallback idCallback);
    }

    interface Presenter {
        void getFollowedEventNames();
        void attachView(MvpContract.View view);
        void detachView();
        void launchDetailsActivityForEvent(String name);
    }

    interface View {
        void displayFollowedEvents(List<String> names);
        void displayError();
        void launchDisplayActivity(String id);
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
