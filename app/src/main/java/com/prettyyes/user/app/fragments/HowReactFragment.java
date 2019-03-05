package com.prettyyes.user.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.react.ReactRootView;
import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseFragment;
import com.prettyyes.user.app.base.MainApplication;
import com.prettyyes.user.app.ui.appview.ApplyKolView;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.ReacPageChange;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.react_native.HowNativeEventManager;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.react_native.HowNativeEventManager.ResetRouteHome;
import static com.prettyyes.user.react_native.HowNativeEventManager.ResetRouteMorePage;

/**
 * Created by chengang on 2017/7/27.
 */

public class HowReactFragment extends BaseFragment {


    private static final String TAG = "OperateFragment";
    @ViewInject(R.id.framelayout)
    FrameLayout frameLayout;
    @ViewInject(R.id.applykol_view)
    ApplyKolView applykol_view;
    private ReactRootView mReactRootView;

    public String type;

    @Override
    public int bindLayout() {
        return R.layout.fragment_empty;
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {
        type = (String) getArguments().get("type");

    }

    public static HowReactFragment newInstance(String type) {
        HowReactFragment simple = new HowReactFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        simple.setArguments(args);
        return simple;
    }


    @Override
    public void initView(View view) {
        frameLayout.removeAllViews();
        frameLayout.addView(mReactRootView);//添加react布局
        showApplykol();


    }

    @Override
    public void doBusiness(Context mContext) {
    }

    public void showApplykol() {
        if (applykol_view == null)
            return;
        applykol_view.reward();
        if (SpMananger.getUserInfo() != null && SpMananger.getUserInfo().isSeller())
            applykol_view.setVisibility(View.GONE);
        else
            applykol_view.setVisibility(View.VISIBLE);


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mReactRootView = new ReactRootView(context);


    }

    @Override
    public void setListener() {
        super.setListener();
       mSubscription= AppRxBus.getInstance().subscribeEvent(new RxCallback<ReplyQuestionSuccessEvent>() {
            @Override
            protected void back(ReplyQuestionSuccessEvent obj) {
                new HowNativeEventManager().sendReplySuccess(MainApplication.getReactInstanceManager().getCurrentReactContext(), ((ReplyQuestionSuccessEvent) obj).getTask_id());

            }
        }, new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent obj) {
                showApplykol();
                new HowNativeEventManager().sendLoginStateChange(MainApplication.getReactInstanceManager().getCurrentReactContext());

            }
        }, new RxCallback<ReacPageChange>() {
            @Override
            protected void back(ReacPageChange obj) {
                if (obj.type.equals(ResetRouteHome) && type.equals(ResetRouteHome)) {
                    new HowNativeEventManager().changeToHome(MainApplication.getReactInstanceManager().getCurrentReactContext());
                } else if (obj.type.equals(ResetRouteMorePage) && type.equals(ResetRouteMorePage)) {
                    new HowNativeEventManager().changeToMorePage(MainApplication.getReactInstanceManager().getCurrentReactContext());

                }
            }
        }, new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent obj) {
                showApplykol();

            }
        });

    }

    @Override
    public void lazyInitBusiness(Context mContext) {

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReactRootView.startReactApplication(
                MainApplication.getReactInstanceManager(),
                getMainComponentName(),
                getPropeerties()
        );
    }

    private Bundle getPropeerties() {
        Bundle bundle = new Bundle();
        String uuid = SpMananger.getUUID();
        if (uuid == null)
            uuid = "";
        bundle.putString("uuid", uuid);
        bundle.putString("api_mode", AppConfig.isTest ? "1" : "0");
        return bundle;
    }

    private String getMainComponentName() {
        return "rnwithhow";
    }


}
