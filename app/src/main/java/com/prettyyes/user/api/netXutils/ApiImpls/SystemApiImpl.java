package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.SystemApi;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.urls.SystemUrl;
import com.prettyyes.user.model.UnreadModel;
import com.prettyyes.user.model.comment.CommentInfo;
import com.prettyyes.user.model.comment.CommentListRes;
import com.prettyyes.user.model.comment.CommentMyList;
import com.prettyyes.user.model.common.AppBanner;
import com.prettyyes.user.model.system.ActivtyUnreadModel;
import com.prettyyes.user.model.system.SystemAddException;
import com.prettyyes.user.model.system.SystemAlertMessage;
import com.prettyyes.user.model.system.SystemBulletScreen;
import com.prettyyes.user.model.system.TaskAct;
import com.prettyyes.user.model.system.TaskActPre;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.ApiImpls
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class SystemApiImpl implements SystemApi {
    /**
     * 系统异常反馈
     *
     * @param function_url_string  错误链接
     * @param params               传递类型
     * @param paramNetWorkCallback
     */
    @Override
    public void systemAddExecption(String function_url_string, String params, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("function_url_string", function_url_string);
        localHashMap.put("params", params);
        Type localType = new TypeToken<ApiResContent<SystemAddException>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_systemAddexection, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 引导图
     *
     * @param app                  客户端类型 buyer saller
     * @param paramNetWorkCallback
     */
    @Override
    public void systemLaunch(String app, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("function_url_string", app);
        Type localType = new TypeToken<ApiResContent<SystemAddException>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_systemLaunch, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    @Override
    public void systemAlertMessage(NetReqCallback paramNetWorkCallback) {
        Type localType = new TypeToken<ApiResContent<SystemAlertMessage>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_systemAlertmessage, new HashMap<String, String>(), localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    /**
     * 弹幕消息
     *
     * @param page           questions 为发问
     * @param netReqCallback
     */
    @Override
    public void systemBulletScreen(String page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<SystemBulletScreen>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_systemBulletscreen, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    /**
     * @param banner_type    home_page 首页 返回广告列表 list alert_page 首页弹出 返回一条广告 kol_page kol页 返回广告列表 list start_page 开机页面 返回一条广告
     * @param netReqCallback
     */
    @Override
    public void getBanner(String banner_type, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("banner_type", banner_type);
        Type localType = new TypeToken<ApiResContent<AppBanner>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_appbanner, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    /**
     * 获取首页的活动预告
     *
     * @param netReqCallback
     */
    @Override
    public void getTaskActPre(NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        Type localType = new TypeToken<ApiResContent<TaskActPre>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_apptaskActPre, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void getTaskAct(NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        Type localType = new TypeToken<ApiResContent<TaskAct>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_apptaskAct, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void activityUnreadCount(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<ActivtyUnreadModel>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_activityUnread, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void clientActivityRead(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);

        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_activityRead, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void addComment(String uuid, String comment, int answer_id, String comment_type, int comment_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("comment", comment);
        localHashMap.put("comment_id", comment_id);
        localHashMap.put("answer_id", answer_id);
        localHashMap.put("comment_type", comment_type);

        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_addComment, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void commentList(int answer_id, String comment_type, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("answer_id", answer_id);
        localHashMap.put("comment_type", comment_type);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<CommentListRes>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_commentList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void delComment(int id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("id", id);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_delComment, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void myCommentList(String uuid,int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<List<CommentMyList>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_myCommentList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void myReplyCommentList(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<List<CommentMyList>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_myReplyCommentList, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void CommentInfo(String uuid, int comment_id, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("comment_id", comment_id);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<CommentInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_CommentInfo, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void updateCommentUnread(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<CommentInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_updateCommentUnread, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }

    @Override
    public void commentUnreadCount(String uuid,  NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<UnreadModel>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(SystemUrl.Url_commentUnreadCount, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"");
        }
    }


}
