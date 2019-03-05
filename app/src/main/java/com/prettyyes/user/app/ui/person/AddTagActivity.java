package com.prettyyes.user.app.ui.person;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.ApiImpls.OtherApiImpl;
import com.prettyyes.user.api.netXutils.requests.UserEditRequest;
import com.prettyyes.user.app.account.AccountDataRepo;
import com.prettyyes.user.app.account.Constant;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.view.FlowLayout;
import com.prettyyes.user.app.view.explosionfield.ExplosionField;
import com.prettyyes.user.core.event.TaskCompleteEvent;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.DensityUtil;
import com.prettyyes.user.model.AlertMessageResponse;
import com.prettyyes.user.model.other.TagAdd;
import com.prettyyes.user.model.other.TagHot;
import com.prettyyes.user.model.user.UserInfo;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class AddTagActivity extends BaseActivity {


    private FlowLayout flowLayout;
    private FlowLayout flowLayout2;
    @ViewInject(R.id.edit_addtag)
    private TextView editTextDel;


    /**
     * 获取点击的Item的对应View，
     * 因为点击的Item已经有了自己归属的父容器MyGridView，所有我们要是有一个ImageView来代替Item移动
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }


    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     *
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     * 用于存放我们移动的View
     */
    private ViewGroup getMoveViewGroup() {
        //window中最顶层的view
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     */
    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final TextView start, final TextView target,
                          final boolean isfirst) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                start.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                if (isfirst) {
                    target.setVisibility(View.VISIBLE);
                    flowLayout.removeView(start);
                    initFlow2Click();
                } else {
                    target.setVisibility(View.VISIBLE);
                    flowLayout2.removeView(start);
                    initFlow1Click();


                }
            }
        });
    }

    private void initFlow1Click() {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            final TextView tv = (TextView) flowLayout.getChildAt(i);
            setCurrentListener(tv, true);
        }
    }

    private void initFlow2Click() {
        boolean ismove = true;
        for (int i = 0; i < flowLayout2.getChildCount(); i++) {
            final TextView tv = (TextView) flowLayout2.getChildAt(i);
            tv.setTag(1);
            for (int j = 0; j < flowLayout.getChildCount(); j++) {
                final TextView tv2 = (TextView) flowLayout.getChildAt(j);
                if (tv.getText().toString().equals(tv2.getText().toString())) {
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showToastShort("不可以重复选择");
                        }
                    });
                    ismove = false;
                    break;
                }
            }
            if (ismove)
                setCurrentListener(tv, false);
            ismove = true;
        }

    }

    private ExplosionField explosionField;

    private void setCurrentListener(TextView tv, final boolean isfromOne) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                final TextView start = (TextView) v;
                final ImageView moveImageView = getView(v);
                if (moveImageView != null) {
                    final int[] startLocation = new int[2];
                    start.getLocationInWindow(startLocation);
                    final TextView target = new TextView(getApplicationContext());
                    initViewSet(target, start.getText().toString(), start.getLayoutParams());
                    target.setTag(start.getTag());
                    target.setTag(R.id.tag, start.getTag(R.id.tag));
                    if (start.getTag() != null &&
                            ((int) start.getTag()) != 1 || start.getTag() == null) {
                        explosionField.explode(start);
                        flowLayout.removeView(start);
                        initFlow2Click();
                        return;
                    }
                    if (isfromOne) {
                        flowLayout2.addView(target);
                    } else {
                        flowLayout.addView(target);
                    }

                    target.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.INVISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                //    start.setVisibility(View.GONE);
                                final int[] endLocation = new int[2];
                                target.getLocationInWindow(endLocation);
                                MoveAnim(moveImageView, startLocation, endLocation, start, target, isfromOne);
                            } catch (Exception localException) {
                            }
                        }
                    }, 50L);
                }
            }
        });
    }


    @Override
    protected int bindLayout() {
        return R.layout.activity_add_tag;
    }

    @Override
    protected void initViews() {
        showBack();
        setActivtytitle("个人标签");
        flowLayout = (FlowLayout) findViewById(R.id.flowlayout_add);
        flowLayout2 = (FlowLayout) findViewById(R.id.flowlayout_add2);
        loadPersonTag(getAccount().getTags_info());
        editTextDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditdialog();
            }
        });
        editTextDel.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getAction() == EditorInfo.IME_ACTION_DONE) {

                }
                return false;
            }
        });
        explosionField = ExplosionField.attach2Window(this);

    }


    private void showEditdialog() {
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("添加标签")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString().trim();
                        if (input.length() > 0)
                            addPersonTag(input);
                    }

                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void addPersonTag(String v) {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            if (v.equals(((TextView) flowLayout.getChildAt(i)).getText().toString())) {
                showToastShort("你已经添加过此标签了");
                return;
            }
        }
        addTagByNet(v);

    }


    @Override
    protected void setListener() {
        setRightTvListener("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = AccountDataRepo.getAccountManager().getAccount();
                if (userInfo.getTags_info() != null)
                    userInfo.getTags_info().clear();
                for (int i = 0; i < flowLayout.getChildCount(); i++) {
                    TextView tv = ((TextView) flowLayout.getChildAt(i));
                    String tag = tv.getText().toString();
                    int tagid = (int) tv.getTag(R.id.tag);
                    UserInfo.TagsInfoBean t = new UserInfo.TagsInfoBean();
                    t.setTag_id(tagid);
                    t.setTag_name(tag);
                    userInfo.getTags_info().add(t);
                }
                uploadPersonInfo(userInfo);

            }
        });
    }

    @Override
    protected void loadData() {
        new OtherApiImpl().getHotTags(new NetReqCallback<TagHot>() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);
                loadFail();
            }

            @Override
            public void apiRequestSuccess(TagHot tagHot,String method) {
                loadSuccess();
                for (int i = 0; i < tagHot.getTags().size(); i++) {
                    final TextView target = new TextView(getApplicationContext());
                    target.setTag(R.id.tag, tagHot.getTags().get(i).getTag_id());
                    ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
                    initViewSet(target, tagHot.getTags().get(i).getTag_name(), layoutParams);
                    flowLayout2.addView(target);
                }
                initFlow2Click();
            }

        });
    }


    private void addTagByNet(String name) {

        new OtherApiImpl().addTags(name, new NetReqCallback<TagAdd>() {
            @Override
            public void apiRequestFail(String message,String method) {
                showToastShort(message);

            }

            @Override
            public void apiRequestSuccess(TagAdd tagAdd,String method) {
                final TextView target = new TextView(getApplicationContext());
                ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
                target.setTag(R.id.tag, tagAdd.getTag_id());
                initViewSet(target, tagAdd.getTag_name(), layoutParams);
                flowLayout.addView(target);
                initFlow1Click();
                initFlow2Click();

            }
        });
    }

    private TextView initViewSet(TextView tv, String txt, ViewGroup.LayoutParams layoutParams) {
        tv.setMinWidth(DensityUtil.dip2px(this, 40));
        tv.setMaxWidth(DensityUtil.dip2px(this, 200));
        tv.setTextColor(Color.rgb(32, 53, 74));
        tv.setGravity(Gravity.CENTER);
        tv.setLines(1);
        tv.setTextSize(11);
        int padding = DensityUtil.dip2px(6);
        int padding3 = DensityUtil.dip2px(3);
        tv.setPadding(padding, padding3, padding, padding3);
        tv.setBackgroundResource(R.drawable.bg_rang_grey);
        tv.setClickable(true);
        tv.setText(txt);
        tv.setLayoutParams(layoutParams);
        return tv;
    }

    private void loadPersonTag(List<UserInfo.TagsInfoBean> tags_info) {
        if (tags_info == null) {
            return;
        }
        for (int i = 0; i < tags_info.size(); i++) {
            final TextView target = new TextView(getApplicationContext());
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            target.setTag(R.id.tag, tags_info.get(i).getTag_id());
            initViewSet(target, tags_info.get(i).getTag_name(), layoutParams);
            flowLayout.addView(target);
        }

        initFlow1Click();
    }

    private void uploadPersonInfo(final UserInfo userInfo) {
        String tags = "";
        for (int i = 0; i < userInfo.getTags_info().size(); i++) {
            tags += userInfo.getTags_info().get(i).getTag_id() + ";";
        }
        new UserEditRequest().setTag(tags).post(new NetReqCallback<AlertMessageResponse>() {
            @Override
            public void apiRequestFail(String message, String method) {

            }

            @Override
            public void apiRequestSuccess(AlertMessageResponse o, String method) {
                if (o.isCompleteNewBie())
                    AppRxBus.getInstance().post(new TaskCompleteEvent());
                AccountDataRepo.getAccountManager().remove();
                AccountDataRepo.getAccountManager().save(userInfo);
                sendBrodcast(Constant.PersonTag);
                finish();
            }
        });
//        new UserApiImpl().userEdit(getUUId(), userInfo.getNickname(), userInfo.getTelephone(), tags, userInfo.getGender(), userInfo.getHeadimg(), new NetReqCallback() {
//            @Override
//            public void apiRequestFail(String message,String method) {
//                showToastShort(message);
//            }
//
//            @Override
//            public void apiRequestSuccess(Object o,String method) {
//
//            }
//
//
//        });
    }
}
