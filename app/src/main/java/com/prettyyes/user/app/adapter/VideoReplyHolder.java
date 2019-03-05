package com.prettyyes.user.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prettyyes.user.R;
import com.prettyyes.user.app.base.MutiTypeViewHolder;
import com.prettyyes.user.core.containter.JumpPageManager;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.model.v8.UploadMediaEntity;

/**
 * Created by chengang on 2018/1/31.
 */

public class VideoReplyHolder extends MutiTypeViewHolder {
    public VideoReplyHolder(View itemView) {
        super(itemView);
        bindView();
    }

    private UploadMediaEntity uploadMediaEntity;
    public void clear()
    {
        uploadMediaEntity=null;
    }

    public UploadMediaEntity getUploadMediaEntity() {
        if (uploadMediaEntity == null)
            uploadMediaEntity = new UploadMediaEntity();
        return uploadMediaEntity;
    }

    private boolean haveMedia = false;

    public boolean isHaveMediaRes() {
        return haveMedia;
    }

    @Override
    public void bindData(Object modle, int position, RecyclerView.Adapter adpter) {

    }

    public View getVideoReplyView() {
        return mView_select_extra_video;
    }


    public void setData(final String video_path, final String video_covery) {
        if (video_path != null && video_path.length() > 0 && video_covery != null && video_covery.length() > 0)
            haveMedia = true;
        getUploadMediaEntity().setCover_image(video_covery);
        getUploadMediaEntity().setVideo(video_path);
        ImageLoadUtils.loadListimg(context, video_covery, mImg_covery);
        mTv_edit_extra_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goAddExtraVideoUrlActivity(context, video_path, video_covery);
            }
        });
        mView_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpPageManager.getManager().goVideoActivity(context, video_path, video_covery, v);
            }
        });

    }

    public void setDeleteOnclickListener(View.OnClickListener onclickListener) {
        mImg_del.setOnClickListener(onclickListener);
    }

    @Override
    public void bindView() {
        mView_select_extra_video = findViewById(R.id.view_select_extra_video);
        mTv_edit_extra_video = findViewById(R.id.tv_edit_extra_video);
        mView_play = findViewById(R.id.view_play);
        mImg_covery = findViewById(R.id.img_covery);
        mImg_del = findViewById(R.id.img_del);
    }

    public LinearLayout mView_select_extra_video;
    public TextView mTv_edit_extra_video;
    public RelativeLayout mView_play;
    public ImageView mImg_covery;
    public ImageView mImg_del;


}
