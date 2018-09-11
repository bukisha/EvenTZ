package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Category;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PresenterTest {
    private static final int TEST_HOUR = 3;
    private static final int TEST_MIN = 33;
    private static final int TEST_YEAR = 2019;
    private static final int TEST_MONTH = 10;
    private static final int TEST_DAY = 11;
    private static final String SHORT_CAT_NAME = "CAT";
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
    public void startDateChooser()  {
        //When
        presenter.startDateChooser();
        //Then
        verify(viewMock).showDateChooser();
    }

    @Test
    public void postEvent() {
        //Given
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                PostEventCallback callback=invocation.getArgument(0);
                callback.onSuccess(anyString());
                return null;
            }
        }).when(modelMock).postEvent(any(PostEventCallback.class));
        //When
        presenter.postEvent();
        //Than

        verify(modelMock, times(1)).postEvent(any(PostEventCallback.class));
        verify(viewMock).displayNewEvent(anyString());
    }

    @Test
    public void startTimeChooser() {
        //When
        presenter.startTimeChooser();
        //Then
        verify(viewMock).showTimeChooser();
        //Than
        viewMock.showTimeChooser();
    }

    @Test
    public void startImageChooser() {
        //When
        presenter.startImageChooser();
        //Then
        verify(viewMock).pickImage();
    }

    @Test
    public void setTime() {
        //When
        presenter.setTime(TEST_HOUR, TEST_MIN);
        //Then
        verify(modelMock).setTime(anyInt(),anyInt());

    }

    @Test
    public void setDate()  {
        //When
        presenter.setDate(TEST_YEAR, TEST_MONTH, TEST_DAY);
        //Then
        verify(modelMock).setDate(anyInt(),anyInt(),anyInt());
    }

    @Test
    public void setShortNamesHashMap()  {
        //Given
        ArrayList<Category> categories = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Category c = new Category();
            c.setShortName(SHORT_CAT_NAME + String.valueOf(i));
            c.setId(String.valueOf(i));
            categories.add(c);
        }
        //When
        presenter.setHashMapWithShortNames(categories);
        //Then
        verify(modelMock).setHashMapWithShortNames(categories);
    }
//          10.9.2018.       this piece of code might be use full latter in some tests
//    private Event createTestEvent() {
//        System.out.println("creating test Event....");
//        Name name = new Name();
//        Logo logo = new Logo();
//        Description description = new Description();
//        description.setHtml(TEST_EVENT_DESCRIPTION);
//        name.setHtml(TEST_EVENT_NAME);
//        logo.setUrl(TEST_EVENT_URL);
//
//        //Creating test event to return
//        Event event = new Event();
//        event.setCurrency("EUR");
//        event.setListed(true);
//        event.setCapacity((long) 3000);
//        event.setCategoryId("104");
//        event.setOnlineEvent(true);
//        event.setShareable(true);
//        event.setName(name);
//        event.setDescription(description);
//        event.setLogo(logo);
//
//        System.out.println("Event that is cooking for post " + event);
//        return event;
//    }
}

