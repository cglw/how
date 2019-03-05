package com.prettyyes.user.model.common;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.v8.AnswerDataBean;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.common
 * Author: SmileChen
 * Created on: 2016/12/20
 * Description: Nothing
 */
public class ReplyModel {
    private int seller_id;
    private int task_id;
    private String answer_type;
    private String ts_reason;
    private String nickname;
    private String headimg;
    private String ts_createtime;
    private String information;
    private String ace_txt;
    private int answer_id;
    private int answer_like_num;
    private int answer_dislike_num;
    private int best_answer;
    private String best_answer_text;
//    private boolean answer_like;
//    private boolean answer_dislike;
    private String share_url;
    private ShareModel share_model;
    private AnswerDataBean answer_data;
    private String question;
    private int famous_type = 0;

    public ShareModel getShare_model() {
        return share_model;
    }

    public void setShare_model(ShareModel share_model) {
        this.share_model = share_model;
    }

    public String getTs_createtime() {
        return ts_createtime;
    }

    public void setTs_createtime(String ts_createtime) {
        this.ts_createtime = ts_createtime;
    }

    public int getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(int famous_type) {
        this.famous_type = famous_type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private int layout_id;//paperview弹出需要的布局

    public int getLayout_id() {
        return layout_id;
    }

    public void setLayout_id(int layout_id) {
        this.layout_id = layout_id;
    }

    public AnswerDataBean getAnswer() {
        return answer_data;
    }

    public void setAnswer(AnswerDataBean answer) {
        this.answer_data = answer;
    }

    public static class Answer {
        private int seller_id;
        private String type;
        private int task_id;


        private int taobao_id;
        private List<String> img_arr;
        private int suit_id;
        private List<String> suit_img_arr;
        private int totalwith;
        private int margin;

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

        private int sku_id;


        public int getTaobao_id() {
            return sku_id;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }


        public List<String> getImg_arr() {
            return img_arr;
        }

        public void setImg_arr(List<String> img_arr) {
            this.img_arr = img_arr;
        }

        public int getSuit_id() {
            return sku_id;
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

        public int getTotalwith() {
            return totalwith;
        }

        public void setTotalwith(int totalwith) {
            this.totalwith = totalwith;
        }


        public String getImg_str() {
            return img_str;
        }

        public String getAudio_str() {
            return audio_str;
        }

        public String getVideo_str() {
            return video_str;
        }


        public String getVideo_cover_img() {
            return video_cover_img;
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

        public String getPaper_image() {
            return paper_image;
        }

        public void setPaper_image(String paper_image) {
            this.paper_image = paper_image;
        }
    }

    public String getTs_reason() {
        return ts_reason;
    }

    public void setTs_reason(String ts_reason) {
        this.ts_reason = ts_reason;
    }

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

    public String getNickname() {
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

    public int getBest_answer() {
        return best_answer;
    }

    public void setBest_answer(int best_answer) {
        this.best_answer = best_answer;
    }

    public String getBest_answer_text() {
        return best_answer_text;
    }

    public void setBest_answer_text(String best_answer_text) {
        this.best_answer_text = best_answer_text;
    }

//    public boolean isAnswer_like() {
//        return answer_like;
//    }
//
//    public void setAnswer_like(boolean answer_like) {
//        this.answer_like = answer_like;
//    }
//
//    public boolean isAnswer_dislike() {
//        return answer_dislike;
//    }
//
//    public void setAnswer_dislike(boolean answer_dislike) {
//        this.answer_dislike =answer_dislike;
//    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
