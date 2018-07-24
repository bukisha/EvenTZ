package com.example.bookee.eventz.create.pojos;

import com.google.gson.annotations.SerializedName;

public class PublishResponse {

    @SerializedName("published")
    private Boolean mPublished;

    public Boolean getPublished() {
        return mPublished;
    }

    public void setPublished(Boolean published) {
        mPublished = published;
    }

}
