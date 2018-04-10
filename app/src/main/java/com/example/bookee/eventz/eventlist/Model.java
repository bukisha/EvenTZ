package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

import java.util.ArrayList;

class Model implements MvpContract.Model {

    private RetrofitEventsRepository repository;
    private MvpContract.FetchEventsForCategoryCallback presenterCallback;

    public Model(RetrofitEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchEventsForCategory(String categoryId, MvpContract.FetchEventsForCategoryCallback callback) {
        presenterCallback = callback;
        FetchEventsForCategoryCallback repositoryCallback = new FetchEventsForCategoryCallback() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                presenterCallback.onSuccess(events);
            }

            @Override
            public void onFailure() {
                presenterCallback.onFailure();
            }
        };
        repository.fetchEventsForCategory(categoryId, repositoryCallback);
    }
}
