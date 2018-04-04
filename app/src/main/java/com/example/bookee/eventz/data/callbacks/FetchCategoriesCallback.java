package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

public interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);
        void onFailure();

}
