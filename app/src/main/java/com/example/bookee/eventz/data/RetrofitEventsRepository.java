package com.example.bookee.eventz.data;

import android.util.Log;

import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitEventsRepository {
    private static final String TAG = "RetrofitEventsRepository";

    private EventsWebApi api;

    public RetrofitEventsRepository(EventsWebApi api) {
       this.api= api;
    }

    public void fetchEventsForCategory(String categoryId,final FetchEventsForCategoryCallback callback) {
        Log.d(TAG, "fetchEventsForCategory: fetching of events for category "+categoryId);
        Call<PaginatedEvents> call=api.fetchEventsForCategory(categoryId,RetrofitFactory.getAuthToken());

        call.enqueue(new Callback<PaginatedEvents>() {
            @Override
            public void onResponse(Call<PaginatedEvents> call, Response<PaginatedEvents> response) {
                        callback.onSuccess(response.body().getEvents());
            }

            @Override
            public void onFailure(Call<PaginatedEvents> call, Throwable t) {
                     callback.onFailure();
            }
        });

    }
}
