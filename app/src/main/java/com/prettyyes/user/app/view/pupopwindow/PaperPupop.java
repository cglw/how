package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.order.OrderManager;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.view.pupopwindow
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class PaperPupop extends AbsPupopWindow {
    private RelativeLayout rel_pupoppaper;
    private TextView tv_pupoppaper_price;
    private TextView tv_pupoppaper_time;
    private TextView tv_pupoppaper_desc;
    private LinearLayout lin_pupoppaper_pay;
    private Activity activity;
    private String rewardtype;
    private boolean reward = false;
    private int reward_type_id;
    private int task_id;
    private float price;

    public PaperPupop(Activity context) {
        super(context);
        activity = context;
    }

    @Override
    public void bindView(View view) {
        rel_pupoppaper = (RelativeLayout) view.findViewById(R.id.rel_pupoppaper);
        tv_pupoppaper_price = (TextView) view.findViewById(R.id.tv_pupoppaper_price);
        tv_pupoppaper_time = (TextView) view.findViewById(R.id.tv_pupoppaper_time);
        tv_pupoppaper_desc = (TextView) view.findViewById(R.id.tv_pupoppaper_desc);
        lin_pupoppaper_pay = (LinearLayout) view.findViewById(R.id.lin_pupoppaper_pay);
    }

    public PaperPupop setData(String num, String price, String simpledesc, boolean reward) {
        this.reward = reward;
        this.price = Float.parseFloat(price);
        tv_pupoppaper_time.setText(Html.fromHtml("这篇文章共<font color=\"#C93A48\" ><big>" + num + "</big></font>字," + "大约需要<font color=\"#C93A48\" ><big>" + ((Integer.parseInt(num) + 149) / 150) + "</big></font>分钟阅读"));
        tv_pupoppaper_price.setText(price + "元");
        tv_pupoppaper_desc.setText(simpledesc);
        return this;
    }

    public PaperPupop setPayInfo(String rewardtype, int reward_type_id, int task_id) {
        this.rewardtype = rewardtype;
        this.reward_type_id = reward_type_id;
        this.task_id = task_id;
        return this;

    }

    @Override
    public int getLayout() {
        return R.layout.popup_paper_pay;
    }

    @Override
    public int getLayoutHeight() {
        return 150;
    }

    @Override
    public void setListener() {
        lin_pupoppaper_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaperPupop.this.dismiss();
                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(activity);
                    return;
                }
                new OrderManager(activity).OrderReward(BaseApplication.UUID, rewardtype, reward_type_id, task_id, price);
            }
        });
    }

    @Override
    public void setLayoutId(int id) {
        super.setLayoutId(id);
        if (reward) {
            if (BaseApplication.UUID == null) {
                RegisterActivity.getRegister(activity);
                return;
            }

            JumpPageManager.getManager().goSkuActivity(activity, TYPE_PAPER, reward_type_id + "");
//            PaperWebviewActivity.goPaperActivity((BaseActivity) activity, reward_type_id);

//            new OrderApiImpl().getPaper(BaseApplication.UUID, reward_type_id + "", new NetReqCallback<PaperEntity>() {
//                @Override
//                public void apiRequestFail(String message) {
//                    AppUtil.showToastShort(message);
//                }
//
//                @Override
//                public void apiRequestSuccess(PaperEntity paperEntity) {
//                }
//            });
        } else {
            this.show();
        }
    }
}
