package com.prettyyes.user.api.netXutils.requests;

import com.prettyyes.user.api.netXutils.BaseRequest;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.user.UserInfo;

import java.util.HashMap;

/**
 * Created by chengang on 2017/8/1.
 */

public class UserEditRequest extends BaseRequest<AlertMessageResponse> {

    @Override
    public String setExtraUrl() {
        return "/app/user/edit";
    }

    @Override
    public HashMap<String, Object> setParams() {
        UserInfo userInfo = SpMananger.getUserInfo();
        if (userInfo != null) {
            if (nickname == null) {
                nickname = userInfo.getNickname();
            }
            if (information == null)
                information = userInfo.getInformation();
            if (tag == null) {
                tag = "";
                for (int i = 0; i < userInfo.getTags_info().size(); i++) {
                    tag += userInfo.getTags_info().get(i).getTag_name() + ";";
                }

            }
            if (gender == null)
                gender = userInfo.getGender() + "";
            if (headimg == null)
                headimg = userInfo.getHeadimg();
            if (ace_txt == null)
                ace_txt = userInfo.getAce_txt();
        }

        arrayMap.put("nickname", nickname);
        arrayMap.put("information", information);
        arrayMap.put("tag", tag);
        arrayMap.put("gender", gender);
        arrayMap.put("headimg", headimg);
        arrayMap.put("ace_txt", ace_txt);
        return super.setParams();
    }


    public UserEditRequest setNickname(String nickname) {
        this.nickname = nickname;
        return this;

    }

    public UserEditRequest setInformation(String information) {
        this.information = information;
        return this;
    }

    public UserEditRequest setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public UserEditRequest setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserEditRequest setHeadimg(String headimg) {
        this.headimg = headimg;
        return this;
    }

    public UserEditRequest setAce_txt(String ace_txt) {
        this.ace_txt = ace_txt;
        return this;
    }

    public String nickname;
    public String information;
    public String tag;
    public String gender;
    public String headimg;
    public String ace_txt;


}
