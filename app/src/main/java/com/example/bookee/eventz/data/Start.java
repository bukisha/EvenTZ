
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Start implements Serializable {

    @SerializedName("local")
    private String Local;
    @SerializedName("timezone")
    private String Timezone;
    @SerializedName("utc")
    private String Utc;

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getTimezone() {
        return Timezone;
    }

    public void setTimezone(String timezone) {
        Timezone = timezone;
    }

    public String getUtc() {
        return Utc;
    }

    public void setUtc(String utc) {
        Utc = utc;
    }

}
