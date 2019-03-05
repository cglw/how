package com.prettyyes.user.app.adapter.detail;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.appview.SuitListQuestionView;
import com.prettyyes.user.app.ui.common.ViewPagerActivity;
import com.prettyyes.user.app.ui.spu.SuitquestionActivity;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.Suit.ReplyGoods;

/**
 * Created by chengang on 2017/5/3.
 */

public class KolGoodsListAdapter extends AbsRecycleAdapter<ReplyGoods> {


    public KolGoodsListAdapter(Context context) {
        super(context, R.layout.item_rv_suitlist);
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, final ReplyGoods data, int position) {

        ((View) img_goodsimg.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JumpPageManager.getManager().goSkuActivity((Activity) context, data.getSpu_type(), data.getModule_id());
            }
        });

//        holder.getRootView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        img_goodsimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.goVpActivity(context, 0, data.getMain_img(), data.getSpu_type(), Integer.parseInt(data.getModule_id()));
            }
        });

        tv_goodsname.setText(data.getSpu_name());
        tv_goodsdesc.setText(data.getSpu_desc());
        tv_goodsprice.setText(Constant.RMB + data.getSpu_price());
        tv_goods_questionnum.setText(String.format(context.getString(R.string.kol_question), data.getTask_count()));
        questionView1.setVisibility(View.GONE);
        questionView2.setVisibility(View.GONE);
        ImageLoadUtils.loadListimg(context, data.getMain_img(), img_goodsimg);

        if (data.getTask_list().size() <= 0) {
            ll_task_list.setVisibility(View.GONE);
        } else
            ll_task_list.setVisibility(View.VISIBLE);

        for (int i = 0; i < data.getTask_list().size(); i++) {
            if (i == 0) {
                questionView1.setVisibility(View.VISIBLE);
                questionView1.setDataReplayGoods(data.getTask_list().get(i), R.id.activity_suitlist, data.getMain_img());
            } else if (i == 1) {
                questionView2.setVisibility(View.VISIBLE);
                questionView2.setBg(R.drawable.how_question_container2);
                questionView2.setDataReplayGoods(data.getTask_list().get(i), R.id.activity_suitlist, data.getMain_img());
            }

        }


        questionView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getTask_list().size() >= 1) {
                    JumpPageManager.getManager().goReplyDetailActivity(context, data.getTask_list().get(0).getTask_id() + "", data.getTask_list().get(0).getAnswer_id() + "");
                }
//                    CommentActivity.goCommentActivity((BaseActivity) context, data.getTask_list().get(0).getAnswer_id(), false);
            }
        });
        questionView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getTask_list().size() >= 2) {
                    JumpPageManager.getManager().goReplyDetailActivity(context, data.getTask_list().get(1).getTask_id() + "", data.getTask_list().get(1).getAnswer_id() + "");

                }
//                    CommentActivity.goCommentActivity((BaseActivity) context, data.getTask_list().get(1).getAnswer_id(), false);
            }
        });
        if (data.getTask_list().size() > 2)
            tv_look_more.setVisibility(View.VISIBLE);
        else
            tv_look_more.setVisibility(View.GONE);
        tv_look_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuitquestionActivity.goSuitToQuestion(context, Integer.parseInt(data.getModule_id()), data.getMain_img(), data.getSpu_type(), data.getSpu_name());

            }
        });

    }

    private TextView tv_goodsname;
    private TextView tv_goodsdesc;
    private TextView tv_goodsprice;
    private ImageView img_goodsimg;
    private TextView tv_goods_questionnum;
    private SuitListQuestionView questionView1;
    private SuitListQuestionView questionView2;
    private LinearLayout ll_task_list;
    private TextView tv_look_more;

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {

        tv_goodsname = holder.getView(R.id.tv_suitlistAdp_goodsname);
        tv_goodsdesc = holder.getView(R.id.tv_suitlistAdp_goodsdesc);
        tv_goodsprice = holder.getView(R.id.tv_suitlistAdp_goodsprice);
        img_goodsimg = holder.getView(R.id.img_suitlistAdp_goodsimg);
        tv_goods_questionnum = holder.getView(R.id.tv_suitlistAdp_questionnumbers);
        questionView1 = holder.getView(R.id.suitListQuestionView_suitlistAdp_question1);
        questionView2 = holder.getView(R.id.suitListQuestionView_suitlistAdp_question2);
        ll_task_list = holder.getView(R.id.ll_task_list);
        tv_look_more = holder.getView(R.id.tv_look_more);
    }
}
