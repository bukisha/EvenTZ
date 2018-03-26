package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaginatedCategoryList {

    @SerializedName("categories")
    private List<Category> mCategories;
    @SerializedName("locale")
    private String mLocale;
    @SerializedName("pagination")
    private Pagination mPagination;

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    public String getLocale() {
        return mLocale;
    }

    public void setLocale(String locale) {
        mLocale = locale;
    }

    public Pagination getPagination() {
        return mPagination;
    }

    public void setPagination(Pagination pagination) {
        mPagination = pagination;
    }

}
