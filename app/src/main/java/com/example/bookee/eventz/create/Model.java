package com.example.bookee.eventz.create;

import android.util.Log;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;

class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private RetrofitEventsRepository eventsRepository;

    Model(RetrofitEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public void postEvent(Event postEvent, final MvpContract.PostEventCallback callback) {
        Log.d(TAG, "postEvent: "+postEvent.toString());
        PostEventCallback postEventCallback=new PostEventCallback() {
            @Override
            public void onSuccess(Event e) {
                callback.onSuccess(e);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };

        eventsRepository.postNewEvent(postEvent,postEventCallback);
        }
}
