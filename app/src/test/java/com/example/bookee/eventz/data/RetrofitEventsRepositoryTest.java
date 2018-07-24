package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
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
import org.joda.time.tz.DateTimeZoneBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class RetrofitEventsRepositoryTest {

    private static final String TEST_CATEGORY_ID = "102";
    private static final String TEST_EVENT_ID = "34163526026";
    private static final String TEST_EVENT_LOGO_URL = "http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png";
    private static final String TEST_EVENT_NAME = "TEST EVENT";
    private static final String TEST_EVENT_DESCRIPTION = "TEST DESCRIPTION";
    private RetrofitEventsRepository repository;

    @Mock
    private FetchEventsForCategoryCallback fetchEventsForCategoryCallbackMock;
    @Mock
    private FetchEventForIdCallback fetchEventForIdCallbackMock;
    @Mock
    private PostEventCallback postEventCallbackMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new RetrofitEventsRepository(RetrofitFactory.buildRetrofit().create(EventsWebApi.class));
    }

    @Test
    public void fetchEventsForCategory() {
        //When
        repository.fetchEventsForCategory(TEST_CATEGORY_ID, fetchEventsForCategoryCallbackMock);
        //Then
        //timeout was needed so the thread that executes fetch can finish its job
        verify(fetchEventsForCategoryCallbackMock, timeout(4000)).onSuccess(any(ArrayList.class));
    }

    @Test
    public void fetchEventForId() {
        //Given

        //When
        repository.fetchEventForId(TEST_EVENT_ID, fetchEventForIdCallbackMock);
        //Then
        verify(fetchEventForIdCallbackMock, timeout(4000)).onSuccess(any(Event.class));
    }

    @Test
    public void postEvent() {
        //Given
        Event testEvent = createTestEvent(TEST_EVENT_NAME, TEST_EVENT_DESCRIPTION, TEST_EVENT_LOGO_URL);

        EventWrapper eventWrapper = new EventWrapper();
        eventWrapper.setEvent(testEvent);
        System.out.println("Event that i want to post : " + eventWrapper);
        //When
        repository.postNewEvent(eventWrapper, postEventCallbackMock);
        //Then
        verify(postEventCallbackMock, timeout(5000)).onSuccess(anyString());
    }

    @Test
    public void testTimezoneCreation() {
        //Given
        DateTimeZone dateTimeZone=DateTime.now().getZone();
        //When

        //Then
        System.out.println("Current time zone is :" +dateTimeZone.toString());


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