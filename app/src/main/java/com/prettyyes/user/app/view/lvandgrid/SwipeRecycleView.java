package com.prettyyes.user.app.view.lvandgrid;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.view.recycle.EmptyRecyclerView;
import com.prettyyes.user.app.view.recycle.EndlessRecyclerOnScrollListener;
import com.prettyyes.user.app.view.recycle.HeaderAndFooterRecyclerViewAdapter;
import com.prettyyes.user.app.view.recycle.LoadingFooter;
import com.prettyyes.user.app.view.recycle.RecyclerViewStateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.lvandgrid
 * Author: SmileChen
 * Created on: 2016/12/22
 * Description: app 使用的下拉列表
 */
public class SwipeRecycleView extends VpSwipeRefreshLayout {
    private static final String TAG = "SwipeRecycleView";
    private NestedScrollView scrollView_empty;
    private Button btn_reload;//重新加载

    private EmptyRecyclerView recyclerView;
    private VpSwipeRefreshLayout swiperefreshlayout;
    private int page = default_startpage;//默认开始page
    private static int default_startpage = 1;//默认page=1开始
    private int DelayedTime = 100;//默认延迟时间
    private boolean isLoadingComplete = true;//是否正在加载
    private boolean isLoadingEnd = false;//是否已加载完成
    private int pageSize = 20;
    private AbsRecycleAdapter absRecycleAdapter;
    private LinearLayout ll_empty;
    private int min_end_show = 5;
    private long timeout = 5 * 1000;//swiperefresh 超时
    private int msg_timeout = 4;
    private int msg_load_dely_next_page = 3;
    private int msg_load_best_new = 2;
    private int msg_load_next_page = 1;
    private Activity activity;
    private FrameLayout framelayout;

