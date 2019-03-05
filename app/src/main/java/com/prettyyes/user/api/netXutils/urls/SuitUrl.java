package com.prettyyes.user.api.netXutils.urls;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.api.netXutils.urls
 * Author: SmileChen
 * Created on: 2016/7/21
 * Description: 套系Url
 */
public class SuitUrl extends BaseUrl {
    //收藏套系
    public static final String Url_suitFavourite= url+"/app/suit/addfavourite";
    //套系列表
    public static final String Url_suitFavouriteList= url+"/app/suit/favouriteList";

    //获取套系的订单评论
    public static final String Url_suitSuitordercomment= url+"/app/suit/suitordercommen";
    //获取套系的评论与问答
    public static final String Url_suitSuitTaskAndComment= url+"/app/suit/suitTaskAndComment";
    //套系详情
    public static final String Url_suitGet= url+"/app/suit/get";
    //用户获取商家已搭配好的套系列表
    public static final String Url_suitUserGetSuitListBySellerId= url+"/app/suit/userGetSuitListBySellerId";
    public static final String Url_userGetSuitListBySellerId= url+"/app/suit/userGetSuitListBySellerId";
    public static final String Url_answerListBySku= url+"/app/task/answerListBySku";
}
