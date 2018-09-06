package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;

public interface FetchUploadDataCallback {
    void onSuccess(FetchUploadDataResponse uploadData);
    void onFailure(Throwable t);
}
