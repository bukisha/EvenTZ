package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.util.GlobalDataOperator;

import java.util.ArrayList;

class Model implements MvpContract.Model {
    private RetrofitCategoryRepository repository;
    private MvpContract.FetchCategoriesCallback callbackFromPresenter;
    Model(RetrofitCategoryRepository repository) {
        this.repository=repository;
    }

    @Override
    public void fetchInitialCategories( MvpContract.FetchCategoriesCallback callback) {
        callbackFromPresenter=callback;

       FetchCategoriesCallback callbackForRepository=new FetchCategoriesCallback() {
           @Override
           public void onSuccess(ArrayList<Category> list) {
            callbackFromPresenter.onSuccess(list);
           }
           @Override
           public void onFailure(Throwable t) {
            callbackFromPresenter.onFailure(t);
           }
       };
        repository.fetchCategories(callbackForRepository);
        }

}
