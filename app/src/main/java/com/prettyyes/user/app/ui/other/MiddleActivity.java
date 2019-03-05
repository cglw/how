package com.prettyyes.user.app.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.indexing.LMUniversalObject;
import com.microquation.linkedme.android.util.LinkProperties;
import com.prettyyes.user.core.PushHandler;
import com.prettyyes.user.data.DataCenter;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * <p>中转页面</p>
 * <p>
 * Created by LinkedME06 on 16/11/17.
 */

public class MiddleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取与深度链接相关的值
        LinkProperties linkProperties = getIntent().getParcelableExtra(LinkedME.LM_LINKPROPERTIES);

        LMUniversalObject lmUniversalObject = getIntent().getParcelableExtra(LinkedME.LM_UNIVERSALOBJECT);
        //LinkedME SDK初始化成功，获取跳转参数，具体跳转参数在LinkProperties中，和创建深度链接时设置的参数相同；
        if (linkProperties != null) {
            Log.i("LinkedME-Demo", "Channel " + linkProperties.getChannel());
            Log.i("LinkedME-Demo", "control params " + linkProperties.getControlParams());
            Log.i("LinkedME-Demo", "link(深度链接) " + linkProperties.getLMLink());
            Log.i("LinkedME-Demo", "是否为新安装 " + linkProperties.isLMNewUser());
            //获取自定义参数封装成的hashmap对象
            HashMap<String, String> hashMap = linkProperties.getControlParams();
            Log.e("TAG", hashMap + "-->" + hashMap.toString());
            new PushHandler(this).handReceiveData(new JSONObject(hashMap) + "");

            DataCenter.setSource_uid(hashMap.get("source_uid"));

//            //获取传入的参数
//            String view = hashMap.get("View");
//            String title = "";
//            String shareContent = "";
//            String url_path = "";
//            if (view != null) {
//                if (view.equals("Demo")) {
////                    //Intent intent = new Intent(MiddleActivity.this, DemoActivity.class);
////                    intent.putExtra("keyValue", hashMap.toString());
////                    startActivity(intent);
//                    return;
//                }
//                }
//                openActivity(title, view, shareContent, url_path, ShareActivity.class);
//            }
        }

        if (lmUniversalObject != null) {
            Log.i("LinkedME-Demo", "title " + lmUniversalObject.getTitle());
            Log.i("LinkedME-Demo", "control " + linkProperties.getControlParams());
            Log.i("ContentMetaData", "metadata " + lmUniversalObject.getMetadata());
        }
        finish();
    }

    public void openActivity(String title, String param_view, String shareContent, String url_path, Class clazz) {
        Intent intent = new Intent(this, clazz);
//        if (!TextUtils.isEmpty(title)) {
//            intent.putExtra(ShareActivity.TITLE, title);
//        }
//        if (!TextUtils.isEmpty(param_view)) {
//            intent.putExtra(ShareActivity.PARAM_VIEW, param_view);
//        }
//        if (!TextUtils.isEmpty(shareContent)) {
//            intent.putExtra(ShareActivity.SHARE_CONTENT, shareContent);
//        }
//        if (!TextUtils.isEmpty(url_path)) {
//            intent.putExtra(ShareActivity.URL_PATH, url_path);
//        }
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
