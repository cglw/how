package com.prettyyes.user.app.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.*;
import com.prettyyes.user.app.adapter.TestViewHolder;
import com.prettyyes.user.app.adapter.holder_presenter.TopicV8Holder;
import com.prettyyes.user.app.adapter.home.BannerHolder;
import com.prettyyes.user.app.adapter.home.HomeAskHolder;
import com.prettyyes.user.app.adapter.home.HomeKolActHistoryHolder;
import com.prettyyes.user.app.adapter.home.HomeSpliceHolder;
import com.prettyyes.user.app.adapter.home.HomeV8Holder;
import com.prettyyes.user.app.adapter.home.RewardHolder;
import com.prettyyes.user.app.adapter.invate.InvateSellerHolder;
import com.prettyyes.user.app.adapter.invate.InvateSellerModel;
import com.prettyyes.user.app.adapter.live.LiveQuestionHolder;
import com.prettyyes.user.app.adapter.live.LiveReplyHolder;
import com.prettyyes.user.app.adapter.moments_myList.AskViewHolder;
import com.prettyyes.user.app.adapter.moments_myList.CommentMeViewHolder;
import com.prettyyes.user.app.adapter.moments_myList.CommentOtherViewHolder;
import com.prettyyes.user.app.adapter.moments_myList.LikeTaskViewHolder;
import com.prettyyes.user.app.adapter.moments_myList.SimpleTextHolder;
import com.prettyyes.user.app.adapter.searchholder.SearchLookMoreHolder;
import com.prettyyes.user.app.adapter.searchholder.SearchLookMoreModel;
import com.prettyyes.user.app.adapter.searchholder.SearchQueHolder;
import com.prettyyes.user.app.adapter.searchholder.SearchQueModel;
import com.prettyyes.user.app.adapter.searchholder.SearchSellerHolder;
import com.prettyyes.user.app.adapter.searchholder.SearchSellerModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTitleHolder;
import com.prettyyes.user.app.adapter.searchholder.SearchTitltModel;
import com.prettyyes.user.app.adapter.searchholder.SearchTopicHolder;
import com.prettyyes.user.app.adapter.searchholder.SearchTopicModel;
import com.prettyyes.user.app.adapter.tasks.NewUserTasksHolder;
import com.prettyyes.user.app.adapter.tasks.ScoreGetHolder;
import com.prettyyes.user.app.adapter.viewholder.WithdrawHolder;
import com.prettyyes.user.app.ui.appview.HomeAskView;
import com.prettyyes.user.app.ui.appview.KolHistoryHomeListView;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.TestEntity;
import com.prettyyes.user.model.v8.AnswerInfoEntity;
import com.prettyyes.user.model.v8.HomeTaskEntity;
import com.prettyyes.user.model.v8.HowScoreGoods;
import com.prettyyes.user.model.v8.ItemTextModel;
import com.prettyyes.user.model.v8.LiveTaskInfo;
import com.prettyyes.user.model.v8.MedalEntity;
import com.prettyyes.user.model.v8.MineDynamicEntity;
import com.prettyyes.user.model.v8.MyAskEntity;
import com.prettyyes.user.model.v8.ScoreEntity;
import com.prettyyes.user.model.v8.ScoreGetEntity;
import com.prettyyes.user.model.v8.TaskNewUserItemEntity;
import com.prettyyes.user.model.v8.WithdrawEntity;

/**
 * Created by chengang on 2017/7/13.
 */

public class ViewTypeFactory implements BaseViewTypeFactory {


