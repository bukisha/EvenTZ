package com.example.bookee.eventz.details;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.bookee.eventz.R;

class FollowEventNotificationFactory {
    private static final String TAG = "FollowEventNotification";
    private static int NOTIFICATION_MANAGER_ID = 11;
    private static final String CHANNEL_ID = "123456";
    private static final String CHANNEL_NAME = "followEventChannel";
    private static final String CHANNEL_DESCRIPTION = "Event about to happen";
    private Context factoryContext;

     FollowEventNotificationFactory(Context context) {
        factoryContext = context;
    }

    public void prepareNotification(Intent intent) {
        String eventId = intent.getStringExtra(Presenter.EVENT_ID);

        Notification notification = createNotification(factoryContext, intent.getStringExtra(Presenter.EVENT_NAME), eventId);
        NotificationManager manager = (NotificationManager) factoryContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager != null) {
            manager.notify(NOTIFICATION_MANAGER_ID, notification);
            NOTIFICATION_MANAGER_ID++;
        }
        //after notification is created and displayed remove event from database
        //EventsDatabaseHelper.getInstance(factoryContext).getAttachedRepository().removeEventWithId(eventId);
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
        return notification;
    }
}
