package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import java.util.ArrayList;

public class Model implements MvpContract.Model {

    private RetrofitEventsRepository repository;

    public Model(RetrofitEventsRepository repository) {
        this.repository=repository;
    }

    @Override
    public void fetchEventsForCategory(String categoryId,final MvpContract.FetchEventsForCategoryCallback callback) {
        repository.fetchEventsForCategory(categoryId, new FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                callback.onSuccess(events);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });

    }
}
