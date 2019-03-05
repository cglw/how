package com.prettyyes.user.model.user;

import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class UserSellerIntroducefromuser extends BaseModel{


    /**
     * seller_id : 1085
     * user_security : 学习学习
     二十四史
     * share_num : 2
     * tags : [{"tag_name":"搭配好手","tag_id":247},{"tag_name":"挑货高手","tag_id":248}]
     * headimg : http://img.prettyyes.com/seller-laravel-1085-6637-1471839612.jpeg
     * ace_txt : &lt;p&gt;xiao xin coolllllll!!!&lt;/p&gt;
     * is_like : no
     * nickname : 雪梨php^_^
     * pei_num : 0
     * zeze_num : 1
     * share_link : http://test.prettyyes.com/index8/share/seller?link=celebrity%3A%2F%2Fseller_id%3D1085&id=1085
     * favourite_num : 0
     * num_list : [{"name":"全部","num":58},{"name":"套系","num":17},{"name":"杂货","num":0}]
     * suit_img : [{"cover_img":"http://img.prettyyes.com/1085-2239-1451657775.jpeg"},{"cover_img":"http://img.prettyyes.com/1085-9869-1445859408.jpeg"},{"cover_img":"http://img.prettyyes.com/1085-2239-1451657775.jpeg"},{"cover_img":"http://img.prettyyes.com/seller-laravel-1085-2569-1471575840.jpeg"}]
     * task_num : 160
     * home_task_num : 0
     * quality_num : 68
     * seller_level : {"status":0,"seller_level":"v10","score_start":100000,"score_end":200000,"seller_level_id":10,"seller_type_name":"KOL","score":"0"}
     * medal : ["1"]
     */

    private int seller_id;
    private String user_security;
    private int share_num;
    private String headimg;
    private String ace_txt;
    private String is_like;
    private String nickname;
    private int pei_num;
    private int zeze_num;
    private String share_link;
    private int favourite_num;
    private int task_num;
    private int home_task_num;
    private int quality_num;
    private int update_kol_img;

    private int famous_type=0;
    private List<String>ingredient;
    private ShareModel share_model;
    public String grade_title;

    public String getGrade_title() {
        return grade_title;
    }

    public ShareModel getShare_model() {
        return share_model;
    }

    public void setShare_model(ShareModel share_model) {
        this.share_model = share_model;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

    public int getFamous_type() {
        return famous_type;
    }

    public void setFamous_type(int famous_type) {
        this.famous_type = famous_type;
    }

    public int getUpdate_kol_img() {
        return update_kol_img;
    }

    public void setUpdate_kol_img(int update_kol_img) {
        this.update_kol_img = update_kol_img;
    }

    /**
     * status : 0
     * seller_level : v10
     * score_start : 100000
     * score_end : 200000
     * seller_level_id : 10
     * seller_type_name : KOL
     * score : 0
     */

    private SellerLevelEntity seller_level;
    /**
     * tag_name : 搭配好手
     * tag_id : 247
     */

    private List<TagsEntity> tags;
    /**
     * name : 全部
     * num : 58
     */

    private List<NumListEntity> num_list;
    /**
     * cover_img : http://img.prettyyes.com/1085-2239-1451657775.jpeg
     */

    private List<SuitImgEntity> suit_img;
    private List<String> medal;

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getUser_security() {
        return user_security;
    }

    public void setUser_security(String user_security) {
        this.user_security = user_security;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getAce_txt() {
        return ace_txt;
    }

    public void setAce_txt(String ace_txt) {
        this.ace_txt = ace_txt;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPei_num() {
        return pei_num;
    }

    public void setPei_num(int pei_num) {
        this.pei_num = pei_num;
    }

    public int getZeze_num() {
        return zeze_num;
    }

    public void setZeze_num(int zeze_num) {
        this.zeze_num = zeze_num;
    }

    public String getShare_link() {
        return share_link;
    }

    public void setShare_link(String share_link) {
        this.share_link = share_link;
    }

    public int getFavourite_num() {
        return favourite_num;
    }

    public void setFavourite_num(int favourite_num) {
        this.favourite_num = favourite_num;
    }

    public int getTask_num() {
        return task_num;
    }

    public void setTask_num(int task_num) {
        this.task_num = task_num;
    }

    public int getHome_task_num() {
        return home_task_num;
    }

    public void setHome_task_num(int home_task_num) {
        this.home_task_num = home_task_num;
    }

    public int getQuality_num() {
        return quality_num;
    }

    public void setQuality_num(int quality_num) {
        this.quality_num = quality_num;
    }

    public SellerLevelEntity getSeller_level() {
        return seller_level;
    }

    public void setSeller_level(SellerLevelEntity seller_level) {
        this.seller_level = seller_level;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public List<NumListEntity> getNum_list() {
        return num_list;
    }

    public void setNum_list(List<NumListEntity> num_list) {
        this.num_list = num_list;
    }

    public List<SuitImgEntity> getSuit_img() {
        return suit_img;
    }

    public void setSuit_img(List<SuitImgEntity> suit_img) {
        this.suit_img = suit_img;
    }

    public List<String> getMedal() {
        return medal;
    }

    public void setMedal(List<String> medal) {
        this.medal = medal;
    }

    public static class SellerLevelEntity {
        private int status;
        private String seller_level;
        private int score_start;
        private int score_end;
        private int seller_level_id;
        private String seller_type_name;
        private String score;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSeller_level() {
            return seller_level;
        }

        public void setSeller_level(String seller_level) {
            this.seller_level = seller_level;
        }

        public int getScore_start() {
            return score_start;
        }

        public void setScore_start(int score_start) {
            this.score_start = score_start;
        }

        public int getScore_end() {
            return score_end;
        }

        public void setScore_end(int score_end) {
            this.score_end = score_end;
        }

        public int getSeller_level_id() {
            return seller_level_id;
        }

        public void setSeller_level_id(int seller_level_id) {
            this.seller_level_id = seller_level_id;
        }

        public String getSeller_type_name() {
            return seller_type_name;
        }

        public void setSeller_type_name(String seller_type_name) {
            this.seller_type_name = seller_type_name;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }

    public static class TagsEntity {
        private String tag_name;
        private int tag_id;

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public int getTag_id() {
            return tag_id;
        }

        public void setTag_id(int tag_id) {
            this.tag_id = tag_id;
        }
    }

    public static class NumListEntity {
        private String name;
        private int num;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class SuitImgEntity {
        private String cover_img;

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }
    }
}
