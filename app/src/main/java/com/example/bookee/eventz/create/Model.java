package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;

class Model implements MvpContract.Model {
    private RetrofitEventsRepository eventsRepository;

    Model(RetrofitEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public void postEvent(Event event) {
        eventsRepository.postNewEvent(event);

    }
}
