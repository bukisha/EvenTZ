package com.example.bookee.eventz;

import com.example.bookee.eventz.data.EventsRepository;

import retrofit2.Retrofit;

public class ApplicationDiHelper {
        private Retrofit retrofit;
        private EventsRepository eventsRepository;

    public ApplicationDiHelper(Retrofit retrofit, EventsRepository eventsRepository) {
        this.retrofit = retrofit;
        this.eventsRepository = eventsRepository;
    }

    public  Retrofit getRetrofit() {
        return retrofit;
    }

    public final EventsRepository getEventsRepository() {
        return eventsRepository;
    }
}
