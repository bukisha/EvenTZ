package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.Category;

import java.util.ArrayList;

interface MvpContract  {

    interface Model {
       void fetchCategoryNames(FetchCategoriesCallback callback);
       String getClickedCategoryId(String categoryName);
       void populateHash(ArrayList<Category> list);
    }
    interface Presenter {
        void populateNameList(ArrayList<Category> serializableExtra);
        void itemClicked(String categoryName);
        void attachView(MvpContract.View view);
        void detachView();
        void floatingActionButtonClick();
        void launchFollowedEvents();
    }
    interface View {
        void updateCategories(ArrayList<Category> categoryList);
        void displayListOfEvents(String categoryId);
        void launchCreateEventActivity();
        void displayErrorMessage(String message);
        void launchFollowedEventsActivity();

    }
    interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);
        void onFailure(Throwable t);

    }
}

