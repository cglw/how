package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.model.other.AceGetList;

import java.util.HashMap;

/**
 * Created by chengang on 2017/7/27.
 */

public class KolListRequest extends PageRequest<AceGetList> {


    @Override
    public String setExtraUrl() {
        return "/app/ace/getlist";
    }


    @Override
    public HashMap<String, Object> setParams() {
        arrayMap.put("gender", 2);
        arrayMap.put("nickname", nickname);
        return super.setParams();
    }
    public String nickname;
    public String gender;

    public KolListRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

}
