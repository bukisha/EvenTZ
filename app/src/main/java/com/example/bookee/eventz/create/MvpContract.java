package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;

interface MvpContract {
    interface Model {
        void postEvent(EventWrapper postEvent, PostEventCallback callback);
    }

    interface Presenter {
        void postEvent();
        void attachView(View view);
        void detachView();
        void startDateChooser();
        void startTimeChooser();
        void startImageChooser();
        void setTime(int hour, int min);
        void setDate(int year, int month, int day);
        void setName(String name);
        void setDescription(String description);
        void setCategory(String category);
        void setCurrency(String currency);
        //String getCategoryId(String itemAtPosition);
    }

    interface View {
        void showCreatedEvent(Event event);
        void showDateChooser();
        void showTimeChooser();
        void displayNewEvent(Event e);
        void displayError();

        void chooseImage();
    }
    interface PostEventCallback {
        void onSuccess(Event e);
        void onFailure(Throwable t);
    }
}
