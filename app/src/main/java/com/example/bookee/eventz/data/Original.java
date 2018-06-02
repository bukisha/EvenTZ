
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Original implements Serializable{

    @SerializedName("height")
    private Object mHeight;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("width")
    private Object mWidth;

    public Object getHeight() {
        return mHeight;
    }

    public void setHeight(Object height) {
        mHeight = height;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Object getWidth() {
        return mWidth;
    }

    public void setWidth(Object width) {
        mWidth = width;
    }

}
