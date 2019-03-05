package com.prettyyes.user.model.order;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.user
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description:物流信息
 */
public class OrderShipInfo extends BaseModel{


    /**
     * info : [{"context":"【山东省济南市金龙公司】 已收件","time":"2016-05-29 18:44:36"},{"context":"【山东省济南市金龙公司】 已打包","time":"2016-05-29 19:40:20"},{"context":"【山东省济南市金龙公司】 已发出 下一站 【济南转运中心】","time":"2016-05-29 19:52:47"},{"context":"【济南转运中心】 已收入","time":"2016-05-29 21:16:54"},{"context":"【济南转运中心】 已发出 下一站 【杭州转运中心】","time":"2016-05-29 21:18:51"},{"context":"【杭州转运中心】 已收入","time":"2016-05-30 22:40:18"},{"context":"【杭州转运中心】 已发出 下一站 【浙江省杭州市下沙大学城公司】","time":"2016-05-30 23:33:11"},{"context":"【浙江省杭州市下沙大学城公司】 已收入","time":"2016-05-31 07:00:10"},{"context":"【浙江省杭州市下沙大学城公司】 派件人 : 吴小平 派件中 派件员电话13515819692","time":"2016-05-31 08:34:47"},{"context":"客户 签收人 : 本人签收 已签收 感谢使用圆通速递，期待再次为您服务","time":"2016-05-31 12:25:24"}]
     * ship_company : 圆通速递
     * ship_number : 560270707568
     */

    private String ship_company;
    private String ship_number;
    /**
     * context : 【山东省济南市金龙公司】 已收件
     * time : 2016-05-29 18:44:36
     */

    private List<InfoEntity> info;

    public void setShip_company(String ship_company) {
        this.ship_company = ship_company;
    }

    public void setShip_number(String ship_number) {
        this.ship_number = ship_number;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public String getShip_company() {
        return ship_company;
    }

    public String getShip_number() {
        return ship_number;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public static class InfoEntity {
        private String context;
        private String time;

        public void setContext(String context) {
            this.context = context;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public String getTime() {
            return time;
        }
    }
}
