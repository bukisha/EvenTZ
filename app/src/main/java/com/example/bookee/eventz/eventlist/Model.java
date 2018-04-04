package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.EventsRepository;
import java.util.ArrayList;

public class Model implements MvpContract.Model {
    private EventsRepository repository;

    public Model(EventsRepository repository) {
        this.repository=repository;
    }

    @Override
    public void fetchEventsForCategory(String categoryId,final FetchEventsForCategoryCallback callback) {
        repository.fetchEventsForCategory(categoryId, new FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                callback.onSuccess(events);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });

    }
}
