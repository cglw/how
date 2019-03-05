package com.prettyyes.user.model.applocal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengang on 2017/6/19.
 */

public class AnswerInfoEntity implements Serializable {
    private String seller_id;
    private String sku_id;
    private String price;
    private String sku_name;
    private int share_status;
    private String brand_id;
    private String brand_name;
    private String brand_image;
    private String uid;
    private String category_name;
    private List<String> img_arr;
    private String task_id;
    private String sku_type;
    private String answer_id;
    private String answer_like_num;
    private String answer_dislike_num;


}
