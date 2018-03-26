package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable{

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("name_localized")
    private String mNameLocalized;
    @SerializedName("resource_uri")
    private String mResourceUri;
    @SerializedName("short_name")
    private String mShortName;
    @SerializedName("short_name_localized")
    private String mShortNameLocalized;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNameLocalized() {
        return mNameLocalized;
    }

    public void setNameLocalized(String nameLocalized) {
        mNameLocalized = nameLocalized;
    }

    public String getResourceUri() {
        return mResourceUri;
    }

    public void setResourceUri(String resourceUri) {
        mResourceUri = resourceUri;
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        mShortName = shortName;
    }

    public String getShortNameLocalized() {
        return mShortNameLocalized;
    }

    public void setShortNameLocalized(String shortNameLocalized) {
        mShortNameLocalized = shortNameLocalized;
    }

}
