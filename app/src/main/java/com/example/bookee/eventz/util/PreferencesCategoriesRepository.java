package com.example.bookee.eventz.util;

import android.content.SharedPreferences;
import com.example.bookee.eventz.data.pojos.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PreferencesCategoriesRepository implements GlobalDataOperator {
    public static final String GLOBAL_CATEGORY_LIST = "globalCategoryList";
    public static final String CATEGORIES_SHORT_NAMES ="shortNamesOfCategories" ;

    private SharedPreferences sharedPreferences;

    public PreferencesCategoriesRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    //stores list of all categories into shared prefs for latter use
    @Override
    public void storeGlobalCategoryList(ArrayList<Category> categories) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String listOfCategories = gson.toJson(categories);
        editor.putString(GLOBAL_CATEGORY_LIST, listOfCategories);
        editor.apply();
    }

    //retrieve list of all categories from shared prefs
    @Override
    public  ArrayList<Category> getGlobalCategoryList() {
        Gson gson = new Gson();
        String categories = sharedPreferences.getString(GLOBAL_CATEGORY_LIST, null);
        Type type = new TypeToken<ArrayList<Category>>() {}.getType();

        return gson.fromJson(categories, type);

    }
    //stores list of short names of categories into shared prefs
    @Override
    public  void storeCategoriesShortIds(ArrayList<String> categoryIdShortNames) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String listOfCategories = gson.toJson(categoryIdShortNames);
        editor.putString(CATEGORIES_SHORT_NAMES, listOfCategories);
        editor.apply();
    }
    //retrieves list of short category names from shared prefs
    @Override
    public  ArrayList<String> getCategoriesShortNames() {
        Gson gson = new Gson();
        String categories = sharedPreferences.getString(CATEGORIES_SHORT_NAMES, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        return gson.fromJson(categories, type);
    }
}
