package com.prettyyes.user.api.netXutils.Apis;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.NetWorkCallback;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.Apis
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public interface SystemApi extends BaseApi {
    public abstract void systemAddExecption(String function_url_string,String params, NetWorkCallback paramNetWorkCallback);
    public abstract void systemLaunch(String app, NetWorkCallback paramNetWorkCallback);
    public abstract void systemAlertMessage( NetReqCallback netReqCallback);
    public abstract void systemBulletScreen(String page, NetReqCallback netReqCallback);
    public abstract void getBanner(String banner_type, NetReqCallback netReqCallback);
    public abstract void getTaskActPre( NetReqCallback netReqCallback);
    public abstract void getTaskAct( NetReqCallback netReqCallback);
    public abstract void activityUnreadCount(String uuid, NetReqCallback netReqCallback);
    public abstract void clientActivityRead(String uuid, NetReqCallback netReqCallback);
    public abstract void addComment(String uuid,String comment,int answer_id,String comment_type,int comment_id, NetReqCallback netReqCallback);
    public abstract void commentList(int answer_id,String comment_type,
                                     int page,NetReqCallback netReqCallback);
    public abstract void delComment(int id, NetReqCallback netReqCallback);
    public abstract void myCommentList(String uuid,int page, NetReqCallback netReqCallback);
    public abstract void myReplyCommentList(String uuid,int page, NetReqCallback netReqCallback);
    public abstract void CommentInfo(String uuid,int comment_id,int page, NetReqCallback netReqCallback);
    public abstract void updateCommentUnread(String uuid,NetReqCallback netReqCallback);
    public abstract void commentUnreadCount(String uuid, NetReqCallback netReqCallback);

}
