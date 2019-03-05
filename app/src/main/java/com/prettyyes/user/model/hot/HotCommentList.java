package com.prettyyes.user.model.hot;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.hot
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class HotCommentList  extends BaseModel{
    private List<CommentEntity> list;
    private int total_number;
    public void setList(List<CommentEntity> list) {
        this.list = list;
    }

    public List<CommentEntity> getList() {
        return list;
    }
    public static class CommentEntity
    {
        private int comment_id;
        private String uuid;
        private String uname;
        private String task_id;
        private String content;
        private String ptc_createtime;
        private String headerimg;

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPtc_createtime() {
            return ptc_createtime;
        }

        public void setPtc_createtime(String ptc_createtime) {
            this.ptc_createtime = ptc_createtime;
        }

        public String getHeaderimg() {
            return headerimg;
        }

        public void setHeaderimg(String headerimg) {
            this.headerimg = headerimg;
        }
    }
}
