package com.example.bookee.eventz.create.pojos;

import com.google.gson.annotations.SerializedName;

public class UploadData {

    @SerializedName("AWSAccessKeyId")
    private String mAWSAccessKeyId;
    @SerializedName("acl")
    private String mAcl;
    @SerializedName("bucket")
    private String mBucket;
    @SerializedName("key")
    private String mKey;
    @SerializedName("policy")
    private String mPolicy;
    @SerializedName("signature")
    private String mSignature;

    public String getAWSAccessKeyId() {
        return mAWSAccessKeyId;
    }

    public void setAWSAccessKeyId(String aWSAccessKeyId) {
        mAWSAccessKeyId = aWSAccessKeyId;
    }

    public String getAcl() {
        return mAcl;
    }

    public void setAcl(String acl) {
        mAcl = acl;
    }

    public String getBucket() {
        return mBucket;
    }

    public void setBucket(String bucket) {
        mBucket = bucket;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getPolicy() {
        return mPolicy;
    }

    public void setPolicy(String policy) {
        mPolicy = policy;
    }

    public String getSignature() {
        return mSignature;
    }

    public void setSignature(String signature) {
        mSignature = signature;
    }

}
