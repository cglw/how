package com.prettyyes.user.model.Suit;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/5/3.
 */

public class ReplyGoods extends BaseModel{




    /**
     * sku_id : 0
     * share_status : 0
     * desc : string
     * type : string
     * name : string
     * cover_img : string
     * status : 0
     * price : string
     * like_num : 0
     * view_num : 0
     * create_time : string
     * task_list : [{"answer_type":"string","paper_id":0,"suit_id":0,"taobao_id":0,"task_id":0,"desc":"string","ts_reason":"string","answer_id":0,"answer_like_num":0,"answer_dislike_num":"string","like":0,"dislike":0,"share_url":"string"}]
     * task_count : 0
     */


    private List<TaskListBean> task_list;
    /**
     * spu_id : 14
     * uid : 31397
     * module_id : 101
     * spu_type : paper
     * spu_name : 测试
     * spu_desc : &lt;img src=sdsdsadsa /&gt;
     * main_img : http://img.prettyyes.com/seller-laravel-31397-2519-1479783449.jpeg
     * small_img :
     * spu_price : 2.00
     * express_price : 0
     * share_status : 0
     * like_num : 0
     * buy_count : 0
     * view_num : 11
     * share_url : null
     * comment_count : 0
     * task_count : 0
     */

    private String spu_id;
    private int uid;
    private String module_id;
    private String spu_type;
    private String spu_name;
    private String spu_desc;
    private String main_img;
    private String small_img;
    private String spu_price;
    private String express_price;
    private int share_status;
    private int like_num;
    private int buy_count;
    private int view_num;
    private String share_url;
    private int comment_count;
    private int task_count;

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getSpu_type() {
        return spu_type;
    }

    public void setSpu_type(String spu_type) {
        this.spu_type = spu_type;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_desc() {
        return spu_desc;
    }

    public void setSpu_desc(String spu_desc) {
        this.spu_desc = spu_desc;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getSmall_img() {
        return small_img;
    }

    public void setSmall_img(String small_img) {
        this.small_img = small_img;
    }

    public String getSpu_price() {
        return spu_price;
    }

    public void setSpu_price(String spu_price) {
        this.spu_price = spu_price;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public int getShare_status() {
        return share_status;
    }

    public void setShare_status(int share_status) {
        this.share_status = share_status;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getTask_count() {
        return task_count;
    }

    public void setTask_count(int task_count) {
        this.task_count = task_count;
    }


    public static class TaskListBean {
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

        public ShareModel getShare_model() {
            return share_model;
        }

        public void setShare_model(ShareModel share_model) {
            this.share_model = share_model;
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

    public List<TaskListBean> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<TaskListBean> task_list) {
        this.task_list = task_list;
    }
}
