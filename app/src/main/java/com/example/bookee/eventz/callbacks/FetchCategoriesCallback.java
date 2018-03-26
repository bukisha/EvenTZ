package com.example.bookee.eventz.callbacks;

import com.example.bookee.eventz.data.Category;
import java.util.List;

public interface FetchCategoriesCallback {
        void onSuccess(List<Category> list);
        void onFailure(Throwable t);

}
