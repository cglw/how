package com.prettyyes.user.app.ui.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.CouponApiImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.coupon.AddShareCoupon;
import com.prettyyes.user.model.coupon.ExistShareCoupon;

import org.xutils.view.annotation.ViewInject;

public class CouponShareActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.tv_couponshare_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_couponshare_code)
    private TextView tv_code;
    @ViewInject(R.id.lin_couponshare_wechat)
    private LinearLayout lin_wechat;
    @ViewInject(R.id.lin_couponshare_weibo)
    private LinearLayout lin_weibo;
    @ViewInject(R.id.lin_couponshare_friends)
    private LinearLayout lin_friends;
    Bitmap share_img = null;
    String share_target_url = "";
    String share_title = "How 读过书的人才懂的app";
    String share_content = "分享好友获得50元优惠券";
    private String coupon_name = "";
    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_coupon_share;
    }

    @Override
    protected void initViews() {
        setActivtytitle(getString(R.string.title_activity_couponshare));
        String text = "•点击右上角生成个性邀请码\n•分享好友获取 50元 优惠券";
        setTitle(text);
        share_img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_coupon_default_share);


    }

    private void setTitle(String text) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.theme_red)), text.indexOf("50"), text.indexOf("50") + 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        tv_title.setText(style);
    }

    private void showEditdialog() {
        final EditText et = new EditText(this);
        et.setMaxEms(10);

        new AlertDialog.Builder(this).setTitle("输入自定义优惠券")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString().trim();
                        if (input.length() > 0)
                            addCouponCode(input);
                        else
                            showToastShort("您需要输入优惠券名称");
                    }

                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void addCouponCode(final String slogan) {
        new CouponApiImpl().couponAddShareCoupon(getUUId(), slogan, new NetReqCallback<AddShareCoupon>() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(AddShareCoupon addShareCoupon,String method) {
                getRight_tv().setVisibility(View.GONE);
                tv_title.setVisibility(View.GONE);
                tv_code.setText(slogan);
                share_target_url = addShareCoupon.getUrl();
                showToastShort("生成优惠券成功");
            }
        });
    }

    @Override
    protected void setListener() {
        super.setListener();
        lin_weibo.setOnClickListener(this);
        lin_wechat.setOnClickListener(this);
        lin_friends.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        checkIsHaveCode();
    }

    private void checkIsHaveCode() {
        new CouponApiImpl().couponExistShareCoupon(getUUId(), new NetReqCallback<ExistShareCoupon>() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);
            }

            @Override
            public void apiRequestSuccess(ExistShareCoupon ex,String method) {
                if (ex.getFlag() == 0) {
                    setRightTvListener("邀请码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showEditdialog();
                        }
                    });
                    return;
                } else {
                    tv_code.setText(ex.getSlogan());
                    share_target_url = ex.getShare_url();
                    tv_title.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (StringUtils.strIsEmpty(share_target_url)) {
            showToastShort("请生成优惠券");
            return;
        }
        if (share_img == null) {
            showToastShort("未获取到分享图片");
            return;
        }

//        switch (v.getId()) {
//            case R.id.lin_couponshare_friends:
//                new ShareHandler(this).setRes(share_target_url, share_content, share_img, share_title).share_friends();
//                break;
//            case R.id.lin_couponshare_wechat:
//                new ShareHandler(this).setRes(share_target_url, share_content, share_img, share_title).share_wechat();
//                break;
//            case R.id.lin_couponshare_weibo:
//                new ShareHandler(this).setRes(share_target_url, share_content, share_img, share_title).share_weibo();
//
//                break;
//        }
    }

}
