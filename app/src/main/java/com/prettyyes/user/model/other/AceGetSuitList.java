package com.prettyyes.user.model.other;

import com.prettyyes.user.model.BaseModel;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.other
 * Author: SmileChen
 * Created on: 2016/7/22
 * Description: Nothing
 */
public class AceGetSuitList extends BaseModel{

    /**
     * suit_id : 6062
     * name : 小个子心机装
     * uid : 2545
     * cover_img : http://img.prettyyes.com/2545-3422-1458454721.jpeg
     * like_num : 0
     * desc : 短款上装蕾丝面料：尼龙混合，建议冷水手洗。搭白色阔腿长裤粘胶纤维混合材质，只可干洗。配简约一字高跟鞋，都市职业女性气质干练清新时尚风格如行云流水般自由舒放。
     * bright_point : 这款蕾丝短款上装圆齿衣边，局部半透明设计，端庄之中多了几分妩媚，后身锁眼及纽扣设计，性感优雅。阔腿长裤边侧西装口袋，帅气利落百搭有型，修饰各种腿型问题。
     * rubbish : 浅色不耐脏哦！
     * create_time : 2016-03-20 14:22:16
     * status : 4
     * price : 2926.00
     * video_src :
     * suit_img : http://img.prettyyes.com/2545-3422-1458454721.jpeg;http://img.prettyyes.com/2545-9622-1458454729.jpeg;http://img.prettyyes.com/2545-1157-1458454729.jpeg;http://img.prettyyes.com/2545-6858-1458454730.jpeg;http://img.prettyyes.com/2545-5658-1458454741.jpeg;http://img.prettyyes.com/2545-9609-1458454742.jpeg;http://img.prettyyes.com/2545-8451-1458454743.jpeg
     * tags_info : [{"tag_name":"休闲","tag_id":126,"suit_id":6062},{"tag_name":"欧美","tag_id":90,"suit_id":6062},{"tag_name":"简约","tag_id":385,"suit_id":6062},{"tag_name":"优雅","tag_id":98,"suit_id":6062},{"tag_name":"OL","tag_id":111,"suit_id":6062}]
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int suit_id;
        private String name;
        private int uid;
        private String cover_img;
        private int like_num;
        private String desc;
        private String bright_point;
        private String rubbish;
        private String create_time;
        private int status;
        private String price;
        private String video_src;
        private String suit_img;
        /**
         * tag_name : 休闲
         * tag_id : 126
         * suit_id : 6062
         */

        private List<TagsInfoEntity> tags_info;

        public void setSuit_id(int suit_id) {
            this.suit_id = suit_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setBright_point(String bright_point) {
            this.bright_point = bright_point;
        }

        public void setRubbish(String rubbish) {
            this.rubbish = rubbish;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setVideo_src(String video_src) {
            this.video_src = video_src;
        }

        public void setSuit_img(String suit_img) {
            this.suit_img = suit_img;
        }

        public void setTags_info(List<TagsInfoEntity> tags_info) {
            this.tags_info = tags_info;
        }

        public int getSuit_id() {
            return suit_id;
        }

        public String getName() {
            return name;
        }

        public int getUid() {
            return uid;
        }

        public String getCover_img() {
            return cover_img;
        }

        public int getLike_num() {
            return like_num;
        }

        public String getDesc() {
            return desc;
        }

        public String getBright_point() {
            return bright_point;
        }

        public String getRubbish() {
            return rubbish;
        }

        public String getCreate_time() {
            return create_time;
        }

        public int getStatus() {
            return status;
        }

        public String getPrice() {
            return price;
        }

        public String getVideo_src() {
            return video_src;
        }

        public String getSuit_img() {
            return suit_img;
        }

        public List<TagsInfoEntity> getTags_info() {
            return tags_info;
        }

        public static class TagsInfoEntity {
            private String tag_name;
            private int tag_id;
            private int suit_id;

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public void setTag_id(int tag_id) {
                this.tag_id = tag_id;
            }

            public void setSuit_id(int suit_id) {
                this.suit_id = suit_id;
            }

            public String getTag_name() {
                return tag_name;
            }

            public int getTag_id() {
                return tag_id;
            }

            public int getSuit_id() {
                return suit_id;
            }
        }
    }
}
