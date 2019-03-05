package com.prettyyes.user.api.netXutils.ApiImpls;

import com.google.gson.reflect.TypeToken;
import com.hornen.storage.StorageProxy;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiResContent;
import com.prettyyes.user.api.netXutils.Apis.TaskApi;
import com.prettyyes.user.api.netXutils.NetWorkCallback;
import com.prettyyes.user.api.netXutils.response.HomeListRes;
import com.prettyyes.user.api.netXutils.urls.TaskUrl;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.TopicCollectRes;
import com.prettyyes.user.model.TopicEntityRes;
import com.prettyyes.user.model.common.Info;
import com.prettyyes.user.model.system.TaskAct;
import com.prettyyes.user.model.task.FollActList;
import com.prettyyes.user.model.task.TaskActivityEntity;
import com.prettyyes.user.model.task.TaskInfo;
import com.prettyyes.user.model.task.TaskLikeList;
import com.prettyyes.user.model.task.TaskMyInfo;
import com.prettyyes.user.model.task.TaskMyList;
import com.prettyyes.user.model.task.TaskRefuseSeller;
import com.prettyyes.user.model.task.TaskReserve;
import com.prettyyes.user.model.task.TaskSetting;
import com.prettyyes.user.model.task.TaskShareTask;
import com.prettyyes.user.model.task.TaskTotalTips;
import com.prettyyes.user.model.v8.LiveTaskInfo;

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
public class TaskImpl implements TaskApi {
    private static final String TAG = "TaskImpl";

