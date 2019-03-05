package com.prettyyes.user.app.adapter.mainpage;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.other.AceGetList;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.mainpage
 * Author: SmileChen
 * Created on: 2016/12/22
 * Description: Nothing
 */
public class KolListRecycleAdapter extends AbsRecycleAdapter<AceGetList.ListEntity> {

    public KolListRecycleAdapter(Context context) {
        super(context, R.layout.item_recycle_kollist_new);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final AceGetList.ListEntity data, int position) {

        //item 的点击事件
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KolInfoActivity.goKolInfo(context, data.getSeller_id());
                JumpPageManager.getManager().goKolInfoActivity(context,data.getSeller_id()+"");
            }
        });


        tv_kollistLeftAdp_nickname.setText(data.getAce_name());
        tv_kollistAdp_desc.setText(data.getAce_txt());
        ImageLoadUtils.loadHeadImg(context, data.getHeadimg(), img_kollistAdp_headimg);
        BadgeView badgeView = new BadgeView(context);
        badgeView.initNopadding(30);
        badgeView.setTargetView(img_kollistAdp_headimg);
        badgeView.setImageTag(AppUtil.isFamous(data.getFamous_type()), 30);
        img_kollistAdp_sendque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
                sellerInfoEntity.setId(data.getSeller_id());
                sellerInfoEntity.setNickname(data.getAce_name());
                AskActivity.goAskActivity(context, sellerInfoEntity);
            }
        });

        for (int i = 0; i < tabLayout_kollistAdp.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(j);
                tv.setVisibility(View.GONE);
            }

        }
        for (int i = 0; i < data.getIngredient().size(); i++) {
            if (i <= 1) {
                TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(0);
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(i);
                tv.setText(data.getIngredient().get(i));
                tv.setVisibility(View.VISIBLE);

            } else if (i <= 3) {
                TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(1);
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(i - 2);
                tv.setText(data.getIngredient().get(i));
                tv.setVisibility(View.VISIBLE);
            }

        }

        tv_kollistAdp_honor.setText(data.getRecommend());
        tv_kollistAdp_zezenum.setText(StringUtils.changebugNumShow(data.getZeze_num()));
        tv_kollistAdp_peinum.setText(StringUtils.changebugNumShow(data.getPei_num()));
        String format = "<font color=\"#C93A48\" >%s</font>";
        int num = 0;
        if (StringUtils.strIsEmpty(data.getChar_count())) {
            num = 0;
        } else {
            num = Integer.parseInt(data.getChar_count());
        }
//        tv_kollistAdp_bingli.setText(Html.fromHtml("TA用" + String.format(format, data.getTemplate_count())
//                + "个物品治愈了"
//
//                + String.format(format, data.getAnswer_task_count())
//                + "个病例，总共回复"
//                + String.format(format, num) + "字，阅读需要"
//                + ) + "分钟"));

        tv_kollistAdp_num2.setText(Html.fromHtml("用" + String.format(format, data.getTemplate_count()) + "个物品"));
        tv_kollistAdp_num1.setText(Html.fromHtml("解决了" + String.format(format, data.getAnswer_task_count()) + "个疑惑"));
        tv_kollistAdp_num3.setText(Html.fromHtml("共" + String.format(format, StringUtils.changebugNumShow(num)) + "字，阅读需要" + String.format(format, data.getReading_min()) + "分钟"));


    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_kollistLeftAdp_nickname = holder.getView(R.id.tv_kollistAdp_name);
        img_kollistAdp_headimg = holder.getView(R.id.img_kollistAdp_headimg);
        img_kollistAdp_sendque = holder.getView(R.id.img_kollistAdp_sendque);
        tabLayout_kollistAdp = holder.getView(R.id.tabLayout_kollistAdp);
        tv_kollistAdp_desc = holder.getView(R.id.tv_kollistAdp_desc);
        tv_kollistAdp_zezenum = holder.getView(R.id.tv_kollistAdp_zezenum);
        tv_kollistAdp_peinum = holder.getView(R.id.tv_kollistAdp_peinum);
        tv_kollistAdp_honor = holder.getView(R.id.tv_kollistAdp_honor);
        tv_kollistAdp_num1 = holder.getView(R.id.tv_kollistAdp_num1);
        tv_kollistAdp_num2 = holder.getView(R.id.tv_kollistAdp_num2);
        tv_kollistAdp_num3 = holder.getView(R.id.tv_kollistAdp_num3);

    }


    private TextView tv_kollistLeftAdp_nickname;
    private RoundImageView img_kollistAdp_headimg;
    private ImageView img_kollistAdp_sendque;
    private TableLayout tabLayout_kollistAdp;
    private TextView tv_kollistAdp_desc;
    private TextView tv_kollistAdp_zezenum;
    private TextView tv_kollistAdp_peinum;
    private TextView tv_kollistAdp_honor;
    private TextView tv_kollistAdp_num1;
    private TextView tv_kollistAdp_num2;
    private TextView tv_kollistAdp_num3;

}
