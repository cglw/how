package com.prettyyes.user.app.ui.kol;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.api.netXutils.requests.BrandCountRequest;
import com.prettyyes.user.api.netXutils.requests.KolReplyRequest;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.api.netXutils.response.BrandCountRes;
import com.prettyyes.user.api.netXutils.response.KolReplyRes;
import com.prettyyes.user.app.adapter.VideoReplyHolder;
import com.prettyyes.user.app.adapter.holder_presenter.MutiSpuHolderViewImpl;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.ui.appview.SelectMediaView;
import com.prettyyes.user.app.view.pupopwindow.ReplySelectPupop;
import com.prettyyes.user.core.AlertDialogManager;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.ReplyQuestionSuccessEvent;
import com.prettyyes.user.core.event.SelectExtraVideoEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.model.v8.SpuInfoEntity;
import com.prettyyes.user.model.v8.UploadMediaEntity;

import org.xutils.view.annotation.ViewInject;

public class KolReplyActivity extends BaseActivity {


    public SpuInfoEntity spuInfoEntity;
    @ViewInject(R.id.edit)
    private EditText edit;
    private String task_id;
    @ViewInject(R.id.ll_brand)
    private View ll_brand;
    @ViewInject(R.id.ll_new_sku)
    private View ll_new_sku;
    @ViewInject(R.id.ll_sku)
    private View ll_sku;
    @ViewInject(R.id.ll_aply_kol)
    private View ll_aply_kol;
    @ViewInject(R.id.tv_brand_count)
    private TextView tv_brand_count;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_que)
    private TextView tv_que;
    @ViewInject(R.id.tv_left)
    private TextView tv_left;
    @ViewInject(R.id.btn_release)
    private Button btn_release;
    @ViewInject(R.id.tv_select_sku)
    private TextView tv_select_sku;
    @ViewInject(R.id.selectMedaiView)
    private SelectMediaView selectMedaiView;
    @ViewInject(R.id.tv_delete)
    private TextView tv_delete;
    @ViewInject(R.id.ll_spu)
    private LinearLayout ll_spu;
    @ViewInject(R.id.view_kol_reply_root)
    private View view_kol_reply_root;
    @ViewInject(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @ViewInject(R.id.ll_reply_desc)
    View ll_reply_desc;

    private CharSequence temp;
    private VideoReplyHolder videoReplyHolder;


    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_kol_reply;
    }

    @Override
    protected void setListener() {
        super.setListener();
        ll_aply_kol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goApplayKolDetailActivity(getThis());
            }
        });
        videoReplyHolder.setDeleteOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMedaiView.setVisibility(View.VISIBLE);
                videoReplyHolder.getVideoReplyView().setVisibility(View.GONE);
                videoReplyHolder.clear();
            }
        });

        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddTemplateOrSelectSuccessEvent>() {
            @Override
            protected void back(AddTemplateOrSelectSuccessEvent addTemplateOrSelectSuccessEvent) {

                new SpuDetailRequest().setSpu_type(addTemplateOrSelectSuccessEvent.spu_type).setModule_id(addTemplateOrSelectSuccessEvent.module_id).post(new NetReqCallback<SpuInfoEntity>() {
                    @Override
                    public void apiRequestFail(String message, String method) {

                        showSnack(message);
                    }

                    @Override
                    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
                        KolReplyActivity.this.spuInfoEntity = spuInfoEntity;
                        setMutiTypeData(spuInfoEntity);
                        nestedScrollView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                nestedScrollView.fullScroll(ScrollView.FOCUS_DOWN);

                            }
                        }, 100);

                    }
                });
            }
        }, new RxCallback<SelectExtraVideoEvent>() {
            @Override
            protected void back(SelectExtraVideoEvent obj) {
                videoReplyHolder.getVideoReplyView().setVisibility(View.VISIBLE);
                videoReplyHolder.setData(obj.getVideo_path(), obj.getVideo_covery());
                selectMedaiView.setVisibility(View.GONE);
                nestedScrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nestedScrollView.fullScroll(ScrollView.FOCUS_DOWN);

                    }
                }, 100);
            }
        });


        tv_select_sku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goSkuSelectActivity(getThis(), true);

            }
        });
        ll_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBrandListActivty(getThis());
            }
        });
        ll_sku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSpuListActivity(getThis());

            }
        });
        getTv_base_title().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOverFlowed(getTv_base_title())) {//超出一行
                    if (desc == null)
                        return;
                    AlertDialogManager.getInstance().show(getThis(), "问题", desc);
                }

            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setLeftTv(temp.length());

            }
        });
        btn_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                TopShowViewPorxy topShowViewPorxy=new TopShowViewPorxy(getRootView());
