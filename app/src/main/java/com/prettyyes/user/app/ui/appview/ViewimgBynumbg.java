package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.model.v8.AnswerDataEntity;

/**
 * Created by chengang on 2017/3/27.
 */

public class ViewimgBynumbg extends AbsLinearlayoutView<AnswerDataEntity> {
    private static final String TAG = "ViewimgBynum";
    private String a = "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";
    private String b = "http://pic55.nipic.com/file/20141208/19462408_171130083000_2.jpg";

    public ViewimgBynumbg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewimgBynumbg(Context context) {
        super(context);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_img_by_num_bg;
    }


    private TextView tv_shop;
    private TextView tv_keyword;
    private ViewimgBynum viewimgBynum;

    @Override
    public void initViews() {
        tv_shop = (TextView) getView(R.id.tv_shop_name);
        tv_keyword = (TextView) getView(R.id.tv_shop_category);
        viewimgBynum = (ViewimgBynum) getView(R.id.viewimgbynum_viewimg_bynum_bg);
    }

    @Override
    public void setDataByModel(AnswerDataEntity data) {
        viewimgBynum.setDataByModel(data);
        tv_shop.setText(data.getBrand_name());
        tv_keyword.setText(data.getCategory_name());
    }


}
