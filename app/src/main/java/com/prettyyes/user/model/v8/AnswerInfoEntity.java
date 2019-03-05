package com.prettyyes.user.model.v8;

import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.app.ui.appview.ShareModel;
import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Created by chengang on 2017/7/11.
 */

public class AnswerInfoEntity extends BaseModel implements BaseType {


    /**
     * seller_id : 13692
     * task_id : 5642
     * seller_type : 1
     * answer_type : suit
     * module_id : 8589
     * comment_count : 0
     * ts_reason : 自己想要怎么穿就怎么穿吧 何必在乎别人的看法
     * nickname : 黑糖
     * headimg : http://img.prettyyes.com/13692-8855-1460572520.jpeg
     * ace_txt :
     * famous_type : 0
     * answer_id : 44999
     * answer_like_num : 0
     * answer_dislike_num : 0
     */

    private String seller_id;
    private String task_id;
    private String seller_type;
    private String answer_type;
    private String module_id;
    private int comment_count;
    private String ts_reason;
    private String nickname;
    private String headimg;
    private String ace_txt;
    private String famous_type;
    private String answer_id;
    private int answer_like_num;
    private int answer_dislike_num;
    private int answer_like;
    private int answer_dislike;
    private AnswerDataEntity answer_data;
    List<CommentChildrenBean> comment_children;
    private ShareModel share_model;
    public String grade_title;
    public boolean ishaveLoad = false;
    private String ts_createtime;
    private String answer_img_video;
    private String answer_view_num;
    private AnswerActionEntity action;

    public AnswerActionEntity getAction() {
        return action;
    }

    public void setAction(AnswerActionEntity action) {
        this.action = action;
    }

    public String getAnswer_view_num() {
        return answer_view_num;
    }

    public void setAnswer_view_num(String answer_view_num) {
        this.answer_view_num = answer_view_num;
    }

    public String getAnswer_img_video() {

        return answer_img_video;
    }

    public UploadMediaEntity getMediaEntity() {
        if (answer_img_video == null || answer_img_video.equals(""))
            return null;
        UploadMediaEntity uploadMediaEntity = null;
        try {
            uploadMediaEntity = GsonHelper.getGson().fromJson(answer_img_video, UploadMediaEntity.class);

        } catch (Exception e) {
            return null;
        }

        return uploadMediaEntity;
    }

    public void setAnswer_img_video(String answer_img_vedio) {
        this.answer_img_video = answer_img_vedio;
    }

    public String getTs_create_time() {
        return ts_createtime;
    }

    public String getGrade_title() {
        return grade_title;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public String getAnswer_type() {
        return answer_type;
    }

    public void setAnswer_type(String answer_type) {
        this.answer_type = answer_type;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getTs_reason() {
        return ts_reason;
    }

    public void setTs_reason(String ts_reason) {
        this.ts_reason = ts_reason;
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

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
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

    public int getAnswer_like() {
        return answer_like;
    }

    public void setAnswer_like(int answer_like) {
        this.answer_like = answer_like;
    }

    public int getAnswer_dislike() {
        return answer_dislike;
    }

    public void setAnswer_dislike(int answer_dislike) {
        this.answer_dislike = answer_dislike;
    }

    public AnswerDataEntity getAnswer_data() {
        return answer_data;
    }

    public void setAnswer_data(AnswerDataEntity answer_data) {
        this.answer_data = answer_data;
    }

    public List<CommentChildrenBean> getComment_children() {
        return comment_children;
    }

    public void setComment_children(List<CommentChildrenBean> comment_children) {
        this.comment_children = comment_children;
    }

    public ShareModel getShare_model() {
        return share_model;
    }

    public void setShare_model(ShareModel share_model) {
        this.share_model = share_model;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }

}