    private static final String TAG = "ViewTypeFactory";
    private static final int TYPE_VH_MINE_ASK = 10;
    private static final int TYPE_VH_MINE_LIKETASK = 11;
    private static final int TYPE_VH_MINE_COMMENT = 12;
    private static final int TYPE_VH_TEST = 0;
    private static final int TYPE_VH_SIMPLE_TEXT = 1;
    private static final int TYPE_VH_SERACH_LINE = 13;
    private static final int TYPE_VH_SERACH_QUE = 14;
    private static final int TYPE_VH_SERACH_SELLER = 15;
    private static final int TYPE_VH_SERACH_TOPIC = 16;
    private static final int TYPE_VH_SERACH_TITLE = 17;
    private static final int TYPE_VH_SERACH_LOOKMORE = 18;
    private static final int TYPE_VH_INVATE_KOL = 19;
    private static final int TYPE_VH_HOME_SPU = 20;
    private static final int TYPE_VH_HOME_ASK = 21;
    private static final int TYPE_VH_HOME_SPLICE = 22;
    private static final int TYPE_VH_HOME_HISTORY = 23;
    private static final int TYPE_VH_MEDAL_LIST = 24;
    private static final int TYPE_VH_MY_ASK_LIST = 25;
    private static final int TYPE_VH_MINE_COMMENT_ME = 26;
    private static final int TYPE_VH_ANSWER = 27;
    private static final int TYPE_VH_TOPIC = 28;
    private static final int TYPE_VH_SCORE = 29;
    private static final int TYPE_VH_LIVE_QUE = 30;
    private static final int TYPE_VH_LIVE_REPLY = 31;
    private static final int TYPE_WITH_DRAW = 32;
    private static final int TYPE_VH_REWARD = 33;

    private static final int TYPE_VH_NEWUSERTASK = 34;
    private static final int TYPE_VH_SCOREGET = 35;
    private static final int TYPE_VH_HOME_ITEM_BANNER = 36;
    private static final int TYPE_VH_HOW_SCORE_LIST = 37;


