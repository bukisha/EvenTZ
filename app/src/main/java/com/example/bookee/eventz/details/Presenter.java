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
                view.displayEvent(event);
            }

            @Override
            public void onFailure() {
                view.displayError();
            }
        };
        model.fetchEventForId(id, modelCallback);
    }
}
