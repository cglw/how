package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/20.
 */

public class ApplyKolRequest extends BaseRequest<Object> {

    @Override
    public String setExtraUrl() {
        return "/app/user/h5Join";
    }

    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("nickname", nickname);
        arrayMap.put("introduce", introduce);
        arrayMap.put("experience", experience);
        arrayMap.put("telephone", telephone);
        arrayMap.put("email", email);
        arrayMap.put("confused", confused);
        arrayMap.put("why", why);
        arrayMap.put("hope", hope);
        return super.setParams();
    }

    public ApplyKolRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ApplyKolRequest setIntroduce(String introduce) {
        this.introduce = introduce;
        return this;
    }

    public ApplyKolRequest setExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public ApplyKolRequest setTelephone(String telephone) {
        this.telephone = telephone;
        return this;

    }

    public ApplyKolRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public ApplyKolRequest setConfused(String confused) {
        this.confused = confused;
        return this;
    }

    public ApplyKolRequest setWhy(String why) {
        this.why = why;
        return this;
    }

    public ApplyKolRequest setHope(String hope) {
        this.hope = hope;
        return this;
    }

    String nickname;
    String introduce;
    String experience;
    String telephone;
    String email;
    String confused;
    String why;
    String hope;


}
