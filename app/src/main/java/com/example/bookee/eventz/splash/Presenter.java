package com.example.bookee.eventz.splash;

import android.content.Context;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.View view, Model model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void fetchInitialCategories(Context context) {
        MvpContract.FetchAndStoreCategoriesCallback presenterCallback = new MvpContract.FetchAndStoreCategoriesCallback() {
            @Override
            public void onSuccess() {
                view.launchHomeActivity();
            }

            @Override
            public void onFailure(Throwable t) {
                {
                    view.buildAndShowErrorFragment();
                }
            }
        };
        model.fetchInitialCategories(presenterCallback,context);
    }
}