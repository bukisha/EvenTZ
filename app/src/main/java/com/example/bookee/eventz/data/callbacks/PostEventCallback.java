package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.Event;

public interface PostEventCallback {
        void onSuccess(Event e);
        void onFailure(Throwable t);
}
