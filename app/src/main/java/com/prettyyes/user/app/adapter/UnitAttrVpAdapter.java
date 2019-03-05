package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.ui.appview.GoodsAttrListView;
import com.prettyyes.user.app.ui.spu.GoosInfoActivity;
import com.prettyyes.user.app.view.pupopwindow.AbsPupopWindow;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.v8.AttrStr;
import com.prettyyes.user.model.v8.SpuInfoEntity;
import com.prettyyes.user.model.v8.UnitUpload;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;

/**
 * Created by chengang on 2017/7/3.
 */

public class UnitAttrVpAdapter extends AbsVpAdapter<SpuInfoEntity> {


    public UnitAttrVpAdapter(Context context, ArrayList<SpuInfoEntity> unitDetailBeanArrayList) {
        super(context, unitDetailBeanArrayList, R.layout.item_vp_unitattr_select);
    }

    @Override
    public void bindView(ViewHolder vHolder) {
        goodsAttrListView = (GoodsAttrListView) vHolder.getView(R.id.GoodsAttrListView);
        mImg_covery = (ImageView) vHolder.getView(R.id.img_covery);
        mTv_name = (TextView) vHolder.getView(R.id.tv_name);
        mTv_price = (TextView) vHolder.getView(R.id.tv_price);
        mTv_express_price = (TextView) vHolder.getView(R.id.tv_express_price);
        lin_select_num = (LinearLayout) vHolder.getView(R.id.lin_select_num);
        img_close = (ImageView) vHolder.getView(R.id.img_close);
        btn_add = (Button) vHolder.getView(R.id.btn_add);
        img_left = (ImageView) vHolder.getView(R.id.img_left);
        img_right = (ImageView) vHolder.getView(R.id.img_right);
    }

