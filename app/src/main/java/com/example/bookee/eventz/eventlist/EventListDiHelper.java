package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.EventsRepository;

public class EventListDiHelper {

    private static Model model;

    public static void createModel(EventsRepository repository) {
        model=new Model(repository);
    }

    public static Model getModel() {
        return model;
    }
}
