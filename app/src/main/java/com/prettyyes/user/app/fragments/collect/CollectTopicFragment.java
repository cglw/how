package com.prettyyes.user.app.fragments.collect;

import com.prettyyes.user.api.netXutils.requests.FollowTopicListRequest;
import com.prettyyes.user.app.adapter.TopicCollectAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.fragments.SingleListFragment;
import com.prettyyes.user.model.TopicCollectRes;

import java.util.List;

/**
 * Created by chengang on 2017/7/25.
 */

public class CollectTopicFragment extends SingleListFragment<TopicCollectRes> {
    @Override
    public void requestPageData(int page) {
        new FollowTopicListRequest().setPage(page).post(this);
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new TopicCollectAdapter(getContext());
    }

    @Override
    public List getListData(TopicCollectRes topicCollectRes) {
        return topicCollectRes.getList();
    }
}
