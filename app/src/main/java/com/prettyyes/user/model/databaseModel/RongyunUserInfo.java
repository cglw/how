package com.prettyyes.user.model.databaseModel;

import com.prettyyes.user.core.utils.DbUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model.databaseModel
 * Author: SmileChen
 * Created on: 2016/9/2
 * Description: Nothing
 */
@Table(name = "RongyunUserInfo")
public class RongyunUserInfo {
    @Column(name = "portraitUri")
    private String portraitUri;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "id", isId = true)
    private String id;

    public RongyunUserInfo() {
        db = x.getDb(DbUtils.getDaoConfig());
    }

    protected DbManager db;

    public void save(RongyunUserInfo rongyunUserInfo) throws DbException {
        RongyunUserInfo find = db.selector(RongyunUserInfo.class).where("user_id", "=", rongyunUserInfo.getUser_id()).findFirst();
        if (find == null) {
            db.save(rongyunUserInfo);
            getRongyunUserInfo();
        } else {
            find.setPortraitUri(rongyunUserInfo.getPortraitUri());
            find.setNickname(rongyunUserInfo.getNickname());
            db.update(find, "nickname", "portraitUri");

        }
    }

    public ArrayList<RongyunUserInfo> getRongyunUserInfo() throws DbException {
        ArrayList<RongyunUserInfo> data = (ArrayList) db.selector(RongyunUserInfo.class).findAll();
        if (data == null) {
            return null;
        }
        return data;
    }

    public void delete() throws DbException {


    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPortraitUri() {

        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "RongyunUserInfo{" +
                "portraitUri=" + portraitUri +
                ", nickname='" + nickname + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
