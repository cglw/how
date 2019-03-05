package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/2/27.
 *
 */

public class LiveSellerInfoAdapter extends AbsVpAdapter<SellerInfoEntity> {

    public LiveSellerInfoAdapter(Context context) {
        super(context, new ArrayList<SellerInfoEntity>(), R.layout.item_lv_seller_live);
    }

    private int act_id = 0;
    private String act_name = "";
    private boolean canSendque = false;

    /**
     * @param act_id
     * @param act_name
     */
    public void setActInfo(int act_id, String act_name, boolean canSendque) {
        this.act_id = act_id;
        this.act_name = act_name;
        this.canSendque = canSendque;
    }

    @Override
    public void bindView(ViewHolder vHolder) {

        img_head = vHolder.getView(R.id.img_head);
        tv_nickname = vHolder.getView(R.id.tv_nickname);
        tv_ace = vHolder.getView(R.id.tv_ace);
        btn_ask = vHolder.getView(R.id.btn_ask);
        img_left = vHolder.getView(R.id.img_left);
        view_kol = vHolder.getView(R.id.view_kol);

    }

    @Override
    public void showData(ViewHolder vHolder, final SellerInfoEntity data, int position) {
        ImageLoadUtils.loadHeadImg(context, data.getHeadimg(), img_head);
//        if (LiveSellerInfoAdapter.this.getData().size() == 1) {
//            img_left.setVisibility(View.GONE);
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view_kol.getLayoutParams();
//            layoutParams.addRule(CENTER_IN_PARENT);
//            btn_ask.setVisibility(View.GONE);
//        }

        tv_nickname.setText(data.getNickname());
        tv_ace.setText(data.getAce_txt());
        if (canSendque)
            btn_ask.setBackgroundResource(R.drawable.bg_round_pink_live_2);
        else
            btn_ask.setBackgroundResource(R.drawable.bg_round_translate_2);


        btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (act_id == 0) {
                    AppUtil.showToastShort("未获取到活动信息");
                    return;
                }
                if (!canSendque) {
                    AppUtil.showToastShort("不在活动时段");

                    return;
                }
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setAct_id(act_id + "");
                questionEntity.setHash_tag("");

                SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
                sellerInfoEntity.setId(Integer.parseInt(data.getSeller_id()));
                sellerInfoEntity.setNickname(data.getNickname());
                AskActivity.goAskActivity(context, questionEntity, sellerInfoEntity);

            }
        });
        vHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goKolInfoActivity(context, data.getSeller_id());
            }
        });

    }

    private ImageView img_head;
    private TypefaceTextView tv_nickname;
    private Button btn_ask;
    public TextView tv_ace;
    public View view_kol;
    public View img_left;

}
