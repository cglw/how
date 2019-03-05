package com.prettyyes.user.app.ui.spu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.netXutils.requests.SpuDetailRequest;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.common.SingleListActivity;
import com.prettyyes.user.app.view.BadgeView;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.RxCallback;

import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_PAPER;
import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;
import static com.prettyyes.user.app.account.Constant.TYPE_UNIT;

public class SkuSelectActivity extends SingleListActivity {


    private IntentParams intentParmas;
    private boolean need_select = true;

    @Override
    protected void initVariables() {
        super.initVariables();
        intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null)
            need_select = intentParmas.need_select;
    }

    @Override
    protected void requestData(int page) {
        adapter.add("0");
        adapter.add("0");
        adapter.add("0");
        if (need_select) {
            adapter.add("0");
            adapter.add("0");

        }
    }

    BadgeView badgeView_goods;
    BadgeView badgeView_links;
    BadgeView badgeView_paper;

    @Override
    protected void initViews() {
        super.initViews();
        badgeView_goods = new BadgeView(this);
        badgeView_links = new BadgeView(this);
        badgeView_paper = new BadgeView(this);
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST, R.drawable.dotted_line);
        swipeRv.getRecycleView().addItemDecoration(decor);
        if (need_select) {
            setActivtytitle("选择SKU类型");
        } else {
            setActivtytitle("添加商品");
        }
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AddTemplateOrSelectSuccessEvent>() {
            @Override
            protected void back(AddTemplateOrSelectSuccessEvent obj) {
                finish();
                LogUtil.i(TAG,"添加商品-->AddTemplateOrSelectSuccessEvent");
            }
        });
        swipeRv.getSwpie().setEnabled(false);


    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    public List getListData(Object o) {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public AbsRecycleAdapter createAdapter() {
        return new ItemAdapter(this);
    }

    class ItemAdapter extends AbsRecycleAdapter<String> {

        public ItemAdapter(Context context) {
            super(context, R.layout.activity_sku_select);
        }

        @Override
        protected void bindData(final AbsRecycleViewHolder holder, String o, final int position) {

            holder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            JumpPageManager.getManager().goAddPaperActivity(context, "");
                            break;
                        case 1:
                            JumpPageManager.getManager().goUnitTempleteActivity(context);
                            break;
                        case 2:
                            JumpPageManager.getManager().goTaobaoUrlSelectActivity(context);

                            break;
                        case 3:
                            JumpPageManager.getManager().goBrandListActivty(context);
                            break;
                        case 4:
                            JumpPageManager.getManager().goSpuListActivity(context);
                            break;
                    }
                }
            });
            if (position == 0) {
                setBadge(badgeView_paper, holder.getView(R.id.img_covery), TYPE_PAPER);
                img_covery.setImageResource(R.mipmap.upload_article);
                tv_text.setText("创作一篇好文");
            } else if (position == 1) {
                setBadge(badgeView_goods, holder.getView(R.id.img_covery), TYPE_UNIT);
                img_covery.setImageResource(R.mipmap.upload_goods);
                tv_text.setText("上传新商品");
            } else if (position == 2) {
                setBadge(badgeView_links, holder.getView(R.id.img_covery), TYPE_TAOBAO);
                img_covery.setImageResource(R.mipmap.upload_link);
                tv_text.setText("复制链接一键添加");
            } else if (position == 3) {
                img_covery.setImageResource(R.mipmap.upload_folder);
                tv_text.setText("从产品库里挑选");
            }else if (position == 4) {
                img_covery.setImageResource(R.mipmap.ic_mine_goods);
                tv_text.setText("从历史商品库里挑选");
            }
            view_dote.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        }

        @Override
        protected void bindView(AbsRecycleViewHolder holder) {
            img_covery = holder.getView(R.id.img_covery);
            tv_text = holder.getView(R.id.tv_text);
            view_dote = holder.getView(R.id.view_dote);
        }

    }

    private ImageView img_covery;
    private TextView tv_text;
    private View view_dote;


    private void setBadge(BadgeView badge, View target, String type) {
        badge.setTargetView(target);
        SpuDetailRequest spuDetailRequest = new SpuDetailRequest();
        boolean haveLocal = spuDetailRequest.setSpu_type(type).isHaveLocal();
        LogUtil.i(TAG, "setBadge" + haveLocal + "-->" + type);
        badge.setImageTagRound(haveLocal);

    }

}
