package com.prettyyes.user.app.ui.other;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;

public class ToastActivity extends BaseActivity {


    private Handler handler;
    private Runnable r;

    @Override
    protected int bindLayout() {
        return R.layout.activity_toast;
    }

    @Override
    public boolean isRemove_loadingLayout() {
        return true;
    }

    @Override
    protected void initViews() {
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };
        handler.postDelayed(r
                , 3000);

    }

    @Override
    public void setStatusBar() {
        setTranslucenBar();
    }

    @Override
    protected void loadData() {

    }

    public static void goToastActivity(Context context) {
        Intent intent = new Intent(context, ToastActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(r);
    }
}
