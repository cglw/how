package com.prettyyes.user.app.ui.how;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.mvpPresenter.AskActPresenter;
import com.prettyyes.user.app.mvpPresenter.QuestionEntity;
import com.prettyyes.user.app.mvpView.AskActView;
import com.prettyyes.user.app.ui.appview.SelectMediaView;
import com.prettyyes.user.app.view.FlowLayout;
import com.prettyyes.user.app.view.tvbtnetv.AutoVerticalScrollTextView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.StatusBarUtil;
import com.prettyyes.user.model.v8.SellerInfoEntity;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import static com.prettyyes.user.core.utils.AppUtil.blur;
import static com.prettyyes.user.data.DataCenter.bgBitmap;

/**
 * 发问
 */
public class AskActivity extends BaseActivity implements View.OnTouchListener, AskActView {
    private static final String TAG = "AskActivity";

    @ViewInject(R.id.btn_askAct_sendquestion)
    private Button btn_post_ask;
    @ViewInject(R.id.editdel_askAct_question)
    private EditTextDel mentionEditText;
    @ViewInject(R.id.img_askAct_bg)
    private ImageView img_bg;
    @ViewInject(R.id.img_askAct_close)
    private ImageView img_close;
    @ViewInject(R.id.selectPhotoView_askAct)
    private SelectMediaView selectPhotoView;
    @ViewInject(R.id.toggle_askAct_isOpen)
    private ToggleButton tglbtn_isopen;
    @ViewInject(R.id.tv_askAct_autoScroll)
    private AutoVerticalScrollTextView autoVerticalScrollTextView;
    @ViewInject(R.id.tv_askAct_leftTv)
    private TextView tv_leftTv;

    @ViewInject(R.id.view_aksAct_selectKol)
    private View view_selectkol;
    @ViewInject(R.id.flowlayout_aksAct_kol)
    private FlowLayout flowlayout_kol;
    @ViewInject(R.id.tv_how_to_ask)
    private TextView tv_how_to_ask;
    @ViewInject(R.id.view_ask_root)
    private View view_activity_ask;
    @ViewInject(R.id.coordinatorLayout)
    private View coordinatorLayout;

    private CharSequence temp;
    private AskActPresenter askActPresenter = new AskActPresenter(this);
    private ArrayList<SellerInfoEntity> sellerArray = new ArrayList<>();
    private TextWatcher watcher;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        sellerArray = (ArrayList<SellerInfoEntity>) getIntent().getSerializableExtra("seller_info");
        QuestionEntity q = (QuestionEntity) getIntent().getSerializableExtra("question");
        if (q == null)
            return;
        askActPresenter.getQuestionEntity().setAct_id(q.getAct_id());
        askActPresenter.getQuestionEntity().setTopic_hash_tag(q.getTopic_hash_tag());
        askActPresenter.getQuestionEntity().setTopic_id(q.getTopic_id());
        askActPresenter.getQuestionEntity().setHash_tag(q.getHash_tag());

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_ask;
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);

