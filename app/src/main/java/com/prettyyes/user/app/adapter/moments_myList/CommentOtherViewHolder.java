package com.prettyyes.user.app.adapter.moments_myList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.app.ui.appview.KolSmallView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.MineDynamicEntity;
import com.prettyyes.user.model.v8.SellerInfoEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public class CommentOtherViewHolder extends MineCommonViewHolder {
    public CommentOtherViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(final MineDynamicEntity modle, int position, RecyclerView.Adapter adpter) {
        super.bindData(modle, position, adpter);
        SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
        sellerInfoEntity.setAce_txt(modle.getAce_txt());
        sellerInfoEntity.setHeadimg(modle.getHeadimg());
        sellerInfoEntity.setFamous_type(modle.getFamous_type());
        sellerInfoEntity.setNickname(modle.getNickname());
        sellerInfoEntity.setSeller_id(modle.getSeller_id());
        sellerInfoEntity.grade_title = modle.getGrade_title();

        kol_samllview.setSellerInfo(sellerInfoEntity);
        getRootView().setTag(position);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int index = (int) v.getTag();
                DataCenter.clearCommentUnread(modle.getComment_id(), new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {

                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        DataCenter.getUnread(DataCenter.UnreadType.COMMENT);
                        MineDynamicEntity itemData = (MineDynamicEntity) getAbsMutiRvAdapter().getItemData(index);
                        itemData.setComment_status("1");
                        absMutiRvAdapter.notifyItemChanged(index);
                    }
                });
                clickItem(modle);
            }
        });

        setCommentInfo(modle);

    }

    public void clickItem(MineDynamicEntity modle) {
        JumpPageManager.getManager().goCommentActivity(context,  modle.getAnswer_id(),modle.getTask_id());
    }

    public void setCommentInfo(final MineDynamicEntity modle) {
        tv_content.setText(modle.getTs_reason());
        tv_comment.setText(modle.getComment());
        tv_title.setText(context.getString(R.string.item_minetype_comment));
        ImageLoadUtils.loadListimg(context, modle.getAnswer_data().getMain_img(), img_covery);
        img_covery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(context, modle.getAnswer_data().getMain_img());
            }
        });

    }

    @Override
    public void bindView() {
        super.bindView();
        tv_comment = getView(R.id.tv_comment);
        img_covery = getView(R.id.img_covery);
        tv_comment = getView(R.id.tv_comment);
        tv_content = getView(R.id.tv_content);
        kol_samllview = getView(R.id.kol_samllview);

    }

    public TextView tv_comment;
    public ImageView img_covery;
    public TextView tv_content;
    public KolSmallView kol_samllview;


}