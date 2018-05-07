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
    private Model model;
    @Mock
    private RetrofitCategoryRepository retrofitCategoryRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        model = new Model(retrofitCategoryRepositoryMock);
    }

    @Test
    public void shouldFetchCategoryNames() {
        //Given

        //When
        model.fetchCategoryNames(Mockito.any(MvpContract.FetchCategoriesCallback.class));
        //Then
        Mockito.verify(retrofitCategoryRepositoryMock).fetchCategories(Mockito.any(FetchCategoriesCallback.class));
    }

    @Test
    public void shouldGetClickedCategoryId() {
        //Given
        ArrayList<Category> list = new ArrayList<>();
        Category category = new Category();
        Category category1 = new Category();
        Category category2 = new Category();
        category.setId(T_VALUE);
        category.setName(T_KEY);
        list.add(category);
        list.add(category1);
        list.add(category2);
        model.populateHash(list);
        //When
        String answer = model.getClickedCategoryId(T_KEY);
        //Then
        Assert.assertEquals(T_VALUE, answer);
    }

    @Test
    public void shouldPopulateHash() {
        //Given
        ArrayList<Category> list = createTestList();
        HashMap<String, String> expected = createTestHashMap();

        //When
        model.populateHash(list);
        //Then
        HashMap<String, String> answer = model.getHash();
        Assert.assertEquals(expected, answer);
    }

    private HashMap<String, String> createTestHashMap() {
        HashMap<String, String> hash = new HashMap<>(50);
        for (int i = 0; i < 3; i++) {
            hash.put(T_CATEGORY_NAME + i, T_CATEGORY_BASE_ID + i);
        }
        return hash;
    }

    private ArrayList<Category> createTestList() {
        ArrayList<Category> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Category category = new Category();
            category.setName(T_CATEGORY_NAME + i);
            category.setId(T_CATEGORY_BASE_ID + i);
            list.add(category);
        }
        return list;
    }
}