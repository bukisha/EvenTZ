package com.example.bookee.eventz.create.pojos;

import com.google.gson.annotations.SerializedName;

public class TicketWrapper {

    @SerializedName("ticket_class")
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
