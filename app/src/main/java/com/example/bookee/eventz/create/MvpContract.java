package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.callbacks.CreateTicketCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.callbacks.PublishEventCallback;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;

interface MvpContract {
    interface Model {
        void postEvent(EventWrapper postEvent, PostEventCallback callback);
        void createTickets(String eventId,CreateTicketCallback callback);
        void publishEvent(String eventId, PublishEventCallback callback);
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
        void displayNewEvent(String eventId);
        void displayError();

        void pickImage();
    }
}
