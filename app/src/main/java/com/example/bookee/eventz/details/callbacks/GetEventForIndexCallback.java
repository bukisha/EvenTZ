package com.example.bookee.eventz.details.callbacks;

import com.example.bookee.eventz.data.Event;

public interface GetEventForIndexCallback {
     void onSuccess(Event event);
     void onFailure();
}
