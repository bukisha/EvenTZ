package com.example.bookee.eventz.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.callbacks.EndUploadImageCallback;
import com.example.bookee.eventz.data.callbacks.FetchUploadDataCallback;
import com.example.bookee.eventz.data.pojos.Logo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitImageRepository {
    private static final String TAG = "RetrofitImageRepository";
    private static final String LOGO_TYPE_QUERY_PARAMETER = "image-event-logo-preserve-quality";
    private static final String MEDIA_TYPE_IMAGE = "image/*";
    private MediaUploadWebApi api;

    public RetrofitImageRepository(MediaUploadWebApi api) {
        this.api = api;
    }

    public void fetchUploadData(final FetchUploadDataCallback fetchUploadDataCallback) {
        Call<FetchUploadDataResponse> call = api.requestUpload(RetrofitFactory.getAuthTokenPersonal(), LOGO_TYPE_QUERY_PARAMETER);

        Callback<FetchUploadDataResponse> callback = new Callback<FetchUploadDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<FetchUploadDataResponse> call, @NonNull Response<FetchUploadDataResponse> response) {
                if (response.body() != null) {
                    fetchUploadDataCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FetchUploadDataResponse> call, @NonNull Throwable t) {
                fetchUploadDataCallback.onFailure(t);
            }
        };
        call.enqueue(callback);
    }

    public void uploadImage(final File currentImageFile, final EndUploadImageCallback endUploadImageCallback) {

        this.fetchUploadData(new FetchUploadDataCallback() {
            @Override
            public void onSuccess(final FetchUploadDataResponse fetchUploadDataResponse) {
                Log.d(TAG, "uploadImage: ");
                RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_TYPE_IMAGE), currentImageFile);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", currentImageFile.getName(), requestBody);
                Log.d(TAG, "uploadImage: " + currentImageFile.getPath());

                Call<Void> call = api.uploadImage(fetchUploadDataResponse.getUploadUrl(),
                        createPartFromString(fetchUploadDataResponse.getUploadData().getAWSAccessKeyId()),
                        createPartFromString(fetchUploadDataResponse.getUploadData().getAcl()),
                        createPartFromString(fetchUploadDataResponse.getUploadData().getBucket()),
                        createPartFromString(fetchUploadDataResponse.getUploadData().getKey()),
                        createPartFromString(fetchUploadDataResponse.getUploadData().getPolicy()),
                        createPartFromString(fetchUploadDataResponse.getUploadData().getSignature()),
                        filePart);

                Callback<Void> callback = new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        Log.d(TAG, "onResponse: image has been uploaded i guess....");
                        signalEndOfUpload(fetchUploadDataResponse.getUploadToken(), endUploadImageCallback);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        //TODO if signaling of end of upload failed
                    }
                };
                call.enqueue(callback);
            }

            @Override
            public void onFailure(Throwable t) {
            //TODO  if fetching of upload data failed
            }
        });
    }

    private RequestBody createPartFromString(String fieldName) {
        return RequestBody.create(okhttp3.MultipartBody.FORM, fieldName);
    }

    private void signalEndOfUpload(String uploadToken, final EndUploadImageCallback endUploadImageCallback) {
        Log.d(TAG, "signalEndOfUpload: ");
        Call<Logo> call = api.uploadEndToken(uploadToken, RetrofitFactory.getAuthTokenPersonal());

        Callback<Logo> callback = new Callback<Logo>() {
            @Override
            public void onResponse(@NonNull Call<Logo> call, @NonNull Response<Logo> response) {

                endUploadImageCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Logo> call, @NonNull Throwable t) {
                //TODO
            }
        };
        call.enqueue(callback);
    }
}
