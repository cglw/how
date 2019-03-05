package com.prettyyes.user.app.view.component;

import android.view.LayoutInflater;
import android.view.View;

import com.blog.www.guideview.Component;
import com.prettyyes.user.R;

/**
 * Created by binIoter on 16/6/17.
 */
public class GuideThreeBottomComponent implements Component {

  @Override public View getView(LayoutInflater inflater) {
    return inflater.inflate(R.layout.layout_guide_start_use_bottom,null);
  }

  @Override public int getAnchor() {
    return Component.ANCHOR_BOTTOM;
  }

  @Override public int getFitPosition() {
    return Component.FIT_CENTER;
  }

  @Override public int getXOffset() {
    return 0;
  }

  @Override public int getYOffset() {
    return 20;
  }

  @Override
  public boolean LayoutFullScreen() {
    return false;
  }
}
