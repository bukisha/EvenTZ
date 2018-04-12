package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.Category;

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

    private Presenter tPresenter;
    private ArrayList<Category> tList;
    @Mock
    private Model modelMock;
    @Mock
    private MvpContract.View viewMock;
    @Spy
    private MvpContract.FetchCategoriesCallback fetchCategoriesCallbackSpy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tPresenter = new Presenter(viewMock, modelMock);
        tList = new ArrayList<>();
    }

    @Test
    public void shouldFetchInitialCategoriesFromModel() {
        //Given
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                MvpContract.FetchCategoriesCallback callback = invocation.getArgument(0);
                callback.onSuccess(tList);
                return null;
            }
        }).when(modelMock).fetchInitialCategories(Mockito.any(MvpContract.FetchCategoriesCallback.class));
        //When
        tPresenter.fetchInitialCategories();
        //Than
        Mockito.verify(modelMock).fetchInitialCategories(Mockito.any(MvpContract.FetchCategoriesCallback.class));
    }

    @Test
    public void shouldPassInitialListToView() {
        //Given
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                MvpContract.FetchCategoriesCallback callback = invocation.getArgument(0);
                fetchCategoriesCallbackSpy = Mockito.spy(callback);
                fetchCategoriesCallbackSpy.onSuccess(tList);
                return null;
            }
        }).when(modelMock).fetchInitialCategories(Mockito.any(MvpContract.FetchCategoriesCallback.class));
        //When
        tPresenter.fetchInitialCategories();
        //Then
        Mockito.verify(fetchCategoriesCallbackSpy).onSuccess(Mockito.any(ArrayList.class));

    }
}

