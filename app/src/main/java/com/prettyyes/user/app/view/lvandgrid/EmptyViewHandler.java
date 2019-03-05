package com.prettyyes.user.app.view.lvandgrid;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chengang on 2017/5/9.
 */

public class EmptyViewHandler {


    public static void setEmptyDefault(EmptyType type, LinearLayout ll, Button button) {
        button.setVisibility(View.VISIBLE);
        ll.setVisibility(View.VISIBLE);
        switch (type) {
            case EMPTY_WITH_BUTTON:
                break;
            case FAIL:
                ((TextView) ll.getChildAt(0)).setText("我们无法预知未来很有挑逗意味");
                ((TextView) ll.getChildAt(1)).setText("——霍金");
                ((TextView) ll.getChildAt(2)).setText("当前网络信号出错，刷新后的页面会让您更加惊喜。");
                button.setText("刷新");
                break;
            case EMPTY:
                ((TextView) ll.getChildAt(0)).setText("我们无法预知未来很有挑逗意味");
                ((TextView) ll.getChildAt(1)).setText("——霍金");
                ((TextView) ll.getChildAt(2)).setText("没有数据。");
                button.setVisibility(View.GONE);
                break;
        }
    }

    public void setEmptyByPage(int type, LinearLayout ll, Button button, String page) {

    }

    public enum EmptyType {
        EMPTY_WITH_BUTTON, FAIL, EMPTY
    }
}
