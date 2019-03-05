package com.prettyyes.user.model.task;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.task
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class TaskMyList extends BaseModel {

    /**
     * list : [{"taskid":11331,"uid":1478,"price_s":"100.00","price_e":"1000.00","gender":2,"desc":"Nnggfgggf","pic_list":"0","b_uid":0,"b_suit_id":0,"status":1,"create_time":"2016-07-15 15:21:52","update_time":"2016-07-15 15:21:52","view_num":0,"like_num":0,"share_num":0,"dislike_num":0,"task_function":"0","seller_rob":"1","page":1,"task_act_id":0,"is_open":0,"tip":0,"userinfo":{"uid":1478,"username":"13390001990","nickname":"FR66Dom==","realname":"","email":"","telephone":"133****1990","headimg":"http://img.prettyyes.com/1478-1381-1457603128.jpeg","gender":2,"grade":1},"scene":{"scene_name":"闷热的尴尬","scene_id":434,"background":"http://img.prettyyes.com/9-1108-1457335288.jpeg"},"is_issue":"0"},{"taskid":11330,"uid":1478,"price_s":"100.00","price_e":"1000.00","gender":2,"desc":"11111111","pic_list":"0","b_uid":0,"b_suit_id":0,"status":1,"create_time":"2016-07-13 10:48:30","update_time":"2016-07-13 10:48:30","view_num":6,"like_num":0,"share_num":0,"dislike_num":0,"task_function":"0","seller_rob":"1","page":1,"task_act_id":0,"is_open":0,"tip":0,"userinfo":{"uid":1478,"username":"13390001990","nickname":"FR66Dom==","realname":"","email":"","telephone":"133****1990","headimg":"http://img.prettyyes.com/1478-1381-1457603128.jpeg","gender":2,"grade":1},"scene":{"scene_name":"撩的就是少女心","scene_id":528,"background":"http://img.prettyyes.com/9-5353-1457337264.jpeg"},"is_issue":"0"}]
     * total_tips : 0
     * ab_test : B
     */

    private int total_tips;
    private String ab_test;
    /**
     * taskid : 11331
     * uid : 1478
     * price_s : 100.00
     * price_e : 1000.00
     * gender : 2
     * desc : Nnggfgggf
     * pic_list : 0
     * b_uid : 0
     * b_suit_id : 0
     * status : 1
     * create_time : 2016-07-15 15:21:52
     * update_time : 2016-07-15 15:21:52
     * view_num : 0
     * like_num : 0
     * share_num : 0
     * dislike_num : 0
     * task_function : 0
     * seller_rob : 1
     * page : 1
     * task_act_id : 0
     * is_open : 0
     * tip : 0
     * userinfo : {"uid":1478,"username":"13390001990","nickname":"FR66Dom==","realname":"","email":"","telephone":"133****1990","headimg":"http://img.prettyyes.com/1478-1381-1457603128.jpeg","gender":2,"grade":1}
     * scene : {"scene_name":"闷热的尴尬","scene_id":434,"background":"http://img.prettyyes.com/9-1108-1457335288.jpeg"}
     * is_issue : 0
     */

    private List<ListEntity> list;

    public void setTotal_tips(int total_tips) {
        this.total_tips = total_tips;
    }

    public void setAb_test(String ab_test) {
        this.ab_test = ab_test;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getTotal_tips() {
        return total_tips;
    }

    public String getAb_test() {
        return ab_test;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {

        private int tv1;
        private int tv2;
        private int tv3;

        public int getTv1() {
            return tv1;
        }

        public void setTv1(int tv1) {
            this.tv1 = tv1;
        }

        public int getTv2() {
            return tv2;
        }

        public void setTv2(int tv2) {
            this.tv2 = tv2;
        }

        public int getTv3() {
            return tv3;
        }

        public void setTv3(int tv3) {
            this.tv3 = tv3;
        }


        private int pricecount;
        private int famous;
        private int excellent;
        private String Pscore;
        private String task_count;

        public String getTask_count() {
            return task_count;
        }

        public void setTask_count(String task_count) {
            this.task_count = task_count;
        }

        public String getPscore() {
            return Pscore;
        }

        public void setPscore(String pscore) {
            Pscore = pscore;
        }

        public int getPricecount() {
            return pricecount;
        }

        public void setPricecount(int pricecount) {
            this.pricecount = pricecount;
        }

        public int getFamous() {
            return famous;
        }

        public void setFamous(int famous) {
            this.famous = famous;
        }

        public int getExcellent() {
            return excellent;
        }

        public void setExcellent(int excellent) {
            this.excellent = excellent;
        }

        private int taskid;
        private int uid;
        private String price_s;
        private String price_e;
        private int gender;
        private String desc;
        private String pic_list;
        private int b_uid;
        private int b_suit_id;
        private int status;
        private String create_time;
        private String update_time;
        private int view_num;
        private int like_num;
        private int share_num;
        private int dislike_num;
        private String task_function;
        private String seller_rob;
        private int page;
        private int task_act_id;
        private int is_open;
        private int tip;
        private String hash_tag;
        private int topic_id;
        private String topic_hash_tag;

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public String getTopic_hash_tag() {
            return topic_hash_tag;
        }

        public void setTopic_hash_tag(String topic_hash_tag) {
            this.topic_hash_tag = topic_hash_tag;
        }

        public String getHash_tag() {
            return hash_tag;
        }

        public void setHash_tag(String hash_tag) {
            this.hash_tag = hash_tag;
        }

        /**
         * uid : 1478
         * username : 13390001990
         * nickname : FR66Dom==
         * realname :
         * email :
         * telephone : 133****1990
         * headimg : http://img.prettyyes.com/1478-1381-1457603128.jpeg
         * gender : 2
         * grade : 1
         */

        private UserinfoEntity userinfo;
        /**
         * scene_name : 闷热的尴尬
         * scene_id : 434
         * background : http://img.prettyyes.com/9-1108-1457335288.jpeg
         */

        private SceneEntity scene;
        private String is_issue;

        public void setTaskid(int taskid) {
            this.taskid = taskid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setPrice_s(String price_s) {
            this.price_s = price_s;
        }

        public void setPrice_e(String price_e) {
            this.price_e = price_e;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPic_list(String pic_list) {
            this.pic_list = pic_list;
        }

        public void setB_uid(int b_uid) {
            this.b_uid = b_uid;
        }

        public void setB_suit_id(int b_suit_id) {
            this.b_suit_id = b_suit_id;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public void setView_num(int view_num) {
            this.view_num = view_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public void setDislike_num(int dislike_num) {
            this.dislike_num = dislike_num;
        }

        public void setTask_function(String task_function) {
            this.task_function = task_function;
        }

        public void setSeller_rob(String seller_rob) {
            this.seller_rob = seller_rob;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public void setTask_act_id(int task_act_id) {
            this.task_act_id = task_act_id;
        }

        public void setIs_open(int is_open) {
            this.is_open = is_open;
        }

        public void setTip(int tip) {
            this.tip = tip;
        }

        public void setUserinfo(UserinfoEntity userinfo) {
            this.userinfo = userinfo;
        }

        public void setScene(SceneEntity scene) {
            this.scene = scene;
        }

        public void setIs_issue(String is_issue) {
            this.is_issue = is_issue;
        }

        public int getTaskid() {
            return taskid;
        }

        public int getUid() {
            return uid;
        }

        public String getPrice_s() {
            return price_s;
        }

        public String getPrice_e() {
            return price_e;
        }

        public int getGender() {
            return gender;
        }

        public String getDesc() {
            return desc;
        }

        public String getPic_list() {
            return pic_list;
        }

        public int getB_uid() {
            return b_uid;
        }

        public int getB_suit_id() {
            return b_suit_id;
        }

        public int getStatus() {
            return status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public int getView_num() {
            return view_num;
        }

        public int getLike_num() {
            return like_num;
        }

        public int getShare_num() {
            return share_num;
        }

        public int getDislike_num() {
            return dislike_num;
        }

        public String getTask_function() {
            return task_function;
        }

        public String getSeller_rob() {
            return seller_rob;
        }

        public int getPage() {
            return page;
        }

        public int getTask_act_id() {
            return task_act_id;
        }

        public int getIs_open() {
            return is_open;
        }

        public int getTip() {
            return tip;
        }

        public UserinfoEntity getUserinfo() {
            return userinfo;
        }

        public SceneEntity getScene() {
            return scene;
        }

        public String getIs_issue() {
            return is_issue;
        }



        public static class UserinfoEntity {
            private int uid;
            private String username;
            private String nickname;
            private String realname;
            private String email;
            private String telephone;
            private String headimg;
            private int gender;
            private int grade;

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getUid() {
                return uid;
            }

            public String getUsername() {
                return username;
            }

            public String getNickname() {
                return nickname;
            }

            public String getRealname() {
                return realname;
            }

            public String getEmail() {
                return email;
            }

            public String getTelephone() {
                return telephone;
            }

            public String getHeadimg() {
                return headimg;
            }

            public int getGender() {
                return gender;
            }

            public int getGrade() {
                return grade;
            }
        }

        public static class SceneEntity {
            private String scene_name;
            private int scene_id;
            private String background;

            public void setScene_name(String scene_name) {
                this.scene_name = scene_name;
            }

            public void setScene_id(int scene_id) {
                this.scene_id = scene_id;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getScene_name() {
                return scene_name;
            }

            public int getScene_id() {
                return scene_id;
            }

            public String getBackground() {
                return background;
            }
        }
    }
}
