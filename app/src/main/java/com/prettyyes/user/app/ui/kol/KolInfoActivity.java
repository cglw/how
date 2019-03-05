package com.prettyyes.user.app.ui.kol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.UserApiImpl;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.mvpPresenter.KolInfoPresenter;
import com.prettyyes.user.app.mvpView.KolInfoView;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.appview.KolSimpleInfoView;
import com.prettyyes.user.app.ui.common.ViewPagerActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.app.view.progress.CircleKolView;
import com.prettyyes.user.app.view.tvbtnetv.ExpandableTextView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.StringUtils;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class KolInfoActivity extends BaseActivity implements KolInfoView {


    @ViewInject(R.id.img_kolinfoAct_head)
    private ImageView img_head;
    @ViewInject(R.id.tv_kolinfoAct_nickname)
    private TextView tv_name;
    @ViewInject(R.id.etv_kolinfoAct_security)
    private ExpandableTextView tv_security;
    @ViewInject(R.id.tv_kolinfoAct_act)
    private TextView tv_act;
    @ViewInject(R.id.tv_kolinfoAct_zezenumber)
    private TextView tv_goodnum;
    @ViewInject(R.id.tv_kolinfoAct_peinumber)
    private TextView tv_badnum;
    @ViewInject(R.id.tv_kolinfoAct_shareumber)
    private TextView tv_sharenum;
    @ViewInject(R.id.tv_kolinfoAct_morenum)
    private TextView tv_more;
    @ViewInject(R.id.tv_kolinfoAct_total)
    private TextView tv_total;
    @ViewInject(R.id.tv_kolinfoAct_goodcontentnum)
    private TextView tv_goodcontent;
    @ViewInject(R.id.tv_kolinfoAct_homenum)
    private TextView tv_homenum;


    @ViewInject(R.id.ll_kolinfoAct_imgs)
    private LinearLayout viewgroup_kolinfoAct_imgs;

    @ViewInject(R.id.tabLayout_kollistAdp)
    private TableLayout tabLayout_kollistAdp;

    @ViewInject(R.id.img_kolinfoAct_chat)
    private ImageView img_chat;
    @ViewInject(R.id.tv_kolinfoAct_collectkol)
    private TextView tv_collectkol;
    @ViewInject(R.id.tv_kolinfoAct_collectnumber)
    private TextView tv_collectnumber;

    @ViewInject(R.id.btn_kolinfoAct_ask)
    private Button btn_ask;

    @ViewInject(R.id.circle_kolinfoAct)
    private CircleKolView circleKolView;
    @ViewInject(R.id.lin_kolinfoAct_numbers)
    private LinearLayout lin_numbers;

    @ViewInject(R.id.img_grade_title)
    ImageView img_grade_title;

//
//    @ViewInject(R.id.scrollView_kolinfoAct_base)
//    private ScrollView scrollView_base;

    private KolInfoPresenter kolInfoParsent = new KolInfoPresenter(this);
    private int seller_id = 0;
    private boolean ischeck = false;

    public void onResume() {
        super.onResume();
        if (seller_id != 0)
            AppStatistics.onPageStart(getThis(),new StringBuilder().append("KolDetailPage_").append(seller_id).toString()); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    public void onPause() {
        super.onPause();
        if (seller_id != 0)
            AppStatistics.onPageEnd(getThis(),new StringBuilder().append("KolDetailPage_").append(seller_id).toString()); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        new UserApiImpl().userSellerIntroducefromuser(getUUId(), seller_id, new NetReqCallback() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(Object o, String method) {

            }
        });

    }

    public static void goKolInfo(Context context, int seller_id) {
        Intent i = new Intent(context, KolInfoActivity.class);
        i.putExtra("seller_id", seller_id);
        ((BaseActivity) context).nextActivity(i);
    }

    @Override
    protected void initVariables() {
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            seller_id = Integer.parseInt(intentParmas.getSeller_id());
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_kol_info;
    }

    @Override
    protected void initViews() {
        setStateBarColor(ContextCompat.getColor(this, R.color.theme_coffee));
        setActivtytitle(R.string.title_activity_kol_info);
        setApptitleColor(ContextCompat.getColor(this, R.color.theme_coffee));
        getImg_base_back().setColorFilter(ContextCompat.getColor(this, R.color.white));
        getImg_right().setColorFilter(ContextCompat.getColor(this, R.color.white));
        setActivtytitleTextColor(ContextCompat.getColor(this, R.color.white));
        showRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kolInfoParsent.share(seller_id, R.id.scrollView_kolinfoAct_base);


            }
        });


        initParams();
    }

    @Override
    protected void loadData() {
        kolInfoParsent.getNetData(BaseApplication.UUID, seller_id);
    }

    @Override
    protected void setListener() {
        tv_collectkol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUUId() == null) {
                    RegisterActivity.getRegister(KolInfoActivity.this);
                    return;
                }
                changeCollectionNum();
                if (!ClickUtils.isFastDoubleClick()) {
                    kolInfoParsent.favorite(tv_collectnumber, BaseApplication.UUID, seller_id, !ischeck);
                }
            }
        });

        img_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seller_id != 0 && getUUId() != null) {
                    AccountDataRepo.getAccountManager().chatWithSeller(seller_id + "", KolInfoActivity.this);
                } else {
                    RegisterActivity.getRegister(getThis());
                }
            }
        });

        viewgroup_kolinfoAct_imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kolInfoParsent.gokolSuitList(seller_id);
            }
        });
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.strIsEmpty(headimg))
                    ViewPagerActivity.goVpActivity(KolInfoActivity.this, 0, headimg, "kolhead");
            }
        });
        btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kolInfoParsent.ask(seller_id);
            }
        });
    }

    private String headimg;

    @Override
    public void setHeadView(String url, int famous_type) {
        headimg = url;
        kolInfoParsent.setImage(url, img_head);
        BadgeView badgeView = new BadgeView(this);
        badgeView.initNopadding(70);

        badgeView.setTargetView(img_head);
        badgeView.setImageTag(AppUtil.isFamous(famous_type), 70);


    }

    @Override
    public void setName(String name) {
        tv_name.setText(name);
    }

    @Override
    public void setInfo(String info) {
        tv_act.setText(info);
    }

    @Override
    public void setGradeTitle(String gradeTitle) {
        KolSimpleInfoView.setGradeTitle(img_grade_title, gradeTitle, this);
    }

    @Override
    public void setTag(ArrayList<String> tags) {

    }

    private View getFlowTv(String txt, boolean isneedMargin) {
        TextView tv = new TextView(this);
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        tv.setText(txt);
        tv.setGravity(Gravity.CENTER);
        if (isneedMargin)
            layoutParams.setMargins(DensityUtil.dip2px(8), 0, 0, 0);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(11);
        int padding = DensityUtil.dip2px(6);
        int padding3 = DensityUtil.dip2px(3);
        tv.setPadding(padding, padding3, padding, padding3);
        tv.setBackgroundResource(R.drawable.bg_rang_grey);
        tv.setLayoutParams(layoutParams);


        return tv;
    }

    @Override
    public void setUser_security(String tv) {
        tv_security.setText(tv);

    }

    @Override
    public void setMormalNum(int good, int bad, int collection, int share) {
        tv_goodnum.setText(new StringBuilder().append(good).toString());
        tv_badnum.setText(new StringBuilder().append(bad).toString());
        tv_collectnumber.setText(new StringBuilder().append(collection).toString());
        tv_sharenum.setText(new StringBuilder().append(share).toString());
    }

    @Override
    public void setShareNum(int share) {
        //重新设置的
        tv_sharenum.setText(new StringBuffer().append((Integer.parseInt(tv_sharenum.getText().toString()) + share)).toString());
    }

    @Override
    public void setSuitNum(int total, int normal, int taobao, int imgs) {
//        tv_suittotal.setText(total + "");
//        tv_suitnormal.setText(normal + "");
//        tv_suittaobao.setText(taobao + "");
//        tv_suitImgtotal.setText(imgs + "");
    }

    @Override
    public void setContentProportion(int good, int firstpageNum) {
//        tv_contentGoodNum.setText(good + "");
//        tv_firstpageNum.setText(firstpageNum + "");
    }

    @Override
    public void setCircleView(double progress, double progress_Inner, int total) {
        circleKolView.setProgress(progress, progress_Inner, total);
    }

    @Override
    public void setImageList(ArrayList<String> data) {

        for (int i = 0; i < data.size(); i++) {
            if (i >= 3) {
                return;
            }
            RoundImageView v = null;
            if (i == 0)
                v = (RoundImageView) viewgroup_kolinfoAct_imgs.getChildAt(0);
            else if (i > 0) {
                RelativeLayout childAt = (RelativeLayout) viewgroup_kolinfoAct_imgs.getChildAt(1);
                v = (RoundImageView) childAt.getChildAt(i - 1);
            }

            kolInfoParsent.setListImage(data.get(i), v);
        }

    }

    private void initParams() {
        int width = (BaseApplication.ScreenWidth - DensityUtil.dip2px((20 + 5) * 2 + 15)) / 2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) circleKolView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = width;

        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) lin_numbers.getLayoutParams();
        layoutParams1.height = width;
        layoutParams1.width = width;


