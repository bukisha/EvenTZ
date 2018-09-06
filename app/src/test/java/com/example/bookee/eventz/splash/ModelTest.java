package com.example.bookee.eventz.splash;

import android.content.Context;

import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class ModelTest {

    private ArrayList<Category> list;

    private Model model;
    @Mock
    private RetrofitCategoryRepository retrofitCategoryRepositoryMock;
    @Mock
    private MvpContract.FetchAndStoreCategoriesCallback mvpFetchAndStoreCategoriesCallbackMock;
    @Mock
    private Context contextMock;
    @Captor
    private ArgumentCaptor<ArrayList<Category>> listArgumentCaptor;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new Model(retrofitCategoryRepositoryMock);

    }
    @Test
    public void fetchAndStoreInitialCategories() {
        //Given
        //nothing is given here :D
        //when
        model.fetchInitialCategories(Mockito.any(MvpContract.FetchAndStoreCategoriesCallback.class),contextMock);
        //Than
        Mockito.verify(retrofitCategoryRepositoryMock).fetchCategories(Mockito.any(FetchCategoriesCallback.class));
    }

}