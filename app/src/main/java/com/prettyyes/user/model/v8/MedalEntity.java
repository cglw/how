package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;
import com.prettyyes.user.model.coupon.CouponInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalEntity extends BaseModel implements BaseType {

    /**
     * medal_id : 1
     * medal_name : 打卡青年
     * medal_content : 每周至少回答一个问题，连续四周
     * all_step : 4
     * medal_type : order
     * medal_image : http://image.prettyyes.com/medal-1@3x.png
     * client : seller
     * mission_finished : 0
     * get_reward : 0
     * current_step : 3
     */

    private String medal_id;
    private String medal_name;
    private int medal_icon=-1;
    private String medal_content;
    private int all_step;
    private String medal_type;
    private String medal_image;
    private String client;
    private String mission_finished;
    private String get_reward;
    private int current_step;
    private String gift_text;
    private String share_url;
    private ShareModel share_model;

    public int getMedal_icon() {
        return medal_icon;
    }

    public void setMedal_icon(int medal_icon) {
        this.medal_icon = medal_icon;
    }

    public ShareModel getShare_model() {
        return share_model;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getGift_text() {
        if (gift_text == null)
            gift_text = "";
        return gift_text;
    }

    public boolean isCanReceive() {
        if (all_step == current_step && "0".equals(get_reward))
            return true;
        return false;
    }

public boolean isHaveReceive() {
        if ("1".equals(get_reward))
            return true;
        return false;
    }


    private List<CouponInfoEntity> coupon_list;
    //    private ShareModel share_model;
    private List<SpuInfoEntity> suit_list;

    public List<SpuInfoEntity> getSuit_list() {
        return suit_list;
    }

    public void setSuit_list(List<SpuInfoEntity> suit_list) {
        this.suit_list = suit_list;
    }

    public String getMedal_id() {
        return medal_id;
    }

    public void setMedal_id(String medal_id) {
        this.medal_id = medal_id;
    }

    public String getMedal_name() {
        return medal_name;
    }

    public void setMedal_name(String medal_name) {
        this.medal_name = medal_name;
    }

    public String getMedal_content() {
        return medal_content;
    }

    public void setMedal_content(String medal_content) {
        this.medal_content = medal_content;
    }

    public int getAll_step() {
        return all_step;
    }

    public void setAll_step(int all_step) {
        this.all_step = all_step;
    }

    public String getMedal_type() {
        return medal_type;
    }

    public void setMedal_type(String medal_type) {
        this.medal_type = medal_type;
    }

    public String getMedal_image() {
        return medal_image;
    }

    public void setMedal_image(String medal_image) {
        this.medal_image = medal_image;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMission_finished() {
        return mission_finished;
    }

    public void setMission_finished(String mission_finished) {
        this.mission_finished = mission_finished;
    }

    public String getGet_reward() {
        return get_reward;
    }

    public void setGet_reward(String get_reward) {
        this.get_reward = get_reward;
    }

    public int getCurrent_step() {
        return current_step;
    }

    public void setCurrent_step(int current_step) {
        this.current_step = current_step;
    }

    public List<CouponInfoEntity> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<CouponInfoEntity> coupon_list) {
        this.coupon_list = coupon_list;
    }

//   public ShareModel getShare_model() {
//        return share_model;
//    }
//
//    public void setShare_model(ShareModel share_model) {
//        this.share_model = share_model;
//    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}
