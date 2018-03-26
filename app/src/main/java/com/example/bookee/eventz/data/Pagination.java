
package com.example.bookee.eventz.data;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("has_more_items")
    private Boolean mHasMoreItems;
    @SerializedName("object_count")
    private Long mObjectCount;
    @SerializedName("page_count")
    private Long mPageCount;
    @SerializedName("page_number")
    private Long mPageNumber;
    @SerializedName("page_size")
    private Long mPageSize;

    public Boolean getHasMoreItems() {
        return mHasMoreItems;
    }

    public void setHasMoreItems(Boolean hasMoreItems) {
        mHasMoreItems = hasMoreItems;
    }

    public Long getObjectCount() {
        return mObjectCount;
    }

    public void setObjectCount(Long objectCount) {
        mObjectCount = objectCount;
    }

    public Long getPageCount() {
        return mPageCount;
    }

    public void setPageCount(Long pageCount) {
        mPageCount = pageCount;
    }

    public Long getPageNumber() {
        return mPageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        mPageNumber = pageNumber;
    }

    public Long getPageSize() {
        return mPageSize;
    }

    public void setPageSize(Long pageSize) {
        mPageSize = pageSize;
    }

}
