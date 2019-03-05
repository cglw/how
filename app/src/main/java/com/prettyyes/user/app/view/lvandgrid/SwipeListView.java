package com.prettyyes.user.app.view.lvandgrid;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.listview
 * Author: SmileChen
 * Created on: 2016/9/22
 * Description: Nothing
 */
public class SwipeListView extends LinearLayout {

    private boolean scrollFlag = false;// 标记是否滑动
    private boolean isLoadingComplete = true;//是否正在加载
    private boolean isLoadingEnd = false;//是否已加载完成
    private ListView lv;
    private VpSwipeRefreshLayout swiperefreshlayout;
    private View footView;
    private LinearLayout lin_loading;//加载中
    private LinearLayout lin_loadingEnd;//全部加载完
    private LinearLayout lin_loadingFail;//加载失败
    private int page = default_startpage;//默认开始page
    private static int default_startpage = 1;//默认page=1开始
    private static int DelayedTime = 500;//默认延迟时间
    private static final int TYPE_PAGE_BY_ID = 0x1;
    private static final int TYPE_PAGE_ADD = 0x2;
    private static int TYPE_PAGE_LOAD = TYPE_PAGE_BY_ID;
    private ScrollView scrollView_empty;
    private LinearLayout ll_empty;
    private Button btn_reload;//重新加载
    private int min_end_show = 10;

    private LoadCallback locallback;//加载回调
    private SpecilAbsAdapter adapter;//自己的封装的适配器，最好带有clear()方法清空数据

    public SwipeListView(Context context) {
        super(context);
        initView(context);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_swipelist, this);
        swiperefreshlayout = (VpSwipeRefreshLayout) view.findViewById(R.id.swipeRef_swipeList);
        lv = (ListView) view.findViewById(R.id.lv_swipeList);

        scrollView_empty = (ScrollView) view.findViewById(R.id.scrollView_emptyView);
        ll_empty = (LinearLayout) view.findViewById(R.id.ll_empty);

        btn_reload = (Button) view.findViewById(R.id.btn_reload);
        //添加footview
        footView = LayoutInflater.from(context).inflate(R.layout.item_footview_loading, null);
        lv.addFooterView(footView);
        //初始化swipe
        initSwipeColor(swiperefreshlayout);

        lin_loading = (LinearLayout) footView.findViewById(R.id.lin_footview_loading);
        lin_loadingEnd = (LinearLayout) footView.findViewById(R.id.lin_footview_end);
        lin_loadingFail = (LinearLayout) footView.findViewById(R.id.lin_footview_loadingfail);

