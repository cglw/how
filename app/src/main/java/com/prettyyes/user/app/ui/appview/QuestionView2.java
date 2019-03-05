package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.base.BaseApplication;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.common.ViewPagerActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.v8.QuestionEntity;

import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.appview
 * Author: SmileChen
 * Created on: 2016/12/21
 * Description: Nothing
 */
public class QuestionView2 extends AbsLinearlayoutView {
    public QuestionView2(Context context) {
        super(context);
    }

    public QuestionView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuestionView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ImageView img_question;
    private TextView tv_name;
    private QuestionTextView tv_question;
    private TextView tv_time;
    private TextView tv_share;
    private TextView tv_more;
    private LinearLayout lin_question_base;


    @Override
    public int bindLayout() {
        return R.layout.view_question;
    }

    private int task_id = 0;

    @Override
    public void initViews() {
        tv_name = (TextView) getView(R.id.tv_questionView2_sendNickname);
        tv_question = (QuestionTextView) getView(R.id.tv_questionView2_question);
        tv_time = (TextView) getView(R.id.tv_questionView2_time);
        tv_share = (TextView) getView(R.id.tv_questionView2_share);
        img_question = (ImageView) getView(R.id.img_questionView2);
        tv_more = (TextView) getView(R.id.tv_questionView2_moreimg);
        lin_question_base = (LinearLayout) getView(R.id.lin_question_base);
    }


    @Override
    public void setDataByModel(Object data) {

    }

    @Override
    public void setListener() {

    }


    public void setData(final QuestionEntity data) {
        tv_name.setText(data.getNickname());
        tv_question.setText(data.getDesc());
        tv_time.setText("Just Now");
        handlerImg(data.getPic_list());
        tv_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (BaseApplication.UUID == null) {
                    RegisterActivity.getRegister(getActivity());
                    return;
                }
                ShareModel share_model = data.getShare_model();
                share_model.setType("task");
                share_model.setTask_id("task");
                share_model.setType_id(data.getTask_id());
                JumpPageManager.getManager().goShareActivity(getActivity(), share_model);
            }
        });


        tv_question.setTextWithData(new QuestionTextView.QuestionInnerModel()
                .setDesc(data.getDesc())
                .setHash_tag(data.getHash_tag())
                .setTopic_hash_tag(data.getTopic_hash_tag())
                .setTask_act_id(data.getTask_act_id())
                .setTopic_id(data.getTopic_id())
        );


    }


    @Override
    public void setData(String... data) {

    }

    private void handlerImg(String value) {
        tv_more.setVisibility(GONE);
        final String[] dataArray;
        if (TextUtils.isEmpty(value)) {
            ((View) img_question.getParent()).setVisibility(GONE);
            return;
        }
        if (!value.endsWith(";")) {
            dataArray = StringUtils.convertStrToArray(value + ";");
        } else {
            dataArray = StringUtils.convertStrToArray(value);
        }
        if (dataArray.length > 0) {
            ((View) img_question.getParent()).setVisibility(VISIBLE);
            ImageLoadUtils.loadListimg(getContext(), dataArray[0], img_question);
            try {
                final ArrayList a = new ArrayList();
                for (int i = 0; i < dataArray.length; i++) {
                    a.add(dataArray[i]);
                }


                if (dataArray.length > 1) {
                    tv_more.setVisibility(VISIBLE);

                }
                img_question.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewPagerActivity.goVpActivity(getContext(), 0, a, "question");
                    }
                });


            } catch (Exception ee) {
            }


        }
    }

    public String getQuestionDesc() {
        return tv_question.getText().toString() + "";
    }


}