    @Override
    public MutiTypeViewHolder createViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        MutiTypeViewHolder mv = null;
        switch (viewType) {
            case TYPE_VH_HOME_SPU:
                mv = new HomeV8Holder(parent);
                break;
            case TYPE_VH_MINE_ASK:
                mv = new AskViewHolder(getView(parent, R.layout.item_mine_que));
                break;
            case TYPE_VH_ANSWER:
                mv = new MoreReplyHolder(parent);
                break;
            case TYPE_VH_MINE_LIKETASK:
                mv = new LikeTaskViewHolder(getView(parent, R.layout.item_mine_collect));
                break;
            case TYPE_VH_MINE_COMMENT:
                mv = new CommentOtherViewHolder(getView(parent, R.layout.item_mine_comment));
                break;
            case TYPE_VH_MINE_COMMENT_ME:
                mv = new CommentMeViewHolder(getView(parent, R.layout.item_mine_comment_me));
                break;
            case TYPE_VH_TEST:
                mv = new TestViewHolder(parent);
                break;
            case TYPE_VH_SIMPLE_TEXT:
                mv = new SimpleTextHolder(getView(parent, R.layout.item_text));
                break;
            case TYPE_VH_SERACH_TOPIC:
                mv = new SearchTopicHolder(context, parent);
                break;
            case TYPE_VH_SERACH_QUE:
                mv = new SearchQueHolder(context, parent);
                break;
            case TYPE_VH_SERACH_SELLER:
                mv = new SearchSellerHolder(context, parent);
                break;
            case TYPE_VH_SERACH_TITLE:
                mv = new SearchTitleHolder(context, parent);
                break;
            case TYPE_VH_SERACH_LOOKMORE:
                mv = new SearchLookMoreHolder(context, parent);
                break;
            case TYPE_VH_INVATE_KOL:
                mv = new InvateSellerHolder(context, parent);
                break;
            case TYPE_VH_HOME_ASK:
                mv = new HomeAskHolder(new HomeAskView(context));
                break;
            case TYPE_VH_HOME_HISTORY:
                mv = new HomeKolActHistoryHolder(new KolHistoryHomeListView(context));
                break;
            case TYPE_VH_HOME_SPLICE:
                mv = new HomeSpliceHolder(parent);
                break;
            case TYPE_VH_MEDAL_LIST:
                mv = new MedalHolder(parent);
                break;
            case TYPE_VH_MY_ASK_LIST:
                mv = new InvateMeHolder(parent);
                break;
            case TYPE_VH_TOPIC:
                mv = new TopicV8Holder(parent);
                break;
            case TYPE_VH_SCORE:
                mv = new ScoreListHolder(parent);
                break;
            case TYPE_VH_LIVE_QUE:
                mv = new LiveQuestionHolder(parent);
                break;
            case TYPE_VH_LIVE_REPLY:
                mv = new LiveReplyHolder(parent);
                break;
            case TYPE_WITH_DRAW:
                mv = new WithdrawHolder(parent);
                break;
            case TYPE_VH_REWARD:
                mv = new RewardHolder(parent);
                break;
            case TYPE_VH_NEWUSERTASK:
                mv = new NewUserTasksHolder(parent);
                break;
            case TYPE_VH_SCOREGET:
                mv = new ScoreGetHolder(parent);
                break;
            case TYPE_VH_HOME_ITEM_BANNER:
                mv = new BannerHolder(parent);
                break;
            case TYPE_VH_HOW_SCORE_LIST:
                mv=new HowScoreGoodsHolder(parent);
                break;

        }
        return mv;
    }

    private View getView(ViewGroup parent, int id) {
        return LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
    }

    @Override
    public int type(DemoType demoType) {
        return 0;
    }


    @Override
    public int type(MineDynamicEntity mineDynamicEntity) {

        if (mineDynamicEntity.page_type != null && mineDynamicEntity.page_type.equals("home")) {
            return TYPE_VH_MY_ASK_LIST;
        }
        String moment_type = mineDynamicEntity.getMoment_type();
        if ("like_task".equals(moment_type)) {
            return TYPE_VH_MINE_LIKETASK;
        } else if ("comment".equals(moment_type)) {
            if (mineDynamicEntity.getParent().getAnswer_id() == null) {
                return TYPE_VH_MINE_COMMENT;
            } else {
                return TYPE_VH_MINE_COMMENT_ME;
            }
        } else if ("ask".equals(moment_type)) {
            return TYPE_VH_MINE_ASK;
        }
        return TYPE_VH_TEST;
    }

    @Override
    public int type(ItemTextModel item) {
        return TYPE_VH_SIMPLE_TEXT;
    }


    @Override
    public int type(SearchLookMoreModel item) {
        return TYPE_VH_SERACH_LOOKMORE;
    }

    @Override
    public int type(SearchSellerModel item) {
        return TYPE_VH_SERACH_SELLER;
    }

    @Override
    public int type(SearchTopicModel item) {
        return TYPE_VH_SERACH_TOPIC;
    }

    @Override
    public int type(SearchTitltModel item) {
        return TYPE_VH_SERACH_TITLE;
    }

    @Override
    public int type(SearchQueModel item) {
        return TYPE_VH_SERACH_QUE;
    }

    @Override
    public int type(InvateSellerModel item) {
        return TYPE_VH_INVATE_KOL;
    }

    @Override
    public int type(HomeTaskEntity item) {
        String type = item.getType();
        if (item.getActivity_type() != null &&
                "topic".equals(item.getActivity_type()))
            return TYPE_VH_TOPIC;
        switch (type) {
            case "task":
                return TYPE_VH_HOME_SPU;
            case "splice":
                return TYPE_VH_HOME_SPLICE;
            case "ask":
                return TYPE_VH_HOME_ASK;
            case "task_act":
                return TYPE_VH_HOME_HISTORY;
            case "reward":
                return TYPE_VH_REWARD;
            case "ad":
                return TYPE_VH_HOME_ITEM_BANNER;
        }
        return 0;
    }

    @Override
    public int type(MedalEntity item) {
        return TYPE_VH_MEDAL_LIST;
    }

    @Override
    public int type(MyAskEntity item) {
        return TYPE_VH_MY_ASK_LIST;
    }

    @Override
    public int type(TestEntity item) {
        return TYPE_VH_TEST;
    }

    @Override
    public int type(AnswerInfoEntity item) {
        return TYPE_VH_ANSWER;
    }

    @Override
    public int type(ScoreEntity item) {
        return TYPE_VH_SCORE;
    }

    @Override
    public int type(LiveTaskInfo item) {

        LogUtil.i(TAG, "TaskInfo" + item);
        if (item.getSuit_list() != null
                && item.getSuit_list().size() > 0
                && item.getSuit_list().get(0).getAnswer_id() != null
                && !item.getSuit_list().get(0).getAnswer_id().equals("0"))
            return TYPE_VH_LIVE_REPLY;
        return TYPE_VH_LIVE_QUE;
    }

    @Override
    public int type(WithdrawEntity item) {
        return TYPE_WITH_DRAW;
    }

    @Override
    public int type(ScoreGetEntity item) {
        return TYPE_VH_SCOREGET;
    }

    @Override
    public int type(TaskNewUserItemEntity item) {
        return TYPE_VH_NEWUSERTASK;
    }

    @Override
    public int type(HowScoreGoods item) {
        return TYPE_VH_HOW_SCORE_LIST;
    }
}
