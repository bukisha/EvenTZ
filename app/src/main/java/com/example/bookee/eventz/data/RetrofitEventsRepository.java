package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.bookee.eventz.create.pojos.PublishResponse;
import com.example.bookee.eventz.data.callbacks.CreateTicketCallback;
import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.callbacks.PublishEventCallback;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.PaginatedEvents;
import com.example.bookee.eventz.data.callbacks.FetchEventsForQueryCallback;
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

    public void fetchEventsForQuery(String query, final FetchEventsForQueryCallback fetchForQueryCallback) {
        Call<PaginatedEvents> call = api.fetchEventsForQuery(RetrofitFactory.getAuthTokenAnonymous(), query);

        Callback<PaginatedEvents> callback = new Callback<PaginatedEvents>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedEvents> call, @NonNull Response<PaginatedEvents> response) {
                if (response.body() != null) {
                    fetchForQueryCallback.onSuccess(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedEvents> call, @NonNull Throwable t) {
                fetchForQueryCallback.onFailure(t);
            }
        };
        call.enqueue(callback);
    }

    public void postNewEvent(EventWrapper event, final PostEventCallback presenterCallback) {
        Call<Event> call = api.createNewEvent(event, RetrofitFactory.getAuthTokenPersonal());

        Callback<Event> callback = new Callback<Event>() {
            @Override
            public void onResponse(@NonNull Call<Event> call, @NonNull Response<Event> response) {
                Event e = response.body();
                if (e != null) {
                    createTicketsForEvent(e.getId(),
                            prepareCreateTicketCallback(preparePublishEventCallback(presenterCallback)));
                  //  presenterCallback.onSuccess(e.getId());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Event> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                presenterCallback.onFailure(t);
            }
        };
        call.enqueue(callback);
    }

    private void publishEvent(final String eventId, final PublishEventCallback callback) {
        Log.d(TAG, "publishEvent: repository is publishing event with id " + eventId);
        Call<PublishResponse> call = api.publishEvent(eventId, RetrofitFactory.getAuthTokenPersonal());

        call.enqueue(new Callback<PublishResponse>() {
            @Override
            public void onResponse(@NonNull Call<PublishResponse> call, @NonNull Response<PublishResponse> response) {
                //noinspection ConstantConditions
                Log.d(TAG, "onResponse: repository published event with back code " + response.body().getPublished());
                callback.onSuccess(eventId);
            }

            @Override
            public void onFailure(@NonNull Call<PublishResponse> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private void createTicketsForEvent(String eventId, final CreateTicketCallback callback) {
        TicketCreator.getInstance().createTicketForEvent(eventId, callback);
    }

    private CreateTicketCallback prepareCreateTicketCallback(final PublishEventCallback callback) {
        return new CreateTicketCallback() {
            @Override
            public void onSuccess(String eventId) {
                Log.d(TAG, "onSuccess: before publishing event with id " + eventId);
                publishEvent(eventId, callback);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };
    }

    private PublishEventCallback preparePublishEventCallback(final PostEventCallback callback) {
        return new PublishEventCallback() {
            @Override
            public void onSuccess(String eventId) {
                Log.d(TAG, "onSuccess: published event with id " + eventId);
                callback.onSuccess(eventId);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        };
    }
}