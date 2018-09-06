package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.pojos.Logo;

public interface EndUploadImageCallback {
    void onSuccess(Logo logo);
    void onFailure(Throwable t);
}
