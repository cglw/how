package com.prettyyes.user.app.ui.template;

import android.view.View;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.TemplateItemView;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.core.SpMananger;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.prettyyes.user.app.account.Constant.BRAND_STR;
import static com.prettyyes.user.app.account.Constant.TYPE_BRAND;
import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_SUIT;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

;

public class TemplateActivity extends BaseActivity {


    @ViewInject(R.id.tempItem_brand)
    TemplateItemView itemView_brand;
    @ViewInject(R.id.tempItem_unit)
    TemplateItemView itemView_unit;
    @ViewInject(R.id.tempItem_paper)
    TemplateItemView itemView_paper;
    @ViewInject(R.id.tempItem_taobao)
    TemplateItemView itemView_taobao;
    @ViewInject(R.id.tempItem_suit)
    TemplateItemView itemView_suit;


    @Override
    protected int bindLayout() {
        return R.layout.activity_template;
    }

    BadgeView badgeView_unit;
    BadgeView badgeView_taobao;
    BadgeView badgeView_paper;
    BadgeView badgeView_suit;
    BadgeView badgeView_brand;

    @Override
    protected void onStart() {
        super.onStart();

        if (badgeView_unit == null)
            badgeView_unit = new BadgeView(this);
        if (badgeView_taobao == null)
            badgeView_taobao = new BadgeView(this);
        if (badgeView_paper == null)
            badgeView_paper = new BadgeView(this);
        if (badgeView_suit == null)
            badgeView_suit = new BadgeView(this);
        if (badgeView_brand == null)
            badgeView_brand = new BadgeView(this);
        setBadge(badgeView_unit, itemView_unit.getImg(), TYPE_UNIT);
        setBadge(badgeView_taobao, itemView_taobao.getImg(), TYPE_TAOBAO);
        setBadge(badgeView_paper, itemView_paper.getImg(), TYPE_PAPER);
        setBadge(badgeView_suit, itemView_suit.getImg(), TYPE_SUIT);
        setBadge(badgeView_brand, itemView_brand.getImg(), TYPE_BRAND);


    }

    private void setBadge(BadgeView badge, View target, String type) {
        badge.setTargetView(target);
        SpuDetailRequest spuDetailRequest = new SpuDetailRequest();
        boolean haveLocal = spuDetailRequest.setSpu_type(type).isHaveLocal();
        badge.setImageTagRound(haveLocal);

    }


    @Override
    protected void initViews() {
        hideHeader();
        AccountDataRepo.getAccountManager().refreshUseInfoLocal();

        itemView_taobao.setVisibility(View.GONE);
        itemView_paper.setVisibility(View.GONE);
        itemView_suit.setVisibility(View.GONE);
        itemView_unit.setVisibility(View.GONE);
        itemView_brand.setVisibility(View.GONE);
        UserInfo userInfo = SpMananger.getUserInfo();
        if (userInfo != null && userInfo.getSeller_template() != null && userInfo.getSeller_template().size() > 0) {
            List<UserInfo.SellerTemplateBean> seller_template = userInfo.getSeller_template();
            for (int i = 0; i < seller_template.size(); i++) {
                if (seller_template.get(i).getStatus() == 1) {
                    String style = seller_template.get(i).getStyle();
                    switch (style) {
                        case BRAND_STR:
                            itemView_brand.setVisibility(View.VISIBLE);
                            break;
                        case TYPE_UNIT:
                            itemView_unit.setVisibility(View.VISIBLE);
                            break;
                        case TYPE_PAPER:
                            itemView_paper.setVisibility(View.VISIBLE);
                            break;
                        case TYPE_SUIT:
                            itemView_suit.setVisibility(View.VISIBLE);
                            break;
                        case TYPE_TAOBAO:
                            itemView_taobao.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }

        }


    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, AppConfig.STARTBAR_ALPHA, null);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(AddTemplateSuccessEvent.class).subscribe(new RxAction1<AddTemplateSuccessEvent>() {
//            @Override
//            public void callback(AddTemplateSuccessEvent addTemplateSuccessEvent) {
//                finish();
//            }
//        });

        mSubscription=  AppRxBus.getInstance().subscribeEvent(new RxCallback<AddTemplateOrSelectSuccessEvent>() {
            @Override
            protected void back(AddTemplateOrSelectSuccessEvent obj) {

                finish();
            }
        });
    }

    public void unit(View view) {
        JumpPageManager.getManager().goTemplateActivity(this, "0", TYPE_UNIT);
    }

    public void taobao(View view) {
        JumpPageManager.getManager().goTemplateActivity(this, "0", TYPE_TAOBAO);
    }

    public void brand(View view) {
        JumpPageManager.getManager().goTemplateActivity(this, "0", TYPE_BRAND);
    }

    public void suit(View view) {
        JumpPageManager.getManager().goTemplateActivity(this, "0", TYPE_SUIT);
    }

    public void paper(View view) {
        JumpPageManager.getManager().goTemplateActivity(this, "0", TYPE_PAPER);
    }
}
