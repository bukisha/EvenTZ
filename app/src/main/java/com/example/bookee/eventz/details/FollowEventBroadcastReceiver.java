package com.example.bookee.eventz.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.bookee.eventz.data.EventsDatabaseHelper;

public class FollowEventBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "FollowEventBroadcastRec";

    @Override
    public void onReceive(Context context, Intent intent) {
        String eventName=intent.getStringExtra(Presenter.EVENT_NAME);
        Log.d(TAG, "onReceive: broadcast received "+eventName);
        FollowEventNotificationFactory followEventNotificationFactory=new FollowEventNotificationFactory(context);
        followEventNotificationFactory.prepareNotification(intent);
        EventsDatabaseHelper databaseHelper = EventsDatabaseHelper.getInstance(context);
        databaseHelper.getAttachedRepository().removeEventWithId(intent.getStringExtra(Presenter.EVENT_ID));
    }
}
