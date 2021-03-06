
package com.example.bookee.eventz.data.pojos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Start implements Serializable {
    @Override
    public String toString() {
        return "Start{" +
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
