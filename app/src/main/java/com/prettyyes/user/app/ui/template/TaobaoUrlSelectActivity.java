package com.prettyyes.user.app.ui.template;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.SpeedSearchRequest;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.TaobaoSelectUrlEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SkuSearchEntity;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

public class TaobaoUrlSelectActivity extends BaseActivity {


    @ViewInject(R.id.btn_confirm)
    public Button btn_confirm;
    @ViewInject(R.id.edit_link)
    public EditTextDel editText;
    @ViewInject(R.id.tv_go_taobao)
    public TextView tv_go_taobao;
    @ViewInject(R.id.tv_go_weidian)
    public TextView tv_go_weidian;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_taobao_url_select;
    }

    @Override
    protected void initViews() {
        setActivtytitle("添加链接");
        tv_go_weidian.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_go_weidian.getPaint().setAntiAlias(true);//抗锯齿
        tv_go_taobao.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_go_taobao.getPaint().setAntiAlias(true);//抗锯齿
        tv_go_taobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goWengetUrlActivity(getThis(), TYPE_TAOBAO);

            }
        });
        tv_go_weidian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goWengetUrlActivity(getThis(), "wechat");

            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.strIsEmpty(editText.getText().toString())) {
                    showToastShort(getString(R.string.template_empty_taobao_url));
                    return;
                }
                new SpeedSearchRequest(editText.getText().toString()).post(new NetReqCallback<SkuSearchEntity>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showToastShort(message);

                    }

                    @Override
                    public void apiRequestSuccess(SkuSearchEntity skuSearchEntity, String method) {
                        LogUtil.i(TAG, "apiRequestSuccess");
                        JumpPageManager.getManager().goTaobaoTempleteActivity(getThis(), skuSearchEntity);
                        finish();


                    }
                });
            }
        });


    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<TaobaoSelectUrlEvent>() {
//            @Override
//            protected void back(TaobaoSelectUrlEvent o) {
//                LogUtil.i(TAG, "mSubscription-->" + o.getSkuSearchEntity());
//                JumpPageManager.getManager().goTaobaoTempleteActivity(getThis(), o.getSkuSearchEntity());
//                finish();
//            }
//        });
        mSubscription= AppRxBus.getInstance().subscribeEvent(new RxCallback<TaobaoSelectUrlEvent>() {
            @Override
            protected void back(TaobaoSelectUrlEvent obj) {
                LogUtil.i(TAG, "mSubscription-->TaobaoSelectUrlEvent" );
                JumpPageManager.getManager().goTaobaoTempleteActivity(getThis(), obj.getSkuSearchEntity());
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LogUtil.i(TAG, "onDestroy");
    }

    @Override
    protected void loadData() {
        if (!StringUtils.strIsEmpty(new SpuDetailRequest().setModule_id("0").setSpu_type(TYPE_TAOBAO).getReqCache())) {
            LogUtil.i(TAG, "loadData");
            JumpPageManager.getManager().goTaobaoTempleteActivity(getThis(), null);
            finish();
        }

//
//                setModule_id("0").setSpu_type(TYPE_TAOBAO).post(new NetReqCallback<SpuInfoEntity>() {
//            @Override
//            public void apiRequestFail(String message, String method) {
//                loadSuccess();
//            }
//
//            @Override
//            public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
//                loadSuccess();
//                if (spuInfoEntity != null) {
//  JumpPageManager.getManager().goTaobaoTempleteActivity(getThis(), null);
//                    finish();
//
//                }
//            }
//
//        });
    }
}
