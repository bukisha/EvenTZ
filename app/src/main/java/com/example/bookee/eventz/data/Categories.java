package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("categories")
    private List<Category> mCategories;

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

}
