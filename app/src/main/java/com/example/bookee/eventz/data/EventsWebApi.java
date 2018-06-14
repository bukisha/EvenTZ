package com.example.bookee.eventz.data;

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

    @POST("events")
    Call<ResponseWrapper> createNewEvent(@Query("token") String token, @Body EventWrapper postEvent);

//    @POST("events")
//    @FormUrlEncoded
//    Call<Event> createNewEvent(@Query("token") String token, @Field("name.html") String name
//                                                                , @Field("start.timezone") String startTimezone
//                                                                ,@Field("start.utc") String startUtc
//                                                                , @Field("end.timezone") String endTimezone
//                                                                ,@Field("end.utc") String endUtc
//                                                                , @Field("currency") String currency);
}
