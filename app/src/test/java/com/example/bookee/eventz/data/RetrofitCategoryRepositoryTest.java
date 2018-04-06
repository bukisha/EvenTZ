package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RetrofitCategoryRepositoryTest {

    private ArrayList<Category> tCategoryList;
    @Mock
    private CategoryWebApi mockApi;
    @Mock
    private Call<PaginatedCategoryList> mockCallPaginatedCategoryList;
    @Mock
    private Callback<PaginatedCategoryList> mockCallbackPaginatedCategoryList;
    @Mock
    private PaginatedCategoryList mockPaginatedCategoryList;
    @Mock
    private Response<PaginatedCategoryList> mockResponse;
    @Captor
    ArgumentCaptor<Response<PaginatedCategoryList>> repositoryResponse;
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
     @Test
     public void shouldFetchCategories() {
        //Given
        tCategoryList =new ArrayList<>();
        tCategoryList.add(tCategory0);
        tCategoryList.add(tCategory1);
        tCategoryList.add(tCategory2);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                ((FetchCategoriesCallback)invocation.getArguments()[0]).onSuccess(tCategoryList);
                return null;
            }
        }).when(tCategoryRepository).fetchCategories(tFetchCategoriesCallback);

        //When
        tCategoryRepository.fetchCategories(tFetchCategoriesCallback);

         //Then
        verify(tFetchCategoriesCallback,times(1)).onSuccess(tCategoryList);
        }


        //A little bit tricky because i should also test what call.enqueue does :D
    //TODO get back on this when u have more info on mockito framework and when u figure out how to mock retrofit calls
//        @Test
//        public void shouldPassFetchedCategoriesToCallback() {
//            //given
//            Mockito.when(mockApi.fetchCategories(anyString())).thenReturn(mockCallPaginatedCategoryList);
//            Mockito.doAnswer(new Answer<Void>() {
//                @Override
//                public Void answer(InvocationOnMock invocation) {
//                    Callback<PaginatedCategoryList> callback=invocation.getArgument(0);
//                    callback.onResponse(mockCallPaginatedCategoryList,mockResponse);
//                    return null;
//                }
//            }).when(mockCallPaginatedCategoryList).enqueue(mockCallbackPaginatedCategoryList);
//
//            //when
//            mockApi.fetchCategories(anyString());
//
//            //then
//            verify(mockCallbackPaginatedCategoryList).onResponse(mockCallPaginatedCategoryList,repositoryResponse.capture());
//            mockPaginatedCategoryList.setCategories(repositoryResponse.getValue().body().getCategories());
//   }
}