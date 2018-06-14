package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.EventWrapper;
import com.example.bookee.eventz.data.ResponseWrapper;

interface MvpContract {
    interface Model {
        void postEvent(EventWrapper postEvent, PostEventCallback callback);
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
        void showCreatedEvent(ResponseWrapper event);
        void showDateChooser();
        void showTimeChooser();
        void displayNewEvent(ResponseWrapper e);
    }
    interface PostEventCallback {
        void onSuccess(ResponseWrapper e);
        void onFailure(Throwable t);
    }
}
