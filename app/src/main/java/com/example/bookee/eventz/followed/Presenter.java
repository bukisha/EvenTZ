package com.example.bookee.eventz.followed;

import android.util.Log;

import java.util.List;

class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
    }

    public void closeDatabase() {
        model.closeDatabase();
    }

    @Override
    public void getFollowedEventNames() {
        MvpContract.GetEventsNamesListCallback callbackForModel=new MvpContract.GetEventsNamesListCallback() {
            @Override
            public void onSuccess(List<String> names) {
                Log.d(TAG, "onSuccess: ");
                if (viewNotExist()) return;
                view.displayFollowedEvents(names);
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (viewNotExist()) return;
                view.displayError();
            }
        };
        model.getFollowedEventNames(callbackForModel);
    }

    @Override
    public void attachView(MvpContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;
    }

    @Override
    public void launchDetailsActivityForEvent(String name) {
        MvpContract.GetEventIdCallback idCallback=new MvpContract.GetEventIdCallback() {
            @Override
            public void onSuccess(String id) {
                if(viewNotExist()) return;
                view.launchDetailsActivity(id);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.displayError();
            }
        };
        model.getIdForName(name,idCallback);
    }

    private boolean viewNotExist() {
        return view==null;
    }
}
