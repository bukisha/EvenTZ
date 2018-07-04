package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.pojos.Event;

public interface PostEventCallback {
        void onSuccess(Event e);
        void onFailure(Throwable t);
}