    public SwipeRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        initViews(context);
    }


    public void setMin_end_show(int min_end_show) {
        this.min_end_show = min_end_show;
    }


    public void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_swiperecycle, null);
        this.addView(view);
        swiperefreshlayout = this;
        recyclerView = (EmptyRecyclerView) view.findViewById(R.id.lv_swiperecycle);
        framelayout = (FrameLayout) view.findViewById(R.id.framelayout);
        scrollView_empty = (NestedScrollView) view.findViewById(R.id.netscrollView_emptyView);
        ll_empty = (LinearLayout) view.findViewById(R.id.ll_empty);
        btn_reload = (Button) view.findViewById(R.id.btn_reload);
        initSwipeColor(swiperefreshlayout);
        setListener();
    }

    public void needWrapHeight() {
        if (framelayout != null)
            framelayout.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    /**
     * 初始化swipe
     *
     * @param swipeRefreshLayout
     */
    public void initSwipeColor(SwipeRefreshLayout swipeRefreshLayout) {
        //设置卷内的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

    }


    public void setListener() {
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_reload.setVisibility(GONE);
                ll_empty.setVisibility(GONE);
                loadingFistEnter();
            }
        });
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(msg_timeout, timeout);
                //发送超时

                //加载最新数据
                handler.sendEmptyMessageDelayed(msg_load_best_new, DelayedTime);
            }
        });
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(getContext()) {
            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                if (!isLoadingEnd && locallback != null)//还没有到底部,可以一直拉，防止拉到底部多次请求
                {
                    if (RecyclerViewStateUtils.getFooterViewState(recyclerView) == LoadingFooter.State.NetWorkError) {
                        handler.sendEmptyMessageDelayed(msg_load_next_page, DelayedTime + 1000);
                    } else {
                        //立马去加载
                        handler.sendEmptyMessage(msg_load_next_page);
                    }
                    //300ms后去监测有没有加载成功
                    //  handler.sendEmptyMessageDelayed(3, 300);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (rvScrollCallback != null)
                    rvScrollCallback.rvonScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (rvScrollCallback != null)
                    rvScrollCallback.rvScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void setRvScrollCallback(RvScrollCallback rvScrollCallback) {
        this.rvScrollCallback = rvScrollCallback;
    }

    private RvScrollCallback rvScrollCallback;

    public interface RvScrollCallback {
        public void rvonScrolled(RecyclerView recyclerView, int dx, int dy);

        public void rvScrollStateChanged(RecyclerView recyclerView, int newState);
    }

    /**
     * 第一次进入加载 供外面调用
     */
    public void loadingFistEnter() {
        if (recyclerView != null)
            recyclerView.scrollToPosition(0);
        loadingFirstEnter();
    }

    /**
     * 第一次进入加载
     *
     * @param
     * @param
     */
    private void loadingFirstEnter() {


        swiperefreshlayout.post(new Runnable() {
            @Override
            public void run() {
                swiperefreshlayout.setRefreshing(true);
                handler.sendEmptyMessageDelayed(msg_timeout, timeout);

                handler.sendEmptyMessageDelayed(msg_load_best_new, DelayedTime);

            }
        });
    }

    /**
     * 延时加载
     */
    private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == msg_load_next_page) {
                loadPageData();
                setStateLoading();//设置成正在加载的状态
            } else if (msg.what == msg_load_best_new) {
                if (localltopback != null)
                    if (page == default_startpage)
                        loadBestNewData();
                    else
                        loadPageData();
                else
                    loadBestNewData();

            } else if (msg.what == msg_load_dely_next_page) {
                if (!isLoadingComplete)//还没300ms上拉还未加载成功
                    setStateLoading();//设置成正在加载的状态
            } else if (msg.what == msg_timeout) {
                isLoadingComplete = true;
                closeSwipelayoutRefresh(swiperefreshlayout);

            }
        }
    };

    private void setStateLoading() {
        if (footviewShow && showLoadPageIsMoreFootView())
            RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.Loading, null);
    }

    public void notifyDataSetChanged() {
        headerAndFooterRecyclerViewAdapter.notifyDataSetChanged();
        if (footviewShow)
            RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Normal);

    }

    /**
     * 加载成功数据为null
     */
    public void loadingEnd() {
        //page==default_startpage 需要清空数据
        if (page == default_startpage) {
            absRecycleAdapter.clear();

        }

        //加载状态完成
        isLoadingComplete = true;
        //数据全部加载完成
        isLoadingEnd = true;
        //关闭swipe的转动
        closeSwipelayoutRefresh(swiperefreshlayout);
        //加载到最后一页，数据为null

        if (absRecycleAdapter.getDataCount() == 0) {
            setEmpty(ll_empty, btn_reload);
        } else {
            if (footviewShow && showLoadPageIsMoreFootView())
                RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.TheEnd, null);
        }
    }

    private boolean showLoadPageIsMoreFootView() {
        return absRecycleAdapter.getItemCount() > min_end_show;
    }


    private void setEmpty(LinearLayout ll_empty, Button btn_reload) {
        EmptyViewHandler.setEmptyDefault(EmptyViewHandler.EmptyType.EMPTY, ll_empty, btn_reload);
        if (empty_listener != null)
            empty_listener.setEmpty(ll_empty, btn_reload);
    }


    /**
     * 加载成功数据为null
     */
    public void loadingEndWithout() {

        //加载状态完成
        isLoadingComplete = true;
        //数据全部加载完成
        isLoadingEnd = true;
        //关闭swipe的转动
        closeSwipelayoutRefresh(swiperefreshlayout);
        //加载到最后一页，数据为null

        if (absRecycleAdapter.getDataCount() == 0) {
            setEmpty(ll_empty, btn_reload);

        } else {
            if (footviewShow && showLoadPageIsMoreFootView())
                RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.TheEnd, null);

        }


    }

    /**
     * 加载成功数据不为null
     */
    public void loadingSuccessHavedata() {
        //page==default_startpage 需要清空数据
        if (page == default_startpage) {
            absRecycleAdapter.clear();

        }
        //加载状态完成
        isLoadingComplete = true;
        //数据全部加载完成状态
        isLoadingEnd = false;
        closeSwipelayoutRefresh(swiperefreshlayout);

        if (footviewShow && showLoadPageIsMoreFootView()) {
            RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.Normal, null);

        }
        page++;//加载成功page自加
    }


    /**
     * 加载失败
     */
    public void loadingfail() {
        //加载失败
        isLoadingComplete = true;
        closeSwipelayoutRefresh(swiperefreshlayout);
        if (absRecycleAdapter.getDataCount() == 0) {
            EmptyViewHandler.setEmptyDefault(EmptyViewHandler.EmptyType.FAIL, ll_empty, btn_reload);
        } else {
            if (footviewShow && showLoadPageIsMoreFootView())
                RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.NetWorkError, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击重新加载  改变成正在加载状态
                        setStateLoading();
                        //延时加载(防止加载太快，刚设置的加载瞬间又变成加载失败，现在最快DelayedTime后才能改变状态)
                        handler.sendEmptyMessageDelayed(msg_load_next_page, DelayedTime + 1000);
                    }
                });
        }
    }


    private void loadBestNewData() {

        if (isLoadingComplete) {
            page = default_startpage;
            isLoadingComplete = false;
            getList(page);
            gettopList(page);

        }

    }

    public void loadPageData() {
        //可以加载的时候,还有是否到底
        if (isLoadingComplete && !isLoadingEnd) {
            isLoadingComplete = false;
            getList(page);
            gettopList(page);


        }
    }

    private void getList(int page) {
        if (this.locallback != null) {
            this.locallback.loadList(page);//内部调用getList
        }
    }

    private void gettopList(int page) {
        if (this.localltopback != null) {
            this.localltopback.loadtopList(page);//内部调用getList
        }
    }

    private LoadCallback locallback;//加载回调
    private LoadTopCallback localltopback;//加载回调

    public interface LoadCallback {
        public void loadList(int page);//加载更多
    }

    public interface LoadTopCallback {
        public void loadtopList(int page);//加载更多
    }

    /**
     * 外部调用，为了重写getList（）方法
     *
     * @param locallback
     */
    public void setListener(LoadCallback locallback) {
        this.locallback = locallback;
    }

    public void setListener(LoadTopCallback localltopback) {
        this.localltopback = localltopback;
    }

    /**
     * 设置适配器（需要自己继承的适配器）
     *
     * @param adapter
     */
    public void setAdapter(AbsRecycleAdapter adapter) {
        this.headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
        this.absRecycleAdapter = adapter;
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setEmptyView(scrollView_empty, adapter);
    }

  public void setAdapterNoHead(AbsRecycleAdapter adapter) {
        recyclerView.setAdapter(adapter);
        this.absRecycleAdapter = adapter;
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setEmptyView(scrollView_empty, adapter);
    }


    /**
     * 设置适配器（需要自己继承的适配器）
     *
     * @param adapter
     */
    public void setAdapter(AbsRecycleAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        this.headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
        this.absRecycleAdapter = adapter;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setEmptyView(scrollView_empty, adapter);


    }


    public void setPageSize(int size) {
        pageSize = size;
    }

    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;

    /**
     * 关闭swpie的转动
     *
     * @param swipeRefreshLayout
     */
    public void closeSwipelayoutRefresh(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 获得ListView 以便设置属性
     *
     * @return
     */
    public RecyclerView getRecycleView() {
        return recyclerView;
    }

    public void setDelyTime(int time) {
        this.DelayedTime = time;
    }

    public void setDefautStartPage(int page) {
        default_startpage = page;
        this.page = default_startpage;
    }


    public void setEmptyClick(OnClickListener onClickListener) {
        scrollView_empty.getChildAt(0).setOnClickListener(onClickListener);
    }

    public void setRefreshNot(boolean is) {
        swiperefreshlayout.setEnabled(is);
    }

    public SwipeRefreshLayout getSwpie() {
        return swiperefreshlayout;
    }

    public void setScrollColor() {
        try {
            Field f = recyclerView.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("mScrollCache");
            f.setAccessible(true);
            Object scrollabilityCache = f.get(recyclerView);
            f = f.getType().getDeclaredField("scrollBar");
            f.setAccessible(true);
            Object scrollBarDrawable = f.get(scrollabilityCache);
            Method m1 = scrollBarDrawable.getClass().getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.yoursdrawablefile);
            m1.invoke(scrollBarDrawable, drawable);
        } catch (Exception ee) {

        }

    }


    private boolean footviewShow = true;

    public boolean isFootviewShow() {
        return footviewShow;
    }

    public void setFootviewShow(boolean footviewShow) {
        this.footviewShow = footviewShow;
    }


    public void clearSelf() {
        RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.TheEnd, null);
        if (handler != null) {
            handler.removeMessages(msg_load_next_page);
            handler.removeMessages(msg_load_best_new);
            handler.removeMessages(msg_load_dely_next_page);
            handler.removeMessages(msg_timeout);
        }
    }

    public void setLoadingEnd(boolean loadingEnd) {
        isLoadingEnd = loadingEnd;
    }

    private EmptyListener empty_listener;

    public void setEmpty_listener(EmptyListener empty_listener) {
        this.empty_listener = empty_listener;
    }

    public HeaderAndFooterRecyclerViewAdapter getHeaderAndFooterRecyclerViewAdapter() {
        return this.headerAndFooterRecyclerViewAdapter;
    }
}
