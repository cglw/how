package com.prettyyes.user.api.netXutils.urls;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.urls
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: 系统Url
 */
public class SystemUrl extends BaseUrl {
    //弹出消息
    public static final String Url_systemAlertmessage= url+"/app/system/alertmessage";
    //弹幕消息
    public static final String Url_systemBulletscreen= url+"/app/system/bulletscreen";
    //引导图
    public static final String Url_systemLaunch= url+"/app/system/launch";
    //系统异常反馈
    public static final String Url_systemAddexection= url+"/app/system/addexception";
    //App的Banner
    public static final String Url_appbanner= url+"/app/banner/bannerByType";
    //App的活动预告
    public static final String Url_apptaskActPre= url+"/app/act/taskActPre";
    //App的活动
    public static final String Url_apptaskAct= url+"/app/task/taskActivity";
    //App的活动
    public static final String Url_activityRead= url+"/app/client/activityRead";
    //App的活动
    public static final String Url_activityUnread= url+"/app/client/activityUnreadCount";
    public static final String Url_addComment= url+"/app/comment/addComment";
    public static final String Url_commentList= url+"/app/comment/commentList";
    public static final String Url_delComment= url+"/app/comment/delComment";
    public static final String Url_myCommentList= url+"/app/comment/myCommentList";
    public static final String Url_myReplyCommentList= url+"/app/comment/myReplyCommentList";
    public static final String Url_CommentInfo= url+"/app/comment/CommentInfo";
    public static final String Url_commentUnreadCount= url+"/app/comment/commentUnreadCount";
    public static final String Url_updateCommentUnread= url+"/app/comment/updateCommentUnread";
}
