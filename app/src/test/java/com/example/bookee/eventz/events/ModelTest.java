package com.example.bookee.eventz.events;

import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventsForCategoryCallback;

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
    private Model model;
    private ArrayList<Event> list;

    @Mock
    private RetrofitEventsRepository eventsRepositoryMock;
    @Mock
    private MvpContract.FetchEventsForCategoryCallback presenterCallbackMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new Model(eventsRepositoryMock);
    }

    @Test
    public void shouldFetchEventsForSpecificCategory() {
        //Given
        list = new ArrayList<>();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                presenterCallbackMock.onSuccess(list);
                return null;
            }
        }).when(eventsRepositoryMock).fetchEventsForCategory(Mockito.anyString(), Mockito.any(FetchEventsForCategoryCallback.class));
        //When
        model.fetchEventsForCategory(T_CATEGORY_ID, presenterCallbackMock);
        //Then
        Mockito.verify(presenterCallbackMock).onSuccess(list);
    }
}