package com.prettyyes.user.model;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.model
 * Author: SmileChen
 * Created on: 2016/9/2
 * Description: Nothing
 */
public class VersionModel extends BaseModel {

    /**
     * lowerVersion : V4
     * version : V4
     * downloadUrl : http://img.prettyyes.com/app-baidu-debug.apk
     * content :
     * needUpdate : 0
     * mustUpdate : 0
     */

    private String lowerVersion;
    private Integer lowerVersion_code;
    private String version;
    private Integer current_version_code;
    private String downloadUrl;
    private String content;
    private String needUpdate;
    private String mustUpdate;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private boolean isNotify=false;

    public String getLowerVersion() {
        return lowerVersion;
    }

    public void setLowerVersion(String lowerVersion) {
        this.lowerVersion = lowerVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(String needUpdate) {
        this.needUpdate = needUpdate;
    }

    public String getMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(String mustUpdate) {
        this.mustUpdate = mustUpdate;
    }

    public boolean isNotify() {
        return isNotify;
    }
    public void setIsNotify(boolean isNotify) {
        this.isNotify = isNotify;
    }

    public Integer getLowerVersion_code() {
        return lowerVersion_code;
    }

    public void setLowerVersion_code(int lowerVersion_code) {
        this.lowerVersion_code = lowerVersion_code;
    }

    public Integer getCurrent_version_code() {
        return current_version_code;
    }

    public void setCurrent_version_code(int current_version_code) {
        this.current_version_code = current_version_code;
    }

    @Override
    public String toString() {
        return "VersionModel{" +
                "lowerVersion='" + lowerVersion + '\'' +
                ", lowerVersion_code=" + lowerVersion_code +
                ", version='" + version + '\'' +
                ", current_version_code=" + current_version_code +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", content='" + content + '\'' +
                ", needUpdate='" + needUpdate + '\'' +
                ", mustUpdate='" + mustUpdate + '\'' +
                ", time=" + time +
                ", isNotify=" + isNotify +
                '}';
    }
}
