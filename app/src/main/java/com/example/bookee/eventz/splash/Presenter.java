package com.example.bookee.eventz.splash;

import android.content.Context;
import com.example.bookee.eventz.data.pojos.Category;
import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.View view, Model model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void fetchInitialCategories(Context context) {
        MvpContract.FetchCategoriesCallback presenterCallback = new MvpContract.FetchCategoriesCallback() {
            @Override
            public void onSuccess(ArrayList<Category> list) {
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