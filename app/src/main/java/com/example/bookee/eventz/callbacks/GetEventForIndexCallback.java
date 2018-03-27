package com.example.bookee.eventz.callbacks;

import com.example.bookee.eventz.data.Event;

public interface GetEventForIndexCallback {
     void onSuccess(Event event);
     void onFailure(Throwable t);
}
