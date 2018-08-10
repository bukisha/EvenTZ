package com.example.bookee.eventz.data;

import com.example.bookee.eventz.create.pojos.PublishResponse;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.PaginatedEvents;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsWebApi {

    @GET("events/search")
    Call<PaginatedEvents> fetchEventsForCategory(@Query("categories") String category, @Query("token") String token);

    @GET("events/{eventId}")
    Call<Event> fetchEventForId(@Path("eventId") String eventId, @Query("token") String token);

    @GET("events/search")
    Call<PaginatedEvents> fetchEventsForQuery(@Query("token") String token,@Query("q") String query);

    @POST("events/")
    Call<Event> createNewEvent(@Body EventWrapper postEvent,@Query("token") String authToken);

    @POST("events/{eventId}/publish/")
    Call<PublishResponse> publishEvent(@Path("eventId") String eventId,@Query("token") String authToken);
}
