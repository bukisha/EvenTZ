package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.pojos.PaginatedCategories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryWebApi {

    @GET("categories")
    Call<PaginatedCategories> fetchCategories(@Query("token") String token);
}
