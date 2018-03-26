package com.example.bookee.eventz.home;

import android.util.Log;
import com.example.bookee.eventz.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.EventsRepository;
import java.util.ArrayList;
import java.util.HashMap;

public class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private EventsRepository repository;
    private HashMap<String,String> nameToCategoryHash;

    Model() {
        repository = new EventsRepository();
        nameToCategoryHash= new HashMap<>(50);
    }

    public void fetchCategoryNames(final FetchCategoryNamesCallback callback) {
        Log.d(TAG, "fetchCategories: fetching of category names starting");
        repository.fetchCategories(new FetchCategoriesCallback() {
            @Override
            public void onSuccess(ArrayList<Category> list) {
                populateHash(list);
                callback.onSuccess( list );
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
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

