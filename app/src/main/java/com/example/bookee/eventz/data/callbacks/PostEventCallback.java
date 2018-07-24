package com.example.bookee.eventz.data.callbacks;

public interface PostEventCallback {
        void onSuccess(String eventId);
        void onFailure(Throwable t);
}
