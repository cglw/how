package com.prettyyes.user.app.adapter.detail;


import android.content.Context;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.SpecilAbsAdapter;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.user.UserLikeseller;

import java.util.ArrayList;


/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter
 * Author: SmileChen
 * Created on: 2016/8/11
 * Description: Nothing
 */
public class CollectKolListAdapter extends SpecilAbsAdapter<UserLikeseller.ListEntity> {


    public CollectKolListAdapter(Context context) {
        super(R.layout.item_lv_collectkol, new ArrayList<UserLikeseller.ListEntity>(), context);
    }

    @Override
    public void showData(ViewHolder vHolder, final UserLikeseller.ListEntity data, final int position) {
        tv_nickname.setText(data.getAce_name());
        tv_desc.setText(data.getAce_txt());
        ImageLoadUtils.loadHeadImg(context, data.getHeadimg(), img_headimg);
        tv_like.setText(data.getZeze_num()+"");
        tv_dislike.setText(data.getPei_num() + "");
        tv_collection.setText(data.getShare_num() + "");
        BadgeView badgeView2 = new BadgeView(context);
        badgeView2.initNopadding(50);
        badgeView2.setTargetView(img_headimg);
        badgeView2.setImageTag(AppUtil.isFamous(data.getFamous_type()),50);

    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        tv_nickname = (TextView) viewHolder.getView(R.id.tv_collectKolAdp_nickname);
        tv_desc = (TextView) viewHolder.getView(R.id.tv_collectKolAdp_desc);
        tv_like = (TextView) viewHolder.getView(R.id.tv_collectKolAdp_like);
        tv_collection = (TextView) viewHolder.getView(R.id.tv_collectKolAdp_collection);
        tv_dislike = (TextView) viewHolder.getView(R.id.tv_collectKolAdp_dislike);
        img_headimg = (RoundImageView) viewHolder.getView(R.id.img_collectKolAdp_headimg);


    }

    private TextView tv_nickname;
    private TextView tv_desc;
    private TextView tv_like;
    private TextView tv_collection;
    private TextView tv_dislike;
    private RoundImageView img_headimg;

}
