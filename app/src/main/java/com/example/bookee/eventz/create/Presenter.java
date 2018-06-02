package com.example.bookee.eventz.create;

import android.util.Log;

import com.example.bookee.eventz.data.Description;
import com.example.bookee.eventz.data.End;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.Logo;
import com.example.bookee.eventz.data.Name;
import com.example.bookee.eventz.data.Start;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    private MvpContract.Model model;
    private MvpContract.View view;
    private Date dateSelected;
    private String timeSelected;
    private Date endDate;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void postEvent(String eventName, String cityName, String description, String logoUrl) {
        Log.d(TAG, "postEvent: " + eventName + " creating " + dateSelected.toString());
        Event event = createEvent(eventName, cityName, dateSelected, description, logoUrl);

        MvpContract.PostEventCallback callback = new MvpContract.PostEventCallback() {
            @Override
            public void onSuccess(Event e) {
                if (notViewExists()) return;
                view.displayNewEvent(e);
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO
            }
        };
        model.postEvent(event, callback);
    }

    private Event createEvent(String eventName, String cityName, Date date, String eventDescription, String logoUrl) {
        Event event = new Event();
        Name name = new Name();
        Logo logo = new Logo();
        Start start = new Start();
        End end = new End();
        Description description = new Description();
        description.setHtml(convertDescriptionToHTML(eventDescription));
        name.setHtml(eventName);
        event.setName(name);
        logo.setUrl(logoUrl);
        event.setLogo(logo);

        start.setTimezone(prepareTimezone(cityName));
        start.setLocal(prepareDate(date));
        start.setUtc(prepareDate(date));

        end.setTimezone(prepareTimezone(cityName));
        end.setLocal(prepareEndDate(endDate));
        end.setUtc(prepareEndDate(endDate));

        event.setStart(start);
        event.setEnd(end);
        event.setCurrency("EUR");
        event.setDescription(description);
        Log.d(TAG, "createEvent: " + event.toString());
        return event;

    }

    private String convertDescriptionToHTML(String eventDescription) {
        String returnDescription=eventDescription;

        return returnDescription;
    }

    private String prepareEndDate(Date date) {
        Log.d(TAG, "prepareDate: ");
        TimeZone localTimeZone = TimeZone.getDefault();

        DateFormat simpleFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleFormat.setTimeZone(localTimeZone);
        String preparedEndDate = simpleFormat.format(date);
        preparedEndDate = preparedEndDate.replace(" ", "T");
        return preparedEndDate;
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

    }

    @Override
    public void setTime(int hour, int min) {

    }

    @Override
    public void setDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, 0, 0);
        dateSelected = c.getTime();
        //================creating end date for development purposes only========================
        c.add(Calendar.HOUR_OF_DAY, 3);
        endDate = c.getTime();
        Log.d(TAG, "setDate: " + dateSelected.toString());
    }

    private String prepareDate(Date date) {
        Log.d(TAG, "prepareDate: ");
        TimeZone localTimeZone = TimeZone.getDefault();

        DateFormat simpleFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleFormat.setTimeZone(localTimeZone);
        String preparedDate = simpleFormat.format(date);
        preparedDate = preparedDate.replace(" ", "T");
        preparedDate = preparedDate.concat("Z");
        return preparedDate;
    }

    private String prepareTimezone(String cityName) {
        String arrangedTimezone = TimeZone.getDefault().getID();
        arrangedTimezone = arrangedTimezone.substring(0, arrangedTimezone.indexOf("/") + 1);
        arrangedTimezone = arrangedTimezone.concat(cityName);
        return arrangedTimezone;
    }

    private boolean notViewExists() {
        return this.view == null;
    }
}
