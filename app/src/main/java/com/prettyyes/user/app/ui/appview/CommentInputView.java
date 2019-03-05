package com.prettyyes.user.app.ui.appview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.recyclePagerAdapter.AbsRelativelayoutView;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;

/**
 * Created by chengang on 2018/1/15.
 */

public class CommentInputView extends AbsRelativelayoutView {
    public CommentInputView(Context context) {
        super(context);
    }

    public CommentInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_comment_input;
    }

    @Override
    public void initViews() {
        edit_comment = (EditText) findViewById(R.id.edit_comment);
        btn_send = (Button) findViewById(R.id.btn_send);

    }

    @Override
    public void setListener() {
        super.setListener();
        edit_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    btn_send.setEnabled(true);
                } else {
                    btn_send.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputCallback != null) {
                    SoftKeyboardUtil.closeInputMethod(getContext(), v);
                    inputCallback.send(edit_comment.getText().toString());
                    edit_comment.setText("");

                }


            }
        });
    }
    public void fouse()
    {
        edit_comment.setFocusable(true);
        edit_comment.setFocusableInTouchMode(true);
        edit_comment.requestFocus();
    }

    public Button getSend() {
        return btn_send;
    }

    public EditText getEditText() {
        return edit_comment;
    }

    public String getCommentText() {
        return edit_comment.getText().toString();
    }


    private InputCallback inputCallback;

    public void setInputCallback(InputCallback inputCallback) {
        this.inputCallback = inputCallback;
    }

    public void setHint(String hint) {
        edit_comment.setText("");
        edit_comment.setHint(hint);
    }

    public interface InputCallback {
        void send(String text);
    }

    private EditText edit_comment;
    private Button btn_send;

    @Override
    public void setDataByModel(Object data) {

    }
}
