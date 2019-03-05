package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.DensityUtil;


/**
 * Created by Cg on 15/10/10.
 * 我的页面item项
 */
public class MineItemView extends CoordinatorLayout {
    private RelativeLayout lin_click;
    private ImageView img_icon;
    private TextView tv_title, tv_desc;
    private AttributeSet attrs;


    public MineItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        initView(context);
    }

    public MineItemView(Context context) {

        super(context);
        initView(context);
    }

    public MineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_mine_list, this);
        lin_click = (RelativeLayout) view.findViewById(R.id.lin_mineItem_click);
        img_icon = (ImageView) view.findViewById(R.id.img_mineItem_icon);
        tv_title = (TextView) view.findViewById(R.id.tv_mineItem_title);
        tv_desc = (TextView) view.findViewById(R.id.tv_mineItem_desc);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mineItem);
        tv_title.setText(a.getString(R.styleable.mineItem_itemtitle));
        tv_desc.setText(a.getString(R.styleable.mineItem_itemdesc));
        img_icon.setImageResource(a.getResourceId(R.styleable.mineItem_itemicon, R.mipmap.ic_question));
        ViewGroup.LayoutParams layoutParams = lin_click.getLayoutParams();
        layoutParams.height = (int) a.getDimension(R.styleable.mineItem_itemHeight, DensityUtil.dip2px(100));
        int type = a.getInt(R.styleable.mineItem_itemDescVisiable, 0);
        switch (type) {
            case 0:
                tv_desc.setVisibility(VISIBLE);
                break;
            case 1:
                tv_desc.setVisibility(INVISIBLE);
                break;
            case 2:
                tv_desc.setVisibility(GONE);
                break;
        }
        a.recycle();

    }

    public View getItemView()
    {
        return lin_click;
    }



    public interface ItemClickInterface {
        void onItemClick(View view);
    }


}
