package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.Event;

class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void fetchEventForId(String id) {
        MvpContract.FetchEventForIdCallback modelCallback = new MvpContract.FetchEventForIdCallback() {
            @Override
            public void onSuccess(Event event) {
                if(notViewExists()) return;
                view.displayEvent(getTitle(event),event.getName().getText(),getDate(event),event.getDescription().getText(),event.getLogo().getUrl());
            }

            @Override
            public void onFailure() {
               if(notViewExists()) return;
                view.displayError();
            }
        };
        model.fetchEventForId(id, modelCallback);
    }

    private boolean notViewExists() {
        return this.view == null;
    }

    private String getDate(Event event) {
        String date= event.getStart().getLocal();
        date=date.substring(0,date.indexOf("T"));
        date=date.replace("-",".");
        return date;
    }

    private String getTitle(Event event) {
        String title = event.getStart().getTimezone();
        title = title.substring(title.indexOf("/") + 1);
        title = title.replace("_", " ");
        return title;
    }

    public void attachView(DetailsActivity detailsActivity) {
        this.view=detailsActivity;
    }

    public void detachView() {
        this.view=null;
    }
}
