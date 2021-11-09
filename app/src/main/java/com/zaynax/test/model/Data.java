package com.zaynax.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("nextPage")
    @Expose
    private Integer nextPage;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

}
