package com.prettyyes.user.app.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.view.indictator.CircleIndicator;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view
 * Author: SmileChen
 * Created on: 2016/12/15
 * Description: Nothing
 */
public class AutoViewPager extends AbsLinearlayoutView {

    private ClipViewPager viewPager;
    private AbsVpAdapter absVpAdapter;
    private int mImageIndex = 0;
    private boolean isStop;
    private int time = 3000;
    private boolean isneedIndict = true;
    private boolean isInner = true;
    private boolean needAutoScroll = true;

    public void setNeedAutoScroll(boolean needAutoScroll) {
        this.needAutoScroll = needAutoScroll;
    }

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_vp_autoscroll;
    }

    @Override
    public void initViews() {
        viewPager = (ClipViewPager) getView(R.id.vp_autoViewpager);
        viewPager.setIscanClickOther(false);

    }

    private boolean iscanClickOther = false;

    public boolean iscanClickOther() {
        return iscanClickOther;
    }

    public void setIscanClickOther(boolean iscanClickOther) {
        this.iscanClickOther = iscanClickOther;
        viewPager.setIscanClickOther(iscanClickOther);

    }

    public ClipViewPager getVp() {
        return viewPager;
    }

    public void setClip() {
        RelativeLayout a = (RelativeLayout) viewPager.getParent();
        viewPager.setClipChildren(false);
        a.setClipChildren(false);
    }

    public void setVpMargin(int margin, int pagemargin) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
        layoutParams.setMargins(margin, 0, margin, 0);
        viewPager.setPageMargin(pagemargin);
    }

    @Override
    public void setListener() {
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 开始图片滚动
                        startImageTimerTask();
                        break;
                    default:
                        // 停止图片滚动
                        stopImageTimerTask();
                        break;
                }
                return false;
            }


        });
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());

    }

    public void clearSelf() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mImageTimerTask);
        }
    }

    private Handler mHandler = new Handler();

    public void setAbsVpAdapter(AbsVpAdapter inputabsVpAdapter) {
        this.absVpAdapter = inputabsVpAdapter;
        if (absVpAdapter.getCount() > 0) {
            viewPager.setOffscreenPageLimit(absVpAdapter.getCount());
            if (absVpAdapter.getCount() > 1) {

                viewPager.setOffscreenPageLimit(absVpAdapter.getCount() + 2);
                this.absVpAdapter.add(0, absVpAdapter.getData().get(this.absVpAdapter.getData().size() - 1));
                this.absVpAdapter.add(absVpAdapter.getData().get(1));

                if (((RelativeLayout) viewPager.getParent()).getChildCount() > 1) {
                    for (int i = ((RelativeLayout) viewPager.getParent()).getChildCount() - 1; i >= 0; i--) {
                        if ((((RelativeLayout) viewPager.getParent()).getChildAt(i) instanceof CircleIndicator)) {
                            ((RelativeLayout) viewPager.getParent()).removeViewAt(i);
                        }
                    }
                }

                viewPager.setAdapter(absVpAdapter);
                viewPager.setCurrentItem(1);
                absVpAdapter.notifyDataSetChanged();
                mHandler.postDelayed(mImageTimerTask, time);


                //initIndicator((LinearLayout) viewPager.getParent(), viewPager);

            } else {

                viewPager.setAdapter(absVpAdapter);
                absVpAdapter.notifyDataSetChanged();
            }


        }

    }

    public boolean isInner() {
        return isInner;
    }

    public void setInner(boolean inner) {
        isInner = inner;
    }

    public void stop() {
        stopImageTimerTask();
    }

    public void start() {
        startImageTimerTask();
    }

    public void setIsneedIndict(boolean isneedIndict) {
        this.isneedIndict = isneedIndict;
    }

    private int currentItem = 1;
    /**
     * 图片自动轮播Task
     */
    private Runnable mImageTimerTask = new Runnable() {
        @Override
        public void run() {
            if (absVpAdapter.getCount() > 0&& needAutoScroll) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                if (!isStop && needAutoScroll) {  //if  isStop=true   //当你退出后 要把这个给停下来 不然 这个一直存在 就一直在后台循环
                    mHandler.postDelayed(mImageTimerTask, time);
                }

            }
        }
    };

    private void stopImageTimerTask() {
        if (!needAutoScroll)
            return;
        isStop = true;
        if (mHandler != null)
            mHandler.removeCallbacks(mImageTimerTask);
    }

    private void startImageTimerTask() {
        if (!needAutoScroll)
            return;
        if (viewPager.getChildCount() > 1) {
            stopImageTimerTask();
            // 图片滚动
            mHandler.postDelayed(mImageTimerTask, time);
        }
    }


    @Override
    public void setDataByModel(Object data) {

    }

    /**
     * 轮播图片监听
     *
     * @author minking
     */
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(absVpAdapter.getCount() - 2, false);
                } else if (viewPager.getCurrentItem() == absVpAdapter.getCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                }
                startImageTimerTask();
            }
        }

        @Override
        public void onPageScrolled(int index, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int index) {

        }
    }

    CircleIndicator circleIndicator;

    public void initIndicator() {

        RelativeLayout relativeLayout = (RelativeLayout) viewPager.getParent();
        if (isneedIndict && absVpAdapter.getCount() > 1) {
            circleIndicator = new CircleIndicator(getContext());
            circleIndicator.isMaxScroll = true;
            relativeLayout.addView(circleIndicator);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) circleIndicator.getLayoutParams();
            circleIndicator.setIndicatorMargin(DensityUtil.dip2px(7));
            circleIndicator.setIndicatorRadius(DensityUtil.dip2px(3));
            layoutParams.height = DensityUtil.dip2px(10);
            layoutParams.setMargins(0, 0, 0, DensityUtil.dip2px(3));
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            circleIndicator.setIndicatorSelectedBackground(ContextCompat.getColor(getContext(), R.color.theme_darkgreen));
            circleIndicator.setIndicatorBackground(ContextCompat.getColor(getContext(), R.color.grey_bg));
            circleIndicator.setIndicatorMode(CircleIndicator.Mode.SOLO);
            circleIndicator.setIndicatorLayoutGravity(CircleIndicator.Gravity.CENTER);
            circleIndicator.setViewPager(viewPager, absVpAdapter.getCount());
            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_in);
            circleIndicator.setAnimation(anim);
        }

    }

    public void initIndicatorBottom() {

        initIndicator();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getVp().getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, 0, layoutParams.rightMargin, DensityUtil.dip2px(8));
        layoutParams.addRule(RelativeLayout.ABOVE, circleIndicator.getId());


    }


}
