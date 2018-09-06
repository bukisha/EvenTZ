package com.example.bookee.eventz.splash;

import android.content.Context;

import com.example.bookee.eventz.data.pojos.Category;

import java.util.ArrayList;

interface MvpContract {

    interface Model {
        void fetchInitialCategories(FetchCategoriesCallback callback,Context context);
    }
    interface Presenter {
        void fetchInitialCategories(Context context);

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