    /**
     * 获取问答价格区间
     *
     * @param uuid
     * @param paramNetWorkCallback
     */
    @Override
    public void TaskSetting(String uuid, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<TaskSetting>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskSetting, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param uuid
     * @param scene          场景id分号隔开：'1;2;'
     * @param price_s        价格区间-下限
     * @param price_e        价格区间-上限
     * @param desc           文字描述
     * @param pic_list       示例图片集，用;分割。例如/33/123.jpg;/44/123
     * @param task_function  男生需求
     * @param is_open        是否匿名 1 匿名 0不匿名
     * @param netReqCallback
     */
    @Override
    public void TaskAdd(String uuid, String scene, float price_s, float price_e, String desc, String pic_list, String task_function, int is_open, String at_users, String act_id,String topic_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("scene", scene);
        localHashMap.put("price_s", price_s);
        localHashMap.put("price_e", price_e);
        localHashMap.put("desc", desc);
        localHashMap.put("pic_list", pic_list);
        localHashMap.put("task_function", task_function);
        localHashMap.put("is_open", is_open);
        localHashMap.put("at_users", at_users);
        localHashMap.put("act_id", act_id);
        localHashMap.put("topic_id", topic_id);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskAdd, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"TaskAdd");
        }
    }

    /**
     * 删除任务
     *
     * @param uuid
     * @param task_id        问答id
     * @param netReqCallback
     */
    @Override
    public void taskTaskDel(String uuid, int task_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("task_id", task_id);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskdel, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskTaskDel");
        }
    }

    /**
     * 自身提问list
     *
     * @param uuid
     * @param page           页数
     * @param netReqCallback
     */
    @Override
    public void taskgetMyList(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("page", page);
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<TaskMyList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskMylist, localHashMap, localType, netReqCallback, false);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskgetMyList");
        }
    }

    /**
     * 自身提问总Tips
     *
     * @param uuid
     * @param netReqCallback
     */
    @Override
    public void taskTaskTotalTips(String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<TaskTotalTips>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskgetTaskTotalTips, localHashMap, localType, netReqCallback);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskTaskTotalTips");
        }
    }

    /**
     * 自身提问详情
     *
     * @param uuid
     * @param task_id              提问id
     * @param page                 页数
     * @param paramNetWorkCallback
     */
    @Override
    public void taskMyInfo(String uuid, int task_id, int page, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("task_id", task_id);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<TaskMyInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskMyinfo, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 初始化提问，返回结果根据res
     *
     * @param uuid
     * @param task_id              提问id
     * @param paramNetWorkCallback
     */
    @Override
    public void taskReset(String uuid, int task_id, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("task_id", task_id);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskReset, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 专治花心
     *
     * @param uuid
     * @param seller_id            卖家id
     * @param task_id              问题id
     * @param paramNetWorkCallback
     */
    @Override
    public void taskRefuseSeller(String uuid, int seller_id, int task_id, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("seller_id", seller_id);
        localHashMap.put("task_id", task_id);
        Type localType = new TypeToken<ApiResContent<TaskRefuseSeller>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskRefuseSeller, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 清除问答提醒
     *
     * @param uuid
     * @param task_id        问答id
     * @param netReqCallback
     */
    @Override
    public void taskTaskTipClear(String uuid, String task_id, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("task_id", task_id);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskTasktipclear, localHashMap, localType, netReqCallback);
            return;
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskTaskTipClear");
        }
    }

    /**
     * 获取正在进行的问答活动
     *
     * @param paramNetWorkCallback
     */
    @Override
    public void taskTaskActivity(NetWorkCallback paramNetWorkCallback) {
        Type localType = new TypeToken<ApiResContent<TaskActivityEntity>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskTaskActivity, new HashMap<String, String>(), localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 添加预约
     *
     * @param uuid
     * @param seller_id            卖家id
     * @param telephone            手机号
     * @param city                 城市
     * @param address              地址
     * @param name                 用户名称
     * @param paramNetWorkCallback
     */
    @Override
    public void taskReserve(String uuid, int seller_id, int telephone, String city, String address, String name, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("seller_id", seller_id);
        localHashMap.put("telephone", telephone);
        localHashMap.put("city", city);
        localHashMap.put("address", address);
        localHashMap.put("name", name);
        Type localType = new TypeToken<ApiResContent<TaskReserve>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskReserve, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }

    /**
     * 首页热门问答
     *
     * @param uuid
     * @param page           页数
     * @param netReqCallback
     */
    @Override
    public void taskHomeTask(String uuid, int page, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        localHashMap.put("cid", SpMananger.getClien_id());
        String last_time = new StorageProxy(BaseApplication.getAppContext()).resolve("last_time", String.class);
        localHashMap.put("last_time", last_time);
        Type localType = new TypeToken<ApiResContent<HomeListRes>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskHomeTask, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskHomeTask");
        }
    }

    @Override
    public void taskTaskVote(String uuid, String vote_type, int type_value, String task_id, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("vote_type", vote_type);
        localHashMap.put("task_id", task_id);
        localHashMap.put("type_value", type_value);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskTaskVote, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getMessage(),"taskTaskVote");
        }
    }


    /**
     * 问答分享
     *
     * @param task_id              问答id
     * @param paramNetWorkCallback
     */
    @Override
    public void taskShareTask(int task_id, NetWorkCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("task_id", task_id);
        Type localType = new TypeToken<ApiResContent<TaskShareTask>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskShareTask, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(), localException.getMessage());
        }
    }


    /**
     * 问答详情
     *
     * @param task_id        问答id
     * @param page           页数
     * @param netReqCallback
     */
    @Override
    public void taskTaskInfo(int task_id, int page, String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("task_id", task_id);
        localHashMap.put("page", page);
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<TaskInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskTaskInfo, localHashMap, localType, netReqCallback, false);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskTaskInfo");
        }
    }

    @Override
    public void taskTaskInfoByAnswerId(int answer_id, String uuid, NetReqCallback netReqCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("answer_id", answer_id);
        localHashMap.put("uuid", uuid);
        Type localType = new TypeToken<ApiResContent<TaskInfo>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskInfoByAnswerId, localHashMap, localType, netReqCallback, true);
        } catch (Exception localException) {
            netReqCallback.apiRequestFail(localException.getMessage(),"taskTaskInfoByAnswerId");
        }
    }

    /**
     * 提问回答点赞或呵呵
     *
     * @param uuid
     * @param vote_type            like 喜欢 dislike不喜欢
     * @param type_value           1,0
     * @param answer_id            回答id
     * @param paramNetWorkCallback
     */
    @Override
    public void taskTaskAnswerVote(String uuid, String vote_type, int type_value, String answer_id, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("vote_type", vote_type);
        localHashMap.put("type_value", type_value);
        localHashMap.put("answer_id", answer_id);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskTaskAnswerVote, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"taskTaskAnswerVote");
        }
    }

    /**
     * 点赞问题列表
     *
     * @param uuid
     * @param page
     * @param paramNetWorkCallback
     */
    @Override
    public void taskVoteLikeList(String uuid, int page, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<TaskLikeList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskVoteLikeList, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"taskVoteLikeList");
        }
    }

    @Override
    public void taskActivityInfo(String uuid, int act_id, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("act_id", act_id);
        Type localType = new TypeToken<ApiResContent<TaskAct>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskActInfo, localHashMap, localType, paramNetWorkCallback, false);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"taskActivityInfo");
        }
    }

    @Override
    public void tasktaskActHistory(String uuid, int act_id, String time, int page, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("act_id", act_id);
        localHashMap.put("time", time);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<List<LiveTaskInfo>>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_taskActHistory, localHashMap, localType, paramNetWorkCallback, false);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"tasktaskActHistory");
        }
    }

    @Override
    public void followAct(String uuid, int act_id, String islike, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("act_id", act_id);
        localHashMap.put("is_like", islike);
        Type localType = new TypeToken<ApiResContent<Info>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_followAct, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"followAct");
        }
    }

    @Override
    public void followActList(String uuid, int page, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<FollActList>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_followActList, localHashMap, localType, paramNetWorkCallback, false);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"followActList");
        }
    }

    @Override
    public void getTopicList(String uuid, int topic_id,int page, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("topic_id", topic_id);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<HomeListRes>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_topiclist, localHashMap, localType, paramNetWorkCallback);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"getTopicList");
        }
    }

    @Override
    public void getTopicInfo(String uuid, int topic_id, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("topic_id", topic_id);
        Type localType = new TypeToken<ApiResContent<TopicEntityRes>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_topicInfo, localHashMap, localType, paramNetWorkCallback, false);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"getTopicInfo");
        }
    }

    @Override
    public void followTopicList(String uuid, int page, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("page", page);
        Type localType = new TypeToken<ApiResContent<TopicCollectRes>>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_followTopicList, localHashMap, localType, paramNetWorkCallback, false);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"followTopicList");
        }
    }

    @Override
    public void followTopic(String uuid, int topic_id, int is_like, NetReqCallback paramNetWorkCallback) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uuid", uuid);
        localHashMap.put("topic_id", topic_id);
        localHashMap.put("is_like", is_like);
        Type localType = new TypeToken<ApiResContent>() {
        }.getType();
        try {
            this.httpAccess.postAsyn(TaskUrl.Url_followTopic, localHashMap, localType, paramNetWorkCallback, true);
        } catch (Exception localException) {
            paramNetWorkCallback.apiRequestFail(localException.getLocalizedMessage(),"followTopic");
        }
    }


}
