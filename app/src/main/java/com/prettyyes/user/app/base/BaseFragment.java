package com.prettyyes.user.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.ResourceUtils;
import com.prettyyes.user.model.user.UserInfo;
import com.squareup.leakcanary.RefWatcher;
import com.weavey.loading.lib.LoadingLayout;

import org.xutils.x;

import static com.prettyyes.user.app.service.InitializeService.getRefWatcher;

/**
 * Created by Administrator on 2016/7/2.
 * bindview的话就不用懒加载
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment {
    protected Activity mContext = null;
    public View mContextView = null;
    protected final String TAG = this.getClass().getSimpleName();
    public boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad = true;
    public String uuid_frg;
    public LoadingLayout loadingLayout;

    public Object mSubscription;

    public UserInfo getAccount() {
        return SpMananger.getUserInfo();
    }

    public String getUUId() {
        return BaseApplication.UUID;
    }

    public void getUUid(int type) {
        uuid_frg = getUUId();

        if (uuid_frg == null || uuid_frg.isEmpty()) {
            RegisterActivity.getRegister(getActivity());
            return;
        }
        doSomethingByUUid(type);

    }

    public void doSomethingByUUid(int type) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        initStatus();
        if (null == mContextView) {
            initParms(getArguments());
            AppRxBus.getInstance().register(this);

            View mView = bindView();
            if (null == mView) {
                isFirstLoad = true;
                mContextView = inflater.inflate(bindLayout(), container, false);
            } else {
                mContextView = mView;
            }
            x.view().inject(this, mContextView);//注解
            initLoadingView();
            injected = true;
            initView(mContextView);
            setListener();
            mContextView.setPadding(0, setFullScreenPadding(), 0, 0);

            isPrepared = true;
            doBusiness(getActivity());
            lazyLoad();

        }

        return mContextView;
    }

    public void initLoadingView() {
        try {
            loadingLayout = (LoadingLayout) mContextView.findViewById(R.id.loadingLayout);
            loadingLayout.setContentView(loadingLayout.getChildAt(0));
            loadingLayout.setStatus(LoadingLayout.Loading);
            loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    doBusiness(getContext());
                    lazyInitBusiness(getContext());
                }
            });
            needLoading(true);
        } catch (Exception ee) {
            LogUtil.i(TAG, "loadingLayout not in xml");
        }
    }


    public void setListener() {

    }

    private boolean injected = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    public int setFullScreenPadding() {
        return ResourceUtils.getStatusBarHeight(getActivity());
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;

        lazyInitBusiness(getActivity());
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContextView != null && mContextView.getParent() != null) {
            ((ViewGroup) mContextView.getParent()).removeView(mContextView);
        }

    }

    public void needLoading(boolean need) {
        if (need && loadingLayout != null)
            loadingLayout.setStatus(LoadingLayout.Loading);
    }

    public void loadFail() {
        if (loadingLayout != null)
            loadingLayout.setStatus(LoadingLayout.Error);

    }

    public void loadSuccess() {
        if (loadingLayout != null)
            loadingLayout.setStatus(LoadingLayout.Success);
    }

    public void loading() {
        if (loadingLayout != null)
            loadingLayout.setStatus(LoadingLayout.Loading);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = getRefWatcher(getActivity());
        refWatcher.watch(this);
        AppRxBus.getInstance().unregister(mSubscription);

    }


    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public void next(Class actclass) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).nextActivity(actclass);
    }


    public void next(Intent intent) {

        if (getActivity() != null)
            ((BaseActivity) getActivity()).nextActivity(intent);
    }

    public void showToastShort(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }



    public void sendBrodcast(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (getActivity() != null)
            getActivity().sendBroadcast(intent);
    }

    public void showSnake(View view, String tv) {
        Snackbar.make(view, tv, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnake(String tv) {
        Snackbar.make(mContextView, tv, Snackbar.LENGTH_SHORT).show();
    }


}
