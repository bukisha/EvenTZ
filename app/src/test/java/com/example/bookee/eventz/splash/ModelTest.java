package com.example.bookee.eventz.splash;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

public class ModelTest {

    private ArrayList<Category> tList;

    private Model tModel;
    @Mock
    private RetrofitCategoryRepository retrofitCategoryRepositoryMock;
    @Mock
    private MvpContract.FetchCategoriesCallback MvpFetchCategoriesCallbackMock;
    @Captor
    private ArgumentCaptor<ArrayList<Category>> listArgumentCaptor;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tModel = new Model(retrofitCategoryRepositoryMock);

    }
    @Test
    public void shouldFetchInitialCategoriesFromRepository() {
        //Given
        //nothing is given here :D
        //when
        tModel.fetchInitialCategories(Mockito.any(MvpContract.FetchCategoriesCallback.class));
        //Than
        Mockito.verify(retrofitCategoryRepositoryMock).fetchCategories(Mockito.any(FetchCategoriesCallback.class));
    }
    //TODO how could i test what is passed to onSuccess and is it valid data or not
    @Test
    public void shouldPassDataToCallback() {
    //Given
            tList=new ArrayList<>();
            tList.add(new Category());
            tList.add(new Category());
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                FetchCategoriesCallback callback=invocation.getArgument(0);
                callback.onSuccess(tList);
                return null;
            }
        }).when(retrofitCategoryRepositoryMock).fetchCategories(Mockito.any(FetchCategoriesCallback.class));
        //When
        tModel.fetchInitialCategories(MvpFetchCategoriesCallbackMock);
       //Then
        Mockito.verify(MvpFetchCategoriesCallbackMock).onSuccess(listArgumentCaptor.capture());
        Assert.assertEquals(tList,listArgumentCaptor.getValue());
    }
}