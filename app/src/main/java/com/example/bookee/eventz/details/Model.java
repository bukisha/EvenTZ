package com.example.bookee.eventz.details;

import android.content.Context;

import com.example.bookee.eventz.data.EventsDatabaseHelper;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.SQLiteDatabaseRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.pojos.Event;

import java.util.List;

class Model implements MvpContract.Model {

    private RetrofitEventsRepository eventsRepository;
    private MvpContract.FetchEventForIdCallback callbackForPresenter;
    private SQLiteDatabaseRepository databaseRepository;


    public Model(RetrofitEventsRepository eventsRepository, Context context) {
        this.eventsRepository = eventsRepository;
        this.databaseRepository = new SQLiteDatabaseRepository(EventsDatabaseHelper.getInstance(context));
    }

    @Override
    public void checkFollowButton(String eventId,MvpContract.CheckFollowedStatusCallback statusCallback) {
           List<String> followedIds= databaseRepository.getEventsIds();
            Boolean status=false;
           for (String id : followedIds) {
              if(id.equals(eventId))
                  status=true;
              }
             statusCallback.onSuccess(status);
        }

    @Override
    public void closeDataSource() {
        databaseRepository.closeDataSource();
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
        eventsRepository.fetchEventForId(id,modelCallback);
    }

    @Override
    public void removeEventWithId(String id) {
       databaseRepository.removeEventWithId(id);
    }

    @Override
    public void addFollowedEvent(Event event) {
        databaseRepository.addEvent(event);
    }
}
