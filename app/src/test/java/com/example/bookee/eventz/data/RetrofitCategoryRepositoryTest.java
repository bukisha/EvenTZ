package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;
import com.example.bookee.eventz.data.pojos.Category;
import com.example.bookee.eventz.data.pojos.PaginatedCategoryList;
import com.example.bookee.eventz.splash.CategoryWebApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RetrofitCategoryRepositoryTest {

    private RetrofitCategoryRepository categoryRepository;
    private ArrayList<Category> categories;
    @Mock
    private CategoryWebApi mockApi;
    @Mock
    private Call<PaginatedCategoryList> callPaginatedCategoryListMock;
    @Mock
    private FetchCategoriesCallback fetchCategoriesCallbackMock;
    @Mock
    private PaginatedCategoryList paginatedCategoryListMock;
    @Spy
    private Callback<PaginatedCategoryList> enqueueCallbackSpy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categories = createTestList();
        categoryRepository = new RetrofitCategoryRepository(mockApi);
    }

    @Test
    public void shouldFetchCategories() {
        //Given
        when(mockApi.fetchCategories(anyString())).thenReturn(callPaginatedCategoryListMock);
        //When
        categoryRepository.fetchCategories(Mockito.any(FetchCategoriesCallback.class));
        //Then
        verify(mockApi).fetchCategories(anyString());
    }

    //
    //Comments were needed for this next one because it really has some tricky parts
    //
    @Test
    public void shouldCallOnSuccessOnModelCallback() {
        //Given
        final Response<PaginatedCategoryList> retrofitResponse = Response.success(paginatedCategoryListMock);
        //what SHOULD mockedApi return after fetchCategories is called on it
        when(mockApi.fetchCategories(anyString())).thenReturn(callPaginatedCategoryListMock);
        //what SHOULD happen when call is enqueued on new thread to do some work
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                enqueueCallbackSpy = invocation.getArgument(0);
                enqueueCallbackSpy.onResponse(callPaginatedCategoryListMock, retrofitResponse);
                return null;
            }
            //used argumentMatcher's functionality to make this part of tests type safe
        }).when(callPaginatedCategoryListMock).enqueue((ArgumentMatchers.<Callback<PaginatedCategoryList>>any()));
        //what SHOULD happen when the work from separate thread is done and onResponse is called on callback
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                fetchCategoriesCallbackMock.onSuccess(categories);
                return null;
            }
        }).when(enqueueCallbackSpy).onResponse(callPaginatedCategoryListMock, retrofitResponse);
        //When
        categoryRepository.fetchCategories(fetchCategoriesCallbackMock);
        //Then
        verify(callPaginatedCategoryListMock).enqueue(ArgumentMatchers.<Callback<PaginatedCategoryList>>any());
        verify(fetchCategoriesCallbackMock).onSuccess(ArgumentMatchers.<ArrayList<Category>>any());
    }

    private ArrayList<Category> createTestList() {
        List<Category> tCategoryList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Category category = new Category();
            tCategoryList.add(category);
        }
        return (ArrayList<Category>) tCategoryList;
    }

}