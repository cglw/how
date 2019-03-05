package com.prettyyes.user.app.adapter.invate;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.InvateKolRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Created by chengang on 2017/7/14.
 */

public class InvateSellerHolder extends MutiTypeViewHolder<InvateSellerModel> {


    public InvateSellerHolder(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_search_seller);
    }

    @Override
    public void bindData(final InvateSellerModel modle, int position, RecyclerView.Adapter adpter) {

        SellerInfoEntity seller = modle.getSeller();
        kolSimpleInfoView.setSellerInfo(seller);

        setCollectTv(seller.getIs_invite());
        btn_collect.setTag(modle);
        btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpMananger.getUUID() == null) {
                    JumpPageManager.getManager().goUnLoginActivity(context);
                    return;
                }

                InvateSellerModel tag = (InvateSellerModel) v.getTag();
                if (tag.getSeller().getIs_invite() == 0) {
                    tag.getSeller().setIs_invite(tag.getSeller().getIs_invite() == 1 ? 0 : 1);
                    setCollectTv(tag.getSeller().getIs_invite());
                    new InvateKolRequest(tag.getSeller().getSeller_id(), tag.getTask_id()).post(new NetReqCallback<Object>() {
                        @Override
                        public void apiRequestFail(String message, String method) {

                            ((BaseActivity) context).showSnack(message);
                        }

                        @Override
                        public void apiRequestSuccess(Object o, String method) {
                            ((BaseActivity) context).showSnack("邀请成功");
                        }
                    });
                }

            }
        });

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolInfoActivity(context, modle.getSeller().getSeller_id());
            }
        });


    }

    private void setCollectTv(int is_like) {
        if (is_like == 1) {
            btn_collect.setText("已邀请");
            btn_collect.setTextColor(ContextCompat.getColor(context, R.color.white));
            btn_collect.setBackgroundResource(R.drawable.select_btn_themered_radius2);
        } else {
            btn_collect.setText("+邀请");
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