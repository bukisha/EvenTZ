package com.example.bookee.eventz.create;

import com.example.bookee.eventz.data.RetrofitEventsRepository;
import com.example.bookee.eventz.data.RetrofitImageRepository;
import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.PostEventCallback;
import com.example.bookee.eventz.data.pojos.EventWrapper;
import com.example.bookee.eventz.data.pojos.Logo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.io.File;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModelTest {

    private static final String TEST_LOGO_ID ="1234567" ;
    private MvpContract.Model model;
    @Mock
    private RetrofitImageRepository imageRepositoryMock;
    @Mock
    private RetrofitEventsRepository eventsRepositoryMock;
    @Mock
    private PostEventCallback postEventCallbackMock;
    @Mock
    private File fileMock;
    @Mock
    private Logo logoMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new Model(eventsRepositoryMock, imageRepositoryMock);

    }

    @Test
    public void postEventWithoutLogo() {
        //Given
        model.setCategoryId(String.valueOf(104));
        model.setDate(2019,9,22);
        model.setTime(15,0);
        model.setDescription("TEST EVENT DESCRIPTION");
        model.setName("GOKU TEST");

        //When
        model.postEvent(postEventCallbackMock);
        //Then
        verify(eventsRepositoryMock).postNewEvent(any(EventWrapper.class),any(PostEventCallback.class));
    }
    @Test
    public void postEventWithLogo() {
        //Given
        model.setCategoryId(String.valueOf(104));
        model.setDate(2019,9,22);
        model.setTime(15,0);
        model.setDescription("TEST EVENT DESCRIPTION");
        model.setName("GOKU TEST");
        model.setLogo(fileMock);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                EndUploadImageCallback callback=invocation.getArgument(1);
                callback.onSuccess(logoMock);
                return null;
            }
        }).when(imageRepositoryMock).uploadImage(any(File.class),any(EndUploadImageCallback.class));

        when(logoMock.getId()).thenReturn(TEST_LOGO_ID);
        //When
        model.postEvent(postEventCallbackMock);
        //Then
        verify(imageRepositoryMock).uploadImage(any(File.class),any(EndUploadImageCallback.class));
        verify(eventsRepositoryMock).postNewEvent(any(EventWrapper.class),any(PostEventCallback.class));
    }
}