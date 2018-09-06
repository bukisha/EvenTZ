package com.example.bookee.eventz.util;

import android.content.SharedPreferences;
import com.example.bookee.eventz.data.pojos.Category;
import java.util.ArrayList;

interface GlobalDataOperator {
    void storeGlobalCategoryList(ArrayList<Category> categories);

    ArrayList<Category> getGlobalCategoryList();

    void storeCategoriesShortIds(ArrayList<String> categoryIdShortNames);

      ArrayList<String> getCategoriesShortNames();
}
