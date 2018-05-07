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
    private Presenter presenter;
    private ArrayList<Category> list;

    @Mock
    private MvpContract.View viewMock;
    @Mock
    private MvpContract.Model modelMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(viewMock, modelMock);
    }

    @Test
    public void shouldPopulateNameListInView() {
        //Given
        list = createTestCategoryList();
        //ArrayList<String> tStringList = createTestNameList();
        Mockito.doNothing().when(modelMock).populateHash(list);
        Mockito.doNothing().when(viewMock).updateCategories(list);
        InOrder verifyOrder = Mockito.inOrder(modelMock, viewMock);
        //When
        presenter.populateNameList(list);
        //Then
        verifyOrder.verify(modelMock).populateHash(list);
        verifyOrder.verify(viewMock).updateCategories(list);
    }

    @Test
    public void shouldUpdateOnItemClicked() {
        //Given

        Mockito.when(modelMock.getClickedCategoryId(Mockito.anyString())).thenReturn(T_STRING);
        //When
        presenter.itemClicked(Mockito.anyString());
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