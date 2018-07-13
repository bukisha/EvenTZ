package com.example.bookee.eventz.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable{


    @SerializedName("capacity")
    @Expose
    private Long capacity;
    @SerializedName("capacity_is_custom")
    @Expose
    private Boolean capacity_is_custom;
    @SerializedName("category_id")
    @Expose
    private String category_id;
    @SerializedName("changed")
    @Expose
    private String changed;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("end")
    @Expose
    private End end;
    @SerializedName("format_id")
    @Expose
    private String format_id;
    @SerializedName("hide_end_date")
    @Expose
    private Boolean hide_end_date;
    @SerializedName("hide_start_date")
    @Expose
    private Boolean hide_start_date;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_free")
    @Expose
    private Boolean is_free;
    @SerializedName("is_locked")
    @Expose
    private Boolean is_locked;
    @SerializedName("is_reserved_seating")
    @Expose
    private Boolean is_reserved_seating;
    @SerializedName("is_series")
    @Expose
    private Boolean is_series;
    @SerializedName("is_series_parent")
    @Expose
    private Boolean is_series_parent;
    @SerializedName("listed")
    @Expose
    private Boolean listed;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("logo")
    @Expose
    private Logo logo;
    @SerializedName("logo_id")
    @Expose
    private String logo_id;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("online_event")
    @Expose
    private Boolean online_event;
    @SerializedName("organization_id")
    @Expose
    private String organization_id;
    @SerializedName("organizer_id")
    @Expose
    private String organizer_id;
    @SerializedName("privacy_setting")
    @Expose
    private String privacy_setting;
    @SerializedName("resource_uri")
    @Expose
    private String resource_url;
    @SerializedName("shareable")
    @Expose
    private Boolean shareable;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subcategory_id")
    @Expose
    private Object subcategory_id;
    @SerializedName("tx_time_limit")
    @Expose
    private Long tx_time_limit;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("venue_id")
    @Expose
    private String venue_id;
    @SerializedName("version")
    @Expose
    private String version;

    public Event(String currency, End end, Name name, Start start) {
        this.currency = currency;
        this.end = end;
        this.name = name;
        this.start = start;
    }
    public Event() {}
    public Event(Long capacity, Boolean capacity_is_custom, String category_id, String changed, String created, String currency, Description description, End end, String format_id, Boolean hide_end_date, Boolean hide_start_date, String id, Boolean is_free, Boolean is_locked, Boolean is_reserved_seating, Boolean is_series, Boolean is_series_parent, Boolean listed, String locale, Logo logo, String logo_id, Name name, Boolean online_event, String organization_id, String organizer_id, String privacy_setting, String resource_url, Boolean shareable, String source, Start start, String status, Object subcategory_id, Long tx_time_limit, String url, String venue_id, String version) {
        this.capacity = capacity;
        this.capacity_is_custom = capacity_is_custom;
        this.category_id = category_id;
        this.changed = changed;
        this.created = created;
        this.currency = currency;
        this.description = description;
        this.end = end;
        this.format_id = format_id;
        this.hide_end_date = hide_end_date;
        this.hide_start_date = hide_start_date;
        this.id = id;
        this.is_free = is_free;
        this.is_locked = is_locked;
        this.is_reserved_seating = is_reserved_seating;
        this.is_series = is_series;
        this.is_series_parent = is_series_parent;
        this.listed = listed;
        this.locale = locale;
        this.logo = logo;
        this.logo_id = logo_id;
        this.name = name;
        this.online_event = online_event;
        this.organization_id = organization_id;
        this.organizer_id = organizer_id;
        this.privacy_setting = privacy_setting;
        this.resource_url = resource_url;
        this.shareable = shareable;
        this.source = source;
        this.start = start;
        this.status = status;
        this.subcategory_id = subcategory_id;
        this.tx_time_limit = tx_time_limit;
        this.url = url;
        this.venue_id = venue_id;
        this.version = version;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Boolean getCapacityIsCustom() {
        return capacity_is_custom;
    }

    public void setCapacityIsCustom(Boolean capacityIsCustom) {
        capacity_is_custom = capacityIsCustom;
    }

    public String getCategoryId() {
        return category_id;
    }

    public void setCategoryId(String categoryId) {
        category_id = categoryId;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public String getFormatId() {
        return format_id;
    }

    public void setFormatId(String formatId) {
        format_id = formatId;
    }

    public Boolean getHideEndDate() {
        return hide_end_date;
    }

    public void setHideEndDate(Boolean hideEndDate) {
        hide_end_date = hideEndDate;
    }

    public Boolean getHideStartDate() {
        return hide_start_date;
    }

    public void setHideStartDate(Boolean hideStartDate) {
        hide_start_date = hideStartDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsFree() {
        return is_free;
    }

    public void setIsFree(Boolean isFree) {
        is_free = isFree;
    }

    public Boolean getIsLocked() {
        return is_locked;
    }

    public void setIsLocked(Boolean isLocked) {
        is_locked = isLocked;
    }

    public Boolean getIsReservedSeating() {
        return is_reserved_seating;
    }

    public void setIsReservedSeating(Boolean isReservedSeating) {
        is_reserved_seating = isReservedSeating;
    }

    public Boolean getIsSeries() {
        return is_series;
    }

    public void setIsSeries(Boolean isSeries) {
        is_series = isSeries;
    }

    public Boolean getIsSeriesParent() {
        return is_series_parent;
    }

    public void setIsSeriesParent(Boolean isSeriesParent) {
        is_series_parent = isSeriesParent;
    }

    public Boolean getListed() {
        return listed;
    }

    public void setListed(Boolean listed) {
        this.listed = listed;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String getLogoId() {
        return logo_id;
    }

    public void setLogoId(String logoId) {
        logo_id = logoId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Boolean getOnlineEvent() {
        return online_event;
    }

    public void setOnlineEvent(Boolean onlineEvent) {
        online_event = onlineEvent;
    }

    public String getOrganizationId() {
        return organization_id;
    }

    public void setOrganizationId(String organizationId) {
        organization_id = organizationId;
    }

    public String getOrganizerId() {
        return organizer_id;
    }

    public void setOrganizerId(String organizerId) {
        organizer_id = organizerId;
    }

    public String getPrivacySetting() {
        return privacy_setting;
    }

    public void setPrivacySetting(String privacySetting) {
        privacy_setting = privacySetting;
    }

    public String getResourceUri() {
        return resource_url;
    }

    public void setResourceUri(String resourceUri) {
        resource_url = resourceUri;
    }

    public Boolean getShareable() {
        return shareable;
    }

    public void setShareable(Boolean shareable) {
        this.shareable = shareable;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getSubcategoryId() {
        return subcategory_id;
    }

    public void setSubcategoryId(Object subcategoryId) {
        subcategory_id = subcategoryId;
    }

    public Long getTxTimeLimit() {
        return tx_time_limit;
    }

    public void setTxTimeLimit(Long txTimeLimit) {
        tx_time_limit = txTimeLimit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVenueId() {
        return venue_id;
    }

    public void setVenueId(String venueId) {
        venue_id = venueId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Event{" +
                "category_id='" + category_id + '\'' +
                ", currency='" + currency + '\'' +
                ", end=" + end +
                ", id='" + id + '\'' +
                ", logo=" + logo +
                ", name=" + name +
                ", start=" + start +
                '}';
    }

}
