package com.prettyyes.user.app.mvpPresenter;

import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.db.annotation.Column;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.presenter
 * Author: SmileChen
 * Created on: 2016/10/21
 * Description: Nothing
 */
public class QuestionEntity  implements Serializable{
    @Column(name = "hash_tag")
    private String hash_tag;
    @Column(name = "at_kols")
    private String at_kols;
    @Column(name = "act_id")
    private String act_id;
    @Column(name = "topic_id")
    private String topic_id;
    @Column(name = "topic_hash_tag")
    private String topic_hash_tag;
    @Column(name = "question")
    private String question;
    @Column(name = "sence")
    private String sence;
    @Column(name = "imgArray")
    private ArrayList<String> imgArray;
    @Column(name = "isopen")
    private int isopen;
    @Column(name = "maxprice")
    private float Maxprice;
    @Column(name = "minprice")
    private float Minprice;
    @Column(name = "pic_list")
    private String pic_list;
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "seller_list")
    private ArrayList<SellerInfoEntity>seller_list;

    public String getTopic_hash_tag() {
        return topic_hash_tag;
    }

    public void setTopic_hash_tag(String topic_hash_tag) {
        this.topic_hash_tag = topic_hash_tag;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public ArrayList<SellerInfoEntity> getSeller_list() {
        return seller_list;
    }

    public void setSeller_list(ArrayList<SellerInfoEntity> seller_list) {
        this.seller_list = seller_list;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public QuestionEntity() {
        imgArray = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSence() {
        return sence;
    }

    public void setSence(String sence) {
        this.sence = sence;
    }

    public ArrayList<String> getImgArray() {
        return imgArray;
    }

    public void setImgArray(ArrayList<String> imgArray) {
        this.imgArray = imgArray;
    }

    public int getIsopen() {
        return isopen;
    }

    public void setIsopen(int isopen) {
        this.isopen = isopen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getMaxprice() {
        return Maxprice;
    }

    public void setMaxprice(float maxprice) {
        Maxprice = maxprice;
    }

    public float getMinprice() {
        return Minprice;
    }

    public void setMinprice(float minprice) {
        Minprice = minprice;
    }

    public String getPic_list() {
        return pic_list;
    }

    public void setPic_list(String pic_list) {
        this.pic_list = pic_list;
    }

    public String getAt_kols() {
        return at_kols;
    }

    public void setAt_kols(String at_kols) {
        this.at_kols = at_kols;
    }

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "question='" + question + '\'' +
                ", sence='" + sence + '\'' +
                ", imgArray=" + imgArray +
                ", isopen=" + isopen +
                ", Maxprice=" + Maxprice +
                ", Minprice=" + Minprice +
                ", pic_list='" + pic_list + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
