package com.prettyyes.user.app.mvpView;

import android.widget.ToggleButton;

import com.prettyyes.user.app.ui.appview.SelectMediaView;
import com.prettyyes.user.app.view.FlowLayout;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/3/31.
 */

public interface AskActView extends BaseView {
    EditTextDel getQuestionView();

    SelectMediaView getSelectPhoto();

    ToggleButton getIsOpenView();

    FlowLayout getFlowly_Kol();

    ArrayList<Integer> getKol_Ids();
    void setBulletscreen(String[] res);



}
