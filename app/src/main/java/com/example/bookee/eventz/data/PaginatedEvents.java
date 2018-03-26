
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class PaginatedEvents {

    @SerializedName("events")
    private List<Event> mEvents;
    @SerializedName("pagination")
    private Pagination mPagination;

    public List<Event> getEvents() {
        return mEvents;
    }

    public void setEvents(List<Event> events) {
        mEvents = events;
    }

    public Pagination getPagination() {
        return mPagination;
    }

    public void setPagination(Pagination pagination) {
        mPagination = pagination;
    }

}
