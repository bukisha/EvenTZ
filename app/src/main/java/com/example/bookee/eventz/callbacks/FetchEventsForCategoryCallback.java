package com.example.bookee.eventz.callbacks;

import com.example.bookee.eventz.data.Event;
import java.util.ArrayList;

public interface FetchEventsForCategoryCallback {
    void onSuccess(ArrayList<Event> events);
    void onFailure();
}
