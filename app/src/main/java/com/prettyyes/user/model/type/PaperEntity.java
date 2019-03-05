package com.prettyyes.user.model.type;

import com.prettyyes.user.model.BaseModel;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.type
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class PaperEntity extends BaseModel {


    /**
     * paper_id : 83
     * uid : 1085
     * paper_status : 0
     * desc : <div><div><blockquote><p><strong>图虫摄影书房 摄影书征集比赛  评委的话</strong></p></blockquote><p>日本作家村上春树的小说《1Q84》在某种程度上回应着英国作家乔治·奥威尔的《1984》，郑龙一海的手工书作品《空气蛹》借助了《1Q84》中空气蛹的概念，并试图以蛹的形象来表征文化在不同地域不同时间演变中的具化物（繁殖的形象），以此展开了许多对于“文化流变”这一现象的发问。书的形式则是制作成了一个长折页构成的（单面）手帖，这种形式天然的连续线性结构与作者探讨的“文化演变”的概念非常契合，而在书的编排方式中我们也可以清晰地看到许多影射流逝、时间、文化编码等关键元素有节奏地埋在其中，以此潜移默化地使观看者得以进入到作者的语境里。在我看来，至少作者制作的这本书是有效地帮助了作品本身叙事的流畅性，而不单单是呈现照片本身。</p><p>——王欢（摄影编辑、写作者）</p><br><br>作者：杜扬Seatory<br>链接：https://zhuanlan.zhihu.com/p/20856265<br>来源：知乎<br>著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。55</div></div>
     * paper_image : http://img.prettyyes.com/default_paper7.png
     * paper_name : 测试
     * create_time : 2016-09-29 10:15:54
     * update_time : 2016-10-31 18:18:34
     * price : 100.00
     * view_num : 2
     * like_num : 0
     * buy_count : 0
     */
    private int seller_id;
    private int paper_id;
    private int uid;
    private int paper_status;
    private String desc;
    private String paper_image;
    private String paper_name;
    private String create_time;
    private String update_time;
    private String simple_desc;

    private String price;
    private int view_num;
    private int like_num;
    private int buy_count;
    private int reward_status;
    private int reward_coupon;//1 有打赏券 0 没有打赏券
    private String share_url;

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSimple_desc() {
        return simple_desc;
    }

    public void setSimple_desc(String simple_desc) {
        this.simple_desc = simple_desc;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getReward_coupon() {
        return reward_coupon;
    }

    public void setReward_coupon(int reward_coupon) {
        this.reward_coupon = reward_coupon;
    }

    public int getReward_status() {
        return reward_status;
    }

    public void setReward_status(int reward_status) {
        this.reward_status = reward_status;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPaper_status() {
        return paper_status;
    }

    public void setPaper_status(int paper_status) {
        this.paper_status = paper_status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPaper_image() {
        return paper_image;
    }

    public void setPaper_image(String paper_image) {
        this.paper_image = paper_image;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }
}
