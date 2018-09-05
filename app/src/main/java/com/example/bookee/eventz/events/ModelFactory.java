package com.example.bookee.eventz.events;

import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import retrofit2.Retrofit;

class ModelFactory {

    public static MvpContract.Model create() {
        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository repository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        return new Model(repository);
    }
}
