package com.prettyyes.user.app.view.app;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by chengang on 2017/3/13.
 */

public class MockTool {
    public void mockClick(Button button)
    {
        int[] location = new int[2];
        button.getLocationOnScreen(location);
        long time = new Date().getTime();
        MotionEvent motionEvent =
                MotionEvent.obtain(
                        time,
                        time,
                        MotionEvent.ACTION_DOWN,
                        location[0] + 1,
                        location[0] + 1,
                        0);
        button.dispatchTouchEvent(motionEvent);
        motionEvent.recycle();

        motionEvent =
                MotionEvent.obtain(
                        time + 1,
                        time + 1,
                        MotionEvent.ACTION_UP,
                        location[0] + 1,
                        location[0] + 1,
                        0);
        button.dispatchTouchEvent(motionEvent);
        motionEvent.recycle();
    }

    private Button buttonConfirmPay;

    public Button getConfirmPayButton(Context context)
    {
        Field wmGlobalField = null;
        try
        {
            wmGlobalField =
                    context.getSystemService(Context.WINDOW_SERVICE).
                            getClass().getDeclaredField("mGlobal");
        }
        catch (Exception e)
        {
            wmGlobalField = null;
        }

        if (wmGlobalField != null)
        {
            try
            {
                wmGlobalField.setAccessible(true);
                Object wmGlobal = wmGlobalField.get(context.getSystemService(Context.WINDOW_SERVICE));

                Field viewsField = wmGlobal.getClass().getDeclaredField("mViews");
                viewsField.setAccessible(true);
                ArrayList<View> views = (ArrayList<View>) viewsField.get(wmGlobal);

                getConfirmPayButton(views);

                return buttonConfirmPay;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            try
            {
                Field wmLocalField =
                        context.getSystemService(Context.WINDOW_SERVICE).getClass().
                                getSuperclass().getDeclaredField("mWindowManager");
//                        context.getSystemService(Context.WINDOW_SERVICE).
//                        getClass().getDeclaredField("mWindowManager");
                wmLocalField.setAccessible(true);
                Object wmLocal =
                        wmLocalField.get(context.getSystemService(Context.WINDOW_SERVICE));

                Field viewsField = wmLocal.getClass().getDeclaredField("mViews");
                viewsField.setAccessible(true);
                List<View> viewList = Arrays.asList((View[])viewsField.get(wmLocal));

                ArrayList<View> views = new ArrayList<>();
                for(View view : viewList)
                {
                    views.add(view);
                }

                getConfirmPayButton(views);

                return buttonConfirmPay;
            }
            catch (Exception e)
            {
                return null;
            }
        }
    }

    private void getConfirmPayButton(ArrayList<View> views)
    {
        for (int i = 0; i < views.size(); i++)
        {
            ViewGroup viewGroup = (ViewGroup) views.get(i);
            traverse(viewGroup);

            if (buttonConfirmPay != null)
            {
                break;
            }
        }

    }

    private void traverse(ViewGroup viewGroup)
    {
        int count = viewGroup.getChildCount();

        for (int i = 0; i < count; i++)
        {
            View view = viewGroup.getChildAt(i);

            if (view instanceof Button)
            {
                if (((Button) view).getText().equals("Test"))
                {
                    buttonConfirmPay = (Button) view;
                    break;
                }
            }

            if (view instanceof ViewGroup)
            {
                traverse((ViewGroup) view);
            }
        }
    }
}
