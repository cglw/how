package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsLinearlayoutView;
import com.prettyyes.user.app.view.tvbtnetv.EditTextDel;
import com.prettyyes.user.core.utils.AppRxBus;

/**
 * Created by chengang on 2017/7/5.
 */

public class SearchView extends AbsLinearlayoutView {
    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        String hint = a.getString(R.styleable.SearchView_search_hint);
        edit_input.setText(hint);


    }

    private EditTextDel edit_input;

    @Override
    public int bindLayout() {
        return R.layout.layout_search;
    }

    @Override
    public void initViews() {
        edit_input = (EditTextDel) getView(R.id.edit_input);

        edit_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    AppRxBus.getInstance().post(edit_input.getText().toString().trim());
                    //点击搜索要做的操作
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindowToken(), 0);
                }
                return false;
            }
        });


    }

    @Override
    public void setDataByModel(Object data) {

    }
}
