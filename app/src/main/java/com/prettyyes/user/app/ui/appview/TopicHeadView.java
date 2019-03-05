package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.TaskImpl;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.ui.RegisterActivity;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.ui.how.TopicActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.TopicEntityRes;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/5/25.
 */

public class TopicHeadView extends AbsLinearlayoutView<TopicEntityRes.InfoBean> {

    public TopicHeadView(Context context) {
        super(context);
    }

    public TopicHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_head_subject;
    }

    @Override
    public void initViews() {
        activity_subject = (RelativeLayout) getView(R.id.activity_subject);
        img_covery = (ImageView) getView(R.id.img_covery);
        lin_left = (LinearLayout) getView(R.id.lin_left);
        tv_topic_title = (TextView) getView(R.id.tv_topic_title);
        tv_topic_topic_num = (TextView) getView(R.id.tv_topic_topic_num);
        tv_topic_person_num = (TextView) getView(R.id.tv_topic_person_num);
        tv_topic_word_num = (TextView) getView(R.id.tv_topic_word_num);
        tv_topic_desc_good = (TextView) getView(R.id.tv_topic_desc_good);
        tv_topic_last = (TextView) getView(R.id.tv_topic_last);
        tv_topic_ask = (TextView) getView(R.id.tv_topic_ask);
        tv_topic_collect = (TextView) getView(R.id.tv_topic_collect);
        tv_topic_share = (TextView) getView(R.id.tv_topic_share);
    }


    @Override
    public void setDataByModel(final TopicEntityRes.InfoBean data) {
        int color = Color.parseColor("#FDB4A2");

        try {
            color = Color.parseColor(data.getTopic_bg_color());
        } catch (Exception ee) {

        }
        activity_subject.setBackgroundColor(color);

        tv_topic_title.setText(data.getTopic_name());
        ImageLoadUtils.loadListimgNodefault(getContext(), data.getCovery_img(), img_covery);
//        tv_topic_topic_num.setText(data.getTopic_desc());
//        tv_topic_person_num.setText(data.getAsker_desc());
//        tv_topic_word_num.setText(data.getReply_desc());

        addStyleSpan(tv_topic_topic_num, data.getTopic_desc());
        addStyleSpan(tv_topic_person_num, data.getAsker_desc());
        addStyleSpan(tv_topic_word_num, data.getReply_desc());

        tv_topic_desc_good.setText(data.getBook_desc());
        tv_topic_last.setText(data.getBook_slogan());
        img_covery.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goBigImgActivity(getActivity(), data.getCovery_img());
            }
        });
        tv_topic_collect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((BaseActivity) getActivity()).getUUId() == null) {
                    RegisterActivity.getRegister(getActivity());
                    return;
                }


                if (((TextView) v).getText().toString().equals("+ 关注")) {
                    is_like = 1;
                } else {
                    is_like = 0;
                }

                new TaskImpl().followTopic(((BaseActivity) getActivity()).getUUId(), data.getTopic_id(), is_like, new NetReqCallback() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        AppUtil.showToastShort(message);

                    }

                    @Override
                    public void apiRequestSuccess(Object o, String method) {
                        if (is_like == 0) {
                            AppUtil.showToastShort("取消关注成功");
                            tv_topic_collect.setText("+ 关注");
                        } else {
                            AppUtil.showToastShort("关注成功");
                            tv_topic_collect.setText("已关注");
                        }


                    }
                });
            }
        });

        tv_topic_ask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setTopic_id(data.getTopic_id() + "");
                questionEntity.setTopic_hash_tag(data.getTopic_hash_tag());
                AskActivity.goAskActivity(getContext(), questionEntity, new ArrayList<SellerInfoEntity>());
            }
        });
        if (data.getIs_follow().equals("1")) {
            tv_topic_collect.setText("已关注");
        } else {
            tv_topic_collect.setText("+ 关注");

        }


    }

    @Override
    public void setListener() {
        super.setListener();

        tv_topic_share.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TopicActivity) getActivity()).share();
            }
        });

    }

    int is_like = 0;
    private RelativeLayout activity_subject;
    private LinearLayout lin_left;
    private TextView tv_topic_title;
    private TextView tv_topic_topic_num;
    private TextView tv_topic_person_num;
    private TextView tv_topic_word_num;
    private TextView tv_topic_desc_good;
    private TextView tv_topic_last;
    private TextView tv_topic_ask;
    private TextView tv_topic_collect;
    private TextView tv_topic_share;
    private ImageView img_covery;

    private void addStyleSpan(TextView tv, String str) {
        tv.setText("");
        SpannableString spanString = new SpannableString(str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
                spanString.setSpan(span, i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }
        tv.append(spanString);


    }

}
