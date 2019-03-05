package com.prettyyes.user.app.ui.how;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.api.NetReqCallback;
import com.prettyyes.user.api.netXutils.requests.AttributeEditRequest;
import com.prettyyes.user.api.netXutils.response.AttributeEditRes;
import com.prettyyes.user.app.adapter.AttributeItemAdapter;
import com.prettyyes.user.app.base.BaseActivity;
import com.prettyyes.user.app.ui.appview.SpecificationTvView;
import com.prettyyes.user.app.view.FlowLayout;
import com.prettyyes.user.app.view.lvandgrid.DividerItemDecoration;
import com.prettyyes.user.app.view.lvandgrid.MoveRecycleview;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.event.AttrSelectEvent;
import com.prettyyes.user.core.rv.FullyLinearLayoutManager;
import com.prettyyes.user.core.utils.AppRxBus;
import com.prettyyes.user.core.utils.ClickUtils;
import com.prettyyes.user.core.utils.RxCallback;
import com.prettyyes.user.core.utils.StringUtils;
import com.prettyyes.user.model.applocal.AttrName;
import com.prettyyes.user.model.applocal.AttrVaule;

import java.util.ArrayList;
import java.util.List;

public class EditAttrsActivity extends BaseActivity {

    private EditText mEdit_sp_name;
    private FlowLayout mFlowlayout_sp;
    private EditText mEdit_sp_info;
    private TextView mTv_add_sp_info;
    private TextView tv_have_added;
    private MoveRecycleview mRv;
    private Button btn_add;
    private AttributeItemAdapter attributeItemAdapter;
    private int change_index = 0;

    @Override
    public void needLoading(boolean need) {
        super.needLoading(false);
    }

    private void bindViews() {
        mEdit_sp_name = (EditText) findViewById(R.id.edit_sp_name);
        mFlowlayout_sp = (FlowLayout) findViewById(R.id.flowlayout_sp);
        mEdit_sp_info = (EditText) findViewById(R.id.edit_sp_info);
        mTv_add_sp_info = (TextView) findViewById(R.id.tv_add_sp_info);
        mRv = (MoveRecycleview) findViewById(R.id.rv);
        btn_add = (Button) findViewById(R.id.btn_add);
        tv_have_added = (TextView) findViewById(R.id.tv_have_added);
    }


