package com.example.bookee.eventz.create;

import android.util.Log;

import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.Description;
import com.example.bookee.eventz.data.pojos.End;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.Name;
import com.example.bookee.eventz.data.pojos.Start;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

public class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private MvpContract.Model model;
    private MvpContract.View view;
    private EventWrapper currentWrapper;
    private Event currentEvent;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    private int selectedHourOfDay;
    private int selectedMinuteOfHour;

    private HashMap<String, String> nameToIdHash;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
        currentWrapper = new EventWrapper();
        currentEvent = new Event();
        nameToIdHash = new HashMap<>(20);
    }

    @Override
    public void setName(String name) {
        Name currentName = new Name();
        currentName.setHtml(name);
        currentEvent.setName(currentName);
    }

    @Override
    public void setDescription(String description) {
        Description currentDescription = new Description();
        currentDescription.setHtml(description);
        currentEvent.setDescription(currentDescription);
    }

    @Override
    public void setCategory(String categoryName) {
        currentEvent.setCategoryId(getCategoryId(categoryName));
    }

    @Override
    public void setCurrency(String currency) {
        currentEvent.setCurrency(currency);
    }

    @Override
    public String getCategoryId(String categoryShortName) {
        Log.d(TAG, "getCategoryId: u selected category with name " + categoryShortName + " and id " + nameToIdHash.get(categoryShortName));
        return nameToIdHash.get(categoryShortName);
    }

    @Override
    public void postEvent() {
        prepareEventDateAndTime();
        setAdditionalEventProperties();

        currentWrapper.setEvent(currentEvent);
        MvpContract.PostEventCallback callback = new MvpContract.PostEventCallback() {
            @Override
            public void onSuccess(Event e) {
                if (notViewExists()) return;
                view.displayNewEvent(e);
            }

            @Override
            public void onFailure(Throwable t) {
                if (notViewExists()) return;
                view.displayError();
            }
        };
        model.postEvent(currentWrapper, callback);
    }

    private void setAdditionalEventProperties() {
        currentEvent.setOnlineEvent(true);
        currentEvent.setListed(true);
        currentEvent.setCapacity((long) 100);
    }

    private void prepareEventDateAndTime() {
        DateTime date = new DateTime()
                .withYear(selectedYear)
                .withMonthOfYear(selectedMonth)
                .withDayOfMonth(selectedDay)
                .withHourOfDay(selectedHourOfDay)
                .withMinuteOfHour(selectedMinuteOfHour);
        DateTimeZone myTimezone;
        myTimezone = DateTimeZone.getDefault();
        String currentTimezone = myTimezone.getID();
        String timeZone = TimeZone.getDefault().getDisplayName();
        Log.d(TAG, "postEvent: JAva timezone is " + timeZone);

        End end = new End();
        Log.d(TAG, "postEvent: timezone is " + myTimezone.getID());
        end.setTimezone(currentTimezone);
        end.setUtc(prepareISODateTime(date.plusHours(4)));
        currentEvent.setEnd(end);
        Start start = new Start();
        start.setTimezone(currentTimezone);
        start.setUtc(prepareISODateTime(date));
        currentEvent.setStart(start);
        Log.d(TAG, "setDate: selected date and time:  " + date);
    }

    private String prepareISODateTime(DateTime date) {
        DateTimeFormatter format = ISODateTimeFormat.dateTimeNoMillis();
        return format.print(date);
    }

    @Override
    public void attachView(MvpContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void startDateChooser() {
        if (notViewExists()) return;
        view.showDateChooser();
    }

    @Override
    public void startTimeChooser() {
        if (notViewExists()) return;
        view.showTimeChooser();
    }

    @Override
    public void startImageChooser() {
        //TODO pick a logo from phone file system
    }

    @Override
    public void setTime(int hour, int min) {
        selectedHourOfDay = hour;
        selectedMinuteOfHour = min;
    }

    @Override
    public void setDate(int year, int month, int day) {
        selectedYear = year;
        selectedMonth = month + 1;
        selectedDay = day;
    }

    private boolean notViewExists() {
        return this.view == null;
    }

    public void setHashMapWithShortNames(ArrayList<Category> categories) {
        for (Category c : categories) {
            nameToIdHash.put(c.getShortName(), c.getId());
        }
    }
}
