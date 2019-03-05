package com.prettyyes.user.app.adapter.searchholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchSellerHolder extends MutiTypeViewHolder<SearchSellerModel> {


    public SearchSellerHolder(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_search_seller);
    }

    @Override
    public void bindData(final SearchSellerModel modle, int position, RecyclerView.Adapter adpter) {

        kolSimpleInfoView.setSellerInfo(modle.getSeller());
        SpannableString ace_txt = StringUtils.matcherSearchTitle(modle.getSeller().getAce_txt(), modle.getKey_word());
        SpannableString nickname = StringUtils.matcherSearchTitle(modle.getSeller().getNickname(), modle.getKey_word());
        kolSimpleInfoView.getTv_nickname().setText(nickname);
        kolSimpleInfoView.getTv_info().setText(ace_txt);
        setCollectTv(modle.getSeller().getIs_like());
        btn_collect.setTag(modle.getSeller());
        btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtils.isFastDoubleClick())
                    return;
                if (SpMananger.getUUID() == null) {
                    JumpPageManager.getManager().goUnLoginActivity(context);
                    return;
                }
                SellerInfoEntity tag = (SellerInfoEntity) v.getTag();
                tag.setIs_like(tag.getIs_like() == 1 ? 0 : 1);
                setCollectTv(tag.getIs_like());
                new OtherApiImpl().AceLike(SpMananger.getUUID(), Integer.parseInt(tag.getSeller_id()), tag.getIs_like(), new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        ((BaseActivity) context).showSnack(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {

                    }
                });
            }
        });

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(getActivity(),"search_kol;"+modle.getSeller().getSeller_id());

                JumpPageManager.getManager().goKolInfoActivity(context, modle.getSeller().getSeller_id());
            }
        });


    }

    private void setCollectTv(int is_like) {
        if (is_like == 1) {
            btn_collect.setText("已收藏");
            btn_collect.setTextColor(ContextCompat.getColor(context, R.color.white));
            btn_collect.setBackgroundResource(R.drawable.select_btn_themered_radius2);
        } else {
            btn_collect.setText("+收藏");
            btn_collect.setTextColor(ContextCompat.getColor(context, R.color.theme_red));
            btn_collect.setBackgroundResource(R.drawable.selector_oval_red);
        }
    }

    @Override
    public void bindView() {
        kolSimpleInfoView = getView(R.id.kolSimpleInfoView);
        btn_collect = getView(R.id.btn_collect);
    }

    private KolSimpleInfoView kolSimpleInfoView;
    private Button btn_collect;
}