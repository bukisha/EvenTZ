package com.example.bookee.eventz.details;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.bookee.eventz.data.pojos.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import static android.content.Context.ALARM_SERVICE;

class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private static final String EVENT_ID = "id";
    private static final String EVENT_NAME = "name";
    private static final String SERVICE_ACTION = "com.example.bookee.eventz.details";
    private static final String TOAST_STARTED_FOLLOWING = "Started following event";
    private static final String TOAST_STOPED_FOLLOWING = "Stopped following event";
    private MvpContract.Model model;
    private MvpContract.View view;
    private boolean followChecked;
    private Event currentEvent;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
        followChecked = false;
    }

    public void setFollowed(boolean followed) {
        followChecked = followed;
    }

    @Override
    public void fetchEventForId(String id) {
        MvpContract.FetchEventForIdCallback modelCallback = new MvpContract.FetchEventForIdCallback() {
            @Override
            public void onSuccess(Event event) {
                if (notViewExists()) return;
                currentEvent = event;
                Log.d(TAG, "onSuccess: Event from server is..." + event);
                if (event.getLogo() != null) {
                    view.displayEvent(getTitle(event), event.getName().getText(), getDate(event), event.getDescription().getText(), event.getLogo().getUrl());
                } else {
                    view.displayEventWithoutLogo(getTitle(event), event.getName().getText(), getDate(event), event.getDescription().getText());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (notViewExists()) return;
                view.displayError();
            }
        };
        model.fetchEventForId(id, modelCallback);
    }

    private boolean notViewExists() {
        return this.view == null;
    }

    private String getDate(Event event) {
        Log.d(TAG, "getDate: ");
        String date = event.getStart().getLocal();
        date = date.substring(0, date.indexOf("T"));
        date = date.replace("-", ".");

        return date;
    }

    private String getTitle(Event event) {
        Log.d(TAG, "getTitle: ");
        String title = event.getStart().getTimezone();
        title = title.substring(title.indexOf("/") + 1);
        title = title.replace("_", " ");
        return title;
    }

    public void attachView(DetailsActivity detailsActivity) {
        this.view = detailsActivity;
    }

    public void detachView() {
        this.view = null;
    }

    @Override
    public void followClicked() {
        if (followChecked) {
            if (notViewExists()) return;
            view.setFollowUncheck(currentEvent);
            followChecked = !followChecked;
            //stop following this Event inside a service
        } else {
            if (notViewExists()) return;
            view.setFollowChecked(currentEvent);
            followChecked = !followChecked;
            //start following this Event inside service if the service is running
            //if the service does not exist than create one and start following Event with it
        }
    }

    @Override
    public void launchFollowedEventsActivity() {
        if (notViewExists()) return;
        view.launchFollowedEventActivity();
    }

    @Override
    public void removeRowWithId(String id) {
        model.removeEventWithId(id);
    }

    @Override
    public void startFollowingEvent(final Event event, final Context context) {

        Thread alarmCreatorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                createAlarm(event, context);
                model.addFollowedEvent(event);
            }
        });
        alarmCreatorThread.start();
        if (notViewExists()) return;
        view.showToast(TOAST_STARTED_FOLLOWING);
    }

    private void createAlarm(Event event, Context context) {
        Intent intentForBroadcast = new Intent(context, FollowEventBroadcastReceiver.class);
        intentForBroadcast.setAction(SERVICE_ACTION);
        intentForBroadcast.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        intentForBroadcast.putExtra(EVENT_ID, event.getId());
        intentForBroadcast.putExtra(EVENT_NAME, event.getName().getText());

        long triggerTime = prepareTriggerTime(event.getStart().getUtc(), event.getStart().getTimezone());
        int pendingIntentId = (int) Long.parseLong(event.getId());   //unique pending intent id,because there could be many of them (many followed events)
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pendingIntentId, intentForBroadcast, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }
    }

    @Override
    public void stopFollowingEvent(final Event event, final Context context) {
        Thread alarmDestroyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                destroyAlarm(event, context);
                model.removeEventWithId(event.getId());
            }
        });
        alarmDestroyThread.start();
        if (notViewExists()) return;
        view.showToast(TOAST_STOPED_FOLLOWING);
    }

    private void destroyAlarm(Event event, Context context) {
        Intent cancelIntent = new Intent(context, FollowEventBroadcastReceiver.class);
        cancelIntent.setAction(SERVICE_ACTION);
        cancelIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        cancelIntent.putExtra(EVENT_ID, event.getId());
        cancelIntent.putExtra(EVENT_NAME, event.getName().getText());

        int cancelPendingIntentId = (int) Long.parseLong(event.getId());

        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(context, cancelPendingIntentId, cancelIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(cancelPendingIntent);
        }
    }

    private long prepareTriggerTime(String utc, String timezone) {
        DateTimeZone zone = DateTimeZone.forID(timezone);
        DateTime currentTime = new DateTime(zone);
        Log.d(TAG, "prepareTriggerTime: current time in " + timezone + ": " + currentTime + " in milliseconds " + currentTime.getMillis());
        DateTime startTime = new DateTime(utc);
        Log.d(TAG, "prepareTriggerTime: Event start time in " + timezone + ": " + startTime + " in milliseconds " + startTime.getMillis());
        Log.d(TAG, "prepareTriggerTime: trigger time in " + (startTime.getMillis() - currentTime.getMillis()));
        return (startTime.getMillis() - currentTime.getMillis()) + System.currentTimeMillis();
    }

    @Override
    public void checkFollowButton(String eventId) {
        MvpContract.CheckFollowedStatusCallback modelCallback = new MvpContract.CheckFollowedStatusCallback() {
            @Override
            public void onSuccess(Boolean status) {
                if (notViewExists()) return;
                view.setupFollowButton(status);
            }
        };
        model.checkFollowButton(eventId, modelCallback);
    }

    @Override
    public void closeDataSource() {
        model.closeDataSource();
    }
}
