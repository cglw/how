package com.prettyyes.user.core;

import android.os.Handler;
import android.os.Message;

import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.AppTimeRequest;
import com.prettyyes.user.api.netXutils.response.AppTimeRes;
import com.prettyyes.user.core.event.AppTimeEvent;
import com.prettyyes.user.core.utils.FormatUtils;
import com.prettyyes.user.core.utils.LogUtil;
import com.prettyyes.user.core.utils.AppRxBus;

/**
 * Created by chengang on 2017/7/7.
 * app 同步服务器时间
 */

public class TimeManager {
    private static final String TAG = "TimeManager";
    private static TimeManager timeManager;

    public TimeManager() {
    }

    public static TimeManager getManager() {
        if (timeManager == null) {
            synchronized (TimeManager.class) {
                timeManager = new TimeManager();
            }
        }
        return timeManager;

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                server_time++;
                LogUtil.d(TAG, "server_time" + server_time);
                checkTime();
                startTiming();

            } else if (msg.what == 2) {
                if (server_time == 0)
                    server_time = System.currentTimeMillis() / 1000;
                handler.sendEmptyMessageDelayed(2, 1000);
                if (server_time - last_request_time > 30) {
                    last_request_time = server_time;
                    syncServerTime();
                }
            }
        }
    };
    private long last_request_time;

    private void checkTime() {

    }

    public long getServer_time() {
        return server_time;
    }

    public void startTiming() {
        handler.sendEmptyMessageDelayed(1, 1000);
        AppRxBus.getInstance().post(new AppTimeEvent(server_time));

    }

    private long server_time;

    public void setServer_time(long server_time) {
        this.server_time = server_time;
    }

    public void syncServerTime() {
        handler.sendEmptyMessageDelayed(2, 1000);
        new AppTimeRequest().get(new NetReqCallback<AppTimeRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                LogUtil.i(TAG, "apiRequestFail" + message);

            }

            @Override
            public void apiRequestSuccess(AppTimeRes appTimeRes, String method) {
                handler.removeMessages(1);
                long l = System.currentTimeMillis();
                server_time = appTimeRes.getTime();
                if (Math.abs(server_time - l / 1000) <= 1) {
                    server_time = System.currentTimeMillis() / 1000;
                }

                startTiming();
            }
        });

    }

    public void syncKolActivityTime(long server_time, long kol_activity_start_time, long kol_activity_end_time, long kol_activity_create_time) {
        this.server_time = server_time;
        this.kol_activity_start_time = kol_activity_start_time;
        this.kol_activity_end_time = kol_activity_end_time;
        this.kol_activity_create_time = kol_activity_create_time;
        handler.removeMessages(1);
        startTiming();
    }


    private long kol_activity_create_time;
    private long kol_activity_start_time;
    private long kol_activity_end_time;

    public long getKol_activity_create_time() {
        return kol_activity_create_time;
    }

    public void setKol_activity_create_time(long kol_activity_create_time) {
        this.kol_activity_create_time = kol_activity_create_time;
    }

    public long getKol_activity_start_time() {
        return kol_activity_start_time;
    }

    public void setKol_activity_start_time(long kol_activity_start_time) {
        this.kol_activity_start_time = kol_activity_start_time;
    }

    public long getKol_activity_end_time() {
        return kol_activity_end_time;
    }

    public void setKol_activity_end_time(long kol_activity_end_time) {
        this.kol_activity_end_time = kol_activity_end_time;
    }


    public String getLiveShowTime(String create_time) {
        String res;
        if (create_time == null)
            return "Just Now";

        if (getServer_time() <= getKol_activity_end_time()) {
            LogUtil.i("TAG", "TimeManager.getManager().getServer_time() " + TimeManager.getManager().getServer_time() + "-->" + FormatUtils.StringToDate(create_time));
            if (TimeManager.getManager().getServer_time() - FormatUtils.StringToDate(create_time) <= 5) {
                res = "Just Now";
            } else
                res = FormatUtils.StringToDateAmPm(create_time);
        } else {
            res = create_time;

        }
        return res;

    }
}
