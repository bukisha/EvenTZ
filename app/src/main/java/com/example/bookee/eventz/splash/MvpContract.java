package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.pojos.Category;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        void fetchInitialCategories(FetchCategoriesCallback callback);
    }
    interface Presenter {
        void fetchInitialCategories();

    }
    interface View {
        void buildAndShowErrorFragment();
        void passInitialCategories(ArrayList<Category> list);
    }
    interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);
        void onFailure(Throwable t);

    }

}
