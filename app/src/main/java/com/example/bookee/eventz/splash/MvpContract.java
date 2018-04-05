package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        void fetchInitialCategories(FetchCategoriesCallback callback);
    }
    interface Presenter {
        void fetchInitialCategories();
    }
    interface View {
        void showErrorFragment();
        void passInitialCategories(ArrayList<Category> list);
    }
    interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);
        void onFailure();

    }

}
