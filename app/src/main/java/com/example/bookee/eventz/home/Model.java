package com.example.bookee.eventz.home;

import android.util.Log;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private RetrofitCategoryRepository repository;
    private Map<String,String> nameToCategoryHash;

    Model(RetrofitCategoryRepository repository) {
        this.repository=repository;
        this.nameToCategoryHash=new HashMap<>(50);
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

    @Override
    public void populateHash(ArrayList<Category> list) {
           for(Category c:list) {
               Log.i(TAG, "populateHash:  name + "+c.getName()+" id "+c.getId());
               nameToCategoryHash.put(c.getName(),c.getId());
           }
    }
}

