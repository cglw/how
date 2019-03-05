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
public abstract interface TaskApi extends BaseApi {
    public abstract void TaskSetting(String uuid, NetWorkCallback paramNetWorkCallback);
    public abstract void TaskAdd(String uuid,String scene,float price_s,float price_e,String desc,String pic_list,String task_function,int is_open,String at_users,String act_id,String topic_id, NetReqCallback netReqCallback);
    public abstract void taskTaskDel(String uuid,int task_id, NetReqCallback netReqCallback);
    public abstract void taskgetMyList(String uuid,int page, NetReqCallback netReqCallback);
    public abstract void taskTaskTotalTips(String uuid, NetReqCallback netReqCallback);
    public abstract void taskMyInfo(String uuid,int task_id,int page, NetWorkCallback paramNetWorkCallback);
    public abstract void taskReset(String uuid,int task_id, NetWorkCallback paramNetWorkCallback);
    public abstract void taskRefuseSeller(String uuid,int seller_id,int task_id, NetWorkCallback paramNetWorkCallback);
    public abstract void taskTaskTipClear(String uuid,String task_id, NetReqCallback netReqCallback);
    public abstract void taskTaskActivity(NetWorkCallback paramNetWorkCallback);
    public abstract void taskReserve(String uuid,int seller_id,int telephone,String city,String address,String name,NetWorkCallback paramNetWorkCallback);
    public abstract void taskHomeTask(String uuid,int page,NetReqCallback netReqCallback);
    public abstract void taskTaskVote(String uuid,String vote_type,int type_value,String task_id,NetReqCallback paramNetWorkCallback);
    public abstract void taskShareTask(int task_id,NetWorkCallback paramNetWorkCallback);
    public abstract void taskTaskInfo(int task_id,int page,String uuid,NetReqCallback netReqCallback);
    public abstract void taskTaskInfoByAnswerId(int answer_id,String uuid,NetReqCallback netReqCallback);
    public abstract void taskTaskAnswerVote(String uuid,String vote_type,int type_value,String answer_id,NetReqCallback paramNetWorkCallback);
    public abstract void taskVoteLikeList(String uuid,int page,NetReqCallback paramNetWorkCallback);
    public abstract void taskActivityInfo(String uuid,int act_id,NetReqCallback paramNetWorkCallback);
    public abstract void tasktaskActHistory(String uuid,int act_id,String time,int page,NetReqCallback paramNetWorkCallback);
    public abstract void followAct(String uuid,int act_id,String islike,NetReqCallback paramNetWorkCallback);
    public abstract void followActList(String uuid,int page,NetReqCallback paramNetWorkCallback);
    public abstract void getTopicList(String uuid,int topic_id,int page,NetReqCallback paramNetWorkCallback);
    public abstract void getTopicInfo(String uuid,int topic_id,NetReqCallback paramNetWorkCallback);
    public abstract void followTopicList(String uuid,int page,NetReqCallback paramNetWorkCallback);
    public abstract void followTopic(String uuid,int topic_id,int is_like,NetReqCallback paramNetWorkCallback);

}
