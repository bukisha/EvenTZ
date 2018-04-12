package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.Event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

public class PresenterTest {

    private static final String T_CATEGORY_NAME = "testName";
    private Presenter tPresenter;
    private ArrayList<Event> tList;
    @Mock
    private MvpContract.View viewMock;
    @Mock
    private MvpContract.Model modelMock;
    @Spy
    private MvpContract.FetchEventsForCategoryCallback modelCallbackSpy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tPresenter = new Presenter(viewMock, modelMock);
        tList = new ArrayList<>();
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

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                MvpContract.FetchEventsForCategoryCallback callback = (MvpContract.FetchEventsForCategoryCallback) invocation.getArguments()[1];
                String name=invocation.getArgument(0);
                System.out.println("Called with arguments "+name );
                System.out.println("Setting up Spy on callback which is passed to the method.........." );

                modelCallbackSpy=Mockito.spy(callback);

                modelCallbackSpy.onSuccess(tList);
                return null;
            }
        }).when(modelMock).fetchEventsForCategory(Mockito.anyString(),Mockito.any(MvpContract.FetchEventsForCategoryCallback.class));
        //When
        tPresenter.fetchEventsForCategory(T_CATEGORY_NAME);
        //Then
        Mockito.verify(modelCallbackSpy).onSuccess(tList);
    }
}