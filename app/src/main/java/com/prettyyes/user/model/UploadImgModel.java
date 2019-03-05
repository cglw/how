package com.prettyyes.user.model;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/8/29
 * Description: Nothing
 */
public class UploadImgModel extends BaseModel
{


    /**
     * res : 200
     * msg : 上传成功
     * extra : {"pic_url":"http://img.prettyyes.com/32447-6979-1472459245.jpeg"}
     */

    private int res;
    private String msg;
    /**
     * pic_url : http://img.prettyyes.com/32447-6979-1472459245.jpeg
     */

    private ExtraEntity extra;

    public void setRes(int res) {
        this.res = res;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setExtra(ExtraEntity extra) {
        this.extra = extra;
    }

    public int getRes() {
        return res;
    }

    public String getMsg() {
        return msg;
    }

    public ExtraEntity getExtra() {
        return extra;
    }

    public static class ExtraEntity {
        private String pic_url;

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getPic_url() {
            return pic_url;
        }
    }
}
