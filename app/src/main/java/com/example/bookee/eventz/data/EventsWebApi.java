package com.example.bookee.eventz.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsWebApi {

    @GET("events/search")
    Call<PaginatedEvents> fetchEventsForCategory(@Query ("categories") String category,@Query("token") String token);
    @GET("events/{eventId}")
    Call<Event> fetchEventForId(@Path("eventId")String eventId ,@Query("token") String token);
    @POST("events")
    Call<Event> createNewEvent(@Body Event event,@Query("token") String token);
}