        //设置旋转动画
        ImageView imageView = (ImageView) footView.findViewById(R.id.img_footview);
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.indicator_rotate);
        imageView.startAnimation(rotateAnim);
        //初始化隐藏footView（第一次进来的时候没有footview）
        footView.setVisibility(GONE);
        //设置监听（上拉下拉）
        setListener();
        swiperefreshlayout.setEnabled(false);
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

    /**
     * 设置上拉下拉监听
     */
    private void setListener() {
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    //  Log.d("Measure","listview.getListPaddingTop():"+listview.getListPaddingTop()+
                    //  " listview.getTop():"+listview.getTop()+"listview.getChildAt(0).getTop():"+listview.getChildAt(0).getTop());
                    if (lv.getFirstVisiblePosition() == 0 &&
                            lv.getChildAt(0).getTop() >= /*listview.getTop()+*/lv.getListPaddingTop()) {
                        swiperefreshlayout.setEnabled(true);
                        //  Log.d("TAG", "reach top!!!");
                    } else swiperefreshlayout.setEnabled(false);
                }
                return false;
            }
        });
        btn_reload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_reload.setVisibility(GONE);
                ll_empty.setVisibility(GONE);
                loadingFistEnter();
            }
        });
        lin_loadingFail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击重新加载  改变成正在加载状态
                setStateLoading();
                //延时加载(防止加载太快，刚设置的加载瞬间又变成加载失败，现在最快DelayedTime后才能改变状态)
                handler.sendEmptyMessageDelayed(1, DelayedTime * 2);
            }
        });

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setStateLoadingStart();
                //加载最新数据
                handler.sendEmptyMessageDelayed(2, DelayedTime);
            }
        });
        lvtoButtom(new Callback() {
            @Override
            public void isbuttomLoad() {
                if (!isLoadingEnd)//还没有到底部,可以一直拉，防止拉到底部多次请求
                {

                    //立马去加载
                    handler.sendEmptyMessage(1);
                    //300ms后去监测有没有加载成功
                    handler.sendEmptyMessageDelayed(3, 300);
                }

            }
        });

    }

    /**
     * listView滑动到底部回调
     *
     * @param callback
     */
    private void lvtoButtom(final Callback callback) {
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //判断滑动超过整个数据的一半的时候加载数据
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() >= view.getCount() - 1) {
                        callback.isbuttomLoad();
                    }
                }
                switch (scrollState) {
                    case SCROLL_STATE_TOUCH_SCROLL:
                        scrollFlag = true;
                        break;
                    case SCROLL_STATE_FLING:
                        scrollFlag = false;
                        break;
                    case SCROLL_STATE_IDLE:
                        scrollFlag = false;
                        break;
                    default:
                        break;
                }
                if (lvScrollCallback != null)
                    lvScrollCallback.lvOnScroll(view, scrollState);


                switch (scrollState) {
                    case SCROLL_STATE_IDLE: // The RecyclerView is not currently scrolling.
                        //当屏幕停止滚动，加载图片
                        try {
                            if (getContext() != null) Glide.with(getContext()).resumeRequests();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SCROLL_STATE_DRAGGING: // The RecyclerView is currently being dragged by outside input such as user touch input.
                        //当屏幕滚动且用户使用的触碰或手指还在屏幕上，停止加载图片
                        try {
                            if (getContext() != null) Glide.with(getContext()).pauseRequests();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SCROLL_STATE_SETTLING: // The RecyclerView is currently animating to a final position while not under outside control.
                        //由于用户的操作，屏幕产生惯性滑动，停止加载图片
                        try {
                            if (getContext() != null) Glide.with(getContext()).pauseRequests();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (firstVisibleItem + visibleItemCount == totalItemCount) {
//                    callback.isbuttomLoad();
//                }
                View childAt = lv.getChildAt(0);
                if (firstVisibleItem == 0 && childAt != null && childAt.getTop() == 0) {
                    swiperefreshlayout.setEnabled(true);
                } else {
                    swiperefreshlayout.setEnabled(false);
                }
                if (lvScrollCallback != null)
                    lvScrollCallback.lvScrollStateChanged(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        });


    }


    public void setLvScrollCallback(LvScrollCallback lvScrollCallback) {
        this.lvScrollCallback = lvScrollCallback;
    }

    private LvScrollCallback lvScrollCallback;

    public interface LvScrollCallback {
        public void lvOnScroll(AbsListView view, int scrollState);

        public void lvScrollStateChanged(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }

    /**
     * 加载成功数据为null
     */
    public void loadingEnd() {
        //page==default_startpage 需要清空数据
        if (page == default_startpage) {
            adapter.clear();
        }
        //加载状态完成
        isLoadingComplete = true;
        //数据全部加载完成
        isLoadingEnd = true;
        //关闭swipe的转动
        closeSwipelayoutRefresh(swiperefreshlayout);
        //加载到最后一页，数据为null
        setStateLoadingEnd();

        if (adapter.getCount() == 0) {
            setEmpty(ll_empty, btn_reload);
        }

    }

    /**
     * 加载成功数据不为null
     */
    public void loadingSuccessHavedata() {
        //page==default_startpage 需要清空数据
        if (page == default_startpage) {
            adapter.clear();
        }
        //加载状态完成
        isLoadingComplete = true;
        //数据全部加载完成状态
        isLoadingEnd = false;
        closeSwipelayoutRefresh(swiperefreshlayout);
        setStateLoadingSuccess();
        page++;//加载成功page自加
    }

    /**
     * 加载失败
     */
    public void loadingfail() {
        //加载失败
        isLoadingComplete = true;
        setStateLoadingFail();
        closeSwipelayoutRefresh(swiperefreshlayout);

        if (adapter.getCount() == 0) {
            btn_reload.setVisibility(VISIBLE);
            EmptyViewHandler.setEmptyDefault(EmptyViewHandler.EmptyType.FAIL, ll_empty, btn_reload);
        }
    }

    /**
     * 设置成正在加载中的状态
     */
    private void setStateLoading() {
        footView.setVisibility(VISIBLE);
        lin_loadingEnd.setVisibility(View.GONE);
        lin_loadingFail.setVisibility(View.GONE);
        lin_loading.setVisibility(VISIBLE);
    }

    /**
     * 设置初始状态
     */
    private void setStateLoadingStart() {
        footView.setVisibility(GONE);
    }

    /**
     * 加载成功状态，隐藏footview
     */
    private void setStateLoadingSuccess() {
        footView.setVisibility(GONE);//加载成功隐藏掉
    }

    /**
     * 设置成数据全部加载结束状态
     */
    private void setStateLoadingEnd() {
        footView.setVisibility(View.VISIBLE);
        if (adapter.getCount() > min_end_show)
            lin_loadingEnd.setVisibility(View.VISIBLE);
        lin_loadingFail.setVisibility(View.GONE);
        lin_loading.setVisibility(GONE);
    }

    /**
     * 设置成加载失败的状态
     */
    private void setStateLoadingFail() {
        footView.setVisibility(VISIBLE);
        lin_loadingEnd.setVisibility(View.GONE);
        lin_loading.setVisibility(View.GONE);
        lin_loadingFail.setVisibility(View.VISIBLE);
    }

    /**
     * 延时加载
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                loadPageData();
            } else if (msg.what == 2) {
                loadBestNewData();
            } else if (msg.what == 3) {
                if (!isLoadingComplete)//还没300ms上拉还未加载成功
                    setStateLoading();//设置成正在加载的状态
            }
        }
    };

    /**
     * 加载更多数据
     */
    public void loadPageData() {
        //可以加载的时候,还有是否到底
        if (isLoadingComplete && !isLoadingEnd) {
            isLoadingComplete = false;
            getList(page);

        }
    }

    /**
     * 加载最新数据 page==default_startpage
     */
    private void loadBestNewData() {
        if (isLoadingComplete) {
            page = default_startpage;
            isLoadingComplete = false;
            getList(page);

        }
    }

    /**
     * 第一次进入加载
     *
     * @param swipeRefreshLayout
     * @param
     */
    private void loadingFirstEnter(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
                handler.sendEmptyMessageDelayed(2, DelayedTime);

            }
        });
    }

    /**
     * 内部回调到底加载
     */
    private interface Callback {
        public void isbuttomLoad();//listview到底部加载数据
    }

    /**
     * 第一次进入加载 供外面调用
     */
    public void loadingFistEnter() {
        loadingFirstEnter(swiperefreshlayout);
    }

    /**
     * 关闭动画
     */
    public void closeAnim() {
        footView.findViewById(R.id.img_footview).clearAnimation();
    }

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

    public interface LoadCallback {
        public void loadList(int page);//加载更多
    }

    /**
     * 获得列表数据
     *
     * @param page
     */
    private void getList(int page) {

        if (this.locallback != null) {
            this.locallback.loadList(page);//内部调用getList
        }
    }


    /**
     * 外部调用，为了重写getList（）方法
     *
     * @param locallback
     */
    public void setListener(LoadCallback locallback) {
        this.locallback = locallback;
    }

    /**
     * 设置适配器（需要自己继承的适配器）
     *
     * @param adapter
     */
    public void setAdapter(SpecilAbsAdapter adapter) {
        lv.setAdapter(adapter);
        this.adapter = adapter;
        if (isneedEmptyView)
            lv.setEmptyView(scrollView_empty);

    }


    public boolean currentIsViaiable(int position) {
        if (position <= lv.getLastVisiblePosition() && position >= lv.getFirstVisiblePosition())
            return true;
        return false;
    }

    /**
     * 获取是否滚动
     *
     * @return
     */
    public boolean getIsScroll() {
        return scrollFlag;
    }

    /**
     * 对外的item点击
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        lv.setOnItemClickListener(onItemClickListener);
    }

    /**
     * 获得ListView 以便设置属性
     *
     * @return
     */
    public ListView getListView() {
        return lv;
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


    public void clearSelf() {
        if (handler != null) {
            handler.removeMessages(1);
            handler.removeMessages(2);
            handler.removeMessages(3);
            closeAnim();
        }
    }

    private EmptyListener empty_listener;

    public void setEmpty_listener(EmptyListener empty_listener) {
        this.empty_listener = empty_listener;
    }

    private void setEmpty(LinearLayout ll_empty, Button btn_reload) {
        EmptyViewHandler.setEmptyDefault(EmptyViewHandler.EmptyType.EMPTY, ll_empty, btn_reload);
        if (empty_listener != null)
            empty_listener.setEmpty(ll_empty, btn_reload);
    }


    boolean isneedEmptyView = true;

    public void setIsneedEmptyView(boolean isneedEmptyView) {
        this.isneedEmptyView = isneedEmptyView;
    }
}
