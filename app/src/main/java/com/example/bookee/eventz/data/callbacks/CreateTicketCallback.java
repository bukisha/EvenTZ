package com.example.bookee.eventz.data.callbacks;

public interface CreateTicketCallback {//todo zasto ovaj callback nije u create package?
    void onSuccess(String eventId);
    void onFailure(Throwable t);
}
