package com.prettyyes.user.api.netXutils.response;

/**
 * Created by chengang on 2017/7/24.
 */

public class RichesRes extends BaseRes {
    /**
     * info : {"total_money":308.64,"reward_money":"308.63","week_money":0,"get_money":0,"is_get_apply":"0","apply_money":"0.00","apply_time":"0000-00-00 00:00:00","bank_card":"4392258383981125","bank_telephone":"13142688817","bank_id":"CMB","bank_card_string":"4392********1125","bank_name":"招商银行","bank_uname":"顾冯冯","alipay_username":"顾冯冯","alipay_account":"13142688817","alipay_telephone":"13142688817"}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * total_money : 308.64
         * reward_money : 308.63
         * week_money : 0
         * get_money : 0
         * is_get_apply : 0
         * apply_money : 0.00
         * apply_time : 0000-00-00 00:00:00
         * bank_card : 4392258383981125
         * bank_telephone : 13142688817
         * bank_id : CMB
         * bank_card_string : 4392********1125
         * bank_name : 招商银行
         * bank_uname : 顾冯冯
         * alipay_username : 顾冯冯
         * alipay_account : 13142688817
         * alipay_telephone : 13142688817
         */

        private String total_money;
        private String reward_money;
        private String week_money;
        private String get_money;
        private String is_get_apply;
        private String apply_money;
        private String apply_time;
        private String bank_card;
        private String bank_telephone;
        private String bank_id;
        private String bank_card_string;
        private String bank_name;
        private String bank_uname;
        private String bank_branch;
        private String alipay_username;
        private String alipay_account;
        private String alipay_telephone;

        public String getBank_branch() {
            return bank_branch;
        }

        public void setBank_branch(String bank_branch) {
            this.bank_branch = bank_branch;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public void setReward_money(String reward_money) {
            this.reward_money = reward_money;
        }

        public String getWeek_money() {
            return week_money;
        }

        public void setWeek_money(String week_money) {
            this.week_money = week_money;
        }

        public String getGet_money() {
            return get_money;
        }

        public void setGet_money(String get_money) {
            this.get_money = get_money;
        }

        public String getReward_money() {
            return reward_money;
        }

        public String getIs_get_apply() {
            return is_get_apply;
        }

        public void setIs_get_apply(String is_get_apply) {
            this.is_get_apply = is_get_apply;
        }

        public String getApply_money() {
            return apply_money;
        }

        public void setApply_money(String apply_money) {
            this.apply_money = apply_money;
        }

        public String getApply_time() {
            return apply_time;
        }

        public void setApply_time(String apply_time) {
            this.apply_time = apply_time;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getBank_telephone() {
            return bank_telephone;
        }

        public void setBank_telephone(String bank_telephone) {
            this.bank_telephone = bank_telephone;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_card_string() {
            return bank_card_string;
        }

        public void setBank_card_string(String bank_card_string) {
            this.bank_card_string = bank_card_string;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_uname() {
            return bank_uname;
        }

        public void setBank_uname(String bank_uname) {
            this.bank_uname = bank_uname;
        }

        public String getAlipay_username() {
            return alipay_username;
        }

        public void setAlipay_username(String alipay_username) {
            this.alipay_username = alipay_username;
        }

        public String getAlipay_account() {
            return alipay_account;
        }

        public void setAlipay_account(String alipay_account) {
            this.alipay_account = alipay_account;
        }

        public String getAlipay_telephone() {
            return alipay_telephone;
        }

        public void setAlipay_telephone(String alipay_telephone) {
            this.alipay_telephone = alipay_telephone;
        }
    }
}
