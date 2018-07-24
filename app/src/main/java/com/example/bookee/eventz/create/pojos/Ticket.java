package com.example.bookee.eventz.create.pojos;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("auto_hide")
    private Boolean mAutoHide;
    @SerializedName("delivery_methods")
    private List<String> mDeliveryMethods;
    @SerializedName("description")
    private Object mDescription;
    @SerializedName("donation")
    private Boolean mDonation;
    @SerializedName("event_id")
    private String mEventId;
    @SerializedName("free")
    private Boolean mFree;
    @SerializedName("has_pdf_ticket")
    private Boolean mHasPdfTicket;
    @SerializedName("hidden")
    private Boolean mHidden;
    @SerializedName("hide_description")
    private Boolean mHideDescription;
    @SerializedName("id")
    private String mId;
    @SerializedName("include_fee")
    private Boolean mIncludeFee;
    @SerializedName("maximum_quantity")
    private Object mMaximumQuantity;
    @SerializedName("maximum_quantity_per_order")
    private Long mMaximumQuantityPerOrder;
    @SerializedName("maximum_quantity_per_order_without_pending")
    private Long mMaximumQuantityPerOrderWithoutPending;
    @SerializedName("minimum_quantity")
    private Long mMinimumQuantity;
    @SerializedName("name")
    private String mName;
    @SerializedName("on_sale_status")
    private String mOnSaleStatus;
    @SerializedName("quantity_sold")
    private Long mQuantitySold;
    @SerializedName("quantity_total")
    private Long mQuantityTotal;
    @SerializedName("resource_uri")
    private String mResourceUri;
    @SerializedName("sales_channels")
    private List<String> mSalesChannels;
    @SerializedName("sales_end")
    private String mSalesEnd;
    @SerializedName("sales_start")
    private String mSalesStart;
    @SerializedName("short_name")
    private String mShortName;
    @SerializedName("split_fee")
    private Boolean mSplitFee;
    @SerializedName("variant_id")
    private String mVariantId;
    @SerializedName("variants")
    private List<Object> mVariants;

    public Boolean getAutoHide() {
        return mAutoHide;
    }

    public void setAutoHide(Boolean autoHide) {
        mAutoHide = autoHide;
    }

    public List<String> getDeliveryMethods() {
        return mDeliveryMethods;
    }

    public void setDeliveryMethods(List<String> deliveryMethods) {
        mDeliveryMethods = deliveryMethods;
    }

    public Object getDescription() {
        return mDescription;
    }

    public void setDescription(Object description) {
        mDescription = description;
    }

    public Boolean getDonation() {
        return mDonation;
    }

    public void setDonation(Boolean donation) {
        mDonation = donation;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String eventId) {
        mEventId = eventId;
    }

    public Boolean getFree() {
        return mFree;
    }

    public void setFree(Boolean free) {
        mFree = free;
    }

    public Boolean getHasPdfTicket() {
        return mHasPdfTicket;
    }

    public void setHasPdfTicket(Boolean hasPdfTicket) {
        mHasPdfTicket = hasPdfTicket;
    }

    public Boolean getHidden() {
        return mHidden;
    }

    public void setHidden(Boolean hidden) {
        mHidden = hidden;
    }

    public Boolean getHideDescription() {
        return mHideDescription;
    }

    public void setHideDescription(Boolean hideDescription) {
        mHideDescription = hideDescription;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIncludeFee() {
        return mIncludeFee;
    }

    public void setIncludeFee(Boolean includeFee) {
        mIncludeFee = includeFee;
    }

    public Object getMaximumQuantity() {
        return mMaximumQuantity;
    }

    public void setMaximumQuantity(Object maximumQuantity) {
        mMaximumQuantity = maximumQuantity;
    }

    public Long getMaximumQuantityPerOrder() {
        return mMaximumQuantityPerOrder;
    }

    public void setMaximumQuantityPerOrder(Long maximumQuantityPerOrder) {
        mMaximumQuantityPerOrder = maximumQuantityPerOrder;
    }

    public Long getMaximumQuantityPerOrderWithoutPending() {
        return mMaximumQuantityPerOrderWithoutPending;
    }

    public void setMaximumQuantityPerOrderWithoutPending(Long maximumQuantityPerOrderWithoutPending) {
        mMaximumQuantityPerOrderWithoutPending = maximumQuantityPerOrderWithoutPending;
    }

    public Long getMinimumQuantity() {
        return mMinimumQuantity;
    }

    public void setMinimumQuantity(Long minimumQuantity) {
        mMinimumQuantity = minimumQuantity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOnSaleStatus() {
        return mOnSaleStatus;
    }

    public void setOnSaleStatus(String onSaleStatus) {
        mOnSaleStatus = onSaleStatus;
    }

    public Long getQuantitySold() {
        return mQuantitySold;
    }

    public void setQuantitySold(Long quantitySold) {
        mQuantitySold = quantitySold;
    }

    public Long getQuantityTotal() {
        return mQuantityTotal;
    }

    public void setQuantityTotal(Long quantityTotal) {
        mQuantityTotal = quantityTotal;
    }

    public String getResourceUri() {
        return mResourceUri;
    }

    public void setResourceUri(String resourceUri) {
        mResourceUri = resourceUri;
    }

    public List<String> getSalesChannels() {
        return mSalesChannels;
    }

    public void setSalesChannels(List<String> salesChannels) {
        mSalesChannels = salesChannels;
    }

    public String getSalesEnd() {
        return mSalesEnd;
    }

    public void setSalesEnd(String salesEnd) {
        mSalesEnd = salesEnd;
    }

    public String getSalesStart() {
        return mSalesStart;
    }

    public void setSalesStart(String salesStart) {
        mSalesStart = salesStart;
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        mShortName = shortName;
    }

    public Boolean getSplitFee() {
        return mSplitFee;
    }

    public void setSplitFee(Boolean splitFee) {
        mSplitFee = splitFee;
    }

    public String getVariantId() {
        return mVariantId;
    }

    public void setVariantId(String variantId) {
        mVariantId = variantId;
    }

    public List<Object> getVariants() {
        return mVariants;
    }

    public void setVariants(List<Object> variants) {
        mVariants = variants;
    }

}
