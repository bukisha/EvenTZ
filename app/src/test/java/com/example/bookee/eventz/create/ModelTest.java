package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.EventsWebApi;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
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
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModelTest {

    private static final String TEST_URL = "http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png";
    private MvpContract.Model model;
    @Mock
    private RetrofitImageRepository imageRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RetrofitEventsRepository repository = new RetrofitEventsRepository(RetrofitFactory.buildRetrofit().create(EventsWebApi.class));
        model = new Model(repository, imageRepositoryMock);

    }

    @Test
    public void postEvent() {
        //Given
        class ServerAnswerWrapper implements PostEventCallback {

            @Override
            public void onSuccess(String eventId) {
                System.out.println("SUCCESS-FULL CALLBACK!!!!");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FAILED CALLBACK!!!!");
            }
        }
        ServerAnswerWrapper answer = new ServerAnswerWrapper();

        Event event = createTestEvent("Will this work or not",
                "test description of event",
                TEST_URL);

        EventWrapper eventWrapper = new EventWrapper();
        eventWrapper.setEvent(event);
        System.out.println("Event wrapper contains event " + eventWrapper.getEvent());
        //When
        model.postEvent(eventWrapper, answer);
        //Then

    }

    private Event createTestEvent(String eventName, String eventDescription, String logoUrl) {
        System.out.println("creating test Event....");
        Name name = new Name();
        Logo logo = new Logo();
        Start start = new Start();
        End end = new End();
        Description description = new Description();
        description.setHtml(eventDescription);
        name.setHtml(eventName);
        logo.setUrl(logoUrl);


        String timezone;
        timezone = DateTimeZone.getDefault().toString();
        System.out.println("Test timezone is " + timezone);

        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
        DateTime dateTimeUTC = DateTime.now(DateTimeZone.forID("UTC"));
        System.out.println("UTC date time now is " + dateTimeUTC);
        //setting start time in UTC format
        String startUTC = formatter.print(dateTimeUTC.plusHours(5));
        System.out.println("Start time UTC is " + startUTC);
        start.setTimezone(timezone);
        start.setUtc(startUTC);
        //setting end time in UTC format
        DateTime endTime = dateTimeUTC.plusHours(7);
        String endUTC = formatter.print(endTime);
        end.setTimezone(timezone);
        end.setUtc(endUTC);
        System.out.println("End time UTC is " + endUTC);

        //Creating test event to return
        Event event = new Event("EUR", end, name, start);
        event.setListed(true);
        event.setCapacity((long) 3000);
        event.setCategoryId("104");
        event.setOnlineEvent(true);
        event.setShareable(true);


        System.out.println("Event that is cooking for post " + event);
        return event;
    }

}