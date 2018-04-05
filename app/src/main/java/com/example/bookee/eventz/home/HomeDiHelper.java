package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.RetrofitCategoryRepository;

import java.util.HashMap;

public class HomeDiHelper {

    private static Model model;
    private static HashMap<String,String> hash;

    public static void createModel(RetrofitCategoryRepository repository) {
        hash=new HashMap<>(50);
        model=new Model(repository,hash);
    }

    public static Model getModel() {
        return model;
    }
}
