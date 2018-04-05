package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.RetrofitEventsRepository;

public class EventListDiHelper {

    private static Model model;

    public static void createModel(RetrofitEventsRepository repository) {
        model=new Model(repository);
    }

    public static Model getModel() {
        return model;
    }
}
