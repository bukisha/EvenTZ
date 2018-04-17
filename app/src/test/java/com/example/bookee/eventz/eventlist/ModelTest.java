package com.example.bookee.eventz.eventlist;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;
import com.example.bookee.eventz.utils.HashFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

public class ModelTest {
    private static final String T_CATEGORY_ID = "103";
    private Model tModel;
    private ArrayList<Event> tList;

    @Mock
    private RetrofitEventsRepository eventsRepositoryMock;
    @Mock
    private MvpContract.FetchEventsForCategoryCallback presenterCallbackMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tModel = new Model(eventsRepositoryMock, HashFactory.createStringToString());
    }

    @Test
    public void shouldFetchEventsForSpecificCategory() {
        //Given
        tList = new ArrayList<>();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                presenterCallbackMock.onSuccess(tList);
                return null;
            }
        }).when(eventsRepositoryMock).fetchEventsForCategory(Mockito.anyString(), Mockito.any(FetchEventsForCategoryCallback.class));
        //When
        tModel.fetchEventsForCategory(T_CATEGORY_ID, presenterCallbackMock);
        //Then
        Mockito.verify(presenterCallbackMock).onSuccess(tList);
    }
}