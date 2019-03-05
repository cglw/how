package com.prettyyes.user.model.v8;

import com.prettyyes.user.app.base.BaseType;
import com.prettyyes.user.app.base.BaseViewTypeFactory;
import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2018/1/31.
 */

public class HowScoreGoods extends BaseModel implements BaseType {
    /**
     * id : string
     * spu_id : string
     * spu_name : string
     * main_img : string
     * spu_type : string
     * module_id : string
     * how_score : string
     */

    private String id;
    private String spu_id;
    private String spu_name;
    private String main_img;
    private String spu_type;
    private String module_id;
    private String how_score;
    private String exchange="0";




    public boolean isHaveReceive() {
        return "1".equals(exchange);
    }

    public boolean canReceive() {
        return "0".equals(exchange);
    }

    public String getIs_receive() {
        return exchange;
    }

    public void setIs_receive(String is_receive) {
        this.exchange = is_receive;
    }

    @Override
    public int getTypeId(BaseViewTypeFactory baseViewTypeFactory) {
        return baseViewTypeFactory.type(this);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getSpu_type() {
        return spu_type;
    }

    public void setSpu_type(String spu_type) {
        this.spu_type = spu_type;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getHow_score() {
        return how_score;
    }

    public void setHow_score(String how_score) {
        this.how_score = how_score;
    }
}
