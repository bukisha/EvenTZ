package com.example.bookee.eventz.followed;

import android.util.Log;

import java.util.List;

public class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getFollowedEventNames() {
        MvpContract.GetDatabaseEventsCallback callbackForModel=new MvpContract.GetDatabaseEventsCallback() {
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

    private boolean viewNotExist() {
        return view==null;
    }
}
