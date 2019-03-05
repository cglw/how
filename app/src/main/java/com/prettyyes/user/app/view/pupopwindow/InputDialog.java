package com.prettyyes.user.app.view.pupopwindow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.prettyyes.user.R;
import com.prettyyes.user.core.event.InputCallback;
import com.prettyyes.user.core.utils.AnimotionUtils;
import com.prettyyes.user.core.utils.SoftKeyboardUtil;

/**
 * Created by chengang on 2017/5/16.
 */

public class InputDialog extends AbsPupopWindow {
    private static final String TAG = "InputDialog";

    public InputDialog(Activity context) {
        super(context);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setDefaultAlpha(1);

    }

    private EditText editText;
    private CheckBox checkBox;
    private View view_input_root;
    private String hint;
    private RecyclerView rv;
    private View click;
    private int itemY;

    public void setRv(RecyclerView rv, View click) {

        this.rv = rv;
        this.click = click;
        if (rv == null || click == null)
            return;
        itemY = SoftKeyboardUtil.getItemY(click);
    }

    public void setHint(String hint) {
        this.hint = hint;
        editText.setHint(hint);
    }

    @Override
    public void bindView(View view) {
        editText = (EditText) view.findViewById(R.id.edit_input);
        checkBox = (CheckBox) view.findViewById(R.id.check_confirm);
        view_input_root = view.findViewById(R.id.view_input_root);

        AnimotionUtils.animateAlphaIn(view_input_root, 1000);

    }

    private String content = "";
    private InputCallback inputCallback;

    public void setInputCallback(InputCallback inputCallback) {
        this.inputCallback = inputCallback;
    }


    private boolean show = false;

    @Override
    public void setListener() {
        super.setListener();
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckBox) v).setChecked(!((CheckBox) v).isChecked());

                if (!((CheckBox) v).isChecked() && inputCallback != null) {
                    inputCallback.inputString(content);
                }
                if (InputDialog.this != null) {
                    dismiss();
                }

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                content = s.toString();
                if (s.toString().trim().length() > 0) {
                    checkBox.setChecked(false);
                } else {
                    checkBox.setChecked(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        if (activity != null)
//            SoftKeyboardUtil.observeSoftKeyboardDestopry(activity);

    }

    @Override
    public int getLayout() {
        return R.layout.dialog_input;
    }

    @Override
    public int getLayoutHeight() {
        return 0;
    }

    @Override
    public void setHeightBy() {
        super.setHeightBy();
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void show(int id) {
        super.show(id);
    }

    @Override
    public void show(View view) {
        super.show(view);
    }
}
