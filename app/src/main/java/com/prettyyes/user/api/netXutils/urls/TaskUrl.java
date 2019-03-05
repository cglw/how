package com.prettyyes.user.api.netXutils.urls;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.urls
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: 任务Url
 */
public class TaskUrl extends BaseUrl {
    //获取问答价格区间
    public static final String Url_taskSetting = url + "/app/task/setting";
    //用户提交任务
    public static final String Url_taskAdd = url + "/app/task/add";
    //用户任务删除
    public static final String Url_taskdel = url + "/app/task/del";
    //自身提问list
    public static final String Url_taskMylist = url + "/app/task/mylist";
    //自身提问tips
    public static final String Url_taskgetTaskTotalTips = url + "/app/task/getTaskTotalTips";
    //自身提问详情
    public static final String Url_taskMyinfo = url + "/app/task/myInfo";
    //无满意套系，初始化提问
    public static final String Url_taskReset = url + "/app/task/reset";
    //添加预约
    public static final String Url_taskReserve = url + "/app/task/reserve";
    //首页热门问答
    public static final String Url_taskHomeTask = url + "/app/task/homeTask";
    //问答点赞或呵呵
    public static final String Url_taskTaskVote = url + "/app/task/taskVote";
    //问答分享
    public static final String Url_taskShareTask = url + "/app/task/shareTask";
    //问答详情
    public static final String Url_taskTaskInfo = url + "/app/task/taskInfo";
    public static final String Url_taskInfoByAnswerId = url + "/app/task/taskInfoByAnswerId";
    //提问回答点赞或呵呵
    public static final String Url_taskTaskAnswerVote = url + "/app/task/taskAnswerVote";
    //获取正在进行的问答活动
    public static final String Url_taskTaskActivity = url + "/app/task/taskActivity";
    //获取提问场合--暂时没有
    public static final String Url_taskGetTaskPlace = url + "/app/task/getTaskPlace";
    //专治花心
    public static final String Url_taskRefuseSeller = url + "/app/task/refuseSeller";
    //清除问答提醒
    public static final String Url_taskTasktipclear = url + "/app/task/tasktipclear";
    //点赞问题列表
    public static final String Url_taskVoteLikeList = url + "/app/task/taskVoteLikeList";
    public static final String Url_taskActInfo = url + "/app/act/taskActInfo";
    public static final String Url_taskActHistory = url + "/app/act/taskActHistory";
    public static final String Url_followAct = url + "/app/act/followAct";
    public static final String Url_followActList = url + "/app/act/followActList";
    public static final String Url_topiclist = url + "/app/topic/topicTask";
    public static final String Url_followTopicList = url + "/app/topic/followTopicList";
    public static final String Url_followTopic = url + "/app/topic/followTopic";
    public static final String Url_topicInfo = url + "/app/topic/getTopic";
//    public static final String Url_topic = url + "/app/topic/getTopic";
//    public static final String Url_topic = url + "/app/topic/getTopic";
//    public static final String Url_topic = url + "/app/topic/getTopic";


}
