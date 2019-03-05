package com.prettyyes.user.app.view.component;

import android.view.LayoutInflater;
import android.view.View;

import com.blog.www.guideview.Component;
import com.prettyyes.user.R;

/**
 * Created by chengang on 2018/1/12.
 */

public class GuideJumpComponent implements Component {

    public GuideJumpComponent(View.OnClickListener onClickListener)
    {
        this.onClickListener=onClickListener;
    }

    @Override
    public View getView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.layout_guide_jump, null);
        View viewById = inflate.findViewById(R.id.tv_jump);
        if (onClickListener != null)
            viewById.setOnClickListener(onClickListener);
        return inflate;
    }

    private View.OnClickListener onClickListener;



    @Override
    public int getAnchor() {
        return 0;
    }

    @Override
    public int getFitPosition() {
        return 0;
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 0;
    }

    @Override
    public boolean LayoutFullScreen() {
        return true;
    }
}