//        setTranslucenBar();
    }

    @Override
    protected void initViews() {
        hideHeader();
        view_activity_ask.setFocusable(true);//获取焦点，因为Scrollview的原因
        view_activity_ask.setFocusableInTouchMode(true);
        view_activity_ask.requestFocus();
        if (bgBitmap != null)
            img_bg.setImageBitmap(bgBitmap);
        selectPhotoView.setMax(4);
        selectPhotoView.setOnSelectImage(true);
        selectPhotoView.setTopShowViewPorxyRoot(coordinatorLayout);
        askActPresenter.getBulletscreen();


    }


    public static void goAskActivity(Context context) {


        try {
            if (context instanceof Activity) {
                bgBitmap = blur((Activity) context);
            }
        } catch (Exception ee) {

        }
        Intent intent = new Intent(context, AskActivity.class);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivityTopIn(intent);
        else
            context.startActivity(intent);
    }

    public static void goAskActivity(Context context, QuestionEntity question, ArrayList<SellerInfoEntity> sellerInfo_Entity_list) {

        if (context instanceof Activity) {
            bgBitmap = blur((Activity) context);
        }
        Intent intent = new Intent(context, AskActivity.class);
        intent.putExtra("seller_info", sellerInfo_Entity_list);
        intent.putExtra("question", question);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivityTopIn(intent);
        else
            context.startActivity(intent);

    }

    public static void goAskActivity(Context context, QuestionEntity question, SellerInfoEntity sellerinfo) {


        if (context instanceof Activity) {
            bgBitmap = blur((Activity) context);
        }
        Intent intent = new Intent(context, AskActivity.class);
        ArrayList<SellerInfoEntity> objects = new ArrayList<>();
        objects.add(sellerinfo);
        intent.putExtra("seller_info", objects);
        intent.putExtra("question", question);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivityTopIn(intent);
        else
            context.startActivity(intent);
    }

    public static void goAskActivity(Context context, SellerInfoEntity sellerinfo) {


        if (context instanceof Activity) {
            bgBitmap = blur((Activity) context);
        }
        Intent intent = new Intent(context, AskActivity.class);
        ArrayList<SellerInfoEntity> objects = new ArrayList<>();
        objects.add(sellerinfo);
        intent.putExtra("seller_info", objects);
        if (context instanceof BaseActivity)
            ((BaseActivity) context).nextActivityTopIn(intent);
        else
            context.startActivity(intent);
    }


    @Override
    protected void setListener() {
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivityTopout();
            }
        });
        view_activity_ask.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                v.requestFocus();
                return false;
            }
        });

        mentionEditText.setClearback(new EditTextDel.ClearCallback() {
            @Override
            public void call() {
                setLeftTv(0);

            }
        });
        mentionEditText.setOnTouchListener(this);
        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setLeftTv(temp.length());

            }
        };
        mentionEditText.addTextChangedListener(watcher);
        btn_post_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askActPresenter.postQuestion();
            }
        });
        view_selectkol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(), SearchKolActivity.class);
                intent.putExtra("seller_info", askActPresenter.getSeller_array());
                getThis().startActivityForResult(intent, 298);
            }
        });
        tv_how_to_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goHowToAsk(getThis());
            }
        });

    }

    private ArrayList<SellerInfoEntity> getSellerInfo() {
        return sellerArray;
    }

    @Override
    protected void loadData() {

        askActPresenter.refreshFlowlayout(sellerArray);

        askActPresenter.setHashTag(askActPresenter.getQuestionEntity());


//        setLeftTv(askActPresenter.getQuestionEntity().getHash_tag().length());

        setLeftTv(mentionEditText.getText().length());

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//
//    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        back();
    }

    @Override
    public void back() {
//        super.back();
        try {
            selectPhotoView.check();
            backActivityTopout();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtil.showToastShort(e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 298) {

                askActPresenter.refreshFlowlayout((ArrayList<SellerInfoEntity>) data.getSerializableExtra("seller_info"));

            } else {
                selectPhotoView.onActivityResult(requestCode, resultCode, data);

            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void setLeftTv(int num) {
        tv_leftTv.setText(String.format("还可输入%s个字", 140 - num));
        if (num == 0) {
            autoVerticalScrollTextView.setVisibility(View.VISIBLE);
        } else
            autoVerticalScrollTextView.setVisibility(View.GONE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if (((v.getId() == R.id.editdel_askAct_question) && canVerticalScroll(mentionEditText))) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if (event.getAction() == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }


    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    @Override
    public EditTextDel getQuestionView() {
        return mentionEditText;
    }

    @Override
    public SelectMediaView getSelectPhoto() {
        return selectPhotoView;
    }

    @Override
    public ToggleButton getIsOpenView() {
        return tglbtn_isopen;
    }

    @Override
    public FlowLayout getFlowly_Kol() {
        return flowlayout_kol;
    }

    @Override
    public ArrayList<Integer> getKol_Ids() {
        return null;
    }

    @Override
    public void setBulletscreen(String[] res) {
        autoVerticalScrollTextView.setStringRes(res);
    }

    @Override
    public void showFailedError(String tv) {
        showToastShort(tv);
    }

    @Override
    public BaseActivity getThis() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        autoVerticalScrollTextView.release();
        mentionEditText.removeTextChangedListener(watcher);
        selectPhotoView.releseResource();

    }

}
