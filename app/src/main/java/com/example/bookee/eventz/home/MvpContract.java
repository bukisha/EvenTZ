package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.pojos.Category;

import java.util.ArrayList;
import java.util.HashMap;

interface MvpContract {

    interface Model {
        /**
         * fetch all category names from api
         *
         * @param callback callback to model so that it can handle result from repository
         */
        void fetchCategoryNames(FetchCategoriesCallback callback);

        /**
         * gets id of category that is clicked on
         *
         * @param categoryName name of clicked category
         * @return string representation of int value that is id of clicked category
         */
        String getClickedCategoryId(String categoryName);

        /**
         * populates HashMap with pairs of (category Id,category name)
         *
         * @param list list of all categories
         */
        void populateHash(ArrayList<Category> list);

        /**
         * @return hashMap with (category id,category name ) pairs for every category
         */
        HashMap<String, String> getHash();
    }

    interface Presenter {
        /**
         * passes list of categories to model so that it can initialize itself properly
         *
         * @param serializableExtra list of categories that are to passed to model
         */
        void populateNameList(ArrayList<Category> serializableExtra);

        /**
         * signals to model that category with given name is clicked on
         *
         * @param categoryName name of category that is clicked on
         */
        void itemClicked(String categoryName);

        /**
         * attaches parent view to presenter
         *
         * @param view
         */
        void attachView(MvpContract.View view);

        /**
         * detaches parent view from presenter
         */
        void detachView();

        /**
         * handles click on floating action button
         */
        void floatingActionButtonClick();

        /**
         * launches FollowedEventsActivity
         */
        void launchFollowedEvents();
    }

    interface View {
        /**
         * displays list of categories in recycler view
         *
         * @param categoryList lsit of categories for display
         */
        void updateCategories(ArrayList<Category> categoryList);

        /**
         * displays list of events for a category with given id
         * @param categoryId id of category whose events we want to display
         */
        void displayListOfEvents(String categoryId);

        /**
         * launch CreateEventsActivity
         */
        void launchCreateEventActivity();

        /**
         * displays error message to user
         * @param message message to display
         */
        void displayErrorMessage(String message);//TODO display message to user if some error happened during init

        /**
         * launches FollowedEventsActivity
         */
        void launchFollowedEventsActivity();

    }

    interface FetchCategoriesCallback {
        void onSuccess(ArrayList<Category> list);

        void onFailure(Throwable t);

    }
}

