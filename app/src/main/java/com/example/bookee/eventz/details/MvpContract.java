package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.Event;

interface MvpContract {

    interface Model {
        void fetchEventForId(String id,FetchEventForIdCallback callback);
    }

    interface Presenter {
        void fetchEventForId(String id);
    }

    interface View {
        void displayError();
        void displayEvent(Event event);
    }

    interface FetchEventForIdCallback {
       void onSuccess(Event event);
       void onFailure();
    }
}
