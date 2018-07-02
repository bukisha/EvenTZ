package com.example.bookee.eventz.details;

import android.util.Log;

import com.example.bookee.eventz.data.Event;

class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.Model model;
    private MvpContract.View view;
    private boolean followChecked;
    private Event currentEvent;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
        followChecked = false;
    }

    @Override
    public void fetchEventForId(String id) {
        MvpContract.FetchEventForIdCallback modelCallback = new MvpContract.FetchEventForIdCallback() {
            @Override
            public void onSuccess(Event event) {
                if (notViewExists()) return;
                currentEvent = event;
                Log.d(TAG, "onSuccess: Event from server is..."+event);
                if (event.getLogo()!= null) {
                    view.displayEvent(getTitle(event), event.getName().getText(), getDate(event), event.getDescription().getText(), event.getLogo().getUrl());
                } else {
                    view.displayEventWithoutLogo(getTitle(event), event.getName().getText(), getDate(event), event.getDescription().getText());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (notViewExists()) return;
                view.displayError();
            }
        };
        model.fetchEventForId(id, modelCallback);
    }

    private boolean notViewExists() {
        return this.view == null;
    }

    private String getDate(Event event) {
        Log.d(TAG, "getDate: ");
        String date = event.getStart().getLocal();
        date = date.substring(0, date.indexOf("T"));
        date = date.replace("-", ".");

        return date;
    }

    private String getTitle(Event event) {
        Log.d(TAG, "getTitle: ");
        String title = event.getStart().getTimezone();
        title = title.substring(title.indexOf("/") + 1);
        title = title.replace("_", " ");
        return title;
    }

    public void attachView(DetailsActivity detailsActivity) {
        this.view = detailsActivity;
    }

    public void detachView() {
        this.view = null;
    }

    @Override
    public void followClicked() {
        if (followChecked) {
            if (notViewExists()) return;
            view.setFollowUncheck(currentEvent);
            followChecked = !followChecked;
            //stop following this Event inside a service
        } else {
            if (notViewExists()) return;
            view.setFollowChecked(currentEvent);
            followChecked = !followChecked;
            //start following this Event inside service if the service is running
            //if the service does not exist than create one and start following Event with it
        }
    }

    @Override
    public void displayFollowedEventsDialog() {
        //TODO create followed Events dialog and display events that are folowed atm
    }
}
