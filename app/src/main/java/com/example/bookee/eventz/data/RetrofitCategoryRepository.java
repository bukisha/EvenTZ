package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.data.pojos.PaginatedCategories;
import com.example.bookee.eventz.splash.CategoryWebApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCategoryRepository {
    private static final String TAG = "RetrofitCategoryRepo";
    private CategoryWebApi api;
    private FetchCategoriesCallback fetchCategoriesToModelCallback;

    public RetrofitCategoryRepository(CategoryWebApi api) {
        this.api = api;
    }

    public void fetchCategories(FetchCategoriesCallback callback) {
        Log.d(TAG, "fetchCategories: fetch starting");
        fetchCategoriesToModelCallback = callback;

        Call<PaginatedCategories> call = api.fetchCategories(RetrofitFactory.getAuthTokenAnonymous());
        Callback<PaginatedCategories> enqueueCallback = new Callback<PaginatedCategories>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedCategories> call, Response<PaginatedCategories> response) {
                //noinspection ConstantConditions
                fetchCategoriesToModelCallback.onSuccess(response.body().getCategories());
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedCategories> call, @NonNull Throwable t) {
                fetchCategoriesToModelCallback.onFailure(t);
            }
        };
        call.enqueue(enqueueCallback);
    }

}

