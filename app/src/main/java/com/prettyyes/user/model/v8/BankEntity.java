package com.prettyyes.user.model.v8;

import com.prettyyes.user.model.BaseModel;

/**
 * Created by chengang on 2017/7/24.
 */

public class BankEntity extends BaseModel{

    /**
     * bank_card : 4392258383981125
     * bank_telephone : 13142688817
     * bank_id : CMB
     * bank_card_string : 4392********1125
     * bank_name : 招商银行
     * bank_uname : 顾冯冯
     */

    private String bank_card;
    private String bank_telephone;
    private String bank_id;
    private String bank_card_string;
    private String bank_name;
    private String bank_uname;
    private String bank_branch;

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
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
}
