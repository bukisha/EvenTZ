package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Description;
import com.example.bookee.eventz.data.pojos.End;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.Logo;
import com.example.bookee.eventz.data.pojos.Name;
import com.example.bookee.eventz.data.pojos.Start;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class RetrofitEventsRepositoryTest {
    private static final String TAG = "RetrofitEventRepoTest";
    private static final String TEST_CATEGORY_ID = "102";
    private static final String TEST_EVENT_ID = "34163526026";
    private static final String TEST_EVENT_LOGO_URL = "http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png";
    private RetrofitEventsRepository repository;

    @Mock
    private FetchEventsForCategoryCallback fetchEventsForCategoryCallbackMock;
    @Mock
    private FetchEventForIdCallback fetchEventForIdCallbackMock;
    @Mock
    private PostEventCallback postEventCallbackMock;
    @Mock
    private Event eventMock;

    @Before
    public void setUp() throws Exception {
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
        Event testEvent = createTestEvent("TEST EVENT", "TEST DESCRIPTION", TEST_EVENT_LOGO_URL);
        //When
        repository.postNewEvent(testEvent, postEventCallbackMock);
        //Then
        verify(postEventCallbackMock, timeout(5000)).onSuccess(any(Event.class));
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
        event.setListed(true);
        event.setCapacity((long) 3000);
        event.setCategoryId("104");
        event.setOnlineEvent(true);
        event.setShareable(true);


        return event;
    }
}