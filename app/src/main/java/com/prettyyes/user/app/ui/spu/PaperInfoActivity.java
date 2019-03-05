package com.prettyyes.user.app.ui.spu;

import com.prettyyes.user.R;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import static com.prettyyes.user.api.netXutils.requests.PaperUpdateRequest.PAPER_DEFAULT_ATTRNAME;

public class PaperInfoActivity extends GoosInfoActivity {


    @ViewInject(R.id.webview)
    private AppWebView appWebView;

    @Override
    public int setLayout() {
        return R.layout.activity_paper_info;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setActivtytitle(R.string.title_activity_paper_info);
    }

    @Override
    public void apiRequestSuccess(SpuInfoEntity spuInfoEntity, String method) {
        super.apiRequestSuccess(spuInfoEntity, method);
        appWebView.loadContent(spuInfoEntity.getSpu_desc());
        setRecommend(spuInfoEntity);

        if (spuInfoEntity.getAttr_list() != null && spuInfoEntity.getAttr_list().size() == 1) {
            if (spuInfoEntity.getAttr_list().get(0).getAttr_name().equals(PAPER_DEFAULT_ATTRNAME)) {
                tv_buy.setText("打赏");
            }

        }
    }
}
