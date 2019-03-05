package com.prettyyes.user.app.adapter.detail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.mvpPresenter.SuitDelPresenter;
import com.prettyyes.user.app.view.PupopAmountView;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/27
 * Description: Nothing
 */
public class PupopSelectAdapter extends SpecilAbsAdapter<SuitDelPresenter.SelectModel> {

  private ClickCallback clickCallback;
    public PupopSelectAdapter(ArrayList datas, ClickCallback clickCallback, Context context) {
        super(R.layout.item_lv_pupopselectnum, datas,context);
        this.clickCallback=clickCallback;
    }

    @Override
    public void showData(ViewHolder vHolder, final SuitDelPresenter.SelectModel data, final int position) {
        mPAmountview.setGoods_Num(data.getNum());
        ImageLoadUtils.loadListimg(context,data.getUint_img(), mImg);
        mTv_price.setText(Constant.RMB+data.getPrice() + "");
        mTv_uintname.setText(data.getTitle());
        mPAmountview.setOnAmountChangeListener(new PupopAmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                data.setNum(amount);
                if(clickCallback!=null)
                {
                    clickCallback.backprice();
                }
            }
        });
        mTv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PupopSelectAdapter.this.remove(position);
                PupopSelectAdapter.this.notifyDataSetChanged();
                if(clickCallback!=null)
                {
                    clickCallback.backprice();
                }
            }
        });

    }

    private ImageView mImg;
    private PupopAmountView mPAmountview;
    private TextView mTv_uintname;
    private TextView mTv_price;
    private TextView mTv_remove;

    @Override
    public void bindView(ViewHolder vHolder) {
        mImg = (ImageView) vHolder.getView(R.id.img_puponSelectNum_uintimg);
        mPAmountview = (PupopAmountView) vHolder.getView(R.id.pAmountview_wishlistInnerLvAdp);
        mTv_uintname = (TextView) vHolder.getView(R.id.tv_puponSelectNum_uintname);
        mTv_price = (TextView) vHolder.getView(R.id.tv_wishlistInnerLvAdp_price);
        mTv_remove = (TextView) vHolder.getView(R.id.tv_wishlistInnerLvAdp_remove);
    }



    public interface ClickCallback
    {
        public void backprice();
    }


}
