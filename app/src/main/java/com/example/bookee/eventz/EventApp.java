package com.example.bookee.eventz;

import android.app.Application;

import java.util.ArrayList;

public class EventApp extends Application {
    
    private static ArrayList<String> globalCategoryIds;

    public static void setGlobalCategoryIds(ArrayList<String> categoryIds) {
        globalCategoryIds=categoryIds;
    }

    public static ArrayList<String> getGlobalCategoryIds() {
        return globalCategoryIds;
    }

}
