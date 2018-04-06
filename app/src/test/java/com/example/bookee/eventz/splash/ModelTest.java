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

public class ModelTest {

    private ArrayList<Category> tList;
    @Mock
    private Model mockModel;
    @Mock
    private MvpContract.FetchCategoriesCallback mockFetchCategoriesCallback;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tList=new ArrayList<>();
        }
    //testing is method onSuccess called
    @Test
    public void shouldFetchInitialCategories() {
        //Given
        //propagates fetched tList to MvpContract.callback via its onSuccess method
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation)  {
                MvpContract.FetchCategoriesCallback callback= (MvpContract.FetchCategoriesCallback) invocation.getArguments()[0];
                callback.onSuccess(tList);
                return null;
            }
        }).when(mockModel).fetchInitialCategories(mockFetchCategoriesCallback);
        //when
        mockModel.fetchInitialCategories(mockFetchCategoriesCallback);
        //Than
        Mockito.verify(mockFetchCategoriesCallback,Mockito.times(1)).onSuccess(tList);
    }
    //TODO how could i test what is passed to onSuccess and is it valid data or not
}