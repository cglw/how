package com.prettyyes.user.app.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.base
 * Author: SmileChen
 * Created on: 2016/8/9
 * Description: Nothing
 */
public interface IBaseFragment {
    public int bindLayout();
    public View bindView();
    public void initParms(Bundle parms);
    public void initView(final View view);
    public void doBusiness(Context mContext);
    public void lazyInitBusiness(Context mContext);

}
