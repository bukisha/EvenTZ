package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

public class PresenterTest {
    private static final int TEST_HOUR = 3 ;
    private static final int TEST_MIN =33 ;
    private static final int TEST_YEAR = 2019;
    private static final int TEST_MONTH = 10;
    private static final int TEST_DAY = 11;
    private static final String SHORT_CAT_NAME ="CAT";
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
    public void startDateChooser() {
        //When
        presenter.startDateChooser();
        //Then
        Mockito.verify(viewMock).showDateChooser();
    }

    @Test
    public void startTimeChooser() {
        //When
        presenter.startTimeChooser();
        //Then
        Mockito.verify(viewMock).showTimeChooser();
    }

    @Test
    public void startImageChooser() {
        //When
        presenter.startImageChooser();
        //Then
        Mockito.verify(viewMock).pickImage();
    }

    @Test
    public void setTime() throws NoSuchFieldException, IllegalAccessException {
        //Given
        Field hourField=presenter.getClass().getDeclaredField("selectedHourOfDay");
        hourField.setAccessible(true);
        Field minutesField=presenter.getClass().getDeclaredField("selectedMinuteOfHour");
        minutesField.setAccessible(true);
        //When
        presenter.setTime(TEST_HOUR,TEST_MIN);
        //Then
        Assert.assertEquals(TEST_HOUR,hourField.getInt(presenter));
        Assert.assertEquals(TEST_MIN,minutesField.getInt(presenter));
    }

    @Test
    public void setDate() throws NoSuchFieldException, IllegalAccessException {
        //Given
        Field currentYear=presenter.getClass().getDeclaredField("selectedYear");
        currentYear.setAccessible(true);
        Field currentMonth=presenter.getClass().getDeclaredField("selectedMonth");
        currentMonth.setAccessible(true);
        Field currentDay=presenter.getClass().getDeclaredField("selectedDay");
        currentDay.setAccessible(true);
        //When
        presenter.setDate(TEST_YEAR,TEST_MONTH,TEST_DAY);
        //Then
        Assert.assertEquals(TEST_YEAR,currentYear.getInt(presenter));
        Assert.assertEquals(TEST_MONTH,currentMonth.getInt(presenter)-1);
        Assert.assertEquals(TEST_DAY,currentDay.getInt(presenter));
    }

    @Test
    public void setShortNamesHashMap() throws NoSuchFieldException, IllegalAccessException {
        //Given
        ArrayList<Category> categories=new ArrayList<>();
        for(int i=0;i<6;i++) {
            Category c=new Category();
            c.setShortName(SHORT_CAT_NAME+String.valueOf(i));
            c.setId(String.valueOf(i));
            categories.add(c);
        }
        Field hashMapField=presenter.getClass().getDeclaredField("shortNameToIdHash");
        hashMapField.setAccessible(true);
        //When
        presenter.setHashMapWithShortNames(categories);
        //Then
        System.out.println(hashMapField.get(presenter));
        Map<?,?> map= (Map<?, ?>) hashMapField.get(presenter);
        for(Category c : categories) {
            Assert.assertEquals(c.getId(),map.get(c.getShortName()));
        }
    }

    @Test
    public void postEvent() {
        //Given

        //When
        presenter.setDate(TEST_YEAR,TEST_MONTH,TEST_DAY);
        presenter.setTime(TEST_HOUR,TEST_MIN);
        presenter.postEvent();
        //Then
        Mockito.verify(modelMock).postEvent(Mockito.any(EventWrapper.class), Mockito.any(PostEventCallback.class));
    }
}
