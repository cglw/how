package com.prettyyes.user.model.Suit;

import com.prettyyes.user.app.ui.appview.ShareModel;

import java.util.List;

/**
 * Created by chengang on 2017/5/3.
 */

public class AnswerListBySku {


    /**
     * current_page : 0
     * data : [{"answer_type":"string","paper_id":0,"suit_id":0,"taobao_id":0,"task_id":0,"desc":"string","ts_reason":"string","answer_id":0,"answer_like_num":0,"answer_dislike_num":"string","like":0,"dislike":0,"share_url":"string"}]
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
         * answer_type : string
         * paper_id : 0
         * suit_id : 0
         * taobao_id : 0
         * task_id : 0
         * desc : string
         * ts_reason : string
         * answer_id : 0
         * answer_like_num : 0
         * answer_dislike_num : string
         * like : 0
         * dislike : 0
         * share_url : string
         */

        private String answer_type;
        private int paper_id;
        private int suit_id;
        private int taobao_id;
        private int task_id;
        private String desc;
        private String ts_reason;
        private int answer_id;
        private int answer_like_num;
        private int answer_dislike_num;
        private int like;
        private int dislike;
        private String share_url;
        private ShareModel share_model;
        private String hash_tag;
        private int task_act_id;
        private int topic_id;
        private String topic_hash_tag;

        public ShareModel getShare_model() {
            return share_model;
        }

        public void setShare_model(ShareModel share_model) {
            this.share_model = share_model;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public String getTopic_hash_tag() {
            return topic_hash_tag;
        }

        public void setTopic_hash_tag(String topic_hash_tag) {
            this.topic_hash_tag = topic_hash_tag;
        }

        public int getTask_act_id() {
            return task_act_id;
        }

        public void setTask_act_id(int task_act_id) {
            this.task_act_id = task_act_id;
        }
        public String getHash_tag() {
            return hash_tag;
        }

        public void setHash_tag(String hash_tag) {
            this.hash_tag = hash_tag;
        }

        public String getAnswer_type() {
            return answer_type;
        }

        public void setAnswer_type(String answer_type) {
            this.answer_type = answer_type;
        }

        public int getPaper_id() {
            return paper_id;
        }

        public void setPaper_id(int paper_id) {
            this.paper_id = paper_id;
        }

        public int getSuit_id() {
            return suit_id;
        }

        public void setSuit_id(int suit_id) {
            this.suit_id = suit_id;
        }

        public int getTaobao_id() {
            return taobao_id;
        }

        public void setTaobao_id(int taobao_id) {
            this.taobao_id = taobao_id;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTs_reason() {
            return ts_reason;
        }

        public void setTs_reason(String ts_reason) {
            this.ts_reason = ts_reason;
        }

        public int getAnswer_id() {
            return answer_id;
        }

        public void setAnswer_id(int answer_id) {
            this.answer_id = answer_id;
        }

        public int getAnswer_like_num() {
            return answer_like_num;
        }

        public void setAnswer_like_num(int answer_like_num) {
            this.answer_like_num = answer_like_num;
        }

        public int getAnswer_dislike_num() {
            return answer_dislike_num;
        }

        public void setAnswer_dislike_num(int answer_dislike_num) {
            this.answer_dislike_num = answer_dislike_num;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getDislike() {
            return dislike;
        }

        public void setDislike(int dislike) {
            this.dislike = dislike;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }
    }
}
