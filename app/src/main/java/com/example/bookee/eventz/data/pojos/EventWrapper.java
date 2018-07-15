package com.example.bookee.eventz.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventWrapper implements Serializable {

    @SerializedName("event")
    @Expose
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventWrapper{" +
                "event=" + event +
                '}';
    }
}
