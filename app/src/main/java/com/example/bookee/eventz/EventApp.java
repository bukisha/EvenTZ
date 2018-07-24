package com.example.bookee.eventz;

import android.app.Application;

import com.example.bookee.eventz.data.pojos.Category;

import java.util.ArrayList;

public class EventApp extends Application {

    private static ArrayList<String> globalCategoryIds;
    private static ArrayList<Category> globalListOfAllCategories;

    public static ArrayList<Category> getGlobalListOfAllCategories() {
        return globalListOfAllCategories;
    }

    public static void setGlobalListOfAllCategories(ArrayList<Category> globalListOfAllCategories) {
        EventApp.globalListOfAllCategories = globalListOfAllCategories;
    }

    public static void setGlobalCategoryIds(ArrayList<String> categoryIds) {
        globalCategoryIds=categoryIds;
    }

    public static ArrayList<String> getGlobalCategoryIds() {
        return globalCategoryIds;
    }

}
