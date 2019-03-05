package com.prettyyes.user.app.adapter.searchholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.StringUtils;

/**
 * Created by chengang on 2017/7/14.
 */

public class SearchTopicHolder extends MutiTypeViewHolder<SearchTopicModel> {

    public SearchTopicHolder(Context context, ViewGroup parent) {
        super(context, parent, R.layout.item_search_topic);
    }

    @Override
    public void bindData(final SearchTopicModel modle, int position, RecyclerView.Adapter adpter) {
        SpannableString name = StringUtils.matcherSearchTitle(new StringBuilder().append("#").append(modle.getTopic_name()).append("#").toString(), modle.getKey_word());
        SpannableString content = StringUtils.matcherSearchTitle(modle.getTopic_content(), modle.getKey_word());
        tv_name.setText(name);

        tv_desc.setText(content);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppStatistics.onEvent(getActivity(),"search_ask;"+modle.getId());
                JumpPageManager.getManager().goTopicActivty(context, modle.getId());
            }
        });
    }

    @Override
    public void bindView() {
        tv_name = getView(R.id.tv_topic_name);
        tv_desc = getView(R.id.tv_topic_desc);

    }

    private TextView tv_name;
    private TextView tv_desc;


}