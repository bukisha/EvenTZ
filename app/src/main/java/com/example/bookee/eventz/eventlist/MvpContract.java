package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.callbacks.FetchEventsForCategoryCallback;

import java.util.ArrayList;

public interface MvpContract {

    interface Model {
        void fetchEventsForCategory(String categoryName, FetchEventsForCategoryCallback callback);
    }

    interface Presenter {
        void fetchEventsForCategory(String categoryName);
    }

    interface View {
        void openEventListActivity(ArrayList<String> eventNames);
        void displayError();
    }

}
