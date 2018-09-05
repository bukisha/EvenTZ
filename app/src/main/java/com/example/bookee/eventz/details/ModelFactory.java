package com.example.bookee.eventz.details;

import android.content.Context;
import com.example.bookee.eventz.data.EventsDatabaseHelper;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.SQLiteDatabaseRepository;
import retrofit2.Retrofit;

class ModelFactory {

    public static Model create(Context context) {
        Retrofit retrofit = RetrofitFactory.buildRetrofit();
        RetrofitEventsRepository eventsRepository = new RetrofitEventsRepository(retrofit.create(EventsWebApi.class));
        SQLiteDatabaseRepository databaseRepository=new SQLiteDatabaseRepository(EventsDatabaseHelper.getInstance(context));
        return  new Model(eventsRepository,databaseRepository);
    }
}
