package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable{


    @SerializedName("capacity")
    private Long mCapacity;
    @SerializedName("capacity_is_custom")
    private Boolean mCapacityIsCustom;
    @SerializedName("category_id")
    private String mCategoryId;
    @SerializedName("changed")
    private String mChanged;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("currency")
    private String mCurrency;
    @SerializedName("description")
    private Description mDescription;
    @SerializedName("end")
    private End mEnd;
    @SerializedName("format_id")
    private String mFormatId;
    @SerializedName("hide_end_date")
    private Boolean mHideEndDate;
    @SerializedName("hide_start_date")
    private Boolean mHideStartDate;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_free")
    private Boolean mIsFree;
    @SerializedName("is_locked")
    private Boolean mIsLocked;
    @SerializedName("is_reserved_seating")
    private Boolean mIsReservedSeating;
    @SerializedName("is_series")
    private Boolean mIsSeries;
    @SerializedName("is_series_parent")
    private Boolean mIsSeriesParent;
    @SerializedName("listed")
    private Boolean mListed;
    @SerializedName("locale")
    private String mLocale;
    @SerializedName("logo")
    private Logo mLogo;
    @SerializedName("logo_id")
    private String mLogoId;
    @SerializedName("name")
    private Name mName;
    @SerializedName("online_event")
    private Boolean mOnlineEvent;
    @SerializedName("organization_id")
    private String mOrganizationId;
    @SerializedName("organizer_id")
    private String mOrganizerId;
    @SerializedName("privacy_setting")
    private String mPrivacySetting;
    @SerializedName("resource_uri")
    private String mResourceUri;
    @SerializedName("shareable")
    private Boolean mShareable;
    @SerializedName("source")
    private String mSource;
    @SerializedName("start")
    private Start mStart;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("subcategory_id")
    private Object mSubcategoryId;
    @SerializedName("tx_time_limit")
    private Long mTxTimeLimit;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("venue_id")
    private String mVenueId;
    @SerializedName("version")
    private String mVersion;

    public Event() {}

    public Long getCapacity() {
        return mCapacity;
    }

    public void setCapacity(Long capacity) {
        mCapacity = capacity;
    }

    public Boolean getCapacityIsCustom() {
        return mCapacityIsCustom;
    }

    public void setCapacityIsCustom(Boolean capacityIsCustom) {
        mCapacityIsCustom = capacityIsCustom;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getChanged() {
        return mChanged;
    }

    public void setChanged(String changed) {
        mChanged = changed;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public Description getDescription() {
        return mDescription;
    }

    public void setDescription(Description description) {
        mDescription = description;
    }

    public End getEnd() {
        return mEnd;
    }

    public void setEnd(End end) {
        mEnd = end;
    }

    public String getFormatId() {
        return mFormatId;
    }

    public void setFormatId(String formatId) {
        mFormatId = formatId;
    }

    public Boolean getHideEndDate() {
        return mHideEndDate;
    }

    public void setHideEndDate(Boolean hideEndDate) {
        mHideEndDate = hideEndDate;
    }

    public Boolean getHideStartDate() {
        return mHideStartDate;
    }

    public void setHideStartDate(Boolean hideStartDate) {
        mHideStartDate = hideStartDate;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIsFree() {
        return mIsFree;
    }

    public void setIsFree(Boolean isFree) {
        mIsFree = isFree;
    }

    public Boolean getIsLocked() {
        return mIsLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        mIsLocked = isLocked;
    }

    public Boolean getIsReservedSeating() {
        return mIsReservedSeating;
    }

    public void setIsReservedSeating(Boolean isReservedSeating) {
        mIsReservedSeating = isReservedSeating;
    }

    public Boolean getIsSeries() {
        return mIsSeries;
    }

    public void setIsSeries(Boolean isSeries) {
        mIsSeries = isSeries;
    }

    public Boolean getIsSeriesParent() {
        return mIsSeriesParent;
    }

    public void setIsSeriesParent(Boolean isSeriesParent) {
        mIsSeriesParent = isSeriesParent;
    }

    public Boolean getListed() {
        return mListed;
    }

    public void setListed(Boolean listed) {
        mListed = listed;
    }

    public String getLocale() {
        return mLocale;
    }

    public void setLocale(String locale) {
        mLocale = locale;
    }

    public Logo getLogo() {
        return mLogo;
    }

    public void setLogo(Logo logo) {
        mLogo = logo;
    }

    public String getLogoId() {
        return mLogoId;
    }

    public void setLogoId(String logoId) {
        mLogoId = logoId;
    }

    public Name getName() {
        return mName;
    }

    public void setName(Name name) {
        mName = name;
    }

    public Boolean getOnlineEvent() {
        return mOnlineEvent;
    }

    public void setOnlineEvent(Boolean onlineEvent) {
        mOnlineEvent = onlineEvent;
    }

    public String getOrganizationId() {
        return mOrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        mOrganizationId = organizationId;
    }

    public String getOrganizerId() {
        return mOrganizerId;
    }

    public void setOrganizerId(String organizerId) {
        mOrganizerId = organizerId;
    }

    public String getPrivacySetting() {
        return mPrivacySetting;
    }

    public void setPrivacySetting(String privacySetting) {
        mPrivacySetting = privacySetting;
    }

    public String getResourceUri() {
        return mResourceUri;
    }

    public void setResourceUri(String resourceUri) {
        mResourceUri = resourceUri;
    }

    public Boolean getShareable() {
        return mShareable;
    }

    public void setShareable(Boolean shareable) {
        mShareable = shareable;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public Start getStart() {
        return mStart;
    }

    public void setStart(Start start) {
        mStart = start;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Object getSubcategoryId() {
        return mSubcategoryId;
    }

    public void setSubcategoryId(Object subcategoryId) {
        mSubcategoryId = subcategoryId;
    }

    public Long getTxTimeLimit() {
        return mTxTimeLimit;
    }

    public void setTxTimeLimit(Long txTimeLimit) {
        mTxTimeLimit = txTimeLimit;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getVenueId() {
        return mVenueId;
    }

    public void setVenueId(String venueId) {
        mVenueId = venueId;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mCategoryId='" + mCategoryId + '\'' +
                ", mCurrency='" + mCurrency + '\'' +
                ", mEnd=" + mEnd +
                ", mId='" + mId + '\'' +
                ", mLogo=" + mLogo +
                ", mName=" + mName +
                ", mStart=" + mStart +
                '}';
    }

}
