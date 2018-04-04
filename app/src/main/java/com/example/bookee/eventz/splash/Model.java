package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.EventsRepository;

import java.util.ArrayList;


public class Model implements MvpContract.Model {
    private EventsRepository repository;

    public Model(EventsRepository repository) {
        this.repository=repository;
    }

    @Override
    public void fetchInitialCategories(final FetchCategoriesCallback callback) {
           repository.fetchCategories(new FetchCategoriesCallback() {
               @Override
               public void onSuccess(ArrayList<Category> list) {
                  // callback.onSuccess(extractCategoryNames(list));
                   callback.onSuccess(list);
               }

               @Override
               public void onFailure() {
                    callback.onFailure();
               }
           });
    }
}
