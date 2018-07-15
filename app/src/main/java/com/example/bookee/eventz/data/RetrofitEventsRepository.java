package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.PaginatedEvents;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitEventsRepository {
    private static final String TAG = "RetrofitEventsRepo";
    private EventsWebApi api;

    public RetrofitEventsRepository(EventsWebApi api) {
        this.api = api;
    }

    public void fetchEventsForCategory(String categoryId, final FetchEventsForCategoryCallback callback) {
        Log.d(TAG, "fetchEventsForCategory: fetching of events for category " + categoryId);
        Call<PaginatedEvents> call = api.fetchEventsForCategory(categoryId, RetrofitFactory.getAuthTokenAnonymous());

        call.enqueue(new Callback<PaginatedEvents>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedEvents> call, @NonNull Response<PaginatedEvents> response) {
                if (response.body() != null) {
                    callback.onSuccess(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedEvents> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void fetchEventForId(String eventId, final FetchEventForIdCallback modelCallback) {
        Call<Event> call = api.fetchEventForId(eventId, RetrofitFactory.getAuthTokenAnonymous());

        Callback<Event> callback = new Callback<Event>() {
            @Override
            public void onResponse(@NonNull Call<Event> call, Response<Event> response) {
                modelCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {
                modelCallback.onFailure(t);
            }
        };

        call.enqueue(callback);
    }

    public void postNewEvent(EventWrapper event, final PostEventCallback postCallback) {
        Call<Event> call = api.createNewEvent( event);

        Callback<Event> callback = new Callback<Event>() {
            @Override
            public void onResponse(@NonNull Call<Event> call, @NonNull Response<Event> response) {
                Event e=response.body();
                postCallback.onSuccess(e);
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                postCallback.onFailure(t);
            }
        };
        call.enqueue(callback);
    }
}
