package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.core.SpMananger;

import java.util.List;

/**
 * Created by chengang on 2017/9/18.
 */

public class LiveTaskInfo extends QuestionEntity implements BaseType {


    private long push_time;

    private List<SellerInfoEntity> live_sellers;

    public void setLive_sellers(List<SellerInfoEntity> live_sellers) {
        this.live_sellers = live_sellers;
    }

    public boolean showReply() {
        if (live_sellers == null)
            return false;
        for (int i = 0; i < live_sellers.size(); i++) {
            if (SpMananger.getUserInfo().getUid().equals(live_sellers.get(i).getSeller_id())) {
                return true;
            }
        }
        return false;


    }

    public long getPush_time() {
        return push_time;
    }

    public void setPush_time(long push_time) {
        this.push_time = push_time;
    }

    private String create_time;

    public String getCreate_time() {
        return create_time;
    }

    private List<AnswerInfoEntity> suit_list;

    public List<AnswerInfoEntity> getSuit_list() {
        return suit_list;
    }

    public void setSuit_list(List<AnswerInfoEntity> suit_list) {
        this.suit_list = suit_list;
    }


    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }
}

