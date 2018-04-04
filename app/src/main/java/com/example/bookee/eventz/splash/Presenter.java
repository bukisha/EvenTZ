package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;
import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;


   public Presenter(MvpContract.View view,Model model) {
        this.model=model;
        this.view = view;
    }

    @Override
    public void fetchInitialCategories() {
        model.fetchInitialCategories(new FetchCategoryNamesCallback() {

            @Override
            public void onSuccess(ArrayList<Category> list) {
                view.passInitialCategories(list);
            }

            @Override
            public void onFailure(Throwable t) {
                view.showErrorFragment();
            }
        });
    }
}