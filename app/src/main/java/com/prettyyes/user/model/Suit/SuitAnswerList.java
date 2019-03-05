package com.prettyyes.user.model.Suit;

import java.util.List;

/**
 * Created by chengang on 2017/5/3.
 */

public class SuitAnswerList {
    private List<ReplyGoods> data;

    public List<ReplyGoods> getSuits() {
        return data;
    }

    public void setSuits(List<ReplyGoods> suits) {
        this.data = suits;
    }
}
