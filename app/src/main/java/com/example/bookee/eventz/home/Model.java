package com.example.bookee.eventz.home;

import android.util.Log;

import com.example.bookee.eventz.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.EventsRepository;

import java.util.ArrayList;
import java.util.List;

public class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private EventsRepository repository;

    Model() {
        repository = new EventsRepository();
    }

    public void fetchCategoryNames(final FetchCategoryNamesCallback callback) {
        Log.d(TAG, "fetchCategories: fetching of category names starting");
        repository.fetchCategories(new FetchCategoriesCallback() {
            @Override
            public void onSuccess(List<Category> list) {
                 callback.onSuccess( extractCategoryNames(list));
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    private ArrayList<String> extractCategoryNames(List<Category> list) {
        ArrayList<String> listOfNames = new ArrayList<>();
        for (Category c: list) {
            listOfNames.add( c.getName());
        }
        return listOfNames;
    }


}

