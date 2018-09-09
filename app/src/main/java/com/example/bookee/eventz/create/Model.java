package com.example.bookee.eventz.create;

import android.util.Log;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitImageRepository;
import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.Description;
import com.example.bookee.eventz.data.pojos.End;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.Logo;
import com.example.bookee.eventz.data.pojos.Name;
import com.example.bookee.eventz.data.pojos.Start;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

class Model implements MvpContract.Model {
    private static final String TAG = "Model";
    private static final String CURRENCY_EUR = "EUR";
    private RetrofitEventsRepository eventsRepository;
    private RetrofitImageRepository imageRepository;

    private Event currentEvent;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    private int selectedHourOfDay;
    private int selectedMinuteOfHour;
    private File currentImageFile;

    private HashMap<String, String> shortNameToIdHash;

    Model(RetrofitEventsRepository eventsRepository, RetrofitImageRepository imageRepository) {
        this.eventsRepository = eventsRepository;
        this.imageRepository = imageRepository;
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
    public void setLogo(File imageFile) {
        currentImageFile = imageFile;
    }

    @Override
    public void setCategoryId(String shortName) {
        currentEvent.setCategoryId(getCategoryId(shortName));
    }

    @Override
    public String getCategoryId(String categoryShortName) {
        Log.d(TAG, "getCategoryId: u selected category with name " + categoryShortName + " and id " + shortNameToIdHash.get(categoryShortName));
        return shortNameToIdHash.get(categoryShortName);
    }

    @Override
    public void setAdditionalEventProperties() {
        currentEvent.setListed(true);
        currentEvent.setCapacity((long) 100);
        currentEvent.setCurrency(CURRENCY_EUR);
    }

    @Override
    public void prepareEventDateAndTime() {
        DateTime date = new DateTime()
                .withYear(selectedYear)
                .withMonthOfYear(selectedMonth)
                .withDayOfMonth(selectedDay)
                .withHourOfDay(selectedHourOfDay)
                .withMinuteOfHour(selectedMinuteOfHour);

        String currentTimezone = DateTime.now().getZone().toString();
        Log.d(TAG, "prepareEventDateAndTime: currentDateTimeZone in UTC is " + currentTimezone);
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

    public void setHashMapWithShortNames(ArrayList<Category> categories) {
        for (Category c : categories) {
            shortNameToIdHash.put(c.getShortName(), c.getId());
        }
    }

    @Override
    public void postEvent(final PostEventCallback callback) {
        prepareEventDateAndTime();
        setAdditionalEventProperties();
        if (currentImageFile != null) {
           uploadLogo(currentImageFile, new EndUploadImageCallback() {
               @Override
               public void onSuccess(Logo logo) {
                   currentEvent.setLogoId(logo.getId());
                   EventWrapper wrapperForSending=new EventWrapper();
                   wrapperForSending.setEvent(currentEvent);
                   eventsRepository.postNewEvent(wrapperForSending, new PostEventCallback() {
                       @Override
                       public void onSuccess(String eventId) {
                           callback.onSuccess(eventId);
                       }

                       @Override
                       public void onFailure(Throwable t) {
                           callback.onFailure(t);
                       }
                   });
               }

               @Override
               public void onFailure(Throwable t) {
                  //TODO signal that image upload failed
               }
           });
        } else {
            EventWrapper wrapperForSending=new EventWrapper();
            wrapperForSending.setEvent(currentEvent);
            eventsRepository.postNewEvent(wrapperForSending, new PostEventCallback() {
                @Override
                public void onSuccess(String eventId) {
                    callback.onSuccess(eventId);
                }

                @Override
                public void onFailure(Throwable t) {
                    callback.onFailure(t);
                }
            });

        }
    }

    @Override
    public void uploadLogo(final File currentImageFile, final EndUploadImageCallback endUploadImageCallback) {
        imageRepository.uploadImage(currentImageFile, endUploadImageCallback);
    }
}
