package com.prettyyes.user.app.ui.person;

import android.view.View;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.UserMonthScoreDetailRequest;
import com.prettyyes.user.api.netXutils.requests.UserScoreLineRequest;
import com.prettyyes.user.api.netXutils.response.MedalListRes;
import com.prettyyes.user.api.netXutils.response.UserMonthScoreDetailRes;
import com.prettyyes.user.api.netXutils.response.UserScoreLineRes;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.MedalEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


public class MyMedalActivity extends MedalListActivity {

    @ViewInject(R.id.tv_more)
    TextView tv_more;

    @ViewInject(R.id.tv_total_score)
    TextView tv_total_score;


    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_medal);

//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mChart.getLayoutParams();
//        layoutParams.width = BaseApplication.ScreenWidth * 5 / 4;
////        layoutParams.setMargins(-BaseApplication.ScreenWidth / 8, 0, 0, 0);


    }


    @Override
    protected void loadData() {
        super.loadData();
        new UserScoreLineRequest().post(new NetReqCallback<UserScoreLineRes>() {
            @Override
            public void apiRequestFail(String message, String method) {


            }

            @Override
            public void apiRequestSuccess(UserScoreLineRes userScoreLineRes, String method) {
                ArrayList<Double> datas = new ArrayList<Double>();
                for (int i = 0; i < userScoreLineRes.getList().size(); i++) {
                    datas.add(Double.valueOf(userScoreLineRes.getList().get(i).getScore()));
                }
//                tv_total_score.setText(userScoreLineRes.getList().);

            }
        });
        new UserMonthScoreDetailRequest().post(new NetReqCallback<UserMonthScoreDetailRes>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(UserMonthScoreDetailRes userMonthScoreDetailRes, String method) {
                tv_total_score.setText(userMonthScoreDetailRes.getThis_month_score());
            }
        });
    }

    public void go_score_detail(View view) {
        JumpPageManager.getManager().goMyScoreListDetailActivity(this);

    }



    @Override
    protected int bindLayout() {
        return R.layout.activity_my_medal;
    }

    @Override
    public List getListData(MedalListRes medalListRes) {
        List<MedalEntity> list = medalListRes.getList();

        if (list.size() > 2) {
            tv_more.setVisibility(View.VISIBLE);
            return list.subList(0, 2);
        } else
            tv_more.setVisibility(View.GONE);
        return list;
    }


}
