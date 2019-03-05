package com.prettyyes.user.model.type;

import java.util.List;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.type
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class RewardListEntity {

    /**
     * reward_id : 3576
     * reward_number : 161101284593595
     * reward_type : paper
     * reward_type_id : 83
     * seller_id : 1085
     * pay_type : 2
     * total_price : 100.00
     * amount_price : 100.00
     * create_time : 2016-11-22 19:34:19
     * pay_time : 2016-11-22 20:09:11
     * task_desc : 我希望在很酷的18岁，购买上门量体的衣服。
     * task_id : 11111
     * reward : {"paper_id":83,"desc":"&lt;div&gt;&lt;div&gt;&lt;blockquote&gt;&lt;p&gt;&lt;strong&gt;图虫摄影书房 摄影书征集比赛  评委的话&lt;/strong&gt;&lt;/p&gt;&lt;/blockquote&gt;&lt;p&gt;日本作家村上春树的小说《1Q84》在某种程度上回应着英国作家乔治·奥威尔的《1984》，郑龙一海的手工书作品《空气蛹》借助了《1Q84》中空气蛹的概念，并试图以蛹的形象来表征文化在不同地域不同时间演变中的具化物（繁殖的形象），以此展开了许多对于\u201c文化流变\u201d这一现象的发问。书的形式则是制作成了一个长折页构成的（单面）手帖，这种形式天然的连续线性结构与作者探讨的\u201c文化演变\u201d的概念非常契合，而在书的编排方式中我们也可以清晰地看到许多影射流逝、时间、文化编码等关键元素有节奏地埋在其中，以此潜移默化地使观看者得以进入到作者的语境里。在我看来，至少作者制作的这本书是有效地帮助了作品本身叙事的流畅性，而不单单是呈现照片本身。&lt;/p&gt;&lt;p&gt;\u2014\u2014王欢（摄影编辑、写作者）&lt;/p&gt;&lt;br&gt;&lt;br&gt;作者：杜扬Seatory&lt;br&gt;链接：https://zhuanlan.zhihu.com/p/20856265&lt;br&gt;来源：知乎&lt;br&gt;著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。55&lt;/div&gt;&lt;/div&gt;","paper_image":"http://img.prettyyes.com/default_paper7.png","price":"0.01","paper_name":"测试","seller_id":1085,"simple_desc":"图虫摄影书房 摄影书征集比赛  评委的话日本作家村上春树的小说《1Q84》在某种程度上回应着英国作家乔治·奥威尔的《1984》，郑龙一海的手工书作品《空气蛹》借助了《1Q84》中空气蛹的概念，并试图以蛹的形象来表征文化在不同地域不同时间演变中的具化物（繁殖的形象），以此展开了许多对于\u201c文化流变\u201d这一现象的发问。书的形式则是制作成了一个长折页构成的（单面）手帖，这种形式天然的连续线性结构与作者探讨的\u201c文化演变\u201d的概念非常契合，而在书的编排方式中我们也可以清晰地看到许多影射流逝、时间、文化编码等关键元素有节奏地埋在其中，以此潜移默化地使观看者得以进入到作者的语境里。在我看来，至少作者制作的这本书是有效地帮助了作品本身叙事的流畅性，而不单单是呈现照片本身。\u2014\u2014王欢（摄影编辑、写作者）作者：杜扬Seatory链接：https://zhuanlan.zhihu.com/p/20856265来源：知乎著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。55","word_num":1185,"reward_status":0}
     */

    private List<InfoEntity> info;

    public List<InfoEntity> getInfo() {
        return info;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public static class InfoEntity {
        private int reward_id;
        private String reward_number;
        private String reward_type;
        private int reward_type_id;
        private int seller_id;
        private String pay_type;
        private String total_price;
        private String amount_price;
        private String create_time;
        private String pay_time;
        private String task_desc;
        private int task_id;
        /**
         * paper_id : 83
         * desc : &lt;div&gt;&lt;div&gt;&lt;blockquote&gt;&lt;p&gt;&lt;strong&gt;图虫摄影书房 摄影书征集比赛  评委的话&lt;/strong&gt;&lt;/p&gt;&lt;/blockquote&gt;&lt;p&gt;日本作家村上春树的小说《1Q84》在某种程度上回应着英国作家乔治·奥威尔的《1984》，郑龙一海的手工书作品《空气蛹》借助了《1Q84》中空气蛹的概念，并试图以蛹的形象来表征文化在不同地域不同时间演变中的具化物（繁殖的形象），以此展开了许多对于“文化流变”这一现象的发问。书的形式则是制作成了一个长折页构成的（单面）手帖，这种形式天然的连续线性结构与作者探讨的“文化演变”的概念非常契合，而在书的编排方式中我们也可以清晰地看到许多影射流逝、时间、文化编码等关键元素有节奏地埋在其中，以此潜移默化地使观看者得以进入到作者的语境里。在我看来，至少作者制作的这本书是有效地帮助了作品本身叙事的流畅性，而不单单是呈现照片本身。&lt;/p&gt;&lt;p&gt;——王欢（摄影编辑、写作者）&lt;/p&gt;&lt;br&gt;&lt;br&gt;作者：杜扬Seatory&lt;br&gt;链接：https://zhuanlan.zhihu.com/p/20856265&lt;br&gt;来源：知乎&lt;br&gt;著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。55&lt;/div&gt;&lt;/div&gt;
         * paper_image : http://img.prettyyes.com/default_paper7.png
         * price : 0.01
         * paper_name : 测试
         * seller_id : 1085
         * simple_desc : 图虫摄影书房 摄影书征集比赛  评委的话日本作家村上春树的小说《1Q84》在某种程度上回应着英国作家乔治·奥威尔的《1984》，郑龙一海的手工书作品《空气蛹》借助了《1Q84》中空气蛹的概念，并试图以蛹的形象来表征文化在不同地域不同时间演变中的具化物（繁殖的形象），以此展开了许多对于“文化流变”这一现象的发问。书的形式则是制作成了一个长折页构成的（单面）手帖，这种形式天然的连续线性结构与作者探讨的“文化演变”的概念非常契合，而在书的编排方式中我们也可以清晰地看到许多影射流逝、时间、文化编码等关键元素有节奏地埋在其中，以此潜移默化地使观看者得以进入到作者的语境里。在我看来，至少作者制作的这本书是有效地帮助了作品本身叙事的流畅性，而不单单是呈现照片本身。——王欢（摄影编辑、写作者）作者：杜扬Seatory链接：https://zhuanlan.zhihu.com/p/20856265来源：知乎著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。55
         * word_num : 1185
         * reward_status : 0
         */

        private RewardEntity reward;

        public int getReward_id() {
            return reward_id;
        }

        public void setReward_id(int reward_id) {
            this.reward_id = reward_id;
        }

        public String getReward_number() {
            return reward_number;
        }

        public void setReward_number(String reward_number) {
            this.reward_number = reward_number;
        }

        public String getReward_type() {
            return reward_type;
        }

        public void setReward_type(String reward_type) {
            this.reward_type = reward_type;
        }

        public int getReward_type_id() {
            return reward_type_id;
        }

        public void setReward_type_id(int reward_type_id) {
            this.reward_type_id = reward_type_id;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getAmount_price() {
            return amount_price;
        }

        public void setAmount_price(String amount_price) {
            this.amount_price = amount_price;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getTask_desc() {
            return task_desc;
        }

        public void setTask_desc(String task_desc) {
            this.task_desc = task_desc;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public RewardEntity getReward() {
            return reward;
        }

        public void setReward(RewardEntity reward) {
            this.reward = reward;
        }

        public static class RewardEntity {
            private int paper_id;
            private String desc;
            private String paper_image;
            private String price;
            private String paper_name;
            private int seller_id;
            private String simple_desc;
            private int word_num;
            private int reward_status;

            public int getPaper_id() {
                return paper_id;
            }

            public void setPaper_id(int paper_id) {
                this.paper_id = paper_id;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPaper_name() {
                return paper_name;
            }

            public void setPaper_name(String paper_name) {
                this.paper_name = paper_name;
            }

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

            public int getWord_num() {
                return word_num;
            }

            public void setWord_num(int word_num) {
                this.word_num = word_num;
            }

            public int getReward_status() {
                return reward_status;
            }

            public void setReward_status(int reward_status) {
                this.reward_status = reward_status;
            }
        }
    }
}