//        int with = (BaseApplication.ScreenWidth - DensityUtil.dip2px(15) * 6) / 5;
//        lin_imgs.getLayoutParams().height = with;
//        RelativeLayout relativeLayout = (RelativeLayout) tv_suitImgtotal.getParent();
//        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
//        layoutParams1.width = with;
//        layoutParams1.height = with;
//        layoutParams1.leftMargin = -(with);
//        int total = 0;
//        for (int i = 0; i < 5; i++) {
//            if (lin_imgs.getChildAt(i) != null) {
//
//                RoundImageView imageView = (RoundImageView) lin_imgs.getChildAt(i);
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
//                layoutParams.height = with;
//                layoutParams.width = with;
//                layoutParams.leftMargin = DensityUtil.dip2px(15);
//                total += DensityUtil.dip2px(15) + with;
//            }
//        }
    }

    @Override
    public void setNumListName(String total, String good_content, String home) {
        tv_total.setText(total);
        tv_goodcontent.setText(good_content);
        tv_homenum.setText(home);

    }

    @Override
    public void setCollecttion(boolean isCollection) {
        ischeck = isCollection;
        if (isCollection) {
            tv_collectkol.setText("已收藏");
        } else {
            tv_collectkol.setText("收藏");

        }

    }

    //点击改变
    @Override
    public void changeCollectionNum() {
//        if (ischeck) {
//            tv_collectionnum.setText((Integer.parseInt(tv_collectionnum.getText().toString()) - 1) + "");
//        } else {
//            tv_collectionnum.setText((Integer.parseInt(tv_collectionnum.getText().toString()) + 1) + "");
//
//        }
    }

    @Override
    public void setIngredient(List<String> datas) {
        for (int i = 0; i < tabLayout_kollistAdp.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(j);
                tv.setVisibility(View.GONE);
            }

        }
        for (int i = 0; i < datas.size(); i++) {
            if (i <= 1) {
                TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(0);
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(i);
                tv.setText(datas.get(i));
                tv.setVisibility(View.VISIBLE);

            } else if (i <= 3) {
                TableRow tableRow = (TableRow) tabLayout_kollistAdp.getChildAt(1);
                TypefaceTextView tv = (TypefaceTextView) tableRow.getChildAt(i - 2);
                tv.setText(datas.get(i));
                tv.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public ScrollView getScrollview() {
        return null;
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tv_security != null)
            tv_security.clearSelf();
    }

}
