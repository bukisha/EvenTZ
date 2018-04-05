package com.example.bookee.eventz.home;

import android.util.Log;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import java.util.ArrayList;
import java.util.HashMap;

class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private RetrofitCategoryRepository repository;
    private HashMap<String,String> nameToCategoryHash;

    public Model(RetrofitCategoryRepository repository, HashMap<String,String> hashMap) {
        this.repository=repository;
        this.nameToCategoryHash=hashMap;
    }

    public void fetchCategoryNames(final MvpContract.FetchCategoriesCallback callback) {
        Log.d(TAG, "fetchCategories: fetching of category names starting");
        repository.fetchCategories(new FetchCategoriesCallback() {
            @Override
            public void onSuccess(ArrayList<Category> list) {
                populateHash(list);
                callback.onSuccess( list );
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
    public String getClickedCategoryId(String name) {
        Log.d(TAG, "getClickedCategoryId: GETTING ID FROM HASH for name "+name);

            String ret=nameToCategoryHash.get(name);

        Log.d(TAG, "getClickedCategoryId: ID FOR NAME " + name + " IS " + ret);
            return nameToCategoryHash.get(name);
    }

    public void populateHash(ArrayList<Category> list) {
           for(Category c:list) {
               Log.i(TAG, "populateHash:  name + "+c.getName()+" id "+c.getId());
               nameToCategoryHash.put(c.getName(),c.getId());
           }
    }
}

