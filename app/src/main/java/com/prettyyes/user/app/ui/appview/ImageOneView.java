package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.core.utils.ImageLoadUtils;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/16
 * Description: Nothing
 */
public class ImageOneView extends AbsLinearlayoutView {
    public ImageOneView(Context context) {
        super(context);
    }

    public ImageOneView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private ImageView img_otherqueAdp_commentOneimg;

    @Override
    public int bindLayout() {
        return R.layout.item_mianlayout_typeoneimg;
    }

    @Override
    public void initViews() {
        img_otherqueAdp_commentOneimg = (ImageView) getView(R.id.img_otherqueAdp_commentOneimg);
    }

    @Override
    public void setDataByModel(Object data) {

    }

    @Override
    public void setData(String... data) {
        if (data.length > 0)
            ImageLoadUtils.loadListimg(getContext(), data[0], img_otherqueAdp_commentOneimg);
    }

    @Override
    public void setListener() {
    }

    public void setListener(OnClickListener onClickListener) {
        img_otherqueAdp_commentOneimg.setOnClickListener(onClickListener);
    }

}
