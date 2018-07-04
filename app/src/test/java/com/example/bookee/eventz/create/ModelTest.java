package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.pojos.Description;
import com.example.bookee.eventz.data.pojos.End;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.pojos.Logo;
import com.example.bookee.eventz.data.pojos.Name;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.pojos.Start;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ModelTest {

    private MvpContract.Model model;

    @Before
    public void setUp() throws Exception {
        RetrofitEventsRepository repository = new RetrofitEventsRepository(RetrofitFactory.buildRetrofit().create(EventsWebApi.class));
        model = new Model(repository);

    }

    @Test
    public void postEvent() {
        //Given
        class ServerAnswerWrapper implements MvpContract.PostEventCallback {
            private Event createdEvent;
            private boolean operationSuccess;

            @Override
            public void onSuccess(Event e) {
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

            public Event getEvent() {
                return createdEvent;
            }

            public boolean isOperationSuccess() {
                return operationSuccess;
            }
        }

         ServerAnswerWrapper answer=new ServerAnswerWrapper();

        Event event = createTestEvent("dalceovodaproradigovno",
                "nebitno",
                null,
                "mojtest",
                "http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png");

        EventWrapper eventWrapper=new EventWrapper();
        eventWrapper.setEvent(event);
        System.out.println("Event wrapper sadrzi u sebi "+ eventWrapper.getEvent());
        //When
        model.postEvent(event, answer );


        //Then

        System.out.println("\nServer response is: "+answer.getEvent());
        Assert.assertNotEquals(answer.getEvent(),null);
    }

    private Event createTestEvent(String eventName, String cityName, Date date, String eventDescription, String logoUrl) {

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
        Event event = new Event("EUR",end,name,start);

//        Event.setStart(start);
//        Event.setEnd(end);
//        Event.setCurrency("EUR");
//        Event.setDescription(description);
//        Event.setStatus("live");
//        Event.setListed(true);
//        Event.setCapacity((long) 3000);
//        Event.setCategoryId("104");
//        Event.setOnlineEvent(true);
//        Event.setShareable(true);
        return event;
    }

}