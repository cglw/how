package com.prettyyes.user.app.ui.template;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.PaperUpdateRequest;
import com.prettyyes.user.api.netXutils.response.AddTempleteRes;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.app.AppWebView;
import com.prettyyes.user.core.containter.IntentParams;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.containter.ZBundleCore;
import com.prettyyes.user.core.event.AddTemplateOrSelectSuccessEvent;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.model.v8.SpuInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import static com.prettyyes.user.core.utils.StringUtils.getImageSrc;
import static com.prettyyes.user.core.utils.StringUtils.getImageUrl;

public class PaperPreActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.webview_appweb)
    private AppWebView appWebView;
    @ViewInject(R.id.btn_release)
    private Button btn_release;
    private SpuInfoEntity spuInfoEntity;

    @Override
    protected int bindLayout() {
        return R.layout.activity_paper_pre;
    }

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private String module_id = "";
    String html = "";

    @Override
    protected void initViews() {
        spuInfoEntity = new SpuInfoEntity();
        setActivtytitle("预览好文");
        IntentParams intentParmas = JumpPageManager.getManager().getIntentParmas(this);
        if (intentParmas != null) {
            tv_title.setText(intentParmas.getTitle());
            html = intentParmas.getHtml();
            module_id = intentParmas.getModule_id();
            appWebView.loadContent(html);
            spuInfoEntity.setSpu_name(intentParmas.getTitle());
            spuInfoEntity.setSpu_desc(html);


        }

    }

    @Override
    protected void setListener() {
        super.setListener();
        btn_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("正在上传...");
                PaperUpdateRequest paperUpdateRequest = new PaperUpdateRequest();
                paperUpdateRequest.setModule_id(module_id);
                paperUpdateRequest.setSpu_name(tv_title.getText().toString());
                paperUpdateRequest.setSpu_desc(html);

                List<String> imageUrl = getImageUrl(html);
                List<String> imageSrc = getImageSrc(imageUrl);
                if (imageSrc != null && imageSrc.size() > 0) {
                    LogUtil.i(TAG, "paperUpdateRequest" + imageSrc.get(0));
                    paperUpdateRequest.setMain_img(imageSrc.get(0));
                }
                paperUpdateRequest.post(new NetReqCallback<AddTempleteRes>() {
                    @Override
                    public void apiRequestFail(String message, String method) {
                        showToastShort(message);
                        dismissProgressDialog();

                    }

                    @Override
                    public void apiRequestSuccess(AddTempleteRes addTempleteRes, String method) {

                        if (addTempleteRes.isCompletetNewBie())
                            AppRxBus.getInstance().post(new TaskCompleteEvent());
                        AppRxBus.getInstance().post(new AddTemplateOrSelectSuccessEvent(addTempleteRes.getModule_id(),addTempleteRes.getModule_type()));
                        if ( ZBundleCore.getInstance().isExistKolReplay()) {
                            JumpPageManager.getManager().goKolReplyActivity(getThis());

                        }
                        dismissProgressDialog();
                        finish();
                    }
                });
            }
        });
    }


    @Override
    protected void loadData() {

    }
}
