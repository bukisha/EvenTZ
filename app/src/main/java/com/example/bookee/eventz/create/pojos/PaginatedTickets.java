package com.example.bookee.eventz.create.pojos;

import java.util.List;

import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
public class PaginatedTickets {

    @SerializedName("pagination")
    private Pagination mPagination;
    @SerializedName("ticket_classes")
    private List<Ticket> mTickets;

    public Pagination getPagination() {
        return mPagination;
    }

    public void setPagination(Pagination pagination) {
        mPagination = pagination;
    }

    public List<Ticket> getTicketClasses() {
        return mTickets;
    }

    public void setTicketClasses(List<Ticket> tickets) {
        mTickets = tickets;
    }

}
