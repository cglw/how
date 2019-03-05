package com.prettyyes.user.app.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.ReceiveHowScoreGoodsDialog;
import com.prettyyes.user.core.event.ReceiveHowScoreGoodsSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.HowScoreGoods;

/**
 * Created by chengang on 2018/1/31.
 */

public class HowScoreGoodsHolder extends MutiTypeViewHolder<HowScoreGoods> {

    private int width = 0;

    public static float how_apply_score;

    public HowScoreGoodsHolder(ViewGroup parent) {
        super(parent, R.layout.item_rv_how_score_goods);
        width = (BaseApplication.ScreenWidth - AppUtil.dip2px(10)) / 2;
        how_apply_score = 0;
    }

    @Override
    public void bindData(final HowScoreGoods modle, int position, final RecyclerView.Adapter adpter) {
        ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
        layoutParams.height = width;
        cardView.setLayoutParams(layoutParams);
        ImageLoadUtils.loadListimg(context, modle.getMain_img(), img_covery);
        tv_name.setText(modle.getSpu_name());

        if (modle.isHaveReceive()) {
            tv_score.setText("已兑换");

        } else {
            tv_score.setText(modle.getHow_score());

        }
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ClickUtils.isFastDoubleClick())
                    return;
                float how_score = 0;
                try {
                    how_score = Float.parseFloat(modle.getHow_score());
                } catch (Exception ee) {

                }
                if(how_score>how_apply_score)
                {
                    AppUtil.showToastShort("兑换积分不足");
                    return;
                }
                if (modle.canReceive())
                    new ReceiveHowScoreGoodsDialog(context, modle).show();


            }
        });

        ((BaseActivity) context).mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ReceiveHowScoreGoodsSuccessEvent>() {
            @Override
            protected void back(ReceiveHowScoreGoodsSuccessEvent obj) {

                for (int i = 0; i < adpter.getItemCount(); i++) {
                    HowScoreGoods itemData = (HowScoreGoods) ((AbsRecycleAdapter) adpter).getItemData(i);
                    if (itemData.getId() != null && obj.id != null && itemData.getId().equals(obj.id)) {
                        itemData.setIs_receive("1");
                        ((AbsRecycleAdapter) adpter).notifyItemChanged(i);
                        break;
                    }
                }
            }
        });


    }

    @Override
    public void bindView() {
        cardView = getView(R.id.cardView);
        tv_name = getView(R.id.tv_name);
        tv_score = getView(R.id.tv_score);
        img_covery = getView(R.id.img_covery);

    }

    private TextView tv_name;
    private TextView tv_score;
    private ImageView img_covery;
    private CardView cardView;
}
