
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class End implements Serializable{
    @Override
    public String toString() {
        return "End{" +
                "local='" + local + '\'' +
                ", timezone='" + timezone + '\'' +
                ", utc='" + utc + '\'' +
                '}';
    }

    @SerializedName("local")
    private String local;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("utc")
    private String utc;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

}
