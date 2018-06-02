
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class Name implements Serializable{

    @SerializedName("html")
    private String Html;
    @SerializedName("text")
    private String Text;

    public String getHtml() {
        return Html;
    }

    public void setHtml(String html) {
        Html = html;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

}
