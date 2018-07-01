package com.example.bookee.eventz.details;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.bookee.eventz.data.End;
import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.Name;
import com.example.bookee.eventz.data.Start;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class EventsDatabaseHelperTest {
    private static final String DELETE_EVENT_ID = "1";
    private static final String GET_EVENT_ID = "2";
    private static final String TEST_EVENT_ZERO = "eventZero";
    private static final String TEST_EVENT_ONE = "eventOne";
    private static final String TEST_EVENT_TWO = "eventTwo";

    private Event eventZero, eventOne, eventTwo;
    private static int dbVersion = 1;
    private static int globalEventId = 0;
    private EventsDatabaseHelper eventsDatabaseHelperTest;

    @Before
    public void setUp()  {
        Context context = InstrumentationRegistry.getTargetContext();
        eventsDatabaseHelperTest = EventsDatabaseHelper.getInstance(context);
        eventZero = createTestEvent(TEST_EVENT_ZERO, globalEventId++);
        eventOne = createTestEvent(TEST_EVENT_ONE,  globalEventId++);
        eventTwo = createTestEvent(TEST_EVENT_TWO,  globalEventId++);
    }

    @After
    public void tearDown() {
        eventsDatabaseHelperTest.onUpgrade(eventsDatabaseHelperTest.getReadableDatabase(), dbVersion, ++dbVersion);
        globalEventId=0;
    }

    @Test
    public void insertAndGetEvent() {
        //Given

        //When
        eventsDatabaseHelperTest.addEvent(eventZero);
        eventsDatabaseHelperTest.addEvent(eventOne);
        eventsDatabaseHelperTest.addEvent(eventTwo);
        eventsDatabaseHelperTest.deleteRowWithId(eventOne.getId());
        //Than
        Assert.assertEquals(eventTwo.getName().getText(), eventsDatabaseHelperTest.getEventNameForId(GET_EVENT_ID));
        Assert.assertNull(eventsDatabaseHelperTest.getEventNameForId(DELETE_EVENT_ID));
    }

    @Test
    public void getFollowedEventIds() {
        //Given
        List<String> resultList;
        eventsDatabaseHelperTest.addEvent(eventZero);
        eventsDatabaseHelperTest.addEvent(eventOne);
        eventsDatabaseHelperTest.addEvent(eventTwo);
        //When
        resultList = eventsDatabaseHelperTest.getAllFollowedEventsIds();
        //Than
        Assert.assertEquals("0", resultList.get(0));
        Assert.assertEquals("1", resultList.get(1));
        Assert.assertEquals("2", resultList.get(2));
    }

    private Event createTestEvent(String eventName, int eventId) {
        System.out.println("creating test Event....");

        Name name = new Name();
        Start start = new Start();
        End end = new End();
        name.setHtml(eventName);
        name.setText(eventName);

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
        event.setId(String.valueOf(eventId));

        return event;
    }
}