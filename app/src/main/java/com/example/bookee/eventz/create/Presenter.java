package com.example.bookee.eventz.create;

import android.util.Log;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    private HashMap<String, String> shortNameToIdHash;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
        currentWrapper = new EventWrapper();
        currentEvent = new Event();
        shortNameToIdHash = new HashMap<>(20);
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

    private String getCategoryId(String categoryShortName) {
        Log.d(TAG, "getCategoryId: u selected category with name " + categoryShortName + " and id " + shortNameToIdHash.get(categoryShortName));
        return shortNameToIdHash.get(categoryShortName);
    }

    @Override
    public void postEvent() {
        prepareEventDateAndTime();
        setAdditionalEventProperties();

        currentWrapper.setEvent(currentEvent);
        PostEventCallback callback = new PostEventCallback() {
            @Override
            public void onSuccess(String eventId) {
                Log.d(TAG, "onSuccess: before creating tickets");
                view.displayNewEvent(eventId);
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
        
        String currentTimezone=DateTime.now().getZone().toString();
        Log.d(TAG, "prepareEventDateAndTime: currentDateTimeZone in UTC is "+currentTimezone);
        date = date.toDateTime(DateTimeZone.UTC);
        DateTimeFormatter format = ISODateTimeFormat.dateTimeNoMillis();

        End end = new End();
        end.setTimezone(currentTimezone);
        end.setUtc(format.print(date.plusHours(4).toDateTimeISO()));
        currentEvent.setEnd(end);
        Start start = new Start();
        start.setTimezone(currentTimezone);
        start.setUtc(date.toDateTimeISO().toString(format));
        currentEvent.setStart(start);
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
        if (notViewExists()) return;
        view.pickImage();
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
            shortNameToIdHash.put(c.getShortName(), c.getId());
        }
    }
}
