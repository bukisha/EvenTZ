
package com.example.bookee.eventz.data.pojos;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("has_more_items")
    private Boolean has_more_items;
    @SerializedName("object_count")
    private Long object_count;
    @SerializedName("page_count")
    private Long page_count;
    @SerializedName("page_number")
    private Long page_number;
    @SerializedName("page_size")
    private Long page_size;

    public Boolean getHasMoreItems() {
        return has_more_items;
    }

    public void setHasMoreItems(Boolean hasMoreItems) {
        has_more_items = hasMoreItems;
    }

    public Long getObjectCount() {
        return object_count;
    }

    public void setObjectCount(Long objectCount) {
        object_count = objectCount;
    }

    public Long getPageCount() {
        return page_count;
    }

    public void setPageCount(Long pageCount) {
        page_count = pageCount;
    }

    public Long getPageNumber() {
        return page_number;
    }

    public void setPageNumber(Long pageNumber) {
        page_number = pageNumber;
    }

    public Long getPageSize() {
        return page_size;
    }

    public void setPageSize(Long pageSize) {
        page_size = pageSize;
    }

}
