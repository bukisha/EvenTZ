package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.Description;
import com.example.bookee.eventz.data.End;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.EventWrapper;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.Logo;
import com.example.bookee.eventz.data.Name;
import com.example.bookee.eventz.data.ResponseWrapper;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.Start;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ModelTest {

    private MvpContract.Model model;
    private RetrofitEventsRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new RetrofitEventsRepository(RetrofitFactory.buildRetrofit().create(EventsWebApi.class));
        model = new Model(repository);

    }

    @Test
    public void postEvent() {
        //Given
        class ServerAnswerWrapper implements MvpContract.PostEventCallback {
            private ResponseWrapper createdEvent;
            private boolean operationSuccess;

            @Override
            public void onSuccess(ResponseWrapper e) {
                System.out.println("SUCCESS-FULL CALLBACK!!!!");
                createdEvent = e;
                operationSuccess = true;

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FAILED CALLBACK!!!!");
                createdEvent = null;
                operationSuccess = false;

            }

            public ResponseWrapper getEvent() {
                return createdEvent;
            }

            public boolean isOperationSuccess() {
                return operationSuccess;
            }
        }

        ServerAnswerWrapper answerWrapper = new ServerAnswerWrapper();
//        String timezone;
//        timezone = DateTimeZone.getDefault().toString();
//        timezone = timezone.substring(0, timezone.indexOf("/") + 1);
//        timezone = timezone.concat("Belgrade");
//        DateTime date = DateTime.now(DateTimeZone.forID(timezone));
//        DateTime dateUTC = DateTime.now(DateTimeZone.forID("UTC"));
//
//        System.out.println("my timezone is " + timezone + " " + date.toString() + " " + dateUTC.toString());
        Event event = createTestEvent("dalceovodaproradigovno",
                "nebitno",
                null,
                "mojtest",
                "http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png");
        //System.out.println("Created event : "+event.toString());
        EventWrapper eventWrapper=new EventWrapper();
        eventWrapper.setEvent(event);
        System.out.println("Event wrapper sadrzi u sebi "+ eventWrapper.getEvent());
        model.postEvent(eventWrapper, answerWrapper);

        //Then
        System.out.println("\nServer response is: "+answerWrapper.getEvent());
    }

    private Event createTestEvent(String eventName, String cityName, Date date, String eventDescription, String logoUrl) {
        Event event = new Event();
        Name name = new Name();
        Logo logo = new Logo();
        Start start = new Start();
        End end = new End();
        Description description = new Description();
        description.setHtml(eventDescription);
        name.setHtml(eventName);
        event.setName(name);
        logo.setUrl(logoUrl);
        event.setLogo(logo);

        String timezone;
        timezone = DateTimeZone.getDefault().toString();
        timezone = timezone.substring(0, timezone.indexOf("/") + 1);
        timezone = timezone.concat("Belgrade");
        DateTime dateTime = DateTime.now(DateTimeZone.forID(timezone));
        DateTime startTime=dateTime.plusHours(5);
        DateTime dateTimeUTC = DateTime.now(DateTimeZone.forID("UTC"));
        dateTimeUTC=dateTimeUTC.plusHours(5);
        start.setTimezone(timezone);
        start.setLocal(startTime.toString());
        start.setUtc(dateTimeUTC.toString());

        end.setTimezone(timezone);
        DateTime endTime = startTime.plusHours(2);
        end.setLocal(endTime.toString());
        end.setUtc(dateTimeUTC.plusHours(2).toString());


        event.setStart(start);
        event.setEnd(end);
        event.setCurrency("EUR");
        event.setDescription(description);
        event.setStatus("live");
        event.setListed(true);

        return event;
    }

}