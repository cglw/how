package com.prettyyes.user.core;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;

import com.prettyyes.user.app.view.pupopwindow.MoreWithSharePupop;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.DensityUtil;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core
 * Author: SmileChen
 * Created on: 2016/10/10
 * Description: Nothing
 */
public class PupopWindowHandler {
    private MoreWithSharePupop morePopupWindow;
    private Activity activity;


    public PupopWindowHandler(Activity activity) {
        this.activity = activity;
    }

    public void showPupop(View view, final ItemClick itemClick) {
        morePopupWindow = new MoreWithSharePupop(activity, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtils.isFastDoubleClick()) {
                    return;
                }
                morePopupWindow.dismiss();
                if (null != itemClick) {
                    switch ((Integer) v.getTag()) {
                        case 0:
                            itemClick.share();
                            break;
                        case 1:
                            itemClick.refuse();
                            break;

                    }
                }
            }
        });
        morePopupWindow.showAsDropDown(view,  DensityUtil.dip2px(activity, 5), -DensityUtil.dip2px(activity, 32), Gravity.TOP | Gravity.RIGHT); //设置layout在PopupWindow中显示的位置

    }

    public interface ItemClick {
        public void share();

        public void refuse();
    }
}
