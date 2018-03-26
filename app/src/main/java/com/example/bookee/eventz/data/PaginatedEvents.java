
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class PaginatedEvents {

    @SerializedName("events")
    private ArrayList<Event> mEvents;
    @SerializedName("pagination")
    private Pagination mPagination;

    public ArrayList<Event> getEvents() {
        return mEvents;
    }

    public void setEvents(ArrayList<Event> events) {
        mEvents = events;
    }

    public Pagination getPagination() {
        return mPagination;
    }

    public void setPagination(Pagination pagination) {
        mPagination = pagination;
    }

}
