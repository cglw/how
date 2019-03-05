package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: 省市区
 */
public class ClientRegion extends BaseModel{

    /**
     * rid : 2
     * pid : 1
     * name : 北京
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int rid;
        private int pid;
        private String name;

        public void setRid(int rid) {
            this.rid = rid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRid() {
            return rid;
        }

        public int getPid() {
            return pid;
        }

        public String getName() {
            return name;
        }
    }
}
