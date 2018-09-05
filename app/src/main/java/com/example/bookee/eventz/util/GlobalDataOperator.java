package com.example.bookee.eventz.util;

import android.content.SharedPreferences;
import com.example.bookee.eventz.data.pojos.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GlobalDataOperator {

    private static final String GLOBAL_CATEGORY_LIST = "globalCategoryList";
    private static final String CATEGORIES_SHORT_NAMES ="shortNamesOfCategories" ;

    //stores list of all categories into shared prefs for latter use
    public static void storeGlobalCategoryList(ArrayList<Category> categories, SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String listOfCategories = gson.toJson(categories);
        editor.putString(GLOBAL_CATEGORY_LIST, listOfCategories);
        editor.apply();
    }

    //retrieve list of all categories from shared prefs
    public static ArrayList<Category> getGlobalCategoryList(SharedPreferences preferences) {
        Gson gson = new Gson();
        String categories = preferences.getString(GLOBAL_CATEGORY_LIST, null);
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();

        return gson.fromJson(categories, type);

    }
    //stores list of short names of categories into shared prefs
    public static void storeCategoriesShortIds(ArrayList<String> categoryIdShortNames,SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String listOfCategories = gson.toJson(categoryIdShortNames);
        editor.putString(CATEGORIES_SHORT_NAMES, listOfCategories);
        editor.apply();
    }
    //retrieves list of short category names from shared prefs
    public static ArrayList<String> getCategoriesShortNames(SharedPreferences sharedPreferences) {
        Gson gson = new Gson();
        String categories = sharedPreferences.getString(CATEGORIES_SHORT_NAMES, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        return gson.fromJson(categories, type);
    }
}
