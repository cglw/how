package com.prettyyes.user.app.ui.common;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.SPUtils;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.ImageHelper;
import com.prettyyes.user.model.common.AppBanner;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
//首页弹出来的透明对话框

public class AlertMessageActivity extends BaseActivity {

    @ViewInject(R.id.img_AlertmessageAct_alertimg)
    private ImageView img_alert;
    @ViewInject(R.id.img_AlertmessageAct_close)
    private ImageView img_close;
    private String filename = "'";

    @Override
    public boolean isRemove_loadingLayout() {
        return true;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initVariables() {
        filename = getIntent().getStringExtra("filename");
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_alertmessage;
    }

    @Override
    protected void initViews() {
        hideHeader();
    }

    @Override
    protected void setListener() {
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivityTopout();
            }
        });
        img_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(mContext,"pop_ad");
                AppBanner.ListEntity a = new SPUtils<AppBanner.ListEntity>().get(AppBanner.ListEntity.class, "alert_page");
                new PushHandler(AlertMessageActivity.this).handReceiveData(a.getBanner_rule());
                finish();
            }
        });
    }


    @Override
    public void back() {
        finish();
        overridePendingTransition(0, R.anim.push_top_out);

    }

    @Override
    public void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    @Override
    protected void loadData() {

        File updateFile = FileUtils.getDiskCacheDir(BaseApplication.getAppContext(), filename);
        if (updateFile.exists() && updateFile.length() > 0) {
            try {
                img_alert.setImageBitmap(ImageHelper.getimage(updateFile.getAbsolutePath(), 1000));
            } catch (Exception ee) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.push_top_out);
    }
}
