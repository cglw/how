package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ClickUtils;

/**
 * Created by chengang on 2017/5/26.
 */

public class QuestionTextView extends AppCompatTextView {

    public QuestionTextView(Context context) {
        super(context);
    }

    public QuestionTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuestionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setTextWithData(final QuestionInnerModel data) {
        String hash_tag = data.getTopic_hash_tag();
        color = ContextCompat.getColor(getContext(), R.color.pink_topic);

//        if (onClickListener != null)
//            setOnClickListener(onClickListener);
//        setText(data.getDesc());


        if (hash_tag != null && hash_tag.length() > 0)
            ClickUtils.setClickHashTag(this, String.format("%s%s", hash_tag, data.getDesc()), hash_tag, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        JumpPageManager.getManager().goTopicActivty(getContext(),Integer.parseInt(data.getTopic_id()));
//                        KolActivityActivity.goKolActActivity(getContext(), Integer.parseInt(data.getTask_act_id()));

                    } catch (Exception ee) {

                    }


                }
            }, color, onClickListener);

        else {
            if (onClickListener != null)
                setOnClickListener(onClickListener);
            setText(data.getDesc());
        }

    }

    public void setClick(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private OnClickListener onClickListener;

    private int color;

    public static class QuestionInnerModel {
        private String desc;
        private String hash_tag;
        private String topic_hash_tag;
        private String topic_id;
        private String task_act_id;

        public String getDesc() {
            return desc;
        }

        public QuestionInnerModel setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public QuestionInnerModel setTopic_id(String topic_id) {
            this.topic_id = topic_id;
            return this;
        }

        public String getTask_act_id() {
            return task_act_id;
        }

        public QuestionInnerModel setTask_act_id(String task_act_id) {
            this.task_act_id = task_act_id;
            return this;
        }

        public String getHash_tag() {
            return hash_tag;
        }

        public QuestionInnerModel setHash_tag(String hash_tag) {
            this.hash_tag = hash_tag;
            return this;
        }

        public String getTopic_hash_tag() {
            return topic_hash_tag;
        }

        public QuestionInnerModel setTopic_hash_tag(String topic_hash_tag) {
            this.topic_hash_tag = topic_hash_tag;
            return this;
        }

        @Override
        public String toString() {
            return "QuestionInnerModel{" +
                    "desc='" + desc + '\'' +
                    ", hash_tag='" + hash_tag + '\'' +
                    ", topic_hash_tag='" + topic_hash_tag + '\'' +
                    ", topic_id='" + topic_id + '\'' +
                    ", task_act_id='" + task_act_id + '\'' +
                    '}';
        }
    }
}
