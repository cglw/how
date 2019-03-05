package com.prettyyes.user.app.fragments.splash;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.ui.MainActivity;
import com.prettyyes.user.core.AdvImgHandler;

public class DefaultFragment extends BaseFragment {

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 1) {
//
//            }
//        }
//    };


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public int bindLayout() {
        return 0;
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

    }

    @Override
    public void doBusiness(Context mContext) {

       // handler.sendEmptyMessageDelayed(1, 2000);
        new AdvImgHandler().loadAdv(getActivity());//下载图片
        next(MainActivity.class);
        if(getActivity()!=null)
            getActivity().finish();
    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }
}
