package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.bookee.eventz.home.MvpContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventsRepository {
    private static final String TAG = "EventsRepository";

    private EventsWebApi api;

    public EventsRepository() {
        Retrofit client = RetrofitFactory.buildRetrofit();
        api= client.create(EventsWebApi.class);
    }

    public void fetchCategories(final MvpContract.fetchCategoriesCallback callback) {
        Log.d(TAG, "fetchCategories: fetch starting");
        Call<PaginatedCategoryList> call=  api.fetchCategories(RetrofitFactory.getAuthToken());
        call.enqueue(new Callback<PaginatedCategoryList>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedCategoryList> call, @NonNull Response<PaginatedCategoryList> response) {
                //noinspection ConstantConditions
                callback.onSuccess(response.body().getCategories());
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedCategoryList> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
