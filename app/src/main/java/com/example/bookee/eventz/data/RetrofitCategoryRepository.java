package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCategoryRepository {
    private static final String TAG = "RetrofitCategoryRepo";
    private CategoryWebApi api;

    public RetrofitCategoryRepository(CategoryWebApi api) {
        this.api=api;
        }

    public void fetchCategories(final FetchCategoriesCallback callback) {
        Log.d(TAG, "fetchCategories: fetch starting");

       Call<PaginatedCategoryList> call= api.fetchCategories(RetrofitFactory.getAuthToken());

       call.enqueue(new Callback<PaginatedCategoryList>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedCategoryList> call, @NonNull Response<PaginatedCategoryList> response) {
                //noinspection ConstantConditions
                callback.onSuccess(response.body().getCategories());
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedCategoryList> call, @NonNull Throwable t) {
                callback.onFailure();
            }
        });
    }

    }

