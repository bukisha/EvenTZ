package com.example.bookee.eventz.details;

import android.content.Context;

import com.example.bookee.eventz.data.EventsDatabaseHelper;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.pojos.Event;

import java.util.List;

class Model implements MvpContract.Model {

    private RetrofitEventsRepository repository;
    private MvpContract.FetchEventForIdCallback callbackForPresenter;
    private EventsDatabaseHelper helper;

    public Model(RetrofitEventsRepository repository,Context context) {
        helper=EventsDatabaseHelper.getInstance(context);
        this.repository = repository;
    }

    @Override
    public void checkFollowButton(String eventId,MvpContract.CheckFollowedStatusCallback statusCallback) {
           List<String> followedIds=helper.getAllEventsIds();
            Boolean status=false;
           for (String id : followedIds) {
              if(id.equals(eventId))
                  status=true;
              }
             statusCallback.onSuccess(status);
        }

    @Override
    public void closeDatabase() {
        helper.close();
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

    @Override
    public void removeEventWithId(String id) {
       helper.deleteRowWithId(id);
    }

    @Override
    public void addFollowedEvent(Event event) {
        helper.addEvent(event);
    }
}
