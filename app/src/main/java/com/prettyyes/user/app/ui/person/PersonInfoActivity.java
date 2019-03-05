package com.prettyyes.user.app.ui.person;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.PersonPresenter;
import com.prettyyes.user.app.mvpView.PersonInfoView;
import com.prettyyes.user.app.view.FlowLayout;
import com.prettyyes.user.app.view.app.SettingItemView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class PersonInfoActivity extends BaseActivity implements PersonInfoView {

    @ViewInject(R.id.img_personinfoAct_head)
    private ImageView img_head;
    @ViewInject(R.id.tv_personinfoAct_edithead)
    private TextView tv_editHead;
    @ViewInject(R.id.settingItem_personinfoAct_nickname)
    private SettingItemView settingItemView_name;
    @ViewInject(R.id.settingItem_personinfoAct_address)
    private SettingItemView settingItemView_address;
    @ViewInject(R.id.settingItem_personinfoAct_sex)
    private SettingItemView settingItemView_sex;
    @ViewInject(R.id.settingItem_personinfoAct_ace)
    private SettingItemView settingItemView_ace;
    @ViewInject(R.id.settingItem_personinfoAct_info)
    private SettingItemView settingItem_personinfoAct_info;
    @ViewInject(R.id.flow_personinfoAct_tags)
    private FlowLayout flow_tags;
    @ViewInject(R.id.coordinatorLayout)
    private CoordinatorLayout view_root;


    private PersonPresenter personPresenter;
    private String headimg = "";

    @Override
    protected int bindLayout() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initViews() {
        showBack();
        setActivtytitle("编辑资料");
        personPresenter = new PersonPresenter(this);


    }


    @Override
    protected void setListener() {
        tv_editHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personPresenter.selectImg();
            }
        });
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goBigImgActivity(getThis(), headimg);

//                alertView = personPresenter.getPhotoAlertView();
            }
        });
        settingItemView_name.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personPresenter.goChangeNameActivity();
            }
        });
        settingItemView_sex.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertView = personPresenter.selectSex(settingItemView_sex);
            }
        });
        settingItemView_address.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goAddressListActivity(getThis());
            }
        });
        settingItemView_ace.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personPresenter.goChangeAceActivity();
            }
        });
        settingItem_personinfoAct_info.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personPresenter.goChangeInformationActivity();
            }
        });
        ((LinearLayout) flow_tags.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(AddTagActivity.class);
            }
        });
//        mSubscription = AppRxBus.getInstance().toObservable(UserInfo.class).subscribe(new RxAction1<UserInfo>() {
//            @Override
//            public void callback(UserInfo userInfo) {
//                if (!StringUtils.strIsEmpty(userInfo.getNickname()))
//                    settingItemView_name.setLeftText(userInfo.getNickname());
//                if (!StringUtils.strIsEmpty(userInfo.getAce_txt()))
//                    settingItemView_ace.setLeftText(userInfo.getAce_txt());
//
//            }
//        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<UserInfo>() {
            @Override
            protected void back(UserInfo userInfo) {
                if (!StringUtils.strIsEmpty(userInfo.getNickname()))
                    settingItemView_name.setLeftText(userInfo.getNickname());
                if (!StringUtils.strIsEmpty(userInfo.getAce_txt()))
                    settingItemView_ace.setLeftText(userInfo.getAce_txt());
                if (!StringUtils.strIsEmpty(userInfo.getInformation()))
                    settingItem_personinfoAct_info.setLeftText(userInfo.getInformation());

            }
        });
    }


    @Override
    protected void loadData() {
        personPresenter.getPersonInfo();
    }

    @Override
    public void setHeadImg(String url) {
        personPresenter.setHeadimg(img_head, url);
        headimg = url;
    }

    @Override
    public void setName(String name) {
        settingItemView_name.setLeftText(name);
    }

    @Override
    public void setSex(String sex) {
        settingItemView_sex.setLeftText(sex);
    }

    @Override
    public void setAce(String ace) {
        settingItemView_ace.setLeftText(ace);
    }

    @Override
    public void setInfo(String info) {
        settingItem_personinfoAct_info.setLeftText(info);
    }

    @Override
    public String getName() {
        return settingItemView_name.getLeftTv();
    }

    @Override
    public String getSex() {
        return settingItemView_sex.getLeftTv();
    }

    @Override
    public String getHeadimg() {
        return headimg;
    }

    @Override
    public String getAce() {
        return settingItemView_ace.getLeftTv();
    }

    @Override
    public String getInfo() {
        return settingItem_personinfoAct_info.getLeftTv();
    }

    @Override
    public CoordinatorLayout getCoordinatorLayout() {
        return view_root;
    }

    @Override
    public void setTag(ArrayList<String> tags) {
        flow_tags.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            flow_tags.addView(getFlowTv(tags.get(i)));
        }
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    private View getFlowTv(String txt) {
        TextView tv = new TextView(this);
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        tv.setText(txt);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        personPresenter.onActivityResultt(requestCode, resultCode, data);

    }

    @Override
    public void setInentFliter(IntentFilter inentFliter) {
        inentFliter.addAction(Constant.PersonName);
        inentFliter.addAction(Constant.PersonTag);
    }

    @Override
    public void handlerIntenter(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.PersonName)) {
            settingItemView_name.setLeftText(getAccount().getNickname());
        } else if (intent.getAction().equals(Constant.PersonTag)) {
            personPresenter.setTagByAccount();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        personPresenter.release();
    }
    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
////        personPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

}
