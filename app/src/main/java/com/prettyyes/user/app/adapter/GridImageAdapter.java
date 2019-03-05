package com.prettyyes.user.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.StringUtils;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.ui.appview.model.HowLocalMedia;
import com.prettyyes.user.app.view.progress.CirclePercentView;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.FileUtils;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.LogUtil;

import java.io.File;
import java.util.List;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.pictureselector.adapter
 * email：893855882@qq.com
 * data：16/7/27
 */
public class GridImageAdapter extends
        AbsRecycleAdapter<HowLocalMedia> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private int selectMax = 9;
    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;


    public GridImageAdapter(Context context, onAddPicClickListener mOnAddPicClickListener) {
        super(context, R.layout.gv_filter_image);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }


    public interface onAddPicClickListener {
        void onAddPicClick();
    }


    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public void setList(List<HowLocalMedia> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMimeType() == PictureMimeType.ofVideo()) {
                long fileSize = FileUtils.getFileSize(new File(list.get(i).getPath()));
                if (fileSize >= 25 * 1024 * 1024) {
                    AppUtil.showToastShort("上传视频的大小不超过25M");
                    return;
                }
            }
        }
        this.items = list;
    }


    @Override
    public int getItemCount() {
        if (items.size() < selectMax) {
            return items.size() + 1;
        } else {
            return items.size();
        }
    }

    @Override
    protected void bindData(final AbsRecycleViewHolder viewHolder, final HowLocalMedia localMedia, final int position) {
        LogUtil.i(TAG, "setPercentNoAnimi0000bindData");

        //少于8张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            mImg.setImageResource(R.mipmap.ic_upload);
            mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnAddPicClickListener.onAddPicClick();
                }
            });
            ((View) cprogress.getParent()).setVisibility(View.GONE);
            ll_del.setVisibility(View.INVISIBLE);

        } else {
            LogUtil.i(TAG, "setPercentNoAnimi0000");
            ((View) cprogress.getParent()).setVisibility(View.VISIBLE);
            ll_del.setVisibility(View.VISIBLE);
            ll_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = viewHolder.getAdapterPosition();
                    // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                    // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                    if (index != RecyclerView.NO_POSITION) {


                        String path = items.get(index).getPath();

                        if (index != RecyclerView.NO_POSITION) {
                            items.remove(index);
                            notifyItemRemoved(index);
                            notifyItemRangeChanged(index, items.size());
                        }
                        if (mItemDeleteListener != null) {
                            mItemDeleteListener.onItemClick(path, index, view);
                        }


                    }

                }
            });
            LocalMedia media = items.get(position);
            int mimeType = media.getMimeType();
            String path = "";
            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            // 图片
            if (media.isCompressed()) {
                Log.i("compress image result:", new File(media.getCompressPath()).length() / 1024 + "k");
                Log.i("压缩地址::", media.getCompressPath());
            }

            Log.i("原图地址::", media.getPath());
            int pictureType = PictureMimeType.isPictureType(media.getPictureType());
            if (media.isCut()) {
                Log.i("裁剪地址::", media.getCutPath());
            }
            long duration = media.getDuration();
            tv_duration.setVisibility(pictureType == PictureConfig.TYPE_VIDEO
                    ? View.VISIBLE : View.GONE);
            if (mimeType == PictureMimeType.ofAudio()) {
                tv_duration.setVisibility(View.VISIBLE);
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.picture_audio);
                StringUtils.modifyTextViewDrawable(tv_duration, drawable, 0);
            } else {
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.video_icon);
                StringUtils.modifyTextViewDrawable(tv_duration, drawable, 0);
            }
            tv_duration.setText(DateUtils.timeParse(duration));
            if (mimeType == PictureMimeType.ofAudio()) {
                mImg.setImageResource(R.drawable.audio_placeholder);
            } else {
//                RequestOptions options = new RequestOptions()
//                        .centerCrop()
//                        .placeholder(R.color.color_f6)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL);
//                Glide.with(viewHolder.itemView.getContext())
//                        .load(path)
//                        .apply(options)
//                        .into(mImg);
               ImageLoadUtils.loadListimg(context,path,mImg);
            }
            //itemView 的点击事件
            if (mItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = viewHolder.getAdapterPosition();
                        mItemClickListener.onItemClick(adapterPosition, v);
                    }
                });
            }

            cprogress.setTag(media.getPath());
            LogUtil.i(TAG, "setPercentNoAnimi");
            cprogress.setPercentNoAnimi(localMedia.getProgress());
            if (localMedia.getProgress() == 100)
                ((View) cprogress.getParent()).setVisibility(View.GONE);


            cprogress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = viewHolder.getAdapterPosition();
                    if (mItemReloadListener != null) {
                        mItemReloadListener.onItemClick(items.get(index), index, v);
                    }
                }
            });
        }
    }


    @Override
    protected void bindView(AbsRecycleViewHolder holder) {
        mImg = (ImageView) holder.findViewById(R.id.fiv);
        ll_del = (LinearLayout) holder.findViewById(R.id.ll_del);
        tv_duration = (TextView) holder.findViewById(R.id.tv_duration);
        cprogress = (CirclePercentView) holder.findViewById(R.id.cprogress);
    }

    ImageView mImg;
    LinearLayout ll_del;
    TextView tv_duration;
    //    TextView tv_progress;
    CirclePercentView cprogress;

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }


    private boolean isShowAddItem(int position) {
        int size = items.size() == 0 ? 0 : items.size();
        return position == size;
    }

    public int getUploadSuccessCount() {
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            if (this.getItemData(i).isUploadSuccess())
                count++;
        }
        return count;
    }


    protected OnItemClickListener mItemClickListener;
    protected OnItemDeleteListener mItemDeleteListener;
    protected OnItemReloadListener mItemReloadListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public interface OnItemDeleteListener {
        void onItemClick(String path, int position, View v);
    }

    public interface OnItemReloadListener {
        void onItemClick(HowLocalMedia howlocalmedia, int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.mItemDeleteListener = listener;
    }

    public void setOnItemReloadListener(OnItemReloadListener listener) {
        this.mItemReloadListener = listener;
    }
}
