package com.prettyyes.user.model.task;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.task
 * Author: SmileChen
 * Created on: 2016/12/19
 * Description: Nothing
 */
public class TaskLikeList {

    /**
     * task_id : 16313
     * like : 1
     * create_time : 2016-12-09 09:14:00
     * nickname : py_1477815152333
     * desc : Sdfadsfasdfasdfsadfsdf
     */

    private List<ListEntity> list;

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {
        private int task_id;
        private int like;
        private String create_time;
        private String nickname;
        private String desc;

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