    @Override
    public void handSoft(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (ClickUtils.isContain(mTv_add_sp_info, ev.getRawX(), ev.getRawY()) || ClickUtils.isContain(mTv_add_sp_info, ev.getRawX(), ev.getRawY()))
                return;
            View currentFocus = getCurrentFocus();
            if (isHideInput(currentFocus, ev)) {
                HideSoftInput(currentFocus.getWindowToken());
            }
        }
    }
    @Override
    protected int bindLayout() {
        return R.layout.activity_specifications;
    }


    @Override
    protected void initViews() {
        setActivtytitle(R.string.title_activity_specifications);
        bindViews();

        attributeItemAdapter = new AttributeItemAdapter(this);
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setThisAdapter(attributeItemAdapter, 1);
        mRv.setNestedScrollingEnabled(false);

        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST, R.drawable.item_divider_h));


        List<AttrName> attrNames = JumpPageManager.getManager().getIntentParmas(this).getAttrNames();
        if (attrNames != null)
            attributeItemAdapter.addAll(attrNames);
        attributeItemAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (attributeItemAdapter.getDataCount() > 0) {
                    tv_have_added.setVisibility(View.VISIBLE);
                } else {
                    tv_have_added.setVisibility(View.GONE);

                }
            }
        });
        if (attributeItemAdapter.getDataCount() > 0) {
            tv_have_added.setVisibility(View.VISIBLE);
        } else {
            tv_have_added.setVisibility(View.GONE);

        }

    }

    @Override
    public void onBackPressed() {
        back();
//        DialogHelper.getInstance().getDialogNoCancel(R.string.template_edit_complete, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        }).show();
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mSubscription = AppRxBus.getInstance().toObservable(Object.class).subscribe(new RxAction1<Object>() {
//            @Override
//            public void callback(Object o) {
//                if (o instanceof AttrName) {
//                    //获得修改的名称
//                    mEdit_sp_name.setText(((AttrName) o).getName());
//                    change_index = ((AttrName) o).getIndex();
//                    //移除
//                    mFlowlayout_sp.removeAllViews();
//                    //重设置flowlayout
//                    for (int i = 0; i < ((AttrName) o).getValues().size(); i++) {
//                        addSpInfo(((AttrName) o).getValues().get(i).getAttr_value());
//                    }
//                    //改变button文字
//                    btn_add.setText(getString(R.string.spftcation_name_change));
//                    mEdit_sp_name.setEnabled(false);
//
//                }
//
//            }
//        });
        mSubscription = AppRxBus.getInstance().subscribeEvent(new RxCallback<AttrName>() {
            @Override
            protected void back(AttrName o) {
                //获得修改的名称
                mEdit_sp_name.setText(((AttrName) o).getName());
                change_index = ((AttrName) o).getIndex();
                //移除
                mFlowlayout_sp.removeAllViews();
                //重设置flowlayout
                for (int i = 0; i < ((AttrName) o).getValues().size(); i++) {
                    addSpInfo(((AttrName) o).getValues().get(i).getAttr_value());
                }
                //改变button文字
                btn_add.setText(getString(R.string.spftcation_name_change));
                mEdit_sp_name.setEnabled(false);

            }
        });

//        setRightTvListener(getString(R.string.confirm), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final List<AttrName> items = attributeItemAdapter.getItems();
//                if (items == null || items.size() <= 0) {
//                    showToastShort(R.string.template_empty_attr);
//                    return;
//                }
//                if (attributeItemAdapter.getSelect().size() > 2) {
//                    showToastShort(R.string.template_error_selectmax);
//                    return;
//                }
//
//                DialogHelper.getInstance().getDialogNoCancel(getString(R.string.template_edit_complete), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        AppRxBus.getInstance().post(new AttrSelectEvent(attributeItemAdapter.getJsonAttr(), items));
//                        finish();
//                    }
//                }).show();
//
//
//            }
//        });

        mEdit_sp_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0)
                    mTv_add_sp_info.setVisibility(View.VISIBLE);
                else
                    mTv_add_sp_info.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEdit_sp_info.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.ACTION_DOWN) {
                    addSpInfo(mEdit_sp_info.getText().toString());
                    mEdit_sp_info.setText("");
                }
                return false;
            }
        });


        mTv_add_sp_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSpInfo(mEdit_sp_info.getText().toString());
                mEdit_sp_info.setText("");

            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = checkAttrName((Button) v);
                if (name == null) return;
                postAttriName(name);

            }
        });


    }

    @Nullable
    private String checkAttrName(Button v) {
        String name = mEdit_sp_name.getText().toString().trim();
        if (StringUtils.strIsEmpty(name)) {
            showToastShort(getString(R.string.spftcation_empty_name));
            return null;
        }
        if (mFlowlayout_sp.getChildCount() <= 0) {
            showToastShort(getString(R.string.spftcation_empty_value));
            return null;
        }

        if (v.getText().toString().equals(getString(R.string.spftcation_add_contincue))) {
            for (int i = 0; i < attributeItemAdapter.getItems().size(); i++) {
                if (attributeItemAdapter.getItemData(i).getName().equals(name)) {
                    showToastShort(getString(R.string.spftcation_repeate_add_name));
                    return null;
                }
            }
        }
        return name;
    }

    private void postAttriName(final String name) {
        final AttributeEditRequest attributeEditRequest = new AttributeEditRequest();
        attributeEditRequest.setAttr_name(name);
        btn_add.setEnabled(false);
        attributeEditRequest.post(new NetReqCallback<AttributeEditRes>() {
            @Override
            public void apiRequestFail(String message, String method) {
                btn_add.setEnabled(true);

                mEdit_sp_name.setEnabled(true);
            }

            @Override
            public void apiRequestSuccess(AttributeEditRes attributeEditRes, String method) {
                addAttrName(attributeEditRes.getAttr_id(), name);
                mEdit_sp_name.setEnabled(true);
                btn_add.setEnabled(true);


            }
        });
    }

    private void addAttrName(String attribute_id, String name) {
        AttrName attrLocal = new AttrName();
        attrLocal.setAttr_id(attribute_id);
        attrLocal.setName(name);
        ArrayList<AttrVaule> info = new ArrayList();
        for (int i = 0; i < mFlowlayout_sp.getChildCount(); i++) {
            SpecificationTvView childAt = (SpecificationTvView) mFlowlayout_sp.getChildAt(i);
            AttrVaule attrVaule = new AttrVaule();
            attrVaule.setAttr_value(childAt.getInfo());
            info.add(attrVaule);
        }
        attrLocal.setValues(info);
        attrLocal.setSelected(true);

        if (btn_add.getText().equals(getString(R.string.spftcation_name_change))) {
            attributeItemAdapter.setItemData(change_index, attrLocal);
            attributeItemAdapter.notifyItemChanged(change_index);
            btn_add.setText(getString(R.string.spftcation_add_contincue));
        } else {
            attributeItemAdapter.add(attrLocal);
        }

        mFlowlayout_sp.removeAllViews();
        mEdit_sp_name.setText("");

    }

    private void addSpInfo(String tv) {
        if (StringUtils.strIsEmpty(tv))
            return;
        if (checkSpInfo()) {
            return;
        }
        SpecificationTvView child = new SpecificationTvView(getThis());
        child.setText(tv);
        mFlowlayout_sp.addView(child);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowLayout parent = (FlowLayout) v.getParent();
                parent.removeView(v);
            }
        });
    }

    private boolean checkSpInfo() {
        for (int i = 0; i < mFlowlayout_sp.getChildCount(); i++) {
            SpecificationTvView childAt = (SpecificationTvView) mFlowlayout_sp.getChildAt(i);

            if (childAt.getInfo().equals(getAttrValue())) {
                showToastShort(getString(R.string.spftcation_repeat_value));
                return true;
            }
        }

        return false;
    }

    private String getAttrName() {
        return mEdit_sp_name.getText().toString().trim();
    }

    private String getAttrValue() {
        return mEdit_sp_info.getText().toString().trim();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void back() {
        List<AttrName> items = attributeItemAdapter.getItems();
        if (items != null || items.size() > 0) {
            AppRxBus.getInstance().post(new AttrSelectEvent(attributeItemAdapter.getJsonAttr(), attributeItemAdapter.getItems()));
        }
        super.back();

    }
}
