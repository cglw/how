package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/16
 * Description: Nothing
 */
public class ImageFourView extends AbsLinearlayoutView {
    public ImageFourView(Context context) {
        super(context);
    }

    public ImageFourView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.item_mianlayout_typeimgs;
    }

    private LinearLayout lin_common_fourImgs;
    private int totalwith = 0;
    private int margin = 5;

    @Override
    public void initViews() {
        lin_common_fourImgs = (LinearLayout) getView(R.id.lin_common_fourImgs);
    }

    @Override
    public void setDataByModel(Object data) {
    }

    @Override
    public void setIntData(Integer... data) {
        for (int i = 0; i < data.length; i++) {
            int value = data[i];
            switch (i) {
                case 0:
                    totalwith = value;
                    break;
                case 1:
                    margin = 0;
                    break;
            }
        }
    }

    //先隐藏掉所有viwe
    public void setResData(final ArrayList<String> data, final ImageClickListener imageClickListener) {
        for (int i = 0; i < lin_common_fourImgs.getChildCount(); i++) {
            lin_common_fourImgs.getChildAt(i).setVisibility(View.INVISIBLE);
        }
        if (data == null)
            return;
        for (int i = 0; i < data.size(); i++) {
            if (i <= 3) {
                ImageView image = (ImageView) lin_common_fourImgs.getChildAt(i);
                image.setVisibility(VISIBLE);
                LinearLayout.LayoutParams layoutParams = (LayoutParams) image.getLayoutParams();
                layoutParams.width = (totalwith - DensityUtil.dip2px(3 * margin)) / 4;

                layoutParams.height = layoutParams.width;
                if (i != 3) {
                    layoutParams.setMargins(0, 0, DensityUtil.dip2px(margin), 0);
                }
                ImageLoadUtils.loadListimg(getContext(), data.get(i), image);
                final int j = i;
                image.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageClickListener.click(v, j, data);
                    }
                });
            }
        }
    }

    public interface ImageClickListener {
        public void click(View v, int index, ArrayList<String> res);
    }
}
