package com.example.bookee.eventz.data;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.MediaUploadWebApi;
import com.example.bookee.eventz.data.RetrofitFactory;
import com.example.bookee.eventz.data.RetrofitImageRepository;
import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.FetchUploadDataCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class RetrofitImageRepositoryTest {

    private RetrofitImageRepository repository;
    private URL testUrl;
    private URI testUri;
    private File testFile;
    @Mock
    private FetchUploadDataCallback fetchUploadDataCallbackMock;
    @Mock
    private EndUploadImageCallback endUploadImageCallbackMock;

    @Before
    public void setUp() throws URISyntaxException {
        MockitoAnnotations.initMocks(this);
        repository=new RetrofitImageRepository(RetrofitFactory.buildRetrofit().create(MediaUploadWebApi.class));
        try {
            testUrl=new URL("https://dummyimage.com/600x400/000/fff");
            testUri=testUrl.toURI();
            testFile=new File(testUri.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fetchUploadData() {
        //Given

        //When
        repository.fetchUploadData(fetchUploadDataCallbackMock);

        //Then
        verify(fetchUploadDataCallbackMock,timeout(4000)).onSuccess(any(FetchUploadDataResponse.class));
    }

    @Test
    public void uploadImage() {
        //Given
        FetchUploadDataCallback testCallback = new FetchUploadDataCallback() {
            @Override
            public void onSuccess(FetchUploadDataResponse uploadData) {
               // repository.uploadImage(testFile, uploadData, endUploadImageCallbackMock);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        //When
        repository.fetchUploadData(testCallback);
        //Then
        //verify(endUploadImageCallbackMock).onSuccess(anyString());
    }
}