package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.task.TaskHomeTask;
import com.prettyyes.user.model.v8.AnswerDataBean;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/11/23
 * Description: Nothing
 */
public class PaperView extends RelativeLayout {
    private static final String TAG = "PaperView";
    private TextView tv_paper_title;
    private TextView tv_paper_desc;
    private ImageView img_paper_covery;
    private TextView tv_paper_iscanread;
    private RelativeLayout rel_common_paper;
    private int LayoutId;
    private Context context;


    public PaperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public PaperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.item_mianlayout_typepaper, this);
        tv_paper_title = (TextView) view.findViewById(R.id.tv_paper_title);
        tv_paper_desc = (TextView) view.findViewById(R.id.tv_paper_desc);
        img_paper_covery = (ImageView) view.findViewById(R.id.img_covery);
        tv_paper_iscanread = (TextView) view.findViewById(R.id.tv_paper_iscanread);
        rel_common_paper = (RelativeLayout) view.findViewById(R.id.rel_comme_typepaper);
    }

    public void setDataByHomeTask(final TaskHomeTask data) {
        AnswerDataBean dataEntity = data.getAnswer_info().getAnswer_data();
        tv_paper_title.setText(dataEntity.getSku_name());
        tv_paper_desc.setText(data.getAnswer_info().getAnswer_data().getDesc());
        ImageLoadUtils.loadListimg(getContext(), dataEntity.getBrand_image(), img_paper_covery);
        tv_paper_iscanread.setVisibility(VISIBLE);

        rel_common_paper.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(getContext(), TYPE_PAPER, data.getAnswer_info().getAnswer_data().getMoudle_id());

            }
        });
    }


    public void setAllData(String title, final String simpledesc, String img, final int sku_id) {
        tv_paper_title.setText(title);
        tv_paper_desc.setText(simpledesc);
        ImageLoadUtils.loadListimg(getContext(), img, img_paper_covery);
        tv_paper_iscanread.setVisibility(VISIBLE);
        rel_common_paper.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goSkuActivity(getContext(), TYPE_PAPER, sku_id + "");
//                PaperWebviewActivity.goPaperActivity((BaseActivity) getContext(), sku_id);
            }

        });
    }

    public void setSimpleData(String title, final String simpledesc, String img) {
        tv_paper_title.setText(title);
        tv_paper_desc.setText(simpledesc);
        ImageLoadUtils.loadListimg(getContext(), img, img_paper_covery);
    }

    public void setLayoutId(int layoutId) {
        LayoutId = layoutId;
    }
}
