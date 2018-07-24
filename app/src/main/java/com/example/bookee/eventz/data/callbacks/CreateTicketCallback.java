package com.example.bookee.eventz.data.callbacks;

public interface CreateTicketCallback {
    void onSuccess(String eventId);
    void onFailure(Throwable t);
}
