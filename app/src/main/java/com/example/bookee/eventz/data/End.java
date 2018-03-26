
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;


public class End {

    @SerializedName("local")
    private String mLocal;
    @SerializedName("timezone")
    private String mTimezone;
    @SerializedName("utc")
    private String mUtc;

    public String getLocal() {
        return mLocal;
    }

    public void setLocal(String local) {
        mLocal = local;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public String getUtc() {
        return mUtc;
    }

    public void setUtc(String utc) {
        mUtc = utc;
    }

}
