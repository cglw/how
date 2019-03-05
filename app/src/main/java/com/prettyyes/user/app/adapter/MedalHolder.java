package com.prettyyes.user.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.MedalOrderRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.ReceiveCouponDialog;
import com.prettyyes.user.app.ui.appview.ReceiveOrderDialog;
import com.prettyyes.user.core.ShareHandler;
import com.prettyyes.user.core.event.ReceiveMedalSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.MedalEntity;

/**
 * Created by chengang on 2017/7/21.
 */

public class MedalHolder extends MutiTypeViewHolder<MedalEntity> {
    public MedalHolder(View itemView) {
        super(itemView);
    }

    public MedalHolder(Context context, ViewGroup parent, int layout_id) {
        super(context, parent, layout_id);
    }


    public MedalHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_medea);
    }

    public int getResource(String imageName) {
        Context ctx = context;
        int resId = context.getResources().getIdentifier(imageName, "mipmap", ctx.getPackageName());
        //如果没有在"mipmap"下找到imageName,将会返回0
        if (resId == 0)
            resId = R.mipmap.ic_launcher;
        return resId;
    }

    @Override
    public void bindData(final MedalEntity modle, int position, final RecyclerView.Adapter adpter) {
        this.tv_medal_goods.setText(modle.getGift_text());
        this.tv_medal_goods.setTag(modle);


        if (modle.getMedal_icon() == -1) {
            modle.setMedal_icon(getResource(String.format("ic_medal_%s", modle.getMedal_id())));
        }
        img_icon.setImageResource(modle.getMedal_icon());


        if ("2".equals(modle.getMedal_id())) {
            img_icon.setImageResource(R.mipmap.ic_launcher);
        } else {
            img_icon.setImageResource(getResource(String.format("ic_medal_%s", modle.getMedal_id())));
        }
//        tv_medal_goods.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MedalEntity tag = (MedalEntity) v.getTag();
//
//
//            }
//        });
        ImageLoadUtils.loadListimg(context, modle.getMedal_image(), img_covery);
        this.tv_title.setText(modle.getMedal_name());
        this.tv_content.setText(modle.getMedal_content());
        String format = String.format("%d/%d", modle.getCurrent_step(), modle.getAll_step());

        SpannableStringBuilder builder = new SpannableStringBuilder(format);

        ForegroundColorSpan redSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.theme_red));
        ForegroundColorSpan blackSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.black));
        builder.setSpan(redSpan, 0, (modle.getCurrent_step() + "").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(blackSpan, (modle.getCurrent_step() + "").length(), format.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.tv_step.setText(builder);


        String state = "";

        this.tv_state.setTextColor(ContextCompat.getColor(context, R.color.hintBlack));
        this.tv_state.setPadding(0, 0, 0, 0);

        if (modle.getGet_reward().equals("0")) {
            this.img_medal_complete.setVisibility(View.GONE);
            state = "未完成";
            if (modle.getMission_finished().equals("1")) {
                state = "立刻领取";
                this.tv_state.setTextColor(ContextCompat.getColor(context, R.color.white));
                this.tv_state.setBackgroundResource(R.drawable.select_btn_themered_radius9);
                this.tv_state.setPadding(AppUtil.dip2px(8), AppUtil.dip2px(2), AppUtil.dip2px(8), AppUtil.dip2px(2));
            }

        } else if (modle.getGet_reward().equals("1")) {
            state = "";
            this.img_medal_complete.setVisibility(View.VISIBLE);

        }
        this.tv_state.setText(state);
        this.tv_state.setClickable(false);

//        ((View) this.tv_state.getParent()).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView childAt = (TextView) ((LinearLayout) v).getChildAt(0);
//                if("立刻领取".equals(childAt.getText().toString()))
//                {
//
//                }
//
//
//            }
//        });
        if (modle.getAll_step() > 0)
            this.progress.setProgress((modle.getCurrent_step()) * 100 / modle.getAll_step());
        else
            this.progress.setProgress(0);
        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReceiveMedalSuccessEvent>() {
            @Override
            protected void back(ReceiveMedalSuccessEvent receiveMedalSuccessEvent) {
                if (absMutiRvAdapter == null)
                    return;
                for (int i = 0; i < absMutiRvAdapter.getItemCount(); i++) {
                    MedalEntity itemData = (MedalEntity) absMutiRvAdapter.getItemData(i);
                    if (itemData.getMedal_id().equals(receiveMedalSuccessEvent.medal_id)) {
                        itemData.setCurrent_step(itemData.getAll_step());
                        itemData.setGet_reward("1");
                        absMutiRvAdapter.notifyItemChanged(i);
                        break;
                    }
                }
            }
        });


        getRootView().setTag(modle);
        getRootView().setTag(R.id.id_view, tv_state);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedalEntity tag = (MedalEntity) v.getTag();
                if (tag.isHaveReceive())
                    return;
                if ("友遍天下".equals(tag.getMedal_name())) {
                    shareInvate(tag, v);
                } else
                    jumpReceive(tag);

            }
        });

    }

    public void jumpReceive(final MedalEntity modle) {
        if (modle.getMedal_type().equals("order")) {
//                        new ReceiveCouponDialog(context, modle.getCoupon_list()).show();
            if (modle.getSuit_list() != null && modle.getSuit_list().size() > 0)
                new ReceiveOrderDialog(context, modle.getSuit_list().get(0), modle.getMedal_id(), modle.isCanReceive()).show();

        } else if (modle.getMedal_type().equals("coupon")) {
//                        new ReceiveCouponDialog(context, modle.getCoupon_list()).show();
//
            if (modle.isCanReceive())
                new MedalOrderRequest().setMedal_id(modle.getMedal_id()).post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        getActivity().showSnack("领取成功");
                        AppRxBus.getInstance().post(new ReceiveMedalSuccessEvent(modle.getMedal_id()));

                        new ReceiveCouponDialog(context, modle.getCoupon_list()).show();
                    }
                });
            else
                new ReceiveCouponDialog(context, modle.getCoupon_list()).setShowBtn(false).show();


        }
    }

    public void shareInvate(MedalEntity modle, View v) {
        new ShareHandler((Activity) context).setRes(modle.getShare_model(), new ShareHandler.ShareCallback() {
            @Override
            public void share(boolean issuccess) {


            }
        }).shareAtWindow(v);

    }

    @Override
    public void bindView() {
        img_covery = getView(R.id.img_covery);
        tv_title = getView(R.id.tv_title);
        tv_content = getView(R.id.tv_content);
        tv_step = getView(R.id.tv_step);
        tv_state = getView(R.id.tv_state);
        progress = getView(R.id.progress);
        tv_medal_goods = getView(R.id.tv_medal_goods);
        img_medal_complete = getView(R.id.img_medal_complete);
        img_icon = getView(R.id.img_icon);
    }


    private ImageView img_covery;
    private ImageView img_icon;
    private ImageView img_medal_complete;
    private TextView tv_title;
    private TextView tv_medal_goods;
    private TextView tv_content;
    private TextView tv_step;
    private TextView tv_state;
    private ProgressBar progress;
}
