package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.Category;
import com.example.bookee.eventz.data.RetrofitCategoryRepository;
import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelTest {
    private static final String T_KEY = "testKey";
    private static final String T_VALUE = "tValue";
    private static final String T_CATEGORY_NAME = "name";
    private static final String T_CATEGORY_BASE_ID = "100";
    private Model tModel;
    private HashMap<String, String> tHash;
    @Mock
    private RetrofitCategoryRepository retrofitCategoryRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tHash = new HashMap<>(50);
        tModel = new Model(retrofitCategoryRepositoryMock, tHash);
    }

    @Test
    public void shouldFetchCategoryNames() {
        //Given

        //When
        tModel.fetchCategoryNames(Mockito.any(MvpContract.FetchCategoriesCallback.class));
        //Then
        Mockito.verify(retrofitCategoryRepositoryMock).fetchCategories(Mockito.any(FetchCategoriesCallback.class));
    }

    @Test
    public void shouldGetClickedCategoryId() {
        //Given
        tHash.put(T_KEY, T_VALUE);
        //When
        String answer = tModel.getClickedCategoryId(T_KEY);
        //Then
        Assert.assertEquals(T_VALUE, answer);
    }

    @Test
    public void shouldPopulateHash() {
        //Given
        ArrayList<Category> tList = createTestList();
        HashMap<String, String> expected = createTestHashMap();

        //When
        tModel.populateHash(tList);
        //Then
        HashMap<String, String> answer = tModel.getHash();
        Assert.assertEquals(expected, answer);

    }

    private HashMap<String, String> createTestHashMap() {
        HashMap<String, String> tHash = new HashMap<>(50);
        for (int i = 0; i < 3; i++) {
            tHash.put(T_CATEGORY_NAME + i, T_CATEGORY_BASE_ID + i);
        }
        return tHash;
    }

    private ArrayList<Category> createTestList() {
        ArrayList<Category> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Category category = new Category();
            category.setName(T_CATEGORY_NAME + i);
            category.setId(T_CATEGORY_BASE_ID + i);
            tList.add(category);
        }
        return tList;
    }
}