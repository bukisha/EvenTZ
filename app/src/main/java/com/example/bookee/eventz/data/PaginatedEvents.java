
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class PaginatedEvents {

    @SerializedName("events")
    private ArrayList<Event> events;
    @SerializedName("pagination")
    private Pagination pagination;

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "PaginatedEvents{" +
                "events=" + events.toString() +
                '}';
    }
}