//                topShowViewPorxy.show();
                postMedia();
            }
        });
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_spu.setVisibility(View.GONE);
                spuInfoEntity = null;
                tv_select_sku.setVisibility(View.VISIBLE);
            }
        });
    }

    public static boolean isOverFlowed(TextView tv) {
        int availableWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight();
        Paint textViewPaint = tv.getPaint();
        float textWidth = textViewPaint.measureText(tv.getText().toString());
        if (textWidth > availableWidth) {
            return true;
        } else {
            return false;
        }
    }

    private void setMutiTypeData(SpuInfoEntity spuInfoEntity) {
        if (ll_spu.getVisibility() == View.GONE) {
            ll_spu.setVisibility(View.VISIBLE);
            tv_select_sku.setVisibility(View.GONE);
        }
        mutiSpuHolderView.setData(spuInfoEntity);
        mutiSpuHolderView.bindMutiSpu();


    }

    private MutiSpuHolderViewImpl mutiSpuHolderView;

    String desc;

    @Override
    protected void initViews() {
        setActivtytitle(R.string.title_activity_kolreply);

        videoReplyHolder = new VideoReplyHolder(getRootView());
        selectMedaiView.setMax(9);
        selectMedaiView.setTopShowViewPorxyRoot(view_kol_reply_root);
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            task_id = intentParmas.getTask_id();
            desc = intentParmas.getQuestion();
        }

        tv_title.setText("选择合适的模板添加详细的商品内容，使你的回复更有说服力。（可选）");

        tv_que.setText(desc);
        mutiSpuHolderView = new MutiSpuHolderViewImpl(new MutiTypeViewHolder(findViewById(R.id.view_mutispu_root)) {
            @Override
            public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

            }

            @Override
            public void bindView() {

            }

        });

        resetDesc();

    }

    private void resetDesc() {
        tv_que.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tv_que.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ll_reply_desc.getLayoutParams();
                layoutParams.setMargins(AppUtil.dip2px(16), tv_que.getBottom() - AppUtil.dip2px(12), AppUtil.dip2px(16), 0);
                ll_reply_desc.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    public void back() {
        if (edit.getText().toString().length() > 0 || spuInfoEntity != null) {

            try {
                selectMedaiView.check();

                DialogHelper.getInstance().getDialogNoCancel(R.string.template_edit_giveup, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                }).show();

            } catch (Exception e) {
                e.printStackTrace();
                AppUtil.showToastShort(e.getMessage());
            }

        } else {

            try {
                selectMedaiView.check();
                finish();

            } catch (Exception e) {
                e.printStackTrace();
                AppUtil.showToastShort(e.getMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void loadData() {
        new BrandCountRequest().post(new NetReqCallback<BrandCountRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(BrandCountRes brandCountRes, String method) {
                tv_brand_count.setText(String.format("囊括%d个全球品牌，免去选货烦恼", brandCountRes.getBrand_count()));

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectMedaiView.onActivityResult(requestCode, resultCode, data);
    }

    public void show(View view) {
        new ReplySelectPupop(this).show(getRootView());
    }

    public void setLeftTv(int leftTv) {
        tv_left.setText(String.format("已经输入%s个字", edit.getText().toString().length()));
    }


    private void postMedia() {

        UploadMediaEntity uploadMedia = null;
        try {
            uploadMedia = selectMedaiView.getUploadMediaEntity();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
            return;
        }
        if (videoReplyHolder.isHaveMediaRes())
            uploadMedia = videoReplyHolder.getUploadMediaEntity();

        if (edit.getText().toString().trim().length() < 20) {
            showSnack(R.string.range_edit_resaon);
            return;
        }
        showProgressDialog(getString(R.string.post_api_ing));

        KolReplyRequest kolReplyRequest = new KolReplyRequest().setReason(edit.getText().toString().trim());
        if (spuInfoEntity != null)
            kolReplyRequest.setAnswer_type(spuInfoEntity.getSpu_type())
                    .setModule_id(spuInfoEntity.getModule_id());

        kolReplyRequest.setTask_id(task_id)
                .setAnswer_img_video(GsonHelper.getGson().toJson(uploadMedia))
                .post(new NetReqCallback<KolReplyRes>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showSnack(message);
                        dismissProgressDialog();
                    }

                    @Override
                    public void apiRequestSuccess(KolReplyRes s, String method) {
                        showToastShort(R.string.reply_success);
                        dismissProgressDialog();
                        finish();
                        AppRxBus.getInstance().postDely(new ReplyQuestionSuccessEvent(task_id));
                        if (s.isCompleteNewBie())
                            AppRxBus.getInstance().post(new TaskCompleteEvent());

                        if (s.needShowApply())
                            JumpPageManager.getManager().goApplayKolActivity(getThis());

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectMedaiView.releseResource();
    }

}
