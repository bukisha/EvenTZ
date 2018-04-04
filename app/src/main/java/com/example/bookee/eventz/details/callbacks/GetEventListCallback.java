package com.example.bookee.eventz.details.callbacks;

import com.example.bookee.eventz.data.Event;
import java.util.ArrayList;

public interface GetEventListCallback {
    void onSuccess(ArrayList<Event> events);
    void onFailure();
}
