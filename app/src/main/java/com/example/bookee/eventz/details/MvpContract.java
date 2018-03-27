package com.example.bookee.eventz.details;

import com.example.bookee.eventz.callbacks.GetEventForIndexCallback;
import com.example.bookee.eventz.callbacks.GetEventListCallback;
import com.example.bookee.eventz.data.Event;
import java.util.ArrayList;

public interface MvpContract {

    interface Model {
        void getAllEvents(GetEventListCallback callback);
        void getEventForIndex(int index,GetEventForIndexCallback callback);
        void updateWithNewEvents(ArrayList<Event> newEvents);
    }

    interface Presenter {
        void getAllEvents();
        void getEventForIndex(int index);
        void updateModelWithNewEvents(ArrayList<Event> newEventsList);
    }
    
    interface View {}
}
