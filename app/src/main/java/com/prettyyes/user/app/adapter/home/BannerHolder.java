package com.prettyyes.user.app.adapter.home;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.AdBlockReq;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.BannerItemEntity;
import com.prettyyes.user.model.v8.HomeTaskEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2018/1/19.
 * 广告
 */


public class BannerHolder extends MutiTypeViewHolder<HomeTaskEntity> {
    public BannerHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_home_banner);
    }

    @Override
    public void bindData(HomeTaskEntity modle, final int position, final RecyclerView.Adapter adpter) {

        view_banner_root.setVisibility(View.GONE);
        final BannerItemEntity ad = modle.getAd();
        if (ad == null)
            return;

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ad.getAd_content() != null) {
                   AppStatistics.onEvent(context,"adv","adv_id",ad.getId());
                    JumpPageManager.getManager().goWebActivity(context, ad.getAd_content().getH5_url());
                }

            }
        });
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelper.getInstance().getDialogNoCancel(R.string.task_filter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        absMutiRvAdapter.remove(position);
                        new AdBlockReq().setAd_id(ad.getId()).post(new NetReqCallback<Object>() {
                            @Override
                            public void apiRequestFail(String message, String method) {

                            }

                            @Override
                            public void apiRequestSuccess(Object o, String method) {

                            }
                        });

                    }
                }).show();


            }
        });
        BannerItemEntity.AdContentBean ad_content = null;
        tv_score.setText(100 + "");
        if (ad != null)
            ad_content = ad.getAd_content();

        if (ad_content != null) {
            tv_title.setText(ad_content.getNickname());
            tv_ace.setText(ad_content.getAce_txt());
            tv_banner_name.setText(ad_content.getNickname());
            tv_banner_que.setText(ad_content.getDesc());
            tv_sloggle.setText(ad_content.getSlogan());
            tv_content.setText(ad_content.getTs_reason());
            ImageLoadUtils.loadHeadImg(context, ad_content.getHeadimg(), img_banner_head);
            String advertisement_img = ad_content.getAdvertisement_img();
            final List<String> splitArray = StringUtils.getSplitArray(advertisement_img);
            if (splitArray.size() > 0) {
                view_banner_root.setVisibility(View.VISIBLE);
            }


            if (splitArray.size() > 3) {
                tv_imgs_num.setVisibility(View.VISIBLE);
                tv_imgs_num.setText(splitArray.size() + "+");
            }

            for (int i = 0; i < ll_reply_content_imgs.getChildCount(); i++) {
                ImageView childAt = (ImageView) ll_reply_content_imgs.getChildAt(i);

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();

                if (i == splitArray.size() - 1) {
                    layoutParams.setMargins(0, 0, 0, 0);
                } else {

                    if (i < ll_reply_content_imgs.getChildCount() - 1)
                        layoutParams.setMargins(0, 0, AppUtil.dip2px(8), 0);
                }
                childAt.setLayoutParams(layoutParams);
                if (i <= splitArray.size() - 1) {
                    final int j = i;
                    childAt.setVisibility(View.VISIBLE);
                    ImageLoadUtils.loadListimg(context, splitArray.get(i), childAt);
                    childAt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JumpPageManager.getManager().goBigImgActivity(context, (ArrayList<String>) splitArray, j);
                        }
                    });
                } else {
                    childAt.setVisibility(View.GONE);
                }

            }
        }


    }

    @Override
    public void bindView() {
        img_close = findViewById(R.id.img_close);
        img_invite = findViewById(R.id.img_invite);
        img_collect = findViewById(R.id.img_collect);
        view_question = findViewById(R.id.view_question);
        view_red_line = findViewById(R.id.view_red_line);
        tv_banner_name = findViewById(R.id.tv_banner_name);
        tv_banner_que = findViewById(R.id.tv_banner_que);
        tv_sloggle = findViewById(R.id.tv_sloggle);
        img_banner_head = findViewById(R.id.img_banner_head);
        tv_title = findViewById(R.id.tv_title);
        tv_ace = findViewById(R.id.tv_ace);
        tv_content = findViewById(R.id.tv_content);
        view_banner_root = findViewById(R.id.view_banner_root);

        tv_score = findViewById(R.id.tv_score);
        ll_reply_content_imgs = getView(R.id.ll_reply_content_imgs);
        ll_reply_content_imgs_root = getView(R.id.ll_reply_content_imgs_root);
        tv_imgs_num = getView(R.id.tv_imgs_num);

    }

    public ViewGroup ll_reply_content_imgs;
    public View ll_reply_content_imgs_root;
    private TextView tv_imgs_num;

    private ImageView img_close;
    private ImageView img_invite;
    private ImageView img_collect;
    private RelativeLayout view_question;
    private View view_red_line;
    private TextView tv_banner_name;
    private TextView tv_banner_que;
    private TextView tv_score;
    private TextView tv_sloggle;
    private ImageView img_banner_head;
    private TypefaceTextView tv_title;
    private TextView tv_ace;
    private TextView tv_content;
    private RelativeLayout view_banner_root;


}
