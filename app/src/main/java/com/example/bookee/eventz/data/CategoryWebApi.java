package com.example.bookee.eventz.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryWebApi {


    @GET("categories")
    Call<PaginatedCategoryList> fetchCategories(@Query("token") String token);
}
