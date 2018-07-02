package com.example.bookee.eventz.details;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.bookee.eventz.R;

public class FollowEventBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "FollowEventBroadcastRec";
    private static int NOTIFICATION_MANAGER_ID = 11;
    private static final String CHANNEL_ID = "123456";
    private static final String CHANNEL_NAME = "followEventChannel";
    private static final String CHANNEL_DESCRIPTION = "Event about to happen";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: broadcast received");
        String eventId = intent.getStringExtra(FollowEventService.EVENT_ID);
        if (eventId.equals("")) {
            Log.d(TAG, "onReceive: doslo je do sranja");
        } else {
            Log.d(TAG, "onReceive: " + eventId);
        }
        Notification notification = createNotification(context, intent.getStringExtra(FollowEventService.EVENT_NAME), eventId);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager != null) {
            manager.notify(NOTIFICATION_MANAGER_ID, notification);
            NOTIFICATION_MANAGER_ID++;
        }
        //after notification is created and displayed remove event from database
        EventsDatabaseHelper.getInstance(context).deleteRowWithId(eventId);
    }

    private Notification createNotification(Context context, String eventName, String eventID) {
        NotificationCompat.Builder compatBuilder;
        Notification.Builder builder;
        Notification notification;

        Intent onNotificationClick = new Intent(context, DetailsActivity.class);
        onNotificationClick.putExtra(DetailsActivity.EXTRA_EVENT_ID, eventID);
        int pendingIntentId = (int) System.currentTimeMillis();
        PendingIntent onClickPendingIntent = PendingIntent.getActivity(context, pendingIntentId, onNotificationClick, 0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
            compatBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
            notification = compatBuilder.setSmallIcon(R.drawable.ic_music_cat)
                    .setContentIntent(onClickPendingIntent)
                    .setContentTitle(eventName)
                    .setContentText(context.getResources().getString(R.string.notification_message))
                    .setAutoCancel(true)
                    .build();
        } else {
            builder = new Notification.Builder(context);
            notification = builder.setSmallIcon(R.drawable.ic_music_cat)
                    .setContentIntent(onClickPendingIntent)
                    .setContentTitle(eventName)
                    .setContentText(context.getResources().getString(R.string.notification_message))
                    .setAutoCancel(true)
                    .build();
        }
        EventsDatabaseHelper databaseHelper=EventsDatabaseHelper.getInstance(context);
        databaseHelper.deleteRowWithId(eventID);
        return notification;
    }
}
