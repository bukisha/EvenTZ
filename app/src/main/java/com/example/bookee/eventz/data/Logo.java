
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class Logo implements Serializable {

    @SerializedName("aspect_ratio")
    private String aspect_ratio;
    @SerializedName("crop_mask")
    private CropMask crop_mask;
    @SerializedName("edge_color")
    private String edge_color;
    @SerializedName("edge_color_set")
    private Boolean edge_color_set;
    @SerializedName("id")
    private String id;
    @SerializedName("original")
    private Original original;
    @SerializedName("url")
    private String url;

    public String getAspectRatio() {
        return aspect_ratio;
    }

    public void setAspectRatio(String aspectRatio) {
        aspect_ratio = aspectRatio;
    }

    public CropMask getCropMask() {
        return crop_mask;
    }

    public void setCropMask(CropMask cropMask) {
        crop_mask = cropMask;
    }

    public String getEdgeColor() {
        return edge_color;
    }

    public void setEdgeColor(String edgeColor) {
        edge_color = edgeColor;
    }

    public Boolean getEdgeColorSet() {
        return edge_color_set;
    }

    public void setEdgeColorSet(Boolean edgeColorSet) {
        edge_color_set = edgeColorSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
