package com.prettyyes.user.app.ui.template;

import android.view.View;

import com.prettyyes.user.api.netXutils.requests.UnitUpdateRequest;

import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

/**
 * Created by chengang on 2017/12/5.
 */

public class GoodsTempleteActivity extends AddTemplateNewActivity<UnitUpdateRequest> {

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle("添加商品");
        photoSelectView.setMax(9);
        webview.setVisibility(View.GONE);
        edit_desc.setVisibility(View.VISIBLE);
    }

    @Override
    public UnitUpdateRequest setRequest() {
        return new UnitUpdateRequest();
    }

    @Override
    public String getSpuType() {
        return TYPE_UNIT;
    }


}
