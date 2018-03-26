
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;



public class Name {

    @SerializedName("html")
    private String mHtml;
    @SerializedName("text")
    private String mText;

    public String getHtml() {
        return mHtml;
    }

    public void setHtml(String html) {
        mHtml = html;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
