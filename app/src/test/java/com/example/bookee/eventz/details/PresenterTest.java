package com.example.bookee.eventz.details;

import com.example.bookee.eventz.data.pojos.Description;
import com.example.bookee.eventz.data.pojos.Event;
import com.example.bookee.eventz.data.pojos.Logo;
import com.example.bookee.eventz.data.pojos.Name;
import com.example.bookee.eventz.data.pojos.Start;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class PresenterTest {
    private static final String TEST_ID = "1234567";
    private static final String TEST_TITLE = "EU/Cork";
    private static final String TEST_EVENT_NAME = "Drinking Beer";
    private static final String TEST_DATE = "2018-05-17T14:30:00";
    private static final String TEST_DESCRIPTION = "WE will be drinking a lot...";
    private static final String TEST_IMG_URL = "http//image/beer.org";

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
    public void fetchEventForId() {
        //Given
        final Event testEvent = new Event();
        Name tName = new Name();
        tName.setText(TEST_EVENT_NAME);
        testEvent.setName(tName);
        Start tStart = new Start();
        tStart.setTimezone(TEST_TITLE);
        tStart.setLocal(TEST_DATE);
        testEvent.setStart(tStart);
        Logo tLogo=new Logo();
        tLogo.setUrl(TEST_IMG_URL);
        testEvent.setLogo(tLogo);
        Description tDescription = new Description();
        tDescription.setText(TEST_DESCRIPTION);
        testEvent.setDescription(tDescription);

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                MvpContract.FetchEventForIdCallback callback = invocation.getArgument(1);
                callback.onSuccess(testEvent);
                return null;
            }
        }).when(modelMock).fetchEventForId(Mockito.anyString(), Mockito.any(MvpContract.FetchEventForIdCallback.class));
        //When
        presenter.fetchEventForId(TEST_ID);
        //Then
        Mockito.verify(viewMock).displayEvent(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        // Mockito.verify(modelMock).fetchEventForId(Mockito.anyString(),Mockito.any(MvpContract.FetchEventForIdCallback.class));

    }
}