    @Override
    public void showData(ViewHolder vHolder, final SpuInfoEntity data, int position) {

        if (getCount() == 1) {
            img_left.setColorFilter(ContextCompat.getColor(context, R.color.grey));
            img_right.setColorFilter(ContextCompat.getColor(context, R.color.grey));

        } else if (position == 0) {
            img_left.setColorFilter(ContextCompat.getColor(context, R.color.grey));
            img_right.setColorFilter(ContextCompat.getColor(context, R.color.black));

        } else if (position == getCount() - 1) {
            img_left.setColorFilter(ContextCompat.getColor(context, R.color.black));
            img_right.setColorFilter(ContextCompat.getColor(context, R.color.grey));
        } else {
            img_left.setColorFilter(ContextCompat.getColor(context, R.color.black));
            img_right.setColorFilter(ContextCompat.getColor(context, R.color.black));
        }

        goodsAttrListView.setAttrNames((ArrayList<AttrName>) data.getAttrNames());
        ImageLoadUtils.loadListimg(context, data.getMain_img(), mImg_covery);
        mTv_price.setText(StringUtils.getPrice(data.getSpu_price()));
        mTv_name.setText(data.getSpu_name());
        mTv_express_price.setText(String.format(context.getString(R.string.fmt_express_price), data.getExpress_price()));

        mImg_covery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(context, data.getMain_img());
            }
        });

        TextView tv_remove = (TextView) lin_select_num.getChildAt(0);
        TextView tv_num = (TextView) lin_select_num.getChildAt(1);
        TextView tv_add = (TextView) lin_select_num.getChildAt(2);
        tv_num.setText(data.getNum() + "");
        tv_remove.setTag(data);
        tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) v.getParent();
                TextView childAt = (TextView) linearLayout.getChildAt(1);
                SpuInfoEntity tag = (SpuInfoEntity) v.getTag();
                if (tag.getNum() > 1) {
                    tag.setNum(tag.getNum() - 1);
                    childAt.setText(tag.getNum() + "");
                }
            }
        });
        tv_add.setTag(data);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) v.getParent();
                TextView childAt = (TextView) linearLayout.getChildAt(1);
                SpuInfoEntity tag = (SpuInfoEntity) v.getTag();
                tag.setNum(tag.getNum() + 1);
                childAt.setText(tag.getNum() + "");
            }
        });
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (absPupopWindow != null)
                    absPupopWindow.dismiss();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkAllAttrselect()) {
                    AppUtil.showToastShort("属性没有选择完整");
                    return;
                }

                if (((GoosInfoActivity) context).spuInfoEntity != null
                        && ((GoosInfoActivity) context).spuInfoEntity.getSpu_type() != null &&
                        ((GoosInfoActivity) context).spuInfoEntity.getSpu_type().equals(TYPE_SUIT)) {
                    List<UnitUpload> unitUpload = getUnitUpload();
                    String unit_detail = GsonHelper.getGson().toJson(unitUpload).toString();
                    ((GoosInfoActivity) context).post("", unit_detail, 1);

                } else {
                    ((GoosInfoActivity) context).post(getSku_id(data.getAttrNames(), data.getAttr_str_list()), "", getSingleNum());

                }
                if (absPupopWindow != null)
                    absPupopWindow.dismiss();
            }
        });

    }


    public List<UnitUpload> getUnitUpload() {

        List<UnitUpload> datas = new ArrayList<>();
        for (int i = 0; i < this.getCount(); i++) {
            UnitUpload unit = new UnitUpload();
            unit.setUnit_id(this.get(i).getModule_id());
            unit.setNum(this.get(i).getNum());
            if (this.get(i).getAttrNames() != null && this.get(i).getAttrNames().size() > 0) {
                String sku_id = getSku_id(this.get(i).getAttrNames(), this.get(i).getAttr_str_list());
                LogUtil.i(TAG, "sku_id" + sku_id);
                unit.setSku_id(sku_id);
            } else {
                unit.setSku_id(this.get(i).getAttr_json().get(0).getSku_id());
            }
            datas.add(unit);
            LogUtil.i(TAG, datas.size() + "size");

        }
        return datas;
    }

    private String getSku_id(List<AttrName> attrnames, List<AttrStr> attr_str_list) {
        String res = "";
        if (attrnames.size() <= 0)
            return "";
        for (int j = 0; j < attrnames.size(); j++) {
            String attr_id = attrnames.get(j).getAttr_id();
            res += attr_id + ":";
            String value_id = "";
            if (attrnames.get(j).getSelectValue() != null) {
                value_id = attrnames.get(j).getSelectValue().getAttr_value_id();
                res += value_id + ";";
            }

        }
        LogUtil.i(TAG, "attrnames" + attrnames.toString());
        LogUtil.i(TAG, "res" + res);

        res = res.substring(0, res.length() - 1);
        for (int i = 0; i < attr_str_list.size(); i++) {
            if (attr_str_list.get(i).getSku_attr_str().equals(res)) {
                return attr_str_list.get(i).getSku_id();
            }
        }


        return "";

    }

    public int getSingleNum() {

        if (this.getCount() <= 0)
            return 1;
        return this.get(0).getNum();
    }

    private boolean checkAllAttrselect() {
        for (int i = 0; i < this.getCount(); i++) {
            if (this.get(i).getAttrNames() != null) {
                for (int j = 0; j < this.get(i).getAttrNames().size(); j++) {
                    if (!this.get(i).getAttrNames().get(j).isHaveSlectOne()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private AbsPupopWindow absPupopWindow;

    public void setAbsPupopWindow(AbsPupopWindow absPupopWindow) {
        this.absPupopWindow = absPupopWindow;
    }

    private Button btn_add;
    private GoodsAttrListView goodsAttrListView;
    private ImageView mImg_covery;
    private TextView mTv_name;
    private TextView mTv_price;
    private TextView mTv_express_price;
    private LinearLayout lin_select_num;
    private ImageView img_close;
    public ImageView img_left;
    public ImageView img_right;

}
