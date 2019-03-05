package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.SpudelRequest;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.spu.BrandSpuListActivity;
import com.prettyyes.user.app.view.dialog.MyBottomDialog;
import com.prettyyes.user.core.DialogHelper;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.ListChangeEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;

import static com.prettyyes.user.app.account.Constant.TYPE_BRAND;
import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

/**
 * Created by chengang on 2017/6/29.
 */

public class SpuListAdapter extends AbsRecycleAdapter<SpuInfoEntity> {
    ArrayList<String> bottom_item;
    private int coloum = 2;

    private boolean isShowSelect = false;


    public SpuListAdapter(Context context) {
        super(context, R.layout.item_spu_manager);
//        with = (BaseApplication.ScreenWidth - AppUtil.dip2px(16) * 2 - AppUtil.dip2px(16)) / 2;
        bottom_item = new ArrayList<>();

        isShowSelect = ZBundleCore.getInstance().isExistKolReplay();
        if (isShowSelect)
            bottom_item.add("选择");
        if (ZBundleCore.getInstance().isTopActivity(BrandSpuListActivity.class)) {

        } else {
            bottom_item.add("编辑");
            bottom_item.add("删除");
        }
        bottom_item.add("查看");

        bottom_item.add("取消");
    }

    public SpuListAdapter(Context context, int layout) {
        super(context, layout);

    }

    private int with = 0;


    @Override
    protected void bindData(AbsRecycleViewHolder holder, SpuInfoEntity data, final int data_position) {

        LogUtil.i(TAG, data.getSpu_name() + "__>");
        tv_price.setText(StringUtils.getPrice(data.getSpu_price()));
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_covery);
        tv_name.setText(data.getSpu_name());
        holder.getRootView().setTag(data);


        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.getRootView().getLayoutParams();
        if ((data_position + 1) % 2 == 0) {
            layoutParams.setMargins(0, AppUtil.dip2px(8), 0, 0);
        } else {
            layoutParams.setMargins(0, AppUtil.dip2px(8), AppUtil.dip2px(8), 0);


        }
        if (data.getSpu_type() == null || tv_type == null)
            return;
        switch (data.getSpu_type()) {
            case TYPE_PAPER:
                tv_type.setText("内容");
                break;
            case TYPE_UNIT:
                tv_type.setText("极简");
                break;
            case TYPE_SUIT:
                tv_type.setText("多物");
                break;
            case TYPE_TAOBAO:
                tv_type.setText("极速");
                break;
            case TYPE_BRAND:
                tv_type.setText("品牌");
                break;
        }

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setFocusable(true);
                v.requestFocus();
                v.setFocusableInTouchMode(true);
                if (bottom_item == null || bottom_item.size() <= 0)
                    return;
                final SpuInfoEntity data = (SpuInfoEntity) v.getTag();
                final MyBottomDialog myBottomDialog = new MyBottomDialog(context);
                myBottomDialog.show();
                myBottomDialog.getBottomDialogAdapter().addAll(bottom_item);

                myBottomDialog.getBottomDialogAdapter().setMyOnItemClickListener(new OnMyItemClickListener<String>() {
                    @Override
                    public void clickItem(AbsRecycleViewHolder holder, View view, String o, int position) {
                        myBottomDialog.dismiss();
                        if (o.equals("编辑")) {
                            JumpPageManager.getManager().goTemplateActivity(context, data.getModule_id(), data.getSpu_type());
                        } else if (o.equals("删除")) {
                            deleteItem(data_position);
                        } else if (o.equals("查看")) {
                            JumpPageManager.getManager().goSkuActivity(context, data.getSpu_type(), data.getModule_id());
                        } else if (o.equals("选择")) {
                            AppRxBus.getInstance().post(new AddTemplateOrSelectSuccessEvent(data.getModule_id(), data.getSpu_type()));
                            JumpPageManager.getManager().goKolReplyActivity(context);

                        }


                    }
                });

            }
        });


    }

    private void deleteItem(final int position) {
        DialogHelper.getInstance().getDialogNoCancel("确认删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SpuInfoEntity itemData = SpuListAdapter.this.getItemData(position);
                new SpudelRequest().setModule_id(itemData.getModule_id()).setSpu_type(itemData.getSpu_type()).post(new NetReqCallback<Object>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);
                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        SpuListAdapter.this.remove(position);
                        AppUtil.showToastShort(context.getString(R.string.success_del));
                        AppRxBus.getInstance().post(new ListChangeEvent());
                    }
                });
            }
        }).show();

    }


    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_price = holder.getView(R.id.tv_price);
        tv_name = holder.getView(R.id.tv_name);
        img_covery = holder.getView(R.id.img_covery);
        tv_type = holder.getView(R.id.tv_type);
    }

    private TextView tv_price;
    private TextView tv_type;
    private TextView tv_name;
    private ImageView img_covery;
}
