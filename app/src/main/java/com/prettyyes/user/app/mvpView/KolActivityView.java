package com.prettyyes.user.app.mvpView;

import android.view.View;
import android.widget.CheckBox;

/**
 * Created by chengang on 2017/2/9.
 */

public interface KolActivityView extends BaseView {
    void setHashTag(String hashTag);
    void setActivityDesc(String desc);
    void setDay(String day);
    void setMonth(String month);
    void setWeek(String week);
    void setLeftTime(String detailTime);
    void setKollistVp();
    void setCollectShow(boolean is);
    View getVp();
    void share();
    void collect();
    void addToCalendar();
    int getActivityId();
    View getBottomUnstart();
    View getBottomStart();
    View getBottomEnd();
    View getTopBg();
    View getTopTag();
    View getAddCalendar();
    CheckBox getCheckBox();
    void setActProgress(int progress);
}
