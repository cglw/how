package com.prettyyes.user.app.fragments.mianpage;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.MonmentsmyListRequest;
import com.prettyyes.user.api.netXutils.response.MonmentsmyListRes;
import com.prettyyes.user.app.base.AbsMutiRvAdapter;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.fragments.SingleListFragment;
import com.prettyyes.user.app.ui.how.AskActivity;
import com.prettyyes.user.app.view.lvandgrid.EmptyListener;
import com.prettyyes.user.app.view.pupopwindow.ItemTextPupop;
import com.prettyyes.user.core.AppStatistics;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.event.ClickItemTextEvent;
import com.prettyyes.user.core.event.CommentUnreadEvent;
import com.prettyyes.user.core.event.HomeTabSelectAgainEvent;
import com.prettyyes.user.core.event.ItemTextPupopDismissEvent;
import com.prettyyes.user.core.event.LoginChangeEvent;
import com.prettyyes.user.core.event.MineDynamicUnreadEvent;
import com.prettyyes.user.core.event.PostAskSuccessEvent;
import com.prettyyes.user.core.utils.AnimotionUtils;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.data.DataCenter;
import com.prettyyes.user.model.v8.ItemTextModel;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MyQuestionFragment extends SingleListFragment<MonmentsmyListRes> implements EmptyListener {


    @ViewInject(R.id.ll_change_moment_type)
    LinearLayout ll_change_moment_type;
    @ViewInject(R.id.roundview_unread)
    View roundview_unread;
    @ViewInject(R.id.img_arrow)
    ImageView img_arrow;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_my_question;

    }

    @Override
    public void onResume() {
        super.onResume();
        AppStatistics.onPageStart(getActivity(), "ReplyPage"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    @Override
    public void onPause() {
        super.onPause();
        AppStatistics.onPageEnd(getActivity(), "ReplyPage"); //统计页面，"MainScreen"为页面名称，可自定义

    }

    ArrayList<ItemTextModel> datas;
    Animation loadAnimation;

    @Override
    public void initView(View view) {
        super.initView(view);
        datas = new ArrayList<>();
        datas.add(new ItemTextModel("全部"));
        datas.add(new ItemTextModel("发问"));
        datas.add(new ItemTextModel("评论"));
        datas.add(new ItemTextModel("关注的问题"));
        loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.refresh_180);
        showUnreadAll();

    }


    @Override
    public AbsRecycleAdapter createAdapter() {
        return new AbsMutiRvAdapter(getActivity());
    }


    @Override
    public void setListener() {
        super.setListener();
        ll_change_moment_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.get(0).setUnread_num(DataCenter.TASK_UNREAD_NUM + DataCenter.COMMENT_UNREAD_NUM);
                datas.get(1).setUnread_num(DataCenter.TASK_UNREAD_NUM);
                datas.get(2).setUnread_num(DataCenter.COMMENT_UNREAD_NUM);
                new ItemTextPupop(getActivity(), datas).showAsdropdown((View) v.getParent());
                AnimotionUtils.animRotate180(img_arrow);
            }
        });
        swipeRv.setEmpty_listener(this);


        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<ClickItemTextEvent>() {
            @Override
            protected void back(ClickItemTextEvent obj) {
                TextView childAt = (TextView) ll_change_moment_type.getChildAt(0);
                childAt.setText(obj.getText());

                if ("发问".equals(obj.getText())) {
                    moment_type = "ask";
                } else if ("评论".equals(obj.getText())) {
                    moment_type = "comment";
                } else if ("关注的问题".equals(obj.getText())) {
                    moment_type = "like_task";
                } else {
                    moment_type = "";
                }
                adapter.clear();
                swipeRv.loadingFistEnter();
            }
        }, new RxCallback<ItemTextPupopDismissEvent>() {
            @Override
            protected void back(ItemTextPupopDismissEvent obj) {
                AnimotionUtils.animRotate180_back(img_arrow);

            }
        }, new RxCallback<PostAskSuccessEvent>() {
            @Override
            protected void back(PostAskSuccessEvent obj) {
                    AppRxBus.getInstance().post(new ClickItemTextEvent("发问"));
            }
        }, new RxCallback<LoginChangeEvent>() {
            @Override
            protected void back(LoginChangeEvent obj) {
                adapter.clear();
                if (swipeRv != null) {
                    loading();
                    swipeRv.loadingFistEnter();
                }
            }
        }, new RxCallback<HomeTabSelectAgainEvent>() {
            @Override
            protected void back(HomeTabSelectAgainEvent obj) {
                if (obj.index == 3 && isVisible) {
                    swipeRv.loadingFistEnter();
                }
            }
        }, new RxCallback<CommentUnreadEvent>() {
            @Override
            protected void back(CommentUnreadEvent obj) {
                showUnreadAll();

            }
        }, new RxCallback<MineDynamicUnreadEvent>() {
            @Override
            protected void back(MineDynamicUnreadEvent obj) {
                showUnreadAll();

            }
        });


    }

    private void showUnreadAll() {
        int count = DataCenter.TASK_UNREAD_NUM + DataCenter.COMMENT_UNREAD_NUM;
        if (count > 0)
            roundview_unread.setVisibility(View.VISIBLE);
        else
            roundview_unread.setVisibility(View.GONE);

    }

    private String moment_type = "";

    @Override
    public void requestPageData(int page) {
        if (SpMananger.getUUID() != null)
            new MonmentsmyListRequest().setPage(page).setMoment_type(moment_type).post(this);
        else
            loadFail();
    }

    @Override
    public List getListData(MonmentsmyListRes monmentsmyListRes) {
        return monmentsmyListRes.getData();
    }

    @Override
    public void setEmpty(LinearLayout ll, Button button) {
        button.setVisibility(View.VISIBLE);
        button.setText("去发问");
        ((TextView) ll.getChildAt(0)).setText("来日谁来问，天高风急双双远飞。");
        ((TextView) ll.getChildAt(1)).setText("——《神话情话》");
        ((TextView) ll.getChildAt(2)).setText("您关注的问题暂时没有新的回复，\n可以多关注几个相似问题或尝试发问。");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskActivity.goAskActivity(getActivity());
            }
        });
    }
}
