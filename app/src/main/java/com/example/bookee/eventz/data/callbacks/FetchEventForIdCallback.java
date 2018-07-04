package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.pojos.Event;

public interface FetchEventForIdCallback {
    void onSuccess(Event event);
    void onFailure(Throwable t);
}
