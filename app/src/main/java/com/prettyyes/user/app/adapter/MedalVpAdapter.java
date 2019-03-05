package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.event.ReceiveMedalSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.MedalEntity;

/**
 * Created by chengang on 2017/9/25.
 */

public class MedalVpAdapter extends AbsVpAdapter<MedalEntity> {

    private MedalHolder medalHolder;

    public MedalVpAdapter(Context context) {

        super(context, R.layout.item_rv_medea);

    }

    ViewPager viewPager;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    private final static int tag_index = 10 << 24;

    @Override
    public void bindView(ViewHolder vHolder) {
        medalHolder = new MedalHolder(vHolder.getRootView());

        medalHolder.bindView();



//        img_covery = vHolder.getView(R.id.img_covery);
//        tv_title = vHolder.getView(R.id.tv_title);
//        tv_content = vHolder.getView(R.id.tv_content);
//        tv_step = vHolder.getView(R.id.tv_step);
//        tv_state = vHolder.getView(R.id.tv_state);
//        progress = vHolder.getView(R.id.progress);
//        tv_medal_goods = vHolder.getView(R.id.tv_medal_goods);
//        img_medal_complete = vHolder.getView(R.id.img_medal_complete);
    }

    @Override
    public void showData(ViewHolder vHolder, final MedalEntity modle, int position) {

        medalHolder.bindData(modle,position,null);


        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReceiveMedalSuccessEvent>() {
            @Override
            protected void back(ReceiveMedalSuccessEvent receiveMedalSuccessEvent) {
                LogUtil.i(TAG, "ReceiveMedalSuccessEvent" + receiveMedalSuccessEvent.medal_id);
                for (int i = 0; i < MedalVpAdapter.this.getCount(); i++) {

                    MedalEntity itemData = (MedalEntity) MedalVpAdapter.this.get(i);
                    LogUtil.i(TAG, "ReceiveMedalSuccessEvent__list" + itemData.getMedal_id());

                    if (itemData.getMedal_id().equals(receiveMedalSuccessEvent.medal_id)) {


                        for (int j = 0; j < viewPager.getChildCount(); j++) {
                            View childAt = viewPager.getChildAt(j);
                            if (childAt.getTag(tag_index).equals(itemData.getMedal_id())) {
                                TextView tv_step = (TextView) childAt.findViewById(R.id.tv_step);
                                ImageView img_medal_complete = (ImageView) childAt.findViewById(R.id.img_medal_complete);
                                TextView tv_state = (TextView) childAt.findViewById(R.id.tv_state);
                               setSeptData(itemData, tv_state, tv_step, img_medal_complete);
                                break;
                            }
                        }

                    }
                }
            }

        });



    }

    private void setSeptData(MedalEntity modle, TextView tv_state, TextView tv_step, ImageView img_medal_complete) {
        String format = String.format("%d/%d", modle.getCurrent_step(), modle.getAll_step());
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.theme_red));
        ForegroundColorSpan blackSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.black));
        builder.setSpan(redSpan, 0, (modle.getCurrent_step() + "").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(blackSpan, (modle.getCurrent_step() + "").length(), format.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_step.setText(builder);
        String state = "";
        tv_state.setTextColor(ContextCompat.getColor(context, R.color.hintBlack));
        if (modle.getGet_reward().equals("0")) {
            img_medal_complete.setVisibility(View.GONE);
            state = "未完成";
            if (modle.getMission_finished().equals("1")) {
                state = "立刻领取";
                tv_state.setTextColor(ContextCompat.getColor(context, R.color.theme_red));
            }

        } else if (modle.getGet_reward().equals("1")) {
            state = "";
            img_medal_complete.setVisibility(View.VISIBLE);

        }
        tv_state.setText(state);
    }

}
