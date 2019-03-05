package com.prettyyes.user.core.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

import com.prettyyes.user.R;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.core.utils
 * Author: SmileChen
 * Created on: 2016/12/21
 * Description: Nothing
 */
public class AnimotionUtils {


    public static void loadRightinAnimotion(Context context, final View v, final ViewCallback callback) {
        AnimationSet animationSet = new AnimationSet(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.push_right_in);
        loadAnimation.setDuration(1500);
        animationSet.addAnimation(loadAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.end(v);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.clearAnimation();
        v.startAnimation(animationSet);
    }public static void loadLeftinAnimotion(Context context, final View v, final ViewCallback callback) {
        AnimationSet animationSet = new AnimationSet(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
        loadAnimation.setDuration(500);
        animationSet.addAnimation(loadAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.end(v);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.clearAnimation();
        v.startAnimation(animationSet);
    }

    public static void loadLeftoutAnimotion(Context context, final View v, final ViewCallback callback) {
        AnimationSet animationSet = new AnimationSet(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.push_right_out);
        loadAnimation.setDuration(1500);
        animationSet.addAnimation(loadAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.end(v);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.clearAnimation();
        v.startAnimation(animationSet);
    }

    private ViewCallback callback;

    public interface ViewCallback

    {
        public void end(View view);

    }

    public static void animateOpen(View v, int height) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, height);
        animator.setDuration(1500);
        animator.start();
    }

    public static void animateTrans(View v, int target, AnimatorListenerAdapter animatorListenerAdapter) {
        ValueAnimator animator = createTransLinRemoveAnimator(v, 0, target);
        animator.setDuration(500);
        animator.addListener(animatorListenerAdapter);
        animator.start();
    }

    public static void animateTrans_noremove(View v, int start, int target, AnimatorListenerAdapter animatorListenerAdapter) {
        ValueAnimator animator = createTransNoRemoveAnimator(v, start, target);
        animator.setDuration(500);
        animator.addListener(animatorListenerAdapter);
        animator.start();
    }

    public static void animateOpen(View v, int height, long duration) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, height);
        animator.setDuration(duration);
        animator.start();
    } public static void animateOpen(View v, int height, long duration,final AnimotionCallback animotionCallback) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, height);
        animator.setDuration(duration);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animotionCallback.end();
            }
        });
        animator.start();
    }

    public static void animateAlphaIn(final View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                v.setAlpha((Float) arg0.getAnimatedValue());
            }
        });
        animator.setDuration(1500);
        animator.start();
    }


    public static void animateAlphaIn(final View v,long durtion) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                v.setAlpha((Float) arg0.getAnimatedValue());
            }
        });
        animator.setDuration(durtion);
        animator.start();
    }

    public static void animateAlphaIn(final View v, ValueAnimator.AnimatorUpdateListener a) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.addUpdateListener(a);
        animator.setDuration(1500);
        animator.start();
    }

    public static void animRotate180(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
        animator.setDuration(500);
        animator.start();
    }

    public static void animRotate180_back(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 180f, 360f);
        animator.setDuration(500);
        animator.start();

    }

    public static AnimationSet animTranslate(View view) {
        if (view == null)
            return null;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bottom_out));

        return animationSet;
    }


    public static void animateOpen(View v, int height, final AnimotionCallback animotionCallback) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, height);
        animator.setDuration(1500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animotionCallback.end();
            }
        });
        animator.start();

    }

    public interface AnimotionCallback {
        public void end();
    }


    public static void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.setDuration(1500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }
 public static void animateClose(final View view,long duration) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.setDuration(duration);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    public static void animateClose(final View view, AnimatorListenerAdapter animatorListenerAdapter) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.setDuration(500);
        animator.setupStartValues();
        animator.addListener(animatorListenerAdapter);
        animator.start();
    }

    public static ValueAnimator createDropAnimator(final View v, int start, final int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

    private static ValueAnimator createTransLinRemoveAnimator(final View view, int start, final int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        //   int value = (Integer)valueAnimator.getAnimatedValue();
                        int value = (Integer) valueAnimator.getAnimatedValue();// 得到的值
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
//                        layoutParams.height = Float.floatToIntBits(value);
                        layoutParams.setMargins(0, value, 0, 0);
                        view.setLayoutParams(layoutParams);
                        ViewGroup parent = (ViewGroup) view.getParent();
                        if (value == end) {
                            if (parent != null && view != null)
                                parent.removeView(view);
                        }
                    }
                }
        );
        return animator;
    }

    private static ValueAnimator createTransNoRemoveAnimator(final View view, int start, final int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        //   int value = (Integer)valueAnimator.getAnimatedValue();
                        int value = (Integer) valueAnimator.getAnimatedValue();// 得到的值
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
//                        layoutParams.height = Float.floatToIntBits(value);
                        layoutParams.setMargins(0, value, 0, 0);
                        view.setLayoutParams(layoutParams);

                    }
                }
        );
        return animator;
    }


    public static Animation animationArrowOpen() {
        RotateAnimation animation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        return animation;
    }

    public static Animation animationArrowClose() {
        RotateAnimation animation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        return animation;
    }

}
