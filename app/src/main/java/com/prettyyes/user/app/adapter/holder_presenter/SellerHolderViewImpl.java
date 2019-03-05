package com.prettyyes.user.app.adapter.holder_presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import static com.prettyyes.user.app.ui.appview.KolSimpleInfoView.setGradeTitle;

/**
 * Created by chengang on 2017/8/10.
 */

public class SellerHolderViewImpl extends BaseHolderViewImpl<SellerInfoEntity> {

    public SellerHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, SellerInfoEntity data) {
        super(mutiTypeViewHolder, position, data);
    }

    private RelativeLayout view_head_parent;
    private ImageView img_head;
    private TypefaceTextView tv_seller_nickname;
    private ImageView img_grade_title;
    private TextView tv_seller_ace;
    public String seller_id;

    public void bindSellerViews() {
        view_head_parent = getView(R.id.view_head_parent);
        img_head = getView(R.id.img_head);
        tv_seller_nickname = getView(R.id.tv_seller_nickname);
        img_grade_title = getView(R.id.img_grade_title);
        tv_seller_ace = getView(R.id.tv_seller_ace);

        if (data.getSeller_id() == null)
            return;
        ImageLoadUtils.loadHeadImg(context, data.getHeadimg(), img_head);
        if (tv_seller_ace != null)
            tv_seller_ace.setText(data.getAce_txt());
        if (tv_seller_nickname != null)
            tv_seller_nickname.setText(data.getNickname());
        seller_id = data.getSeller_id();

        String title = data.getGrade_title();
        setGradeTitle(img_grade_title, title, context);

        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.strIsEmpty(seller_id))
                    return;
                JumpPageManager.getManager().goKolInfoActivity(getContext(), seller_id);
            }
        });
    }


}
