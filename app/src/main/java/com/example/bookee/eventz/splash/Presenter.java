package com.example.bookee.eventz.splash;

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
    public void fetchInitialCategories() {
        MvpContract.FetchCategoriesCallback presenterCallback = new MvpContract.FetchCategoriesCallback() {
            @Override
            public void onSuccess(ArrayList<Category> list) {

               // EventApp.setGlobalCategoryIds(getCategoryIdShortNames(list));//todo i pazi ovo: model ti fecuje podaatke i umesto u modelu, ti u prezenteru manipulises sa njima i smestas ih u static promenljivu! Prezenter ne zna za podatke i za njihovu manipulaciju: prezenter uzme podatak i pripremi ga za View i NISTA VISE
                //EventApp.setGlobalListOfAllCategories(list);
                view.passInitialCategories(list);
            }

            @Override
            public void onFailure(Throwable t) {
                {
                    view.buildAndShowErrorFragment();
                }
            }
        };
        model.fetchInitialCategories(presenterCallback);
    }
}