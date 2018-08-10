package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.Description;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.Logo;
import com.example.bookee.eventz.data.pojos.Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PresenterTest {

    private static final String TEST_EVENT_NAME = "test event name";
    private static final String TEST_EVENT_DESCRIPTION = "test description of my event";
    private static final String TEST_EVENT_URL = "http://memoryboundscrapbookstore.com/wordpress/wp-content/uploads/2016/02/lets-party.png";
    private static final int TEST_MONTH_NUMBER = 11;
    private static final int TEST_DAY_NUMBER = 11;
    private static final int TEST_YEAR_NUMBER = 2018;
    private static final int TEST_MIN = 15;
    private static final int TEST_HOUR = 20;
    private static final int NUMBER_OF_CATEGORIES_IN_LIST = 5;
    private Presenter presenter;

    @Mock
    private MvpContract.Model modelMock;
    @Mock
    private MvpContract.View viewMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(modelMock, viewMock);
    }

    @Test
    public void postEvent() {
        //Given
        EventWrapper testEventWrapper = new EventWrapper();
        Event testEvent = createTestEvent();
        testEventWrapper.setEvent(testEvent);
        presenter.setName(testEvent.getName().getHtml());
        presenter.setDescription(testEvent.getDescription().getHtml());
        presenter.setDate(TEST_YEAR_NUMBER, TEST_MONTH_NUMBER, TEST_DAY_NUMBER);
        presenter.setTime(TEST_HOUR, TEST_MIN);
        presenter.setCategory(testEvent.getCategoryId());
        presenter.setCurrency(testEvent.getCurrency());
        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                PostEventCallback callback = invocation.getArgument(1);
                callback.onSuccess(anyString());
                return null;
            }
        }).when(modelMock).postEvent(any(EventWrapper.class), any(PostEventCallback.class));
        //When
        presenter.postEvent();
        //Than

        verify(modelMock, times(1)).postEvent(any(EventWrapper.class), any(PostEventCallback.class));
        verify(viewMock).displayNewEvent(anyString());
    }

    @Test
    public void startDateChooser() {
        //When
        presenter.startDateChooser();
        //Than
        viewMock.showDateChooser();
    }

    @Test
    public void startTimeChooser() {
        //When
        presenter.startTimeChooser();
        //Than
        viewMock.showTimeChooser();
    }

    @Test
    public void startImageChooser() {
        //When
        presenter.startImageChooser();
        //Than
        viewMock.pickImage();
    }

    @Test
    public void setHashMapWithShortNames() throws InvocationTargetException, IllegalAccessException {
        //Given

        ArrayList<Category> testCategoriesList = createTestCategoriesList(NUMBER_OF_CATEGORIES_IN_LIST);
        //When
        presenter.setHashMapWithShortNames(testCategoriesList);
        //Than
        for (int i = 0; i < NUMBER_OF_CATEGORIES_IN_LIST; i++) {
            Assert.assertEquals(callGetCategoryId(presenter, "short" + i), String.valueOf(i));
        }
    }

    private String callGetCategoryId(Presenter presenter, String argument) throws InvocationTargetException, IllegalAccessException {
        //using reflections for the first time xD
        Method getCategoryIdMethod = null;
        try {
            getCategoryIdMethod = presenter.getClass().getDeclaredMethod("getCategoryId", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (getCategoryIdMethod != null) {
            getCategoryIdMethod.setAccessible(true);
            return (String) getCategoryIdMethod.invoke(presenter, argument);
        }
        return "error";
    }

    private ArrayList<Category> createTestCategoriesList(int numOfItems) {
        ArrayList<Category> list = new ArrayList<>();
        for (int i = 0; i < numOfItems; i++) {
            Category category = new Category();
            category.setId(String.valueOf(i));
            category.setShortName("short" + i);
            list.add(category);
        }

        return list;
    }

    private Event createTestEvent() {
        System.out.println("creating test Event....");
        Name name = new Name();
        Logo logo = new Logo();
        Description description = new Description();
        description.setHtml(TEST_EVENT_DESCRIPTION);
        name.setHtml(TEST_EVENT_NAME);
        logo.setUrl(TEST_EVENT_URL);

        //Creating test event to return
        Event event = new Event();
        event.setCurrency("EUR");
        event.setListed(true);
        event.setCapacity((long) 3000);
        event.setCategoryId("104");
        event.setOnlineEvent(true);
        event.setShareable(true);
        event.setName(name);
        event.setDescription(description);
        event.setLogo(logo);

        System.out.println("Event that is cooking for post " + event);
        return event;
    }
}