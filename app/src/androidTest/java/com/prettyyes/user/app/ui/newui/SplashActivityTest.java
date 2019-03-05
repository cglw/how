package com.prettyyes.user.app.ui.newui;

import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.SplashActivity;
import com.prettyyes.user.model.task.TaskHomeTask;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.ui.newui
 * Author: SmileChen
 * Created on: 2016/11/14
 * Description: Nothing
 */
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule = new ActivityTestRule<>(
            SplashActivity.class);

    @Test
    public void testAdv() throws Exception {

        SystemClock.sleep(1500);
        onView(withId(R.id.btn_advfragment_jump)).perform(click()); //line 1

        SystemClock.sleep(1000);
        ViewInteraction editText = onView(
                withId(R.id.index_rb2)
        );
        editText.perform(click());
        SystemClock.sleep(1000);
        String question = "UI测试UI测试-----------------------------I测试UI测试UI测试UI测试";
        String tag = "UI测试标签--";

//        onView(withId(R.id.editdel_sendQueAct_question)).perform(replaceText(question), closeSoftKeyboard()); //line 1
//        onView(withId(R.id.editdel_sendQueAct_tags)).perform(replaceText(tag), closeSoftKeyboard()); //line 1
//        onView(withId(R.id.btn_sendQueAct_sendquestion)).perform(click()); //line 1

        SystemClock.sleep(5000);
        onView(withId(R.id.index_rb4)).perform(click()); //line 1
        SystemClock.sleep(1000);
        onView(withId(R.id.lin_minefmt_myinfo)).perform(click()); //line 1
        SystemClock.sleep(3000);



        // onView(withId(R.id.img_otherqueFmt_chat)).perform(click()); //line 1
        //   onData(allOf(is(instanceOf(TaskHomeTask.class)),withBookTitle("16261"))).perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public Matcher<Object> withBookTitle(final String task_id) {


        return new BoundedMatcher<Object, TaskHomeTask>(TaskHomeTask.class) {
            @Override
            protected boolean matchesSafely(TaskHomeTask book) {
                return task_id.equals(book.getTask_id() + "");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with id: " + task_id);
            }
        };
    }


}