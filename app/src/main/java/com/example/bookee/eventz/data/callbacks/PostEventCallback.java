package com.example.bookee.eventz.data.callbacks;

import com.example.bookee.eventz.data.ResponseWrapper;

public interface PostEventCallback {
        void onSuccess(ResponseWrapper e);
        void onFailure(Throwable t);
}
