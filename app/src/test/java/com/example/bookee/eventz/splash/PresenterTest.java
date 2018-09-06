package com.example.bookee.eventz.splash;

import android.content.Context;

import com.example.bookee.eventz.data.pojos.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterTest {

    private Presenter presenter;

    @Mock
    private Model modelMock;
    @Mock
    private MvpContract.View viewMock;
    @Mock
    private Context contextMock;
    @Spy
    private MvpContract.FetchAndStoreCategoriesCallback fetchAndStoreCategoriesCallbackSpy;
    @Mock
    private MvpContract.FetchAndStoreCategoriesCallback fetchAndStoreCategoriesCallbackMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(viewMock, modelMock);
    }

    @Test
    public void shouldFetchAndStoreInitialData() {
        //Given

        //When
        presenter.fetchInitialCategories(contextMock);
        //Than
        Mockito.verify(modelMock).fetchInitialCategories(Mockito.any(MvpContract.FetchAndStoreCategoriesCallback.class),Mockito.any(Context.class));
    }
    @Test
    public void shouldLaunchHomeActivityWhenSuccess() {
        //Given
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                fetchAndStoreCategoriesCallbackSpy= (MvpContract.FetchAndStoreCategoriesCallback) Mockito.spy(invocation.getArgument(0));
                fetchAndStoreCategoriesCallbackSpy.onSuccess();
                return null;
            }
        }).when(modelMock).fetchInitialCategories(Mockito.any(MvpContract.FetchAndStoreCategoriesCallback.class),Mockito.any(Context.class));

        //When
        presenter.fetchInitialCategories(contextMock);
        //Then
        verify(fetchAndStoreCategoriesCallbackSpy).onSuccess();
        verify(viewMock).launchHomeActivity();
    }

}

