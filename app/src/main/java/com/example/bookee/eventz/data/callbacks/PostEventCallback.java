package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.pojos.Event;

public interface PostEventCallback {
        void onSuccess(String eventId);
        void onFailure(Throwable t);
}
