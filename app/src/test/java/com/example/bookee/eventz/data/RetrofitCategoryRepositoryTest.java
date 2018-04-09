package com.example.bookee.eventz.data;

import com.example.bookee.eventz.data.callbacks.FetchCategoriesCallback;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RetrofitCategoryRepositoryTest {

    private RetrofitCategoryRepository tCategoryRepository;
    private ArrayList<Category> tCategoryList;
    @Mock
    private CategoryWebApi mockApi;
    @Mock
    private Call<PaginatedCategoryList> mockCallPaginatedCategoryList;
    @Mock
    private Callback<PaginatedCategoryList> mockCallbackPaginatedCategoryList;
    @Mock
    private PaginatedCategoryList mockPaginatedCategoryList;
//   @Captor
//   ArgumentCaptor<Call<PaginatedCategoryList>> callCaptor;
//    private MockWebServer webServerMock;

    @Mock
    private FetchCategoriesCallback fetchCategoriesCallbackMock;
    @Mock
    private Category tCategory0;
    @Mock
    private Category tCategory1;
    @Mock
    private Category tCategory2;

   @Before
   public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

       tCategoryList = new ArrayList<>();
       tCategoryList.add(tCategory0);
       tCategoryList.add(tCategory1);
       tCategoryList.add(tCategory2);

       tCategoryRepository = new RetrofitCategoryRepository(mockApi);
       }

       @After
       public void tearDown() throws IOException {

       }

     @Test
     public void shouldFetchCategories() {
        //Given
         when(mockApi.fetchCategories(Mockito.anyString())).thenReturn(mockCallPaginatedCategoryList);
        //When
        tCategoryRepository.fetchCategories(fetchCategoriesCallbackMock);
        //Then
        verify(mockApi).fetchCategories(anyString());
        }
      @Test
     public void shouldCallOnSuccessOnCallback() throws IOException {
       //Given

          //When

        //Then

     }
}