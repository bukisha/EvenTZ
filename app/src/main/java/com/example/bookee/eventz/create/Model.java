package com.example.bookee.eventz.create;

import android.util.Log;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitImageRepository;
import com.example.bookee.eventz.data.callbacks.CreateTicketCallback;
import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.FetchUploadDataCallback;
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


    private CreateTicketCallback prepareCreateEventCallback(final PostEventCallback callback) {
       return new CreateTicketCallback() {
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
    }


    private PublishEventCallback preparePublishEventCallback(final PostEventCallback callback) {
        return new PublishEventCallback() {
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
    public void uploadLogo(final File currentImageFile, final EndUploadImageCallback endUploadImageCallback) {

        imageRepository.fetchUploadData(new FetchUploadDataCallback() {//todo ako ti za upload podataka treba da nesto fecujes, zasto to sve ne sakrijes u imagerepository i da ovaj model bude potpuno nesvestan toga.
            @Override                                                                   //zato sto imam u modelu 2 razlicita repo jedan pravi karte i sam event a drugi se bavi problematikom uploada slike,model je taj koji
            public void onSuccess(FetchUploadDataResponse uploadDataResponse) {            //ih povezuje da zajedno odrade nesto smisleno,jedino je resenje da pravim eventRepository koji ce da se bavi i uploadom slike , a to smo rekli
                imageRepository.uploadImage(currentImageFile,uploadDataResponse,endUploadImageCallback);       //da je onda narusavanje SRP patterna...zar ne? :D
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO
            }
        });

    }
}
