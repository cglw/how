package com.prettyyes.user.app.ui.appview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.ui.order.WishListActivity;

/**
 * Created by chengang on 2017/6/2.
 */

public class AddWishSuccessDialog extends Dialog{


    private Button btn_go_wishlist;
    private ImageView img_close;

    public AddWishSuccessDialog(final Context context) {
        super(context,R.style.simple_dialog);
        setContentView(R.layout.view_add_wishlist_success);


        btn_go_wishlist = (Button) findViewById(R.id.btn_go_wishlist);
        img_close = (ImageView) findViewById(R.id.img_close);


        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
        btn_go_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishListActivity.goWishListActivity(context);
                dismiss();
            }
        });
    }



}
