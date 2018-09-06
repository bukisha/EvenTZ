package com.example.bookee.eventz.splash;

import android.content.Context;

interface MvpContract {

    interface Model {
        void fetchInitialCategories(FetchAndStoreCategoriesCallback callback, Context context);
    }
    interface Presenter {
        void fetchInitialCategories(Context context);

    }
    interface View {
        void buildAndShowErrorFragment();
        void launchHomeActivity();
    }
    interface FetchAndStoreCategoriesCallback {
        void onSuccess();
        void onFailure(Throwable t);

    }

}
