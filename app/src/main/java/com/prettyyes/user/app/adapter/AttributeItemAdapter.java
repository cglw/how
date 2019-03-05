package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.GsonHelper;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.v8.Attr;
import com.prettyyes.user.model.v8.AttrParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2017/6/28.
 */

public class AttributeItemAdapter extends AbsRecycleAdapter<AttrName> {
    public AttributeItemAdapter(Context context) {
        super(context, R.layout.item_spftcation);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final AttrName o, final int position) {
        tv_name.setText(o.getName());
        String res = "";
        for (int i = 0; i < o.getValues().size(); i++) {
            res += o.getValues().get(i).getAttr_value();
            if (i < o.getValues().size() - 1) {
                res += "/";
            }
        }

        tv_info.setText(res);

//        tv_info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String formmat = context.getString(R.string.spftcation_name)
//                        + "：\n%s" + "\n" +
//                        context.getString(R.string.spftcation_detail) + "：\n%s";
//
//
//                String showInfo = "";
//                for (int i = 0; i < o.getValues().size(); i++) {
//
//                    showInfo += o.getValues().get(i).getAttr_value() + "\n";
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage(String.format(formmat, o.getName(), showInfo));
//                builder.show();
//
//
//            }
//        });

        if (o.isSelected())
            img_select.setImageResource(R.mipmap.ic_check_red_checked);
        else
            img_select.setImageResource(R.mipmap.ic_check_red_uncheck);


        holder.getRootView().setTag(o);
        holder.getRootView().setTag(R.id.tag, img_select);
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttrName tag = (AttrName) v.getTag();
                if (!tag.isSelected() && getSelect().size() >= 2) {
                    AppUtil.showToastShort("最多可以选择两个");
                    return;
                }
                tag.setSelected(!tag.isSelected());
                ImageView img_select = (ImageView) v.getTag(R.id.tag);
                if (tag.isSelected())
                    img_select.setImageResource(R.mipmap.ic_check_red_checked);
                else
                    img_select.setImageResource(R.mipmap.ic_check_red_uncheck);


            }
        });

        img_edit.setTag(o);
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttrName tag = (AttrName) v.getTag();
                tag.setIndex(position);
                AppRxBus.getInstance().post(tag);

            }
        });


    }

    public String getJsonAttr() {
        select = getSelect();
        if (select.size() <= 0) {
            return "";
        }
        List<AttrParent> result = getAttrResult(select.size());
        return GsonHelper.getGson().toJson(result);
    }

    ArrayList<AttrName> select = new ArrayList();

    public List<AttrParent> getAttrResult(int n) {
        if (n == 1) {
            ArrayList<AttrParent> res = new ArrayList<>();

            AttrName attrName = select.get(0);
            for (int i = 0; i < attrName.getValues().size(); i++) {
                String result = attrName.getValues().get(i).getAttr_value();
                AttrParent parnet = new AttrParent();
                ArrayList<Attr> attrs = new ArrayList<>();
                Attr attr = new Attr();
                parnet.setSku_id(attrName.getSku_id());
                parnet.setSku_price(attrName.getSku_price());
                parnet.setSku_no(attrName.getSku_no());
                parnet.setSku_store(attrName.getSku_store());

                attr.setAttr_id(select.get(0).getAttr_id());
                attr.setAttr_name(select.get(0).getName());
                attr.setAttr_value(result);
                attrs.add(attr);
                parnet.setAttr(attrs);
                res.add(parnet);
            }
            return res;
        }
        List<AttrParent> stringResult = getAttrResult(n - 1);
        ArrayList<AttrParent> res = new ArrayList<>();

        for (int i = 0; i < stringResult.size(); i++) {

            AttrName attrName = select.get(n - 1);
            for (int j = 0; j < attrName.getValues().size(); j++) {
                AttrParent parnet = new AttrParent();
                Attr attr = new Attr();
                attr.setAttr_id(attrName.getAttr_id());
                attr.setAttr_name(attrName.getName());
                attr.setAttr_value(attrName.getValues().get(j).getAttr_value());
                attr.setParent_attr_id(stringResult.get(i).getAttr().get(stringResult.get(i).getAttr().size() - 1).getAttr_id());

                parnet.setSku_id(attrName.getSku_id());
                parnet.setSku_price(attrName.getSku_price());
                parnet.setSku_no(attrName.getSku_no());
                parnet.setSku_store(attrName.getSku_store());


                AttrParent attrParent = stringResult.get(i);
                List<Attr> attrs = new ArrayList<>();
                attrs.addAll(attrParent.getAttr());
                attrs.add(attr);
                parnet.setAttr(attrs);
                res.add(parnet);

            }
        }

        return res;
    }


    public ArrayList<AttrName> getSelect() {
        ArrayList<AttrName> res = new ArrayList();
        for (int i = 0; i < AttributeItemAdapter.this.getDataCount(); i++) {
            if (AttributeItemAdapter.this.getItemData(i).isSelected())
                res.add(AttributeItemAdapter.this.getItemData(i));
        }
        return res;
    }

    @Override
    public void add(AttrName data) {

        if (getSelect().size() >= 2) {
            data.setSelected(false);
        }
        super.add(data);


    }

    @Override
    public void addAll(List<AttrName> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelected())
                count++;
            if (count > 2) {
                list.get(i).setSelected(false);
            }

        }
        super.addAll(list);

    }

    @Override
    public void setItemData(int position, AttrName data) {
        if (getSelect().size() >= 2) {
            data.setSelected(false);
        }
        super.setItemData(position, data);
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        img_select = holder.getView(R.id.checkbox);
        tv_name = holder.getView(R.id.tv_name);
        tv_info = holder.getView(R.id.tv_info);
        img_edit = holder.getView(R.id.img_edit);
    }

    private ImageView img_select;
    private TextView tv_name;
    private TextView tv_info;
    private ImageView img_edit;

}
