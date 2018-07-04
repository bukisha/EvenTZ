package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.pojos.Event;

interface MvpContract {
    interface Model {
        void postEvent(Event postEvent, PostEventCallback callback);
    }

    interface Presenter {
        void postEvent(String eventName, String cityName,String description, String logoUrl);
        void attachView(View view);
        void detachView();
        void startDateChooser();
        void startTimeChooser();
        void startImageChooser();
        void setTime(int hour, int min);
        void setDate(int year, int month, int day);

    }

    interface View {
        void showCreatedEvent(Event event);
        void showDateChooser();
        void showTimeChooser();
        void displayNewEvent(Event e);
    }
    interface PostEventCallback {
        void onSuccess(Event e);
        void onFailure(Throwable t);
    }
}
