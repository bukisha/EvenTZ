package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.Category;
import java.util.List;

public interface MvpContract {

    interface Model {
        void fetchCategories(fetchCategoriesCallback callback);
    }
    interface Presenter {
        void fetchCategories();
    }
    interface View {
        void updateCategories(List<Category> categoryList);
        void displayErrorMessage(String s);
    }
    //callback interface for fetching
    interface fetchCategoriesCallback {
        void onSuccess(List<Category> list);
        void onFailure(Throwable t);
    }
}

