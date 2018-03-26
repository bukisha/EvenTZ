
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Logo {

    @SerializedName("aspect_ratio")
    private String mAspectRatio;
    @SerializedName("crop_mask")
    private CropMask mCropMask;
    @SerializedName("edge_color")
    private String mEdgeColor;
    @SerializedName("edge_color_set")
    private Boolean mEdgeColorSet;
    @SerializedName("id")
    private String mId;
    @SerializedName("original")
    private Original mOriginal;
    @SerializedName("url")
    private String mUrl;

    public String getAspectRatio() {
        return mAspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        mAspectRatio = aspectRatio;
    }

    public CropMask getCropMask() {
        return mCropMask;
    }

    public void setCropMask(CropMask cropMask) {
        mCropMask = cropMask;
    }

    public String getEdgeColor() {
        return mEdgeColor;
    }

    public void setEdgeColor(String edgeColor) {
        mEdgeColor = edgeColor;
    }

    public Boolean getEdgeColorSet() {
        return mEdgeColorSet;
    }

    public void setEdgeColorSet(Boolean edgeColorSet) {
        mEdgeColorSet = edgeColorSet;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Original getOriginal() {
        return mOriginal;
    }

    public void setOriginal(Original original) {
        mOriginal = original;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
