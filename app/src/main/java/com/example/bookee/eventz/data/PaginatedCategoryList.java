package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PaginatedCategoryList {

    @SerializedName("categories")
    private ArrayList<Category> categories;
    @SerializedName("locale")
    private String locale;
    @SerializedName("pagination")
    private Pagination pagination;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
