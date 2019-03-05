package com.prettyyes.user.app.ui.template;

import android.view.View;

import com.prettyyes.user.api.netXutils.requests.TaobaoUpdateRequest;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.model.v8.SkuSearchEntity;

import java.util.List;

import static com.prettyyes.user.app.account.Constant.TYPE_TAOBAO;

/**
 * Created by chengang on 2017/12/5.
 */

public class TaobaoNewTempleteActivity extends AddTemplateNewActivity<TaobaoUpdateRequest> {

    private String desc = "";
    private SkuSearchEntity skuSearchEntity;


    @Override
    protected void initViews() {
        super.initViews();
        webview.setVisibility(View.VISIBLE);
        edit_desc.setVisibility(View.GONE);
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            this.skuSearchEntity = intentParmas.skuSearchEntity;
            if (this.skuSearchEntity != null) {
                desc = this.skuSearchEntity.getSku_desc();
                List<String> small_image = skuSearchEntity.getSmall_image();
                small_image.add(0, skuSearchEntity.getMain_image());
                setImages(small_image);
                setGoodsName(skuSearchEntity.getSku_name());
                setGoodsFreight(skuSearchEntity.getExpress_price());
                setGoodsPrice(skuSearchEntity.getPrice());
                setDesc(skuSearchEntity.getSku_desc());
                desc = skuSearchEntity.getSku_desc();
            }

        }
        photoSelectView.setMax(100);
        edit_desc.setEnabled(false);



    }

    @Override
    public TaobaoUpdateRequest setRequest() {
        return new TaobaoUpdateRequest();
    }

    @Override
    public String getSpuType() {
        return TYPE_TAOBAO;
    }


}
