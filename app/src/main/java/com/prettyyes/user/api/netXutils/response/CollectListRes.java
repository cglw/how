package com.prettyyes.user.api.netXutils.response;

import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/6.
 */

public class CollectListRes extends BaseRes{

    private int current_page;
    private List<SpuInfoEntity> data;
    /**
     * from : 1
     * last_page : 1
     * next_page_url : null
     * path : http://test.prettyyes.com/app/spu/favouriteList
     * per_page : 20
     * prev_page_url : null
     * to : 5
     * total : 5
     */

    private int from;
    private int last_page;
    private Object next_page_url;
    private String path;
    private int per_page;
    private Object prev_page_url;
    private int to;
    private int total;

    public List<SpuInfoEntity> getData() {
        return data;
    }

    public void setData(List<SpuInfoEntity> data) {
        this.data = data;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }


    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public Object getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(Object next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public Object getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(Object prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
