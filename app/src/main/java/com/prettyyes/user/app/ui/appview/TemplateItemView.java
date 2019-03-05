package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;

/**
 * Created by chengang on 2017/8/17.
 */

public class TemplateItemView extends LinearLayout {
    public TemplateItemView(Context context) {
        super(context);
    }

    public TemplateItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }


    private ImageView img_simple;
    private TextView tv_type;
    private TextView tv_desc;
    private TextView tv_range;
    private View view_line;

    // End Of Content View Elements

    private void bindViews() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_templateitem, this);

        img_simple = (ImageView) view.findViewById(R.id.img_simple);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        tv_range = (TextView) view.findViewById(R.id.tv_range);
        view_line = view.findViewById(R.id.view_line);
    }

    public ImageView getImg() {
        return img_simple;
    }

    public void initViews(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        bindViews();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TemplateItemView);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.TemplateItemView_template_icon:
                    img_simple.setImageResource(a.getResourceId(i, R.mipmap.how_simple_upload_icon));
                    break;
                case R.styleable.TemplateItemView_template_desc:
                    tv_desc.setText(a.getText(i));
                    break;
                case R.styleable.TemplateItemView_template_bottom_line_show:
                    boolean aBoolean = a.getBoolean(i, true);
                    if (aBoolean)
                        view_line.setVisibility(VISIBLE);
                    else
                        view_line.setVisibility(GONE);

                    break;
                case R.styleable.TemplateItemView_template_range:
                    tv_range.setText(a.getText(i));
                    break;
                case R.styleable.TemplateItemView_template_type:
                    tv_type.setText(a.getText(i));

                    break;

            }
        }
        a.recycle();

    }


}
