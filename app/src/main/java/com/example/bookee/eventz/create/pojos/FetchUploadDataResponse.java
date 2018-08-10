package com.example.bookee.eventz.create.pojos;

import com.google.gson.annotations.SerializedName;


public class FetchUploadDataResponse {

    @SerializedName("file_parameter_name")
    private String mFileParameterName;
    @SerializedName("upload_data")
    private UploadData mUploadData;
    @SerializedName("upload_method")
    private String mUploadMethod;
    @SerializedName("upload_token")
    private String mUploadToken;
    @SerializedName("upload_url")
    private String mUploadUrl;

    public String getFileParameterName() {
        return mFileParameterName;
    }

    public void setFileParameterName(String fileParameterName) {
        mFileParameterName = fileParameterName;
    }

    public UploadData getUploadData() {
        return mUploadData;
    }

    public void setUploadData(UploadData uploadData) {
        mUploadData = uploadData;
    }

    public String getUploadMethod() {
        return mUploadMethod;
    }

    public void setUploadMethod(String uploadMethod) {
        mUploadMethod = uploadMethod;
    }

    public String getUploadToken() {
        return mUploadToken;
    }

    public void setUploadToken(String uploadToken) {
        mUploadToken = uploadToken;
    }

    public String getUploadUrl() {
        return mUploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        mUploadUrl = uploadUrl;
    }

}
