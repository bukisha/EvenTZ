package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.SQLiteDatabaseRepository;
import com.example.bookee.eventz.data.callbacks.FetchEventForIdCallback;
import com.example.bookee.eventz.data.pojos.Event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModelTest {
    private static final String TEST_ID = "1234567";
    private MvpContract.Model model;
    @Mock
    private RetrofitEventsRepository retrofitEventsRepositoryMock;
    @Mock
    private MvpContract.FetchEventForIdCallback fetchEventForIdCallbackMock;
    @Mock
    private MvpContract.CheckFollowedStatusCallback checkFollowedStatusCallbackMock;
    @Mock
    private SQLiteDatabaseRepository databaseRepositoryMock;
    @Mock
    private Event eventMock;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new Model(retrofitEventsRepositoryMock,databaseRepositoryMock);
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
        model.fetchEventForId(TEST_ID, fetchEventForIdCallbackMock);
        //Then
        verify(fetchEventForIdCallbackMock).onSuccess(tEvent);
    }
    @Test
    public void checkFollowButton() {
        //Given
        ArrayList<String> testList=new ArrayList<>();
        testList.add(TEST_ID);
        testList.add("34645234");
        testList.add("2221123124");
        when(databaseRepositoryMock.getEventsIds()).thenReturn(testList);
        //When
        model.checkFollowButton(TEST_ID,checkFollowedStatusCallbackMock);
        //Then
        verify(checkFollowedStatusCallbackMock).onSuccess(anyBoolean());
    }
    @Test
    public void addFollowedEvent() {
        //When
        model.addFollowedEvent(eventMock);
        //Then
        verify(databaseRepositoryMock).addEvent(any(Event.class));
    }
    @Test
    public void removeFollowedEvent() {
        //When
        model.removeEventWithId(TEST_ID);
        //Then
        verify(databaseRepositoryMock).removeEventWithId(anyString());
    }
    @Test
    public void closeDataSource() {
        //When
        model.closeDataSource();
        //Then
        verify(databaseRepositoryMock).closeDataSource();
    }
}