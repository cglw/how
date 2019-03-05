package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.core.TimeManager;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.other.NotifyContentList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static android.util.Patterns.WEB_URL;

/**
 * Created by chengang on 2017/4/19.
 */

public class NotifyAdapter extends AbsRecycleAdapter<NotifyContentList.DataBean> {
    public NotifyAdapter(Context context) {
        super(context, R.layout.item_rv_notifyadp);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final NotifyContentList.DataBean dataBean, int position) {
        tv_desc.setText(dataBean.getContent());


        ImageLoadUtils.loadHeadImg(context, dataBean.getHeadimg_arr().get(0), img_1);
        if (dataBean.getHeadimg_arr().size() > 1) {
            img_2.setVisibility(View.VISIBLE);
            ImageLoadUtils.loadHeadImg(context, dataBean.getHeadimg_arr().get(1), img_2);

        } else
            img_2.setVisibility(View.GONE);
        tv_time.setText(FormatUtils.getTimeBefore(FormatUtils.StringToDate(dataBean.getCreate_time()), TimeManager.getManager().getServer_time()));
        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(context,"notification;"+dataBean.getId());
                new PushHandler(getContext()).handReceiveData(dataBean.getNotice_rule());
            }
        });
    }

    public static List<String> getCompleteUrl(String text) {
        List<String> termList = new ArrayList<String>();

        Matcher matcher = WEB_URL.matcher(text);
        while (matcher.find()) {
            termList.add(matcher.group());
            LogUtil.i("NotifyAdapter", matcher.group());
        }
        return termList;
    }


    private TextView tv_desc;
    private ImageView img_1;
    private ImageView img_2;
    private TextView tv_time;

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        tv_desc = holder.getView(R.id.tv_desc);
        img_1 = holder.getView(R.id.img_1);
        img_2 = holder.getView(R.id.img_2);
        tv_time = holder.getView(R.id.tv_time);
    }

    private class MyURLSpan extends ClickableSpan {

        private String mUrl;

        MyURLSpan(String url) {
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
            JumpPageManager.getManager().goWebActivity(context, mUrl);
//            WebviewActivity.goWebActivity((Activity) context, mUrl);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(context, R.color.blue_dark));
        }
    }
}
