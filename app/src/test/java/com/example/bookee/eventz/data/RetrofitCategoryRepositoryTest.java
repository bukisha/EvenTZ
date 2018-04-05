package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import org.junit.After;
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

    private ArrayList tArrayList;
    @Mock
    private RetrofitCategoryRepository tCategoryRepository;
    @Mock
    private CategoryWebApi api;
    @Mock
    private FetchCategoriesCallback tFetchCategoriesCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       // tCategoryRepository=new RetrofitCategoryRepository(api);
            }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void shouldFetchCategories() {
        //Given
        tArrayList=new ArrayList();
        tArrayList.add("test0");
        tArrayList.add("test1");
        tArrayList.add("test2");

        //synchronous answer
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
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