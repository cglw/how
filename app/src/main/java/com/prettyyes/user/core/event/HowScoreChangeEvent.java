package com.prettyyes.user.core.event;

/**
 * Created by chengang on 2017/9/25.
 */

public class HowScoreChangeEvent {
    private String how_score;

    public HowScoreChangeEvent(String how_score) {
        this.how_score = how_score;
    }

    public String getHow_score() {
        return how_score;
    }
}
