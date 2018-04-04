package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.Category;

import java.io.Serializable;
import java.util.ArrayList;

public interface MvpContract  {

    interface Model {
       void fetchCategoryNames(FetchCategoriesCallback callback);
       String getClickedCategoryId(String categoryName);
    }
    interface Presenter {
        void populateNameList(Serializable serializableExtra);
        void itemClicked(String categoryName);
    }
    interface View {
        void updateCategories(ArrayList<String> categoryNamesList);
        void displayListOfEvents(String categoryId);
        void displayErrorMessage(String message);
    }
    interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);
        void onFailure();

    }
}

