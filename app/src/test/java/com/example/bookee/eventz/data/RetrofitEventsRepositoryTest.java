package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.PostEventCallback;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class RetrofitEventsRepositoryTest {
    private static final String TAG = "RetrofitEventRepoTest";
    private RetrofitEventsRepository repository;
    private Event responseEvent;

    @Before
    public void setUp() throws Exception {
        repository = new RetrofitEventsRepository(RetrofitFactory.buildRetrofit().create(EventsWebApi.class));
    }

    @Test
    public void postNewEvent() {
        //Given
        Event event = createTestEvent("testEvent", "Belgrade", null, "test test test", null);

        System.out.println("posting test Event....");

        //When
        repository.postNewEvent(event, new PostEventCallback() {
            @Override
            public void onSuccess(Event e) {
                responseEvent = e;
                System.out.println("Fetched Event is " + e);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("error while fetching Event for id" + t.getMessage());
            }
        });
        //Than
        Assert.assertNotEquals(null, responseEvent);

        System.out.println("Fetched Event is " + responseEvent);

    }

    private Event createTestEvent(String eventName, String cityName, Date date, String eventDescription, String logoUrl) {
        System.out.println("creating test Event....");
        Name name = new Name();
        Logo logo = new Logo();
        Start start = new Start();
        End end = new End();
        Description description = new Description();
        description.setHtml(eventDescription);
        name.setHtml(eventName);
        // Event.setName(name);
        logo.setUrl(logoUrl);
        // Event.setLogo(logo);


        String timezone;
        timezone = DateTimeZone.getDefault().toString();
        timezone = timezone.substring(0, timezone.indexOf("/") + 1);
        timezone = timezone.concat("Belgrade");
        DateTime dateTime = DateTime.now(DateTimeZone.forID(timezone));
        DateTime startTime = dateTime.plusHours(5);
        DateTime dateTimeUTC = DateTime.now(DateTimeZone.forID("UTC"));
        dateTimeUTC = dateTimeUTC.plusHours(5);
        start.setTimezone(timezone);
        start.setLocal(startTime.toString());
        start.setUtc(dateTimeUTC.toString());

        end.setTimezone(timezone);
        DateTime endTime = startTime.plusHours(2);
        end.setLocal(endTime.toString());
        end.setUtc(dateTimeUTC.plusHours(2).toString());
        Event event = new Event("EUR", end, name, start);


        return event;
    }
}