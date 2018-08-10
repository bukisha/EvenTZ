package com.example.bookee.eventz.create;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.RetrofitFactory;

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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RetrofitImageRepositoryTest {

    private RetrofitImageRepository repository;
    private URL testUrl;
    private URI testUri;
    private File testFile;
    @Mock
    private MvpContract.FetchUploadDataCallback fetchUploadDataCallbackMock;
    @Mock
    private MvpContract.EndUploadImageCallback endUploadImageCallbackMock;

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
        MvpContract.FetchUploadDataCallback testCallback = new MvpContract.FetchUploadDataCallback() {
            @Override
            public void onSuccess(FetchUploadDataResponse uploadData) {
                repository.uploadImage(testFile, uploadData, endUploadImageCallbackMock);
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