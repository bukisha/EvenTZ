package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.Logo;
import com.example.bookee.eventz.data.Name;
import com.example.bookee.eventz.data.Start;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    public Presenter(MvpContract.Model model, MvpContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void postEvent(String eventName, String cityName, Date date, String description, String logoUrl) {
        Event event = createEvent(eventName, cityName, date, description, logoUrl);
        model.postEvent(event);
    }

    private Event createEvent(String eventName, String cityName, Date date, String description, String logoUrl) {
        Event event = new Event();
        Name name = new Name();
        Logo logo = new Logo();
        Start start = new Start();

        name.setText(eventName);
        event.setName(name);
        logo.setUrl(logoUrl);
        event.setLogo(logo);
        start.setTimezone(prepareTimezone(cityName));
        start.setLocal(prepareDate(date));
        event.setStart(start);

        return event;

    }

    private String prepareDate(Date date) {
        TimeZone localTimeZone = TimeZone.getDefault();
        DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleFormat.setTimeZone(localTimeZone);
        String preparedDate = simpleFormat.format(date);
        preparedDate = preparedDate.replace(" ", "T");
        return preparedDate;
    }

    private String prepareTimezone(String cityName) {
        String arrangedTimezone = TimeZone.getDefault().getID();
        arrangedTimezone = arrangedTimezone.substring(0, arrangedTimezone.indexOf("/") + 1);
        arrangedTimezone = arrangedTimezone.concat(cityName);
        return arrangedTimezone;
    }

}
