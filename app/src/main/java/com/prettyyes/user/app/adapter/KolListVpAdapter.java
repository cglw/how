package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.view.AutoViewPager;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.ClipViewPager;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.mainpage
 * Author: SmileChen
 * Created on: 2016/12/22
 * Description: Nothing
 */
public class KolListVpAdapter extends AbsVpAdapter<SellerInfoEntity> {


    private TypefaceTextView tv_kollistLeftAdp_nickname;
    private RoundImageView img_kollistAdp_headimg;
    private ImageView img_kollistAdp_sendque;
    private TableLayout tabLayout_kollistAdp;
    private TypefaceTextView tv_kollistAdp_desc;
    private int act_id = 0;
    private String act_name = "";
    private boolean canSendque = false;


    public void setCanSendque(boolean canSendque) {
        this.canSendque = canSendque;
        this.notifyDataSetChanged();
    }

    public void setCanSendque(boolean canSendque, AutoViewPager autoViewPager) {
        this.canSendque = canSendque;
        if (autoViewPager == null)
            return;
        ClipViewPager vp = autoViewPager.getVp();
        for (int i = 0; i < vp.getChildCount(); i++) {
            View childAt = vp.getChildAt(i);
            View viewById = childAt.findViewById(R.id.img_kollistAdp_sendque);
            if (viewById == null)
                return;
            if (canSendque) {
                viewById.setVisibility(View.VISIBLE);
            } else {
                viewById.setVisibility(View.GONE);
            }

        }
    }


    /**
     * @param act_id
     * @param act_name
     * @param canSendque
     */
    public void setActInfo(int act_id, String act_name, boolean canSendque) {
        this.act_id = act_id;
        this.act_name = act_name;
        this.canSendque = canSendque;
    }

    public KolListVpAdapter(Context context) {
        super(context, new ArrayList<SellerInfoEntity>(), R.layout.item_recycle_kollist);
    }

    @Override
    public void bindView(ViewHolder holder) {
        tv_kollistLeftAdp_nickname = (TypefaceTextView) holder.getView(R.id.tv_kollistAdp_name);
        img_kollistAdp_headimg = (RoundImageView) holder.getView(R.id.img_kollistAdp_headimg);
        img_kollistAdp_sendque = (ImageView) holder.getView(R.id.img_kollistAdp_sendque);
        tabLayout_kollistAdp = (TableLayout) holder.getView(R.id.tabLayout_kollistAdp);
        tv_kollistAdp_desc = (TypefaceTextView) holder.getView(R.id.tv_kollistAdp_desc);
    }

    @Override
    public void showData(ViewHolder vHolder, final SellerInfoEntity data, int position) {
        tv_kollistLeftAdp_nickname.setText(data.getNickname());
        tv_kollistAdp_desc.setText(data.getAce_txt());
        // tv_kollistAdp_desc.setText("");
        ImageLoadUtils.loadHeadImg(context, data.getHeadimg(), img_kollistAdp_headimg);
        BadgeView badgeView2 = new BadgeView(context);
        badgeView2.initNopadding(30);
        badgeView2.setTargetView(img_kollistAdp_headimg);
        badgeView2.setImageTag(data.isFamous(), 30);

        img_kollistAdp_sendque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionEntity questionEntity=new QuestionEntity();
                questionEntity.setAct_id(act_id+"");
                questionEntity.setHash_tag("");

                SellerInfoEntity sellerInfoEntity =new SellerInfoEntity();
                sellerInfoEntity.setId(Integer.parseInt(data.getSeller_id()));
                sellerInfoEntity.setNickname(data.getNickname());
                AskActivity.goAskActivity(context, questionEntity, sellerInfoEntity);
            }
        });
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                KolInfoActivity.goKolInfo(context, Integer.parseInt(data.getSeller_id()));
                JumpPageManager.getManager().goKolInfoActivity(context,data.getSeller_id());
            }
        });
        if (canSendque) {
            img_kollistAdp_sendque.setVisibility(View.VISIBLE);
        } else {
            img_kollistAdp_sendque.setVisibility(View.GONE);

        }


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
                tv.setTextSize(10);
                tv.setVisibility(View.VISIBLE);

            } else if (i <= 3) {
                TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(1);
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(i - 2);
                tv.setText(data.getIngredient().get(i));
                tv.setTextSize(10);
                tv.setVisibility(View.VISIBLE);
            }

        }
    }
    public interface ClickCallBack {
        public void click(View view, int position);
    }

}
