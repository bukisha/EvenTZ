package com.example.bookee.eventz.home;

import com.example.bookee.eventz.data.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class PresenterTest {
    private static final String T_CATEGORY_NAME = "name";
    private static final String T_CATEGORY_BASE_ID = "100";
    private static final String T_STRING = "testString";
    private Presenter tPresenter;
    private ArrayList<Category> tList;

    @Mock
    private MvpContract.View viewMock;
    @Mock
    private MvpContract.Model modelMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tPresenter = new Presenter(viewMock, modelMock);
    }

    @Test
    public void shouldPopulateNameListInView() {
        //Given
        tList = createTestCategoryList();
        ArrayList<String> tStringList = createTestNameList();
        Mockito.doNothing().when(modelMock).populateHash(tList);
        Mockito.doNothing().when(viewMock).updateCategories(tStringList);
        InOrder verifyOrder = Mockito.inOrder(modelMock, viewMock);
        //When
        tPresenter.populateNameList(tList);
        //Then
        verifyOrder.verify(modelMock).populateHash(tList);
        verifyOrder.verify(viewMock).updateCategories(tStringList);
    }

    @Test
    public void shouldUpdateOnItemClicked() {
        //Given

        Mockito.when(modelMock.getClickedCategoryId(Mockito.anyString())).thenReturn(T_STRING);
        //When
        tPresenter.itemClicked(Mockito.anyString());
        //Then
        Mockito.verify(viewMock).displayListOfEvents(Mockito.anyString());
    }

    private ArrayList<Category> createTestCategoryList() {
        ArrayList<Category> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Category category = new Category();
            category.setName(T_CATEGORY_NAME + i);
            category.setId(T_CATEGORY_BASE_ID + i);
            tList.add(category);
        }
        return tList;
    }

    private ArrayList<String> createTestNameList() {
        ArrayList<String> tList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tList.add(T_CATEGORY_NAME + i);
        }
        return tList;
    }
}