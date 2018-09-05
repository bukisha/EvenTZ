package com.example.bookee.eventz;

import android.app.Application;

import com.example.bookee.eventz.data.pojos.Category;

import java.util.ArrayList;

public class EventApp extends Application {

    private static ArrayList<String> globalCategoryIds;//todo da li ovo treba da bude ovde? Koliko vidim ovo fetchujes na splash. NIKAKO nemoj da smestas ovo u static varijablu. Napravi neki CategoriesRespository i onda kroz to pristupaj kategorijama. Mozda ces morati da te IDs smestis u shared prefs, mozda u SQLIte.... ali mu pristupas kroz Repo...
    private static ArrayList<Category> globalListOfAllCategories;//todo isto kao i gore

    public static ArrayList<Category> getGlobalListOfAllCategories() {//todo unused
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
