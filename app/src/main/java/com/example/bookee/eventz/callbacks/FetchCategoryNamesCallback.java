package com.example.bookee.eventz.callbacks;

import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

public interface FetchCategoryNamesCallback {
    void onSuccess(ArrayList<Category> list);
    void onFailure(Throwable t);
}