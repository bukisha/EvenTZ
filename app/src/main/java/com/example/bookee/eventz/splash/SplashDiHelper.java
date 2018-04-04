package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.EventsRepository;

public class SplashDiHelper {

    private static Model model;

    public static void createModel(EventsRepository repository) {
        model=new Model(repository);
    }

    public static Model getModel() {
        return model;
    }
}
