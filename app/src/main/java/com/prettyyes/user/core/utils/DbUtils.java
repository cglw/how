package com.prettyyes.user.core.utils;

import org.xutils.DbManager;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/9/2
 * Description: Nothing
 */
public class DbUtils {
    private static DbManager.DaoConfig daoConfig;
    public static DbManager.DaoConfig getDaoConfig(){
        if(daoConfig==null){
            daoConfig = new DbManager.DaoConfig()
                    .setAllowTransaction(true)//设置允许开启事务
                    .setDbName("prettyyes_user.db")//创建数据库的名称
                    .setDbVersion(1)//数据库版本号
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }
}
