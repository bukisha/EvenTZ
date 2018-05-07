package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.EventApp;
import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;


    public Presenter(MvpContract.View view, Model model) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void fetchInitialCategories() {
        MvpContract.FetchCategoriesCallback presenterCallback = new MvpContract.FetchCategoriesCallback() {
            @Override
            public void onSuccess(ArrayList<Category> list) {

                EventApp.setGlobalCategoryIds(getCategoryIdNames(list));
                view.passInitialCategories(list);
            }

            @Override
            public void onFailure(Throwable t) {
                {
                    view.showErrorFragment();
                }
            }
        };
        model.fetchInitialCategories(presenterCallback);
    }

    private ArrayList<String> getCategoryIdNames(ArrayList<Category> list) {
        ArrayList<String> CategoryIds=new ArrayList<>();
        for(Category c : list) {
            CategoryIds.add(c.getShortName());
        }
        return CategoryIds;
    }
}