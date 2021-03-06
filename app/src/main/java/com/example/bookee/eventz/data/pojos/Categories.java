package com.example.bookee.eventz.data.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    @SerializedName("categories")
    private ArrayList<Category> mCategories;

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(ArrayList<Category> categories) {
        mCategories = categories;
    }

}
