package com.example.bookee.eventz.data;

import android.content.Context;

import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.Name;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SQLiteDatabaseRepositoryTest {

    private static final String TEST_ID_ZERO = "100";
    private static final String TEST_ID_ONE = "101";
    private static final String TEST_ID_TWO = "102";
    private static final String TEST_NAME_ZERO = "testEventNameZero";
    private static final String TEST_NAME_ONE = "testEventNameOne";
    private static final String TEST_NAME_TWO = "testEventNameTwo";
    private static int TEST_DB_VERSION;

    private SQLiteDatabaseRepository repository;
    private EventsDatabaseHelper testDatabaseHelper;
    @Mock
    private Event eventMockZero;
    @Mock
    private Event eventMockOne;
    @Mock
    private Event eventMockTwo;
    @Mock
    private Name nameMockZero;
    @Mock
    private Name nameMockOne;
    @Mock
    private Name nameMockTwo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Context testContext = RuntimeEnvironment.application.getApplicationContext();
        testDatabaseHelper = EventsDatabaseHelper.getInstance(testContext);
        repository = new SQLiteDatabaseRepository(testDatabaseHelper);
        TEST_DB_VERSION = 1;
    }

    @After
    public void tearDown() {
        testDatabaseHelper.onUpgrade(repository.getDatabase(), TEST_DB_VERSION++, TEST_DB_VERSION);
        repository.closeDataSource();
    }

    @Test
    public void addEvent() {
        //Given
        Mockito.when(eventMockZero.getId()).thenReturn(TEST_ID_ZERO);
        Mockito.when(eventMockZero.getName()).thenReturn(nameMockZero);
        Mockito.when(nameMockZero.getText()).thenReturn(TEST_NAME_ZERO);
        //When
        repository.addEvent(eventMockZero);
        //Then
        String idFromDatabase = repository.getEventIdForName(TEST_NAME_ZERO);
        String nameFromDatabase = repository.getEventNameForId(TEST_ID_ZERO);
        Assert.assertEquals(TEST_NAME_ZERO, nameFromDatabase);
        Assert.assertEquals(TEST_ID_ZERO, idFromDatabase);
    }

    @Test
    public void getSingleEventOrId() {
        //Given
        Mockito.when(eventMockZero.getId()).thenReturn(TEST_ID_ZERO);
        Mockito.when(eventMockZero.getName()).thenReturn(nameMockZero);
        Mockito.when(nameMockZero.getText()).thenReturn(TEST_NAME_ZERO);
        //When
        repository.addEvent(eventMockZero);
        String idFromDatabase = repository.getEventIdForName(TEST_NAME_ZERO);
        String nameFromDatabase = repository.getEventNameForId(TEST_ID_ZERO);
        //Then
        Assert.assertEquals(TEST_NAME_ZERO, nameFromDatabase);
        Assert.assertEquals(TEST_ID_ZERO, idFromDatabase);
    }

    @Test
    public void getListOfIds() {
        //Given
        Mockito.when(eventMockZero.getId()).thenReturn(TEST_ID_ZERO);
        Mockito.when(eventMockZero.getName()).thenReturn(nameMockZero);
        Mockito.when(nameMockZero.getText()).thenReturn(TEST_NAME_ZERO);

        Mockito.when(eventMockOne.getId()).thenReturn(TEST_ID_ONE);
        Mockito.when(eventMockOne.getName()).thenReturn(nameMockOne);
        Mockito.when(nameMockOne.getText()).thenReturn(TEST_NAME_ONE);

        Mockito.when(eventMockTwo.getId()).thenReturn(TEST_ID_TWO);
        Mockito.when(eventMockTwo.getName()).thenReturn(nameMockTwo);
        Mockito.when(nameMockTwo.getText()).thenReturn(TEST_NAME_TWO);
        repository.addEvent(eventMockZero);
        repository.addEvent(eventMockOne);
        repository.addEvent(eventMockTwo);
        //When
        List<String> eventIds = repository.getEventsIds();
        //Then
        Assert.assertEquals(TEST_ID_ZERO, eventIds.get(0));
        Assert.assertEquals(TEST_ID_ONE, eventIds.get(1));
        Assert.assertEquals(TEST_ID_TWO, eventIds.get(2));
    }
    //Possibly test closing of data source in thsi case it is SQLite database....but how...
    @Test
    public void removeEventWithId() {
        //Given
        Mockito.when(eventMockZero.getId()).thenReturn(TEST_ID_ZERO);
        Mockito.when(eventMockZero.getName()).thenReturn(nameMockZero);
        Mockito.when(nameMockZero.getText()).thenReturn(TEST_NAME_ZERO);
        repository.addEvent(eventMockZero);
        //When
        repository.removeEventWithId(eventMockZero.getId());
        //Then
        Assert.assertNull(repository.getEventNameForId(eventMockZero.getId()));
    }
}