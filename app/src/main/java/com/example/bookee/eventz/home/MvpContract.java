package com.example.bookee.eventz.home;

import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;

import java.io.Serializable;
import java.util.ArrayList;

public interface MvpContract  {

    interface Model {
       void fetchCategoryNames(FetchCategoryNamesCallback callback);
       String getClickedCategoryId(String categoryName);
    }
    interface Presenter {
        void populateNameList(Serializable serializableExtra);
        void itemClicked(String categoryName);
    }
    interface View {
        void updateCategories(ArrayList<String> categoryNamesList);
        void displayListOfEvents(String categoryId);
        void displayErrorMessage(String s);
    }
}

