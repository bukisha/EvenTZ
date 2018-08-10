package com.example.bookee.eventz.create;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.callbacks.CreateTicketCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.callbacks.PublishEventCallback;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.Logo;

import java.io.File;

interface MvpContract {
    interface Model {
        void postEvent(EventWrapper postEvent, PostEventCallback callback);
        void createTickets(String eventId,CreateTicketCallback callback);
        void publishEvent(String eventId, PublishEventCallback callback);
        void uploadLogo(File currentImageFile,EndUploadImageCallback callback);
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

        void setLogo(File imageFile);

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
    interface EndUploadImageCallback {
        void onSuccess(Logo logo);
        void onFailure(Throwable t);
    }

    interface FetchUploadDataCallback {
        void onSuccess(FetchUploadDataResponse uploadData);
        void onFailure(Throwable t);
    }
}
