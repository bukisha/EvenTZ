package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;

public class PresenterTest {

    private static final String T_CATEGORY_NAME = "testName";
    private Presenter tPresenter;
    private ArrayList<Event> tList;
    private ArrayList<String> tNamesList;
    @Mock
    private MvpContract.View viewMock;
    @Mock
    private MvpContract.Model modelMock;
    @Mock
    private MvpContract.FetchEventsForCategoryCallback modelCallbackMock;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tPresenter = new Presenter(viewMock, modelMock);
        tList = new ArrayList<>();
        tNamesList = new ArrayList<>();
    }

    @Test
    public void shouldFetchEventsForCategory() {
        //Given

        //When
        tPresenter.fetchEventsForCategory(T_CATEGORY_NAME);
        //Then
        Mockito.verify(modelMock).fetchEventsForCategory(Mockito.anyString(), Mockito.any(MvpContract.FetchEventsForCategoryCallback.class));
    }

    @Test
    public void shouldPopulateEventListActivity() {
        //Given
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                viewMock.populateEventListActivity(tNamesList);
                return null;
            }
        }).when(modelCallbackMock).onSuccess(tList);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                MvpContract.FetchEventsForCategoryCallback callback = invocation.getArgument(1);
                callback.onSuccess(tList);
                return null;
            }
        }).when(modelMock).fetchEventsForCategory(T_CATEGORY_NAME, modelCallbackMock);
        //When
        tPresenter.fetchEventsForCategory(T_CATEGORY_NAME);
        //Then
        Mockito.verify(viewMock).populateEventListActivity(tNamesList);
    }
}