package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.Event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class PresenterTest {
    private static final String TEST_ID="1234567";
    private Presenter tPresenter;
    @Mock
    private MvpContract.Model modelMock;
    @Mock
    private MvpContract.View viewMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tPresenter=new Presenter(modelMock,viewMock);
    }

    @Test
    public void fetchEventForId() {
        //Given
        final Event testEvent=new Event();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                MvpContract.FetchEventForIdCallback callback=invocation.getArgument(1);
                callback.onSuccess(testEvent);
                return null;
            }
        }).when(modelMock).fetchEventForId(Mockito.anyString(),Mockito.any(MvpContract.FetchEventForIdCallback.class));
        //When
        tPresenter.fetchEventForId(TEST_ID);

        //Then
        Mockito.verify(viewMock).displayEvent(testEvent);
    }
}