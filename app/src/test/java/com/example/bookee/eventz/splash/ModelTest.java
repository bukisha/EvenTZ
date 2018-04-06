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

    @Test
    public void shouldFetchInitialCategories() {
        //given
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation)  {
                ((MvpContract.FetchCategoriesCallback)invocation.getArguments()[0]).onSuccess(tList);
                return null;
            }
        }).when(mockModel).fetchInitialCategories(mockFetchCategoriesCallback);
        //when
        mockModel.fetchInitialCategories(mockFetchCategoriesCallback);
        //than
        Mockito.verify(mockFetchCategoriesCallback,Mockito.times(1)).onSuccess(tList);
    }
}