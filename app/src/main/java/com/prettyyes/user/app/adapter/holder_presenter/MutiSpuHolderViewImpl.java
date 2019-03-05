package com.prettyyes.user.app.adapter.holder_presenter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.app.view.app.TypefaceTextView;
import com.prettyyes.user.app.view.imageview.RoundImageView;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import java.util.ArrayList;
import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

/**
 * Created by chengang on 2017/8/10.
 */

public class MutiSpuHolderViewImpl extends BaseHolderViewImpl<SpuInfoEntity> implements MutiSpuHolderView {


    public MutiSpuHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder, int position, SpuInfoEntity data) {
        super(mutiTypeViewHolder, position, data);
    }

    public MutiSpuHolderViewImpl(MutiTypeViewHolder mutiTypeViewHolder) {
        super(mutiTypeViewHolder);
    }


    public LinearLayout ll_units;
    public RoundImageView img_one;
    public TextView tv_one_price;
    public TextView tv_one_name;
    public RoundImageView img_two;
    public TextView tv_two_price;
    public TextView tv_two_name;
    public TextView rel_look_detail;
    public ImageView img_suit_covery;
    public TextView tv_suit_name;
    public TextView tv_suit_price;

    @Override
    public MutiSpuHolderView bindSuit() {
        ll_units = getView(R.id.ll_units);
        img_one = getView(R.id.img_one);
        tv_one_price = getView(R.id.tv_one_price);
        tv_one_name = getView(R.id.tv_one_name);
        img_two = getView(R.id.img_two);
        tv_two_price = getView(R.id.tv_two_price);
        tv_two_name = getView(R.id.tv_two_name);
        rel_look_detail = getView(R.id.rel_look_detail);
        img_suit_covery = getView(R.id.img_suit_covery);
        tv_suit_name = getView(R.id.tv_suit_name);
        tv_suit_price = getView(R.id.tv_suit_price);
        setSuitListener();
        setSuitData(data);
        return this;

    }

    public void setSuitListener() {
        rel_look_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSpuDetailActivity();


            }
        });
        view_suit_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSpuDetailActivity();

            }
        });
        List<SpuInfoEntity> units = data.getUnits();
        final ArrayList<String> imgs = new ArrayList<>();
        if (units.size() > 0)
            for (int i = 0; i < units.size(); i++) {
                imgs.add(units.get(i).getMain_img());
            }
        img_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(context, imgs, 0);

            }
        });
        img_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(context, imgs, 1);

            }
        });


    }

    public void setSuitData(SpuInfoEntity data) {
        ImageLoadUtils.loadListimg(getContext(), data.getMain_img(), img_suit_covery);
        tv_suit_name.setText(data.getSpu_name());
        tv_suit_price.setText(String.format("搭配购买价：¥%s", data.getSpu_price()));

        List<SpuInfoEntity> unit_list = data.getUnits();

//        unit_list = data.getUnit_list();
//        if (data.getUnit_detail() != null && data.getUnit_detail().size() > 0) {
//            unit_list = data.getUnit_detail();
//        }
        if (unit_list == null || unit_list.size() == 0) {
            ll_units.setVisibility(View.GONE);
            return;
        } else {
            ll_units.setVisibility(View.VISIBLE);

        }


        tv_one_name.setText(unit_list.get(0).getSpu_name());
        tv_one_price.setText(unit_list.get(0).getSpu_price());
        ImageLoadUtils.loadListimg(getContext(), unit_list.get(0).getMain_img(), img_one);

        tv_two_name.setText(unit_list.get(1).getSpu_name());
        tv_two_price.setText(unit_list.get(1).getSpu_price());
        ImageLoadUtils.loadListimg(context, unit_list.get(1).getMain_img(), img_two);


    }


    //unit
