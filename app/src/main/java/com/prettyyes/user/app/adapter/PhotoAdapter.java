package com.prettyyes.user.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.core.AlertDialogManager;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.ImageLoadUtils;

import java.util.ArrayList;

/**
 * Created by chengang on 2017/6/25.
 */

public class PhotoAdapter extends AbsRecycleAdapter<String> {
    private static final String TYPE_PHOTO = "photo";

    private PhotoCallback callback;

    public PhotoAdapter(Context context, PhotoCallback p) {
        super(context, R.layout.item_photo);
        this.callback = p;
    }

    private int img_width;
    private int coloum = 4;

    public void setColoum(int coloum) {
        this.coloum = coloum;
    }

    private boolean needDelete = true;
    private boolean needBigImg = false;

    public int getColoum() {
        return coloum;
    }

    public boolean isNeedDelete() {
        return needDelete;
    }

    public void setNeedDelete(boolean needDelete) {
        this.needDelete = needDelete;
    }

    public boolean isNeedBigImg() {
        return needBigImg;
    }

    public void setNeedBigImg(boolean needBigImg) {
        this.needBigImg = needBigImg;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    @Override
    protected void bindData(AbsRecycleViewHolder holder, String photoInfo, final int position) {


        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) img.getLayoutParams();
        layoutParams.width = img_width;
        layoutParams.height = img_width;
        if ((position + 1) % coloum == 0) {
            layoutParams.setMargins(0, 0, 0, AppUtil.dip2px(8));

        } else {
            layoutParams.setMargins(0, 0, AppUtil.dip2px(8), AppUtil.dip2px(8));

        }
            ImageLoadUtils.loadListimg(context, photoInfo, img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (needDelete)
                    AlertDialogManager.getInstance().show((Activity) context, "你确定删除图片吗", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PhotoAdapter.this.remove(position);
                            if (callback != null)
                                callback.deleteImg(position);
                        }
                    });
                else if(needBigImg)
                {
                    JumpPageManager.getManager().goBigImgActivity(context, (ArrayList<String>) getItems(),position);
                }

            }
        });

    }


    public interface PhotoCallback {
        void deleteImg(int position);
    }

    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        img = holder.getView(R.id.img_photo);
    }

    private ImageView img;



}


