package com.prettyyes.user.model.task;

import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.v8.SellerInfoEntity;
import com.prettyyes.user.model.v8.AnswerInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.task
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class TaskHomeTask extends BaseModel {

    public int getLayout_id() {
        return layout_id;
    }

    public void setLayout_id(int layout_id) {
        this.layout_id = layout_id;
    }

    /**
     * task_id : 11438
     * desc : 很简单的藏蓝色T，除了同样简单的素色裤子，不知道搭什么能不那么朴素
     * uid : 32453
     * pic_list : http://img.prettyyes.com/h5-9370-1470053181.jpeg
     * like_num : 3
     * dislike_num : 2
     * share_num : 60
     * user_name : Slowlife大小姐
     * is_open : 0
     * share_url : http://test.prettyyes.com/main_questions/share?task_id=11438
     * like : 0
     * dislike : 0
     * answer_info : {"seller_id":32453,"task_id":11438,"answer_type":TYPE_TAOBAO,"ts_reason":"Test go go","nickname":"Slowlife大小姐","headimg":"http://wx.qlogo.cn/mmopen/8GKKLgN5M7Vz8AT0RPHdhgyUb2ljIAC4oQytPdUxnmBiaIhD6BKqrjskGicibpg1XHGH0vQ6dDGXYy4Q9ibl7iaPrbJ06BkTMlbL1/0","information":null,"ace_txt":null,"answer_id":84335,"answer_like_num":0,"answer_dislike_num":0,"answer_data":{"taobao_id":50,"seller_id":1085,"img_arr":["http://img2.tbcdn.cn/tfscom/i2/61908958/TB2hwr7kFXXXXbUXpXXXXXXXXXX_!!61908958.jpg","http://img4.tbcdn.cn/tfscom/i1/61908958/TB2xNklkFXXXXXoXpXXXXXXXXXX_!!61908958.jpg","http://img1.tbcdn.cn/tfscom/i3/61908958/TB2twZnkFXXXXXnXpXXXXXXXXXX_!!61908958.jpg","http://img.prettyyes.com/seller-laravel-1085-2684-1474351227.jpeg"]}}
     */








    private String update_date;
    private String new_task;
    private String home_task_count;
    private String ask_text;
    private String ask_color;


    public String getAsk_text() {
        return ask_text;
    }

    public void setAsk_text(String ask_text) {
        this.ask_text = ask_text;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getNew_task() {
        return new_task;
    }

    public void setNew_task(String new_task) {
        this.new_task = new_task;
    }

    public String getHome_task_count() {
        return home_task_count;
    }

    public void setHome_task_count(String home_task_count) {
        this.home_task_count = home_task_count;
    }

    public String getAsk_color() {
        return ask_color;
    }

    public void setAsk_color(String ask_color) {
        this.ask_color = ask_color;
    }

    private int layout_id;
    private int task_id;
    private String desc;
    private int uid;
    private String pic_list;
    private int like_num;
    private int dislike_num;
    private int share_num;
    private String nickname;
    private int is_open;
    private String share_url;
    private int like;
    private int dislike;


    private int famous;
    private int excellent;
    private String pricecount;
    private String Pscore;
    private String hash_tag;
    private String topic_hash_tag;
    private int topic_id;
    private int task_act_id;
    private int dis_excellent;

    private List<SellerInfoEntity> famous_kol;
    private String task_describe_text;
    private String type;

    public String getTopic_hash_tag() {
        return topic_hash_tag;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setTopic_hash_tag(String topic_hash_tag) {
        this.topic_hash_tag = topic_hash_tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTask_describe_text() {
        return task_describe_text;
    }

    public void setTask_describe_text(String task_describe_text) {
        this.task_describe_text = task_describe_text;
    }

    public List<SellerInfoEntity> getFamous_kol() {
        return famous_kol;
    }

    public void setFamous_kol(List<SellerInfoEntity> famous_kol) {
        this.famous_kol = famous_kol;
    }

    public int getDis_excellent() {
        return dis_excellent;
    }

    public void setDis_excellent(int dis_excellent) {
        this.dis_excellent = dis_excellent;
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

    public int getFamous() {
        return famous;
    }

    public void setFamous(int famous) {
        this.famous = famous;
    }

    public int getExcellent() {
        return excellent;
    }

    public void setExcellent(int excellent) {
        this.excellent = excellent;
    }

    public String getPricecount() {
        return pricecount;
    }

    public void setPricecount(String pricecount) {
        this.pricecount = pricecount;
    }

    public String getPscore() {
        return Pscore;
    }

    public void setPscore(String pscore) {
        Pscore = pscore;
    }

    /**
     * seller_id : 32453
     * task_id : 11438
     * answer_type : taobao
     * ts_reason : Test go go
     * nickname : Slowlife大小姐
     * headimg : http://wx.qlogo.cn/mmopen/8GKKLgN5M7Vz8AT0RPHdhgyUb2ljIAC4oQytPdUxnmBiaIhD6BKqrjskGicibpg1XHGH0vQ6dDGXYy4Q9ibl7iaPrbJ06BkTMlbL1/0
     * information : null
     * ace_txt : null
     * answer_id : 84335
     * answer_like_num : 0
     * answer_dislike_num : 0
     * answer_data : {"taobao_id":50,"seller_id":1085,"img_arr":["http://img2.tbcdn.cn/tfscom/i2/61908958/TB2hwr7kFXXXXbUXpXXXXXXXXXX_!!61908958.jpg","http://img4.tbcdn.cn/tfscom/i1/61908958/TB2xNklkFXXXXXoXpXXXXXXXXXX_!!61908958.jpg","http://img1.tbcdn.cn/tfscom/i3/61908958/TB2twZnkFXXXXXnXpXXXXXXXXXX_!!61908958.jpg","http://img.prettyyes.com/seller-laravel-1085-2684-1474351227.jpeg"]}
     */

    private AnswerInfo answer_info;

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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPic_list() {
        return pic_list;
    }

    public void setPic_list(String pic_list) {
        this.pic_list = pic_list;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getDislike_num() {
        return dislike_num;
    }

    public void setDislike_num(int dislike_num) {
        this.dislike_num = dislike_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public String getUser_name() {
        if (nickname == null)
            nickname = "";
        return nickname;
    }

    public void setUser_name(String user_name) {
        this.nickname = user_name;
    }

    public int getIs_open() {
        return is_open;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
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

    public AnswerInfo getAnswer_info() {
        return answer_info;
    }

    public void setAnswer_info(AnswerInfo answer_info) {
        this.answer_info = answer_info;
    }

    public static class AnswerInfoEntity implements Serializable {
        private int seller_id;
        private int task_id;
        private String answer_type;
        private String ts_reason;
        private String nickname;
        private String headimg;
        private String information;
        private String ace_txt;
        private int answer_id;
        private int answer_like_num;
        private int answer_dislike_num;
        private int famous_type = 0;

        public int getFamous_type() {
            return famous_type;
        }

        public void setFamous_type(int famous_type) {
            this.famous_type = famous_type;
        }

        /**
         * taobao_id : 50
         * seller_id : 1085
         * img_arr : ["http://img2.tbcdn.cn/tfscom/i2/61908958/TB2hwr7kFXXXXbUXpXXXXXXXXXX_!!61908958.jpg","http://img4.tbcdn.cn/tfscom/i1/61908958/TB2xNklkFXXXXXoXpXXXXXXXXXX_!!61908958.jpg","http://img1.tbcdn.cn/tfscom/i3/61908958/TB2twZnkFXXXXXnXpXXXXXXXXXX_!!61908958.jpg","http://img.prettyyes.com/seller-laravel-1085-2684-1474351227.jpeg"]
         */

        private AnswerDataEntity answer_data;

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getAnswer_type() {
            return answer_type;
        }

        public void setAnswer_type(String answer_type) {
            this.answer_type = answer_type;
        }

        public String getTs_reason() {
            return ts_reason;
        }

        public void setTs_reason(String ts_reason) {
            this.ts_reason = ts_reason;
        }

        public String getNickname() {
            if (nickname == null)
                nickname = "";
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getAce_txt() {
            if (ace_txt == null)
                ace_txt = "";
            return ace_txt;
        }

        public void setAce_txt(String ace_txt) {
            this.ace_txt = ace_txt;
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

        public AnswerDataEntity getAnswer_data() {
            return answer_data;
        }

        public void setAnswer_data(AnswerDataEntity answer_data) {
            this.answer_data = answer_data;
        }

        public static class AnswerDataEntity implements Serializable{
            private int seller_id;

            private int taobao_id;
            private List<String> img_arr;

            private String title;
            private int suit_id;
            private List<String> suit_img_arr;
            private int share_status = 0;
            private String brand_id;
            private String suit_name;
            private String brand_name;
            private String brand_image;
            private String category_name;
            private int img_id;
            private String img_str;
            private String audio_id;
            private String audio_str;
            private String video_id;
            private String video_str;
            private String video_cover_img;
            private String paper_id;
            private String desc;
            private String price;
            private String paper_name;
            private String simple_desc;
            private String word_num;
            private String reward_status;//1打赏过 可阅读 0未打赏
            private String paper_image;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPaper_image() {
                return paper_image;
            }

            public void setPaper_image(String paper_image) {
                this.paper_image = paper_image;
            }

            public boolean isHaveReward() {
                if (reward_status.equals("1")) {
                    return true;
                } else {
                    return false;
                }
            }

            public String getSuit_name() {
                return suit_name;
            }

            public void setSuit_name(String suit_name) {
                this.suit_name = suit_name;
            }

            public int getShare_status() {
                return share_status;
            }

            public void setShare_status(int share_status) {
                this.share_status = share_status;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getBrand_image() {
                return brand_image;
            }

            public void setBrand_image(String brand_image) {
                this.brand_image = brand_image;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getPaper_id() {
                return paper_id;
            }

            public void setPaper_id(String paper_id) {
                this.paper_id = paper_id;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPaper_name() {
                return paper_name;
            }

            public void setPaper_name(String paper_name) {
                this.paper_name = paper_name;
            }

            public String getSimple_desc() {
                return simple_desc;
            }

            public void setSimple_desc(String simple_desc) {
                this.simple_desc = simple_desc;
            }

            public String getWord_num() {
                return word_num;
            }

            public void setWord_num(String word_num) {
                this.word_num = word_num;
            }

            public String getReward_status() {
                return reward_status;
            }

            public void setReward_status(String reward_status) {
                this.reward_status = reward_status;
            }

            public String getAudio_id() {
                return audio_id;
            }

            public void setAudio_id(String audio_id) {
                this.audio_id = audio_id;
            }

            public String getAudio_str() {
                return audio_str;
            }

            public void setAudio_str(String audio_str) {
                this.audio_str = audio_str;
            }

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

            public String getVideo_str() {
                return video_str;
            }

            public void setVideo_str(String video_str) {
                this.video_str = video_str;
            }

            public String getVideo_cover_img() {
                return video_cover_img;
            }

            public void setVideo_cover_img(String video_cover_img) {
                this.video_cover_img = video_cover_img;
            }

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public String getImg_str() {
                return img_str;
            }

            public void setImg_str(String img_str) {
                this.img_str = img_str;
            }

            public int getSuit_id() {
                return suit_id;
            }

            public void setSuit_id(int suit_id) {
                this.suit_id = suit_id;
            }

            public List<String> getSuit_img_arr() {
                return suit_img_arr;
            }

            public void setSuit_img_arr(List<String> suit_img_arr) {
                this.suit_img_arr = suit_img_arr;
            }

            public int getTaobao_id() {
                return taobao_id;
            }

            public void setTaobao_id(int taobao_id) {
                this.taobao_id = taobao_id;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public List<String> getImg_arr() {
                return img_arr;
            }

            public void setImg_arr(List<String> img_arr) {
                this.img_arr = img_arr;
            }
        }
    }


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * act_id : 0
         * act_content : string
         * status : 0
         * act_name : string
         * act_img : string
         * create_time : string
         * start_time : string
         * end_time : string
         * hash_tag : string
         * seller_list : [{"seller_id":0,"headimg":"string","nickname":"string","ace_txt":"string","famous_type":"string","ingredient":[null],"session_ace_txt":"string"}]
         */

        private int act_id;
        private String act_content;
        private int status;
        private String act_name;
        private String act_img;
        private String create_time;
        private String start_time;
        private String end_time;
        private String hash_tag;
        private List<SellerListBean> seller_list;

        public int getAct_id() {
            return act_id;
        }

        public void setAct_id(int act_id) {
            this.act_id = act_id;
        }

        public String getAct_content() {
            return act_content;
        }

        public void setAct_content(String act_content) {
            this.act_content = act_content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAct_name() {
            return act_name;
        }

        public void setAct_name(String act_name) {
            this.act_name = act_name;
        }

        public String getAct_img() {
            return act_img;
        }

        public void setAct_img(String act_img) {
            this.act_img = act_img;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getHash_tag() {
            return hash_tag;
        }

        public void setHash_tag(String hash_tag) {
            this.hash_tag = hash_tag;
        }

        public List<SellerListBean> getSeller_list() {
            return seller_list;
        }

        public void setSeller_list(List<SellerListBean> seller_list) {
            this.seller_list = seller_list;
        }

        public static class SellerListBean {
            /**
             * seller_id : 0
             * headimg : string
             * nickname : string
             * ace_txt : string
             * famous_type : string
             * ingredient : [null]
             * session_ace_txt : string
             */

            private int seller_id;
            private String headimg;
            private String nickname;
            private String ace_txt;
            private String famous_type;
            private String session_ace_txt;
            private List<String> ingredient;

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAce_txt() {
                return ace_txt;
            }

            public void setAce_txt(String ace_txt) {
                this.ace_txt = ace_txt;
            }

            public String getFamous_type() {
                return famous_type;
            }

            public void setFamous_type(String famous_type) {
                this.famous_type = famous_type;
            }

            public String getSession_ace_txt() {
                return session_ace_txt;
            }

            public void setSession_ace_txt(String session_ace_txt) {
                this.session_ace_txt = session_ace_txt;
            }

            public List<String> getIngredient() {
                return ingredient;
            }

            public void setIngredient(List<String> ingredient) {
                this.ingredient = ingredient;
            }
        }
    }

    @Override
    public String toString() {
        return "TaskHomeTask{" +
                "task_id=" + task_id +
                ", desc='" + desc + '\'' +
                ", uid=" + uid +
                ", pic_list='" + pic_list + '\'' +
                ", like_num=" + like_num +
                ", dislike_num=" + dislike_num +
                ", share_num=" + share_num +
                ", is_open=" + is_open +
                ", share_url='" + share_url + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                ", answer_info=" + answer_info +
                '}';
    }
}
