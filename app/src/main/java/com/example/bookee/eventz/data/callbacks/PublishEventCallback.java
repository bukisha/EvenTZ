package com.example.bookee.eventz.data.callbacks;

public interface PublishEventCallback {
    void onSuccess(String eventId);
    void onFailure(Throwable t);
}
