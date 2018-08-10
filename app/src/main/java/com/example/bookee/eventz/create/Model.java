package com.example.bookee.eventz.create;

import android.util.Log;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.CreateTicketCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.callbacks.PublishEventCallback;
import com.example.bookee.eventz.data.pojos.EventWrapper;

import java.io.File;

class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private RetrofitEventsRepository eventsRepository;
    private RetrofitImageRepository imageRepository;

    Model(RetrofitEventsRepository eventsRepository,RetrofitImageRepository imageRepository) {
        this.eventsRepository = eventsRepository;
        this.imageRepository=imageRepository;
    }

    @Override
    public void postEvent(EventWrapper postEvent, final PostEventCallback callback) {
        Log.d(TAG, "postEvent: " + postEvent.toString());

        PostEventCallback postEventCallback = new PostEventCallback() {
            @Override
            public void onSuccess(final String eventId) {
                createTickets(eventId,prepareCreateEventCallback(callback));
                //callback.onSuccess(e);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };
        eventsRepository.postNewEvent(postEvent, postEventCallback);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private CreateTicketCallback prepareCreateEventCallback(final PostEventCallback callback) {
        final CreateTicketCallback createTicketsCallback = new CreateTicketCallback() {
            @Override
            public void onSuccess(String eventId) {
                Log.d(TAG, "onSuccess: before publishing event with id "+eventId);
                publishEvent(eventId,preparePublishEventCallback(callback));
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };
        return createTicketsCallback;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private PublishEventCallback preparePublishEventCallback(final PostEventCallback callback) {
        final PublishEventCallback publishEventCallback = new PublishEventCallback() {
            @Override
            public void onSuccess(String eventId) {
                Log.d(TAG, "onSuccess: published event with id " + eventId);
               callback.onSuccess(eventId);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };
        return publishEventCallback;
    }

    @Override
    public void createTickets(String eventId,final CreateTicketCallback callback) {
        Log.d(TAG, "createTickets: create Model ");
        eventsRepository.createTicketsForEvent(eventId,callback);
    }

    @Override
    public void publishEvent(String eventId,final PublishEventCallback callback) {
        eventsRepository.publishEvent(eventId,callback);
    }

    @Override
    public void uploadLogo(final File currentImageFile, final MvpContract.EndUploadImageCallback endUploadImageCallback) {

        imageRepository.fetchUploadData(new MvpContract.FetchUploadDataCallback() {
            @Override
            public void onSuccess(FetchUploadDataResponse uploadDataResponse) {
                imageRepository.uploadImage(currentImageFile,uploadDataResponse,endUploadImageCallback);
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO
            }
        });

    }
}
