package com.example.bookee.eventz.create;

import com.example.bookee.eventz.create.pojos.PaginatedTickets;
import com.example.bookee.eventz.create.pojos.TicketWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface TicketWebApi {

    @POST("events/{eventId}/ticket_classes")
    Call<PaginatedTickets> createTicketForEvent(@Path("eventId") String eventId, @Body TicketWrapper ticket);
}
