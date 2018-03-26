package com.example.bookee.eventz.home;

import android.util.Log;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.EventsRepository;
import java.util.List;

public class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private EventsRepository repository;

    Model() {
        repository=new EventsRepository();
    }

    public void fetchCategories(final MvpContract.fetchCategoriesCallback callback) {
        Log.d(TAG, "fetchCategories: fetch starting");
        repository.fetchCategories(new MvpContract.fetchCategoriesCallback() {
            @Override
            public void onSuccess(List<Category> list) {
                callback.onSuccess(list);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
