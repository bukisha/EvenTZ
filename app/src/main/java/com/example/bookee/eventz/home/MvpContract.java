package com.example.bookee.eventz.home;

import com.example.bookee.eventz.callbacks.FetchCategoryNamesCallback;

import java.io.Serializable;
import java.util.ArrayList;

public interface MvpContract  {

    interface Model {
       void fetchCategoryNames(FetchCategoryNamesCallback callback);
    }
    interface Presenter {
        void populateNameList(Serializable serializableExtra);
    }
    interface View {
        void updateCategories(ArrayList<String> categoryNamesList);
        void displayErrorMessage(String s);
    }
}

