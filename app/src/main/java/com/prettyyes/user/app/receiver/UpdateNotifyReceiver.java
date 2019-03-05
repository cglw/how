package com.prettyyes.user.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.prettyyes.user.AppConfig;
import com.prettyyes.user.api.netXutils.HttpAccess;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.service.UpdateService;

public class UpdateNotifyReceiver extends BroadcastReceiver {
    String url;
    boolean isdownloading = false;
    String filepath;

    public UpdateNotifyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.DownloadAPkNotifyClick)) {
            Intent intent1 = new Intent();
            intent1.setAction(Constant.DownloadAPk);
            isdownloading = intent.getBooleanExtra("isDownloading", false);
            url = intent.getStringExtra("url");
            if (isdownloading) {
                HttpAccess.getInstance().cancelDownLoad(url);
            }
            intent1.putExtra("isDownloading", !isdownloading);
            intent1.putExtra("url", url);
            context.sendBroadcast(intent1);
        } else if (intent.getAction().equals(Constant.NotifyDelete)) {
            HttpAccess.getInstance().cancelDownLoad(AppConfig.APK_DOWNLOAD_URL);
            context.stopService(new Intent(context,UpdateService.class));

        }
    }
}
