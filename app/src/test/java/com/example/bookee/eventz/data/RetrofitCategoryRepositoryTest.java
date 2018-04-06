package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RetrofitCategoryRepositoryTest {

    private ArrayList<Category> tArrayList;
    @Mock
    private RetrofitCategoryRepository tCategoryRepository;
    @Mock
    private FetchCategoriesCallback tFetchCategoriesCallback;
    @Mock
    private Category tCategory0;
    @Mock
    private Category tCategory1;
    @Mock
    private Category tCategory2;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
            }

    //A little bit tricky because i should also test what call.enqueue does :D
    //TODO get back on this when u have more info on mockito framework and when u figure out how to mock retrofit calls
    @Test
    public void shouldFetchCategories() {
        //Given
        tArrayList=new ArrayList<>();
        tArrayList.add(tCategory0);
        tArrayList.add(tCategory1);
        tArrayList.add(tCategory2);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                ((FetchCategoriesCallback)invocation.getArguments()[0]).onSuccess(tArrayList);
                return null;
            }
        }).when(tCategoryRepository).fetchCategories(tFetchCategoriesCallback);
        //When
         tCategoryRepository.fetchCategories(tFetchCategoriesCallback);
         //Then
        verify(tFetchCategoriesCallback,times(1)).onSuccess(tArrayList);
        }
}