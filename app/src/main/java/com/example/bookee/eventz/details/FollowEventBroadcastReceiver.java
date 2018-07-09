package com.example.bookee.eventz.details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FollowEventBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "FollowEventBroadcastRec";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: broadcast received");
        FollowEventNotificationFactory followEventNotificationFactory=new FollowEventNotificationFactory(context);
        followEventNotificationFactory.prepareNotification(intent);
    }
}
