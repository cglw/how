package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.applocal.QueEntity;
import com.prettyyes.user.model.v8.QuestionEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/7/10.
 */

public class NewQuestionView extends AbsRelativelayoutView<QueEntity> {


    @ViewInject(R.id.view_name)
    View view_name;
    @ViewInject(R.id.tv_name)
    TextView tv_name;
    @ViewInject(R.id.img_que)
    ImageView img_que;
    @ViewInject(R.id.tv_more)
    TextView tv_more;
    @ViewInject(R.id.tv_que)
    QuestionTextView tv_que;

    public QuestionTextView getTv_que() {
        return tv_que;
    }

    public NewQuestionView(Context context) {
        super(context);
    }

    public NewQuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int bindLayout() {
        return R.layout.view_question_new;
    }

    @Override
    public void initViews() {

    }


    @Override
    public void setDataByModel(final QueEntity data) {
        tv_name.setText(data.getName());
        tv_que.setTextWithData(new QuestionTextView.QuestionInnerModel()
                .setDesc(data.getQuestion())
                .setHash_tag(data.getHash_tag())
                .setTopic_hash_tag(data.getTopic_hash_tag())
                .setTask_act_id(data.getTask_act_id())
                .setTopic_id(data.getTopic_id()));

        tv_more.setVisibility(GONE);
        img_que.setVisibility(GONE);
        if (data.getQue_imgs() != null && data.getQue_imgs().size() > 0) {
            img_que.setVisibility(VISIBLE);
            if (data.getQue_imgs().size() > 1)
                tv_more.setVisibility(VISIBLE);
            ImageLoadUtils.loadListimg(getActivity(), data.getQue_imgs().get(0), img_que);
            img_que.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goBigImgActivity(getActivity(), data.getQue_imgs(), 0);
                }
            });
        }

    }

    public void setData(QuestionEntity data, final ArrayList<String> imgs) {
        view_name.setVisibility(GONE);
        tv_more.setVisibility(GONE);
        img_que.setVisibility(GONE);
        tv_que.setTextWithData(new QuestionTextView.QuestionInnerModel()
                .setDesc(data.getDesc())
                .setHash_tag(data.getHash_tag())
                .setTopic_hash_tag(data.getTopic_hash_tag())
                .setTask_act_id(data.getTask_act_id())
                .setTopic_id(data.getTopic_id()));

        if (imgs != null && imgs.size() > 0) {
            img_que.setVisibility(VISIBLE);
            if (imgs.size() > 1)
                tv_more.setVisibility(VISIBLE);
            else
                tv_more.setVisibility(GONE);
            ImageLoadUtils.loadListimg(getActivity(), imgs.get(0), img_que);
            img_que.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpPageManager.getManager().goBigImgActivity(getActivity(), imgs, 0);
                }
            });
        }
    }


}
