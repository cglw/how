package com.prettyyes.user.model.v8;

/**
 * Created by chengang on 2018/1/22.
 */

public class BannerItemEntity {


    /**
     * id : 10
     * ad_type :
     * ad_title : 测试10
     * ad_content : {"desc":"测试测试测试测试","nickname":"测试","ts_reason":"测试测试测试测试测试","headimg":"https://image.prettyyes.com/seller-laravel-9-5777-1516356863.png","ace_txt":"测试测试测试测试测试","advertisement_img":"https://image.prettyyes.com/seller-laravel-9-8739-1516356873.png;https://image.prettyyes.com/seller-laravel-9-6247-1516356876.jpg","slogen":"测试","h5_url":"www.baidu.com"}
     * start_time : 2018-01-19 00:00:00
     * end_time : 2018-02-22 00:00:00
     */

    private String id;
    private String ad_type;
    private String ad_title;
    private AdContentBean ad_content;
    private String start_time;
    private String end_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_title() {
        return ad_title;
    }

    public void setAd_title(String ad_title) {
        this.ad_title = ad_title;
    }

    public AdContentBean getAd_content() {
        return ad_content;
    }

    public void setAd_content(AdContentBean ad_content) {
        this.ad_content = ad_content;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public static class AdContentBean {
        /**
         * desc : 测试测试测试测试
         * nickname : 测试
         * ts_reason : 测试测试测试测试测试
         * headimg : https://image.prettyyes.com/seller-laravel-9-5777-1516356863.png
         * ace_txt : 测试测试测试测试测试
         * advertisement_img : https://image.prettyyes.com/seller-laravel-9-8739-1516356873.png;https://image.prettyyes.com/seller-laravel-9-6247-1516356876.jpg
         * slogen : 测试
         * h5_url : www.baidu.com
         */

        private String desc;
        private String nickname;
        private String ts_reason;
        private String headimg;
        private String ace_txt;
        private String advertisement_img;
        private String slogan;
        private String h5_url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTs_reason() {
            return ts_reason;
        }

        public void setTs_reason(String ts_reason) {
            this.ts_reason = ts_reason;
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

        public String getAdvertisement_img() {
            return advertisement_img;
        }

        public void setAdvertisement_img(String advertisement_img) {
            this.advertisement_img = advertisement_img;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }


        public String getH5_url() {
            return h5_url;
        }

        public void setH5_url(String h5_url) {
            this.h5_url = h5_url;
        }
    }
}
