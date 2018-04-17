package com.example.bookee.eventz;

import android.app.Application;

import com.example.bookee.eventz.data.CategoryWebApi;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;

import retrofit2.Retrofit;

public class EventApp extends Application {

    private static RetrofitEventsRepository retrofitEventsRepository;
    private static RetrofitCategoryRepository retrofitCategoryRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit= RetrofitFactory.buildRetrofit();
        retrofitEventsRepository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        retrofitCategoryRepository = new RetrofitCategoryRepository(retrofit.create(CategoryWebApi.class));

        }

    public static RetrofitCategoryRepository getRetrofitCategoryRepository() {
        return retrofitCategoryRepository;
    }

    public static RetrofitEventsRepository getRetrofitEventsRepository() {
        return retrofitEventsRepository;
    }
}
