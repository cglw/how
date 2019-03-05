package com.prettyyes.user.app.ui;

import android.support.test.rule.ActivityTestRule;

import com.prettyyes.user.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui
 * Author: SmileChen
 * Created on: 2016/11/14
 * Description: Nothing
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test
    public void testLoadData() throws Exception {
        onView(withId(R.id.img_otherqueFmt_chat)).perform(click()); //line 1
    }


}