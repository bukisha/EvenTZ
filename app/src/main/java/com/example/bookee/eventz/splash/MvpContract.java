package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;
import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

public interface MvpContract {

    interface Model {
        void fetchInitialCategories(FetchCategoryNamesCallback callback);
    }
    interface Presenter {
        void fetchInitialCategories();
    }
    interface View {
        void showErrorFragment();
        void passInitialCategories(ArrayList<Category> list);
    }

}
