package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import com.example.bookee.eventz.create.pojos.PaginatedTickets;
import com.example.bookee.eventz.create.pojos.Ticket;
import com.example.bookee.eventz.create.pojos.TicketWrapper;
import com.example.bookee.eventz.data.callbacks.CreateTicketCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 class TicketCreator  {
    private static final String FREE_TICKET_NAME ="Free ticket" ;
    private static final int NUMBER_OF_TICKETS = 100 ;
    private static TicketCreator instance;
    private TicketWebApi api;

    private TicketCreator() {
        api= RetrofitFactory.buildRetrofit().create(TicketWebApi.class);
    }

    public static TicketCreator getInstance() {
        if(instance == null) {
            instance=new TicketCreator();
        }
        return  instance;
    }

    private TicketWrapper createFreeTicket() {
        Ticket freeTicket=new Ticket();
        freeTicket.setFree(true);
        freeTicket.setName(FREE_TICKET_NAME);
        freeTicket.setQuantityTotal((long) NUMBER_OF_TICKETS);
        TicketWrapper freeTicketWrapper=new TicketWrapper();
        freeTicketWrapper.setTicket(freeTicket);
        return  freeTicketWrapper;
    }

    public void createTicketForEvent(final String eventId, final CreateTicketCallback callback) {
        Call<PaginatedTickets> call=api.createTicketForEvent(eventId,createFreeTicket(),RetrofitFactory.getAuthTokenPersonal());


        call.enqueue(new Callback<PaginatedTickets>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedTickets> call, @NonNull Response<PaginatedTickets> response) {
                callback.onSuccess(eventId);
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedTickets> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
