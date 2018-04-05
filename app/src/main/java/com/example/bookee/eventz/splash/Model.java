package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;

import java.util.ArrayList;

class Model implements MvpContract.Model {
    private RetrofitCategoryRepository repository;

    Model(RetrofitCategoryRepository repository) {
        this.repository=repository;
    }

    @Override
    public void fetchInitialCategories(final MvpContract.FetchCategoriesCallback callback) {
           repository.fetchCategories(new FetchCategoriesCallback() {
               @Override
               public void onSuccess(ArrayList<Category> list) {
                   callback.onSuccess(list);
               }

               @Override
               public void onFailure() {
                    callback.onFailure();
               }
           });
    }

}
