package com.prettyyes.user.app.adapter.holder_presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AnimotionUtils;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.common.ActInfo;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.List;

/**
 * Created by chengang on 2017/9/20.
 */

public class KolActEndHoldeViewImpl extends BaseHolderViewImpl<ActInfo> {

    private RecyclerView recyclerView;

    public KolActEndHoldeViewImpl setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public KolActEndHoldeViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);
        bindview();


    }

    @Override
    public void setData(final ActInfo data) {
        super.setData(data);
        view_shrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view_top_act.getVisibility() == View.VISIBLE) {
                    AnimotionUtils.animateClose(view_top_act, 300);
                    AnimotionUtils.animRotate180(img_shrink);
                } else {
                    AnimotionUtils.animateOpen(view_top_act, AppUtil.dip2px(176), 300);
                    AnimotionUtils.animRotate180_back(img_shrink);
                }


            }
        });

        view_top_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolActActivity(context, data.getAct_id());
            }
        });

        tv_act_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolActActivity(context, data.getAct_id());
            }
        });
        if (Long.parseLong(data.getEnd_time()) > Long.parseLong(data.getServer_time())) {
            ll_act_end_top.setVisibility(View.GONE);

        } else {
            ll_act_end_top.setVisibility(View.VISIBLE);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (dy - last_dy > 0) {
                        if (view_top_act.getVisibility() == View.VISIBLE) {
                            AnimotionUtils.animateClose(view_top_act, 300);
                            AnimotionUtils.animRotate180(img_shrink);
                        }
                    }
                }
            });


        }
        tv_act_desc.setText(data.getAct_content());
        List<SellerInfoEntity> seller_list = data.getSeller_list();
        if (seller_list.size() == 0) {
            ll_kols.setVisibility(View.GONE);
        } else if (seller_list.size() == 1) {
            ll_kols.getChildAt(1).setVisibility(View.GONE);
        }
        for (int i = 0; i < seller_list.size(); i++) {
            if (i == 2)
                return;
            KolSimpleInfoView childAt = (KolSimpleInfoView) ll_kols.getChildAt(i);
            childAt.img_grade_title.setVisibility(View.GONE);
            childAt.setSellerInfo(seller_list.get(i));
        }


    }

    private int last_dy;

    private void bindview() {
        view_shrink = getView(R.id.view_shrink);
        img_shrink = getView(R.id.img_shrink);
        view_top_act = getView(R.id.view_top_act);
        ll_act_end_top = getView(R.id.ll_act_end_top);
        tv_act_desc = getView(R.id.tv_act_desc);
        ll_kols = getView(R.id.ll_kols);
    }

    private View view_shrink;
    private View img_shrink;
    private View view_top_act;
    private View ll_act_end_top;
    private ExpandableTextView tv_act_desc;
    private LinearLayout ll_kols;
}
