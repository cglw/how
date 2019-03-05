package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.comment.CommentInfo;
import com.prettyyes.user.model.comment.CommentList;
import com.prettyyes.user.model.comment.CommentMyList;
import com.prettyyes.user.model.v8.CommentChildrenBean;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/16
 * Description: Nothing
 */
public class KolSimpleInfoView extends AbsRelativelayoutView {
    private static final String TAG = "KolSimpleInfoView";

    public KolSimpleInfoView(Context context) {
        super(context);
    }

    public KolSimpleInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_kolsimpleinfo;
    }


    @ViewInject(R.id.img_moreCommentAdp_header)
    private ImageView img_header;
    @ViewInject(R.id.tv_moreCommentAdp_info)
    private TextView tv_info;
    @ViewInject(R.id.tv_moreCommentAdp_nickname)
    private TypefaceTextView tv_nickname;
    @ViewInject(R.id.img_grade_title)
    public ImageView img_grade_title;
    private String seller_id;




    public TypefaceTextView getTv_nickname() {
        return tv_nickname;
    }

    public TextView getTv_info() {
        return tv_info;
    }

    @Override
    public void initViews() {

        img_header = (ImageView) getView(R.id.img_moreCommentAdp_header);
        tv_info = (TextView) getView(R.id.tv_moreCommentAdp_info);
        tv_nickname = (TypefaceTextView) getView(R.id.tv_moreCommentAdp_nickname);

    }

    @Override
    public void setListener() {
        img_header.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.strIsEmpty(seller_id))
                    return;
                if (needClickHead)
                    JumpPageManager.getManager().goKolInfoActivity(getContext(), seller_id);
            }
        });
    }

    boolean needClickHead = true;

    public void setNeedClickHead(boolean needClickHead) {
        this.needClickHead = needClickHead;
    }

    @Override
    public void setDataByModel(Object data) {

    }

    public void setSellerInfo(SellerInfoEntity data) {
        setSellerInfo(data, 30);
    }

    public void setSellerInfo(SellerInfoEntity data, int head_height) {
        if (data.getSeller_id() == null)
            return;
        ImageLoadUtils.loadHeadImg(this.getContext(), data.getHeadimg(), img_header);
        if (tv_info != null)
            tv_info.setText(data.getAce_txt());
        if (tv_nickname != null)
            tv_nickname.setText(data.getNickname());
        seller_id = data.getSeller_id();
        BadgeView badgeView2 = new BadgeView(getActivity());
        badgeView2.initNopadding(head_height);
        badgeView2.setTargetView(img_header);
        badgeView2.setImageTag(data.isFamous(), head_height);
        String title = data.getGrade_title();
        setGradeTitle(img_grade_title, title, getContext());


    }

    public static void setGradeTitle(ImageView imageView, String title, Context context) {
        int res = R.mipmap.ic_grade_haoshou;
        if (title == null) {
            title = "";
        }

        switch (title) {
            case "好心人路过":
                res = R.mipmap.ic_grade_haoshou;
                break;
            case "金牌好手":
                res = R.mipmap.ic_grade_excellect;
                break;
            case "名人":
                res = R.mipmap.ic_grade_famous;
                break;
            case "品牌":
                res = R.mipmap.ic_grade_brand;
                break;
            default:
                res = 0;
        }
        imageView.setImageResource(res);

    }


    public void setDataByCommentList(CommentList data) {
        ImageLoadUtils.loadHeadImg(this.getContext(), data.getHeadimg(), img_header);
        tv_info.setText(data.getCreated_at());
        tv_nickname.setText(data.getUsername());
        img_grade_title.setVisibility(GONE);
    }

    public void setDataByCommentChildrenBean(CommentInfo.ChildrenBean data) {
        ImageLoadUtils.loadHeadImg(this.getContext(), data.getHeadimg(), img_header);
        tv_info.setText(data.getCreated_at());
        tv_nickname.setText(data.getUsername());
        seller_id = data.getForm_uid() + "";
        img_grade_title.setVisibility(GONE);


    }
    public void setDataByCommentChildrenBean(CommentChildrenBean data) {
        ImageLoadUtils.loadHeadImg(this.getContext(), data.getHeadimg(), img_header);
        tv_info.setText(data.getCreate_time());
        tv_nickname.setText(data.getUsername());
        seller_id="";
        img_grade_title.setVisibility(GONE);


    }


    public void setDataByCommentMyList(CommentMyList data) {
        ImageLoadUtils.loadHeadImg(this.getContext(), data.getHeadimg(), img_header);
        tv_info.setText(data.getCreated_at());
        tv_nickname.setText(data.getUsername());
        seller_id = data.getForm_uid() + "";
        img_grade_title.setVisibility(GONE);
    }

    /**
     * 根据index 设置值
     *
     * @param data
     */
    @Override
    public void setData(String... data) {

    }

}
