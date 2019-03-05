package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

/**
 * Created by chengang on 2017/7/10.
 */

public class MutiSpuView extends AbsLinearlayoutView<SpuInfoEntity> {

    private static final String TAG = "MutiSpuView";
    private AbsLinearlayoutView view_unit;
    private AbsRelativelayoutView view_suit;
    private AbsRelativelayoutView view_paper;
    private AbsRelativelayoutView view_taobao;

    public MutiSpuView(Context context) {
        super(context);
    }

    public MutiSpuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.view_muti_spu;
    }

    @Override
    public void initViews() {
        view_unit = (ViewimgBynum) getView(R.id.view_unit);
        view_suit = (NewSuitView) getView(R.id.view_suit);
        view_taobao = (NewTaobaoView) getView(R.id.view_taobao);
        view_paper = (NewPaperView) getView(R.id.view_paper);
    }


    public int width = 0;

    @Override
    public void setDataByModel(SpuInfoEntity data) {
        setAnswer(data);
    }


    private void setAnswer(SpuInfoEntity data) {
        view_unit.setVisibility(GONE);
        view_suit.setVisibility(GONE);
        view_taobao.setVisibility(GONE);
        view_paper.setVisibility(GONE);

        if (data == null || data.getSpu_type() == null) {
            return;
        }

        switch (data.getSpu_type()) {
            case TYPE_SUIT:
//                view_no_answer.setVisibility(VISIBLE);
//
                if (data.getUnit_detail() != null&&data.getUnit_detail().size()>0)
                    data.setUnit_list(data.getUnit_detail());

                if (data.getUnit_list() != null && data.getUnit_list().size() == 1) {
                    view_suit.setVisibility(GONE);
                    view_unit.setVisibility(VISIBLE);
                    view_unit.setData(data.getUnit_list().get(0));
                    return;
                }
                view_suit.setVisibility(VISIBLE);
                view_suit.setData(data);
                break;
            case TYPE_PAPER:
                view_paper.setVisibility(VISIBLE);
                view_paper.setData(data);
                break;
            case TYPE_UNIT:
                view_unit.setVisibility(VISIBLE);
                view_unit.setData(data);
                break;
            case TYPE_TAOBAO:
                view_taobao.setVisibility(VISIBLE);
                view_taobao.setData(data);
                break;
            default:


        }
    }

    public void show(View view) {
        if (view.getVisibility() == VISIBLE)
            return;
        else
            view.setVisibility(GONE);
        view_unit.setVisibility(GONE);
        view_suit.setVisibility(GONE);
        view_taobao.setVisibility(GONE);
        view_paper.setVisibility(GONE);

    }

}
