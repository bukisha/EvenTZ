package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

interface MvpContract  {

    interface Model {
       void fetchCategoryNames(FetchCategoriesCallback callback);
       String getClickedCategoryId(String categoryName);
       void populateHash(ArrayList<Category> list);
    }
    interface Presenter {
        void populateNameList(ArrayList<Category> serializableExtra);
        void itemClicked(String categoryName);
    }
    interface View {
        void updateCategories(ArrayList<String> categoryNamesList);
        void displayListOfEvents(String categoryId);
       // void displayErrorMessage(String message);
    }
    interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);
        void onFailure();

    }
}

