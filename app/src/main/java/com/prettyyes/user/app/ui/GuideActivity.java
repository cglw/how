package com.prettyyes.user.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.prettyyes.user.R;
import com.prettyyes.user.app.fragments.splash.GuideFragment;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.framlayout);
        loadfragment(new GuideFragment());


    }


    @Override
    public void onBackPressed() {
        finish();
    }

    public void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout, fragment)
                .commit();
    }
}