//    private RoundImageView img_viewimg_src;
//    private RelativeLayout view_viewimg_translate;
//    private TextView tv_viewimg_price;
//    private TextView tv_viewimg_desc;

    @Override
    public MutiSpuHolderView bindUnit() {
//        img_viewimg_src = getView(R.id.img_viewimg_src);
//        view_viewimg_translate = getView(R.id.view_viewimg_translate);
//        tv_viewimg_price = getView(R.id.tv_viewimg_price);
//        tv_viewimg_desc = getView(R.id.tv_viewimg_desc);

        setUnitData(data);
        return this;
    }


    List<String> imgs;
    int max_size = 0;

    public void setUnitData(final SpuInfoEntity data) {
        imgs = data.getShowImg();
//        if (data.getImg_arr() == null || data.getImg_arr().size() <= 0) {
//            imgs.add(data.getMain_img());
//        } else
//            imgs.addAll(data.getImg_arr());

        max_size = imgs.size();
        if (max_size > 3)
            max_size = 3;
        for (int i = 0; i < view_unit_root.getChildCount(); i++) {
            final RelativeLayout childAt = (RelativeLayout) view_unit_root.getChildAt(i);
            ViewGroup buy = (ViewGroup) childAt.getChildAt(1);
            ImageView img = (ImageView) childAt.getChildAt(0);
            TextView price = (TextView) buy.getChildAt(0);
            TextView desc = (TextView) buy.getChildAt(1);
            if (i == max_size - 1) {
                buy.setVisibility(View.VISIBLE);
            } else {
                buy.setVisibility(View.GONE);
            }

            if (i > max_size - 1) {
                childAt.setVisibility(View.GONE);
            } else {
                childAt.setVisibility(View.VISIBLE);
                desc.setText(data.getSpu_name());
                price.setText(StringUtils.getPrice(data.getSpu_price()));
                ImageLoadUtils.loadListimg(getContext(), imgs.get(i), img);
            }


            childAt.setTag(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean showbg = ((RelativeLayout) v).getChildAt(1).getVisibility() == View.VISIBLE;
                    if (showbg) {
                        goSpuDetailActivity();
                    } else
                        JumpPageManager.getManager().goBigImgActivity(getContext(), (ArrayList<String>) imgs, (Integer) v.getTag());

                }
            });

        }

        for (int i = 0; i < max_size - 1; i++) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view_unit_root.getChildAt(i).getLayoutParams();
            layoutParams.setMargins(0, 0, AppUtil.dip2px(8), 0);
        }

    }


    //paper
    private ImageView img_paper_covery;
    private TextView tv_paper_name;
    private TextView tv_paper_desc;

    @Override
    public MutiSpuHolderView bindPaper() {
        img_paper_covery = getView(R.id.img_paper_covery);
        tv_paper_name = getView(R.id.tv_paper_name);
        tv_paper_desc = getView(R.id.tv_paper_desc);
        setPaperData();
        return this;
    }

    public void setPaperData() {
        ImageLoadUtils.loadListimg(getContext(), data.getMain_img(), img_paper_covery);
        tv_paper_name.setText(data.getSpu_name());
        tv_paper_desc.setText(data.getSpu_desc());
        view_paper_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goSpuDetailActivity();

            }
        });
    }


    @Override
    public MutiSpuHolderView bindBrand() {
        return this;


    }

    private ImageView img_taobao_covey;
    private TextView tv_taobao_name;
    private TypefaceTextView tv_taobao_price;

    @Override
    public MutiSpuHolderView bindTaobao() {
        img_taobao_covey = getView(R.id.img_taobao_covey);
        tv_taobao_name = getView(R.id.tv_taobao_name);
        tv_taobao_price = getView(R.id.tv_taobao_price);
        setTaobaoData();
        return this;

    }

    public void setTaobaoData() {
        ImageLoadUtils.loadListimg(getContext(), data.getMain_img(), img_taobao_covey);
        tv_taobao_name.setText(data.getSpu_name());
        tv_taobao_price.setText(StringUtils.getPrice(data.getSpu_price()));
        view_taobao_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSpuDetailActivity();
            }
        });
    }


    private ViewGroup fm_mutispu;
    private RelativeLayout view_suit_root;
    private LinearLayout view_unit_root;
    private RelativeLayout view_paper_root;
    private RelativeLayout view_taobao_root;
    private View view_mutispu_root;

    @Override
    public MutiSpuHolderView bindMutiSpu() {
        view_mutispu_root = getView(R.id.view_mutispu_root);
        fm_mutispu = getView(R.id.fm_mutispu);
        view_suit_root = getView(R.id.view_suit_root);
        view_unit_root = getView(R.id.view_unit_root);
        view_paper_root = getView(R.id.view_paper_root);
        view_taobao_root = getView(R.id.view_taobao_root);

        for (int i = 0; i < fm_mutispu.getChildCount(); i++) {
            LogUtil.i(TAG, "child" + fm_mutispu.getChildAt(i));
            fm_mutispu.getChildAt(i).setVisibility(View.GONE);
        }
        LogUtil.i(TAG, "view_mutispu_root" + view_mutispu_root);


        String spu_type = data.getSpu_type();
        if (spu_type == null) {
            view_mutispu_root.setVisibility(View.GONE);
            return this;
        } else {
            view_mutispu_root.setVisibility(View.VISIBLE);
        }
        bindShareSpu();


        switch (spu_type) {
            case TYPE_PAPER:
                bindPaper();
                view_paper_root.setVisibility(View.VISIBLE);
                view_paper_root = getView(R.id.view_paper_root);
                break;
            case TYPE_SUIT:

//                if (data.getUnits().size() == 1) {
//                    bindUnit();
//                    view_unit_root.setVisibility(View.VISIBLE);
//                    return this;
//                }
//                bindSuit();
//                view_suit_root.setVisibility(View.VISIBLE);
//                view_suit_root = getView(R.id.view_suit_root);
//                break;

            case TYPE_UNIT:
//                bindUnit();
//                view_unit_root.setVisibility(View.VISIBLE);
//                view_unit_root = getView(R.id.view_unit_root);
//                break;
            case TYPE_TAOBAO:
                bindTaobao();
                view_taobao_root.setVisibility(View.VISIBLE);
                view_taobao_root = getView(R.id.view_taobao_root);
                break;
            default:
                bindTaobao();
                view_taobao_root.setVisibility(View.VISIBLE);
                view_taobao_root = getView(R.id.view_taobao_root);


        }


        bindBrand();
        return this;
    }

    private View view_share_brand;
    private TextView tv_shop_name;
    private TextView tv_shop_category;

    @Override
    public MutiSpuHolderView bindShareSpu() {
        view_share_brand = getView(R.id.view_share_brand);
        tv_shop_name = getView(R.id.tv_shop_name);
        tv_shop_category = getView(R.id.tv_shop_category);
        bindShareSpuData();

        return this;
    }

    @Override
    public MutiSpuHolderView bindShareSpuData() {

        if (data.getShare_status() != null && data.getShare_status().equals("1")) {
            view_share_brand.setVisibility(View.VISIBLE);
            if (data.getBrand_name() == null || data.getBrand_name().length() <= 0) {
                view_share_brand.setVisibility(View.GONE);
                return this;
            }
            tv_shop_name.setText(data.getBrand_name());
            tv_shop_category.setText(data.getCategory_name());
        } else
            view_share_brand.setVisibility(View.GONE);
        return this;
    }

    @Override
    public MutiSpuHolderView goSpuDetailActivity() {
        if (data != null) {
            AppStatistics.onEventCommon(context, data.getSpu_type() + ";" + data.getModule_id());
            JumpPageManager.getManager().goSkuActivity(getContext(), data.getSpu_type(), data.getModule_id());
        }
        return null;
    }
}
