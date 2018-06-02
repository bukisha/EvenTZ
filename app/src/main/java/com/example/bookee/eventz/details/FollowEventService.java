package com.example.bookee.eventz.details;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.bookee.eventz.data.Event;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class FollowEventService extends Service {
    private static final String TAG = "FollowEventBackgroundS";
    public static final String EVENT_ID="id";
    public static final String EVENT_NAME="name";
    private static final String SERVICE_ACTION="com.example.bookee.eventz.details";
    private static final String HANDLER_THREAD_NAME="FollowedEventThread";

    private HandlerThread followEventThread;
    private Handler followServiceHandler;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        followEventThread = new HandlerThread(HANDLER_THREAD_NAME);
        followEventThread.start();
        followServiceHandler = new Handler(followEventThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Intent intentForBroadcast = new Intent(getApplicationContext(),FollowEventBroadcastReceiver.class);
                intentForBroadcast.setAction(SERVICE_ACTION);
                intentForBroadcast.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                Event event= (Event) msg.obj;
                Log.d(TAG, "handleMessage: event here is "+event.toString());

                intentForBroadcast.putExtra(EVENT_ID, event.getId());
                intentForBroadcast.putExtra(EVENT_NAME,event.getName().getText());

                long triggerTime=prepareTriggerTime(event.getStart().getUtc(),event.getStart().getTimezone());
                int pendingIntentId= (int) System.currentTimeMillis();   //unique pending intent id,because there could be many of them (many followed events)
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), pendingIntentId, intentForBroadcast, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (alarmManager != null) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                }
                Toast.makeText(FollowEventService.this, "Following event", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private long prepareTriggerTime(String utc,String timezone) {
        DateTimeZone zone=DateTimeZone.forID(timezone);
        DateTime currentTime=new DateTime(zone);
        Log.d(TAG, "prepareTriggerTime: current time in "+timezone+": "+currentTime+" in milliseconds "+currentTime.getMillis());
        DateTime startTime=new DateTime(utc);
        Log.d(TAG, "prepareTriggerTime: event start time in "+timezone+": "+startTime+" in milliseconds "+startTime.getMillis());
        Log.d(TAG, "prepareTriggerTime: trigger time in "+(startTime.getMillis()-currentTime.getMillis()));
        return  (startTime.getMillis()-currentTime.getMillis())+System.currentTimeMillis();

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        Message msg = followServiceHandler.obtainMessage();
        msg.obj=intent.getSerializableExtra(DetailsActivity.CHECKED_EVENT_EXTRA);
        Log.d(TAG, "onStartCommand: msg is "+msg.obj.toString());
        followServiceHandler.sendMessage(msg);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
