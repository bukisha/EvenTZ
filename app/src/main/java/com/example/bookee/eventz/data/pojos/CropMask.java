
package com.example.bookee.eventz.data.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CropMask implements Serializable {

    @SerializedName("height")
    private Long mHeight;
    @SerializedName("top_left")
    private TopLeft mTopLeft;
    @SerializedName("width")
    private Long mWidth;

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public TopLeft getTopLeft() {
        return mTopLeft;
    }

    public void setTopLeft(TopLeft topLeft) {
        mTopLeft = topLeft;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
