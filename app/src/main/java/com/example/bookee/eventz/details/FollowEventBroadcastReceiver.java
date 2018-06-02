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
    private static final String CHANNEL_DESCRIPTION = "event about to happen";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: broadcast received");
        String eventId = intent.getStringExtra(FollowEventService.EVENT_ID);
        if (eventId.equals("")) {
            Log.d(TAG, "onReceive: doslo je do sranja");
        } else {
            Log.d(TAG, "onReceive: " + eventId);
        }
        Notification notification = createNotification(context, intent.getStringExtra(FollowEventService.EVENT_NAME),eventId);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager != null) {
            manager.notify(NOTIFICATION_MANAGER_ID, notification);
            NOTIFICATION_MANAGER_ID++;
        }
        //DetailsActivity.launch(eventId,context);
    }

    private Notification createNotification(Context context, String eventName,String eventID) {
        NotificationCompat.Builder compatBuilder;
        Notification.Builder builder;
        Notification notification;

        Intent onNotificationClick=new Intent(context,DetailsActivity.class);
        onNotificationClick.putExtra(DetailsActivity.EXTRA_EVENT_ID,eventID);
        int pendingIntentId= (int) System.currentTimeMillis();
        PendingIntent onClickPendingIntent=PendingIntent.getActivity(context,pendingIntentId,onNotificationClick,0);


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

    private String convertIntToMonthName(int month) {
        String monthName;
        switch (month) {
            case 0:
                monthName = "January";
                break;
            case 1:
                monthName = "February";
                break;
            case 2:
                monthName = "March";
                break;
            case 3:
                monthName = "April";
                break;
            case 4:
                monthName = "May";
                break;
            case 5:
                monthName = "June";
                break;
            case 6:
                monthName = "July";
                break;
            case 7:
                monthName = "August";
                break;
            case 8:
                monthName = "September";
                break;
            case 9:
                monthName = "October";
                break;
            case 10:
                monthName = "November";
                break;
            case 11:
                monthName = "December";
                break;
            default:
                monthName = "Unknown";
        }
        return monthName;
    }
}
