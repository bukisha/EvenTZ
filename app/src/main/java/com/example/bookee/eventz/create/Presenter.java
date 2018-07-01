package com.example.bookee.eventz.create;

import android.util.Log;

import com.example.bookee.eventz.data.Description;
import com.example.bookee.eventz.data.End;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.Logo;
import com.example.bookee.eventz.data.Name;
import com.example.bookee.eventz.data.Start;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Presenter implements MvpContract.Presenter {
    private static final String TAG = "Presenter";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
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

        Name name = new Name();
        Logo logo = new Logo();
        Start start = new Start();
        End end = new End();
        Description description = new Description();
        description.setHtml(convertDescriptionToHTML(eventDescription));
        name.setHtml(eventName);
        logo.setUrl(logoUrl);


        start.setTimezone(prepareTimezone("Belgrade"));
        start.setLocal(prepareLocalDate(date));
        start.setUtc(prepareLocalDateUTC(date));

        end.setTimezone(prepareTimezone("Belgrade"));
        end.setLocal(prepareLocalDate(endDate));
        end.setUtc(prepareLocalDateUTC(endDate));
        Event event = new Event("EUR", end, name, start);

        Log.d(TAG, "createEvent: " + event.toString());
        return event;
        }

    private String prepareTimezone(String cityName) {
        Log.d(TAG, "prepareTimezone: start");
        DateTimeZone timeZone = DateTimeZone.getDefault();//DateTimeZone.forID("Europe/" + cityName);
        String returnTimezone = timeZone.toString();
        Log.d(TAG, "prepareTimezone: arangedTimeZone " + returnTimezone);
        Log.d(TAG, "prepareTimezone: arangedTimeZone before return " + returnTimezone);
        return returnTimezone;
    }

    private String prepareLocalDate(Date date) {
        Log.d(TAG, "prepareLocalDate: ");
        TimeZone localTimeZone = TimeZone.getTimeZone("Europe/Belgrade");
        Log.d(TAG, "prepareLocalDate: default local timezone is " + localTimeZone);

        DateTime localTimeUTC = new DateTime(date);
        Log.d(TAG, "prepareLocalDate: local time in UTC is " + localTimeUTC);

        int currentTimeZoneOffset = localTimeZone.getOffset(new Date().getTime()) / 1000 / 60;
        DateTime localTimeGMT = localTimeUTC.plusMinutes(currentTimeZoneOffset);
        Log.d(TAG, "prepareLocalDate: local time in GMT is " + localTimeGMT);
        Log.d(TAG, "prepareLocalDate: =============================================================================================");

        String returnDate = localTimeGMT.toString();
        returnDate = returnDate.substring(0, returnDate.indexOf("Z") - 1);
        return returnDate;
    }

    private String prepareLocalDateUTC(Date date) {
        Log.d(TAG, "prepareLocalDate: ");
        TimeZone localTimeZone = TimeZone.getTimeZone("Europe/Belgrade");
        Log.d(TAG, "prepareLocalDate: default local timezone is " + localTimeZone);

        DateTime localTimeUTC = new DateTime(date);
        Log.d(TAG, "prepareLocalDate: local time in UTC is " + localTimeUTC);

        return localTimeUTC.toString();
    }

    private String convertDescriptionToHTML(String eventDescription) {
        String returnDescription = eventDescription;
        return returnDescription;
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
        //TODO set start Time for Event
    }

    @Override
    public void setDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        Log.d(TAG, "setDate: currentDate is " + c.getTime().toString());
        c.set(year, month, day, 0, 0);
        dateSelected = c.getTime();
        //================creating end date for development purposes only========================
        c.add(Calendar.HOUR_OF_DAY, 3);
        endDate = c.getTime();
        Log.d(TAG, "setDate: " + dateSelected.toString());
    }

    private boolean notViewExists() {
        return this.view == null;
    }
}
