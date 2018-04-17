package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.Event;
import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ModelTest {
    private static final String TEST_ID = "1234567";
    private MvpContract.Model tModel;
    @Mock
    private RetrofitEventsRepository retrofitEventsRepositoryMock;
    @Mock
    private MvpContract.FetchEventForIdCallback fetchEventForIdCallbackMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tModel = new Model(retrofitEventsRepositoryMock);
    }

    @Test
    public void shouldFetchEventForId() {
        //Given
        final Event tEvent=new Event();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                FetchEventForIdCallback callback = invocation.getArgument(1);
                callback.onSuccess(tEvent);
                return null;
            }
        }).when(retrofitEventsRepositoryMock).fetchEventForId(Mockito.anyString(), Mockito.any(FetchEventForIdCallback.class));
        //When
        tModel.fetchEventForId(TEST_ID, fetchEventForIdCallbackMock);
        //Then
        Mockito.verify(fetchEventForIdCallbackMock).onSuccess(tEvent);
    }
}