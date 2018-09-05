package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import retrofit2.Retrofit;

class ModelFactory {

    public static Model create() {
        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository eventsRepository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        RetrofitImageRepository imageRepository = new RetrofitImageRepository(retrofit.create(MediaUploadWebApi.class));
        return new Model(eventsRepository, imageRepository);
    }
}
