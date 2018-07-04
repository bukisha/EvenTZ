package com.example.bookee.eventz.home;

import android.content.Context;
import android.content.Intent;

import com.example.bookee.eventz.data.pojos.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class HomeActivityTest {

    private HomeActivity homeActivity;
    @Mock
    private Context contextMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        homeActivity = new HomeActivity();
    }

    @Test
    public void launch() {
        //Given
        ArrayList<Category> tCategoryList = new ArrayList<>();
        //When
        HomeActivity.launch(tCategoryList, contextMock);
        //Then
        Mockito.verify(contextMock).startActivity(Mockito.any(Intent.class));
    }

    @Test
    public void updateCategories() {
        //Given


        //When

        //Then
    }

    @Test
    public void displayListOfEvents() {
    }

    @Test
    public void displayErrorMessage() {
    }
}