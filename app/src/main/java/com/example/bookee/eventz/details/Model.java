package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;

class Model implements MvpContract.Model {

    private RetrofitEventsRepository repository;
    private MvpContract.FetchEventForIdCallback callbackForPresenter;

    public Model(RetrofitEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchEventForId(String id, MvpContract.FetchEventForIdCallback callback) {
        callbackForPresenter=callback;
        FetchEventForIdCallback modelCallback=new FetchEventForIdCallback() {
            @Override
            public void onSuccess(Event event) {
                callbackForPresenter.onSuccess(event);
            }

            @Override
            public void onFailure(Throwable t) {
                callbackForPresenter.onFailure(t);
            }
        };
        repository.fetchEventForId(id,modelCallback);
    }
}
