package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

class Model implements MvpContract.Model {
    private RetrofitEventsRepository repository;

    Model(RetrofitEventsRepository repository) {
        this.repository=repository;

    }
    @Override
    public void postEvent(Event event) {
        repository.postNewEvent(event);

    }
}
