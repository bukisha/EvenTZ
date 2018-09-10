package com.example.bookee.eventz.data;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.create.pojos.UploadData;
import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.FetchUploadDataCallback;
import com.example.bookee.eventz.data.pojos.Logo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RetrofitImageRepositoryTest {

    private RetrofitImageRepository repository;
    private File testFile;
    @Mock
    private FetchUploadDataCallback fetchUploadDataCallbackMock;
    @Mock
    private MediaUploadWebApi apiMock;
    @Mock
    private Call<FetchUploadDataResponse> callMock;
    @Mock
    private Call<Void> callVoidMock;
    @Mock
    private EndUploadImageCallback endUploadImageCallbackMock;
    @Mock
    private Call<Logo> callLogoMock;
    @Mock
    private FetchUploadDataResponse fetchUploadDataResponseMock;
    @Mock
    private UploadData uploadDataMock;

    @Before
    public void setUp() throws URISyntaxException {
        MockitoAnnotations.initMocks(this);
        repository = new RetrofitImageRepository(apiMock);
        try {
            URL testUrl = new URL("https://dummyimage.com/600x400/000/fff");
            URI testUri = testUrl.toURI();
            testFile = new File(testUri.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchUploadData() {
        //Given
        when(apiMock.requestUpload(anyString(), anyString())).thenReturn(callMock);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                Callback<FetchUploadDataResponse> callback = invocation.getArgument(0);
                callback.onResponse(ArgumentMatchers.<Call<FetchUploadDataResponse>>any(),
                        Response.success(new FetchUploadDataResponse()));
                return null;
            }
        }).when(callMock).enqueue(ArgumentMatchers.<Callback<FetchUploadDataResponse>>any());

        //When
        repository.fetchUploadData(fetchUploadDataCallbackMock);

        //Then
        verify(callMock).enqueue(ArgumentMatchers.<Callback<FetchUploadDataResponse>>any());
        verify(fetchUploadDataCallbackMock).onSuccess(any(FetchUploadDataResponse.class));
    }

    @Test
    public void uploadImage() {
        //Given
        final Response<Void> testResponse=Response.success(null);
        when(apiMock.requestUpload(anyString(), anyString())).thenReturn(callMock);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation)  {
                Callback<FetchUploadDataResponse> callback = invocation.getArgument(0);
                callback.onResponse(callMock, Response.success(fetchUploadDataResponseMock));
                return null;
            }
        }).when(callMock).enqueue(ArgumentMatchers.<Callback<FetchUploadDataResponse>>any());

        when(fetchUploadDataResponseMock.getUploadData()).thenReturn(uploadDataMock);
        when(fetchUploadDataResponseMock.getUploadToken()).thenReturn("token1234");
        when(fetchUploadDataResponseMock.getUploadUrl()).thenReturn("https://dummyimage.com/600x400/000/fff");
        when(uploadDataMock.getAWSAccessKeyId()).thenReturn("1234567");
        when(uploadDataMock.getAcl()).thenReturn("1234");
        when(uploadDataMock.getBucket()).thenReturn("34546");
        when(uploadDataMock.getKey()).thenReturn("1212342");
        when(uploadDataMock.getPolicy()).thenReturn("123214");
        when(uploadDataMock.getSignature()).thenReturn("asd123213");

 when(apiMock.uploadImage(anyString(),
                any(RequestBody.class),
                any(RequestBody.class),
                any(RequestBody.class),
                any(RequestBody.class),
                any(RequestBody.class),
                any(RequestBody.class),
                any(MultipartBody.Part.class))).thenReturn(callVoidMock);


        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Callback<Void> callback=invocation.getArgument(0);
                callback.onResponse(callVoidMock,testResponse);
                return null;
            }
        }).when(callVoidMock).enqueue(ArgumentMatchers.<Callback<Void>>any());

        when(apiMock.uploadEndToken(anyString(),anyString())).thenReturn(callLogoMock);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Callback<Logo> callback=invocation.getArgument(0);
                callback.onResponse(callLogoMock,Response.success(new Logo()));
                return null;
            }
        }).when(callLogoMock).enqueue(ArgumentMatchers.<Callback<Logo>>any());

        //When
        repository.uploadImage(testFile,endUploadImageCallbackMock);
        //Then
        verify(callVoidMock).enqueue(ArgumentMatchers.<Callback<Void>>any());
        verify(endUploadImageCallbackMock).onSuccess(any(Logo.class));
    }
}