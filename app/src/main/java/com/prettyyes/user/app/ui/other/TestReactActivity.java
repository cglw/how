package com.prettyyes.user.app.ui.other;

import android.os.Bundle;

import com.prettyyes.user.app.base.BaseActivity;

/**
 * Created by chengang on 2017/7/31.
 */

public class TestReactActivity extends BaseActivity {
    private static final String TAG = "TestReactActivity";

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mSubscription = RxBus.getInstance().toObservable(ReplyQuestionSuccessEvent.class).subscribe(new RxAction1<ReplyQuestionSuccessEvent>() {
//            @Override
//            public void callback(ReplyQuestionSuccessEvent replyQuestionSuccessEvent) {
//                new HowNativeEventManager().sendReplySuccess(getReactInstanceManager().getCurrentReactContext(), replyQuestionSuccessEvent.getTask_id());
//            }
//        });

    }

    @Override
    protected int bindLayout() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
