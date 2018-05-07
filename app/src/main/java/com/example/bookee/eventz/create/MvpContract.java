package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.Event;

import java.util.Date;

interface MvpContract {
    interface Model {
        void postEvent(Event event);
    }

    interface Presenter {
        void postEvent(String eventName, String cityName, Date date, String description, String logoUrl);
        void attachView(View view);
        void detachView();
        void startDateChooser();
        void startTimeChooser();
        void startImageChooser();
    }

    interface View {
        void showCreatedEvent(Event event);
        void showDateChooser();
        void showTimeChooser();
    }
}
