
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class Name implements Serializable{
    @Override
    public String toString() {
        return "Name{" +
                "html='" + html + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }

    @SerializedName("html")
    private String html;
    @SerializedName("text")
    private String Text;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

}
