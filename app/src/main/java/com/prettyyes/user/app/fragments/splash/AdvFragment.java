package com.prettyyes.user.app.fragments.splash;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.SPUtils;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.ImageHelper;
import com.prettyyes.user.model.common.AppBanner;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.IOException;

public class AdvFragment extends BaseFragment {
    @ViewInject(R.id.img_advfragment_adv)
    private ImageView img_adv;
    @ViewInject(R.id.btn_advfragment_jump)
    private Button btn_jump;
    private int time = 5;
    private boolean iscontinue = true;
    AppBanner.ListEntity banner;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (iscontinue)
                if (msg.what == 1) {
                    if (time > 0) {
                        btn_jump.setText("跳过 " + time + "s");
                        time--;
                        sendEmptyMessageDelayed(1, 1000);
                    } else {
                        if (iscontinue)
                            jumpToMain();
                    }
                }
        }
    };

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_adv;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        banner = new SPUtils<AppBanner.ListEntity>().get(AppBanner.ListEntity.class, "start_page");

    }

    @Override
    public void setListener() {
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iscontinue = false;
                jumpToMain();
            }
        });
        img_adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (banner.getBanner_rule().length() > 0) {
                    if (getActivity() != null) {
                        AppStatistics.onEvent(mContext, "main_ad;"+banner.getId());
//                        jumpToMain();
                        new PushHandler(getActivity()).handReceiveData(banner.getBanner_rule());
                        getActivity().finish();
                        handler.removeMessages(1);
                    }
                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        handler.sendEmptyMessageDelayed(1, 1000);
        if (banner != null && banner.getBanner_rule().length() > 0) {//链接长度正常
            File updateFile = FileUtils.getDiskCacheDir(getActivity(), banner.getImg_url());
            if (!updateFile.exists()) {
                try {
                    updateFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (updateFile.length() > 0) {
                img_adv.setImageBitmap(ImageHelper.comp(updateFile.getAbsolutePath()));
//                ImageLoadUtils.loadSplash(mContext, updateFile.getAbsolutePath(), img_adv);
            }
        } else {
            jumpToMain();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(1);
            handler = null;
        }


    }

    private void jumpToMain() {
        if (!ZBundleCore.getInstance().isExist(MainActivity.class))
            startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }


}
