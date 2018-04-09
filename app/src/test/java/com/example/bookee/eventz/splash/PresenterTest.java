package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

public class PresenterTest {

    @Mock
    private Presenter mockPresenter;
    @Mock
    private Model mockModel;
    @Mock
    private MvpContract.View mockView;
    @Mock
    private MvpContract.FetchCategoriesCallback mockCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        }
    @Test
    public void shouldFetchInitialCategoriesFromModel() {
        //Given
        final ArrayList<Category> tList=new ArrayList<>();

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                mockModel.fetchInitialCategories(mockCallback);
                return null;
            }
        }).when(mockPresenter).fetchInitialCategories();

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation)  {
                MvpContract.FetchCategoriesCallback callback=invocation.getArgument(0);
                callback.onSuccess(tList);
                return null;
            }
        }).when(mockModel).fetchInitialCategories(mockCallback);
        //When
        mockPresenter.fetchInitialCategories();
        //Than
        Mockito.verify(mockModel).fetchInitialCategories(mockCallback);
        Mockito.verify(mockCallback).onSuccess(tList);
    }
}

