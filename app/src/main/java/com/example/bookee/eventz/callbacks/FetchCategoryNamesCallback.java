package com.example.bookee.eventz.callbacks;

import java.util.ArrayList;

public interface FetchCategoryNamesCallback {
    void onSuccess(ArrayList<String> list);
    void onFailure(Throwable t);
}
