package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/4/19.
 */

public class NotifyContentList extends BaseModel {

    /**
     * current_page : 0
     * data : [{"id":0,"content":"string","create_time":"string","is_read":"string"}]
     * from : 0
     * to : 0
     * last_page : 0
     * next_page_url : string
     * prev_page_url : string
     * per_page : 0
     * total : 0
     */

    private int current_page;
    private int from;
    private int to;
    private int last_page;
    private String next_page_url;
    private String prev_page_url;
    private int per_page;
    private int total;
    private List<DataBean> data;

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

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0
         * content : string
         * create_time : string
         * is_read : string
         */

        private int id;
        private String content;
        private String create_time;
        private String is_read;
        private String headimg;
        public String notice_rule;
        private List<String> headimg_arr;

        public List<String> getHeadimg_arr() {
            if (headimg_arr == null)
                headimg_arr = new ArrayList<>();
            if (headimg_arr.size() == 0)
                headimg_arr.add(headimg);

            return headimg_arr;
        }

        public String getNotice_rule() {
            return notice_rule;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }
    }
}
