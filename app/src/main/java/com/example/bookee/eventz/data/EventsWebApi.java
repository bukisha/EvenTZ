package com.example.bookee.eventz.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventsWebApi {

    @GET("events/search")
    Call<PaginatedEvents> fetchEventsForCategory(@Query ("categories") String category,@Query("token") String token);
}
