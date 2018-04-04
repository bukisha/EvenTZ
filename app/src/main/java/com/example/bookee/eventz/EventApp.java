package com.example.bookee.eventz;

import android.app.Application;

import com.example.bookee.eventz.data.EventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;

import retrofit2.Retrofit;

public class EventApp extends Application {

    private static ApplicationDiHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit= RetrofitFactory.buildRetrofit();
        EventsRepository repository=new EventsRepository(retrofit);
        helper = new ApplicationDiHelper(retrofit,repository);
    }

    public static ApplicationDiHelper getHelper() {
        return helper;
    }
}
