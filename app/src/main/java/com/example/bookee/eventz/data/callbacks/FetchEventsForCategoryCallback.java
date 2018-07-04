package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.pojos.Event;

import java.util.ArrayList;

public interface FetchEventsForCategoryCallback {
    void onSuccess(ArrayList<Event> list);
    void onFailure(Throwable t);
}
