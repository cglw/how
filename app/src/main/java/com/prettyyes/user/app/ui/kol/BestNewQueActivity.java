package com.prettyyes.user.app.ui.kol;

import android.support.v4.app.Fragment;
import android.view.View;

import com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment;
import com.prettyyes.user.app.ui.common.MutiTabActivity;

import java.util.ArrayList;

import static com.prettyyes.user.app.fragments.mianpage.QuestionVpFragment.PAGE_MATCH;

/**
 * Created by chengang on 2017/12/27.
 */

public class BestNewQueActivity extends MutiTabActivity {



    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("最新问答");
//        vp.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
    }


    @Override
    public String[] addTabs() {
        return new String[]{"11"};
    }

    @Override
    public ArrayList<Fragment> addFragment() {
        fragments.add(QuestionVpFragment.newInstance(PAGE_MATCH, "", ""));
//        fragments.add(new QuestionFragment());
        return fragments;
    }
}
