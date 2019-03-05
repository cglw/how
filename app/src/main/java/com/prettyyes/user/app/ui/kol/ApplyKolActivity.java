package com.prettyyes.user.app.ui.kol;

import android.view.View;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.StatusBarUtil;

public class ApplyKolActivity extends BaseActivity {


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, AppConfig.STARTBAR_ALPHA, null);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_apply_kol;
    }

    @Override
    protected void initViews() {
        hideHeader();

    }

    @Override
    protected void loadData() {

    }
    public void goApplyKolDetail(View view)
    {
        JumpPageManager.getManager().goApplayKolDetailActivity(this);
    }

}
