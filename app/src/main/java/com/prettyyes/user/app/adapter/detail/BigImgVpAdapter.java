package com.prettyyes.user.app.adapter.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.prettyyes.user.R;
import com.prettyyes.user.app.base.AbsRecycleAdapter;
import com.prettyyes.user.app.base.AbsRecycleViewHolder;
import com.prettyyes.user.app.base.AbsVpAdapter;
import com.prettyyes.user.app.view.dialog.MyBottomDialog;
import com.prettyyes.user.core.utils.AppUtil;
import com.prettyyes.user.core.utils.FormatTools;
import com.prettyyes.user.core.utils.ImageHelper;
import com.prettyyes.user.core.utils.ImageLoadUtils;
import com.prettyyes.user.core.utils.PermissionUtils;
import com.prettyyes.user.core.utils.StringUtils;

import org.xutils.common.util.MD5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project Name: user
 * Package Name: com.prettyyes.user.app.adapter.del
 * Author: SmileChen
 * Created on: 2016/10/25
 * Description: Nothing
 */
public class BigImgVpAdapter extends AbsVpAdapter<String> {
    ArrayList<String> bottom_item;

    public BigImgVpAdapter(Context context, ArrayList<String> datas) {
        super(context, datas, R.layout.item_vp_bigimg);
        bottom_item = new ArrayList<>();
        bottom_item.add("保存图片");
        bottom_item.add("取消");
    }

    private final static int tag_url = 5 << 24;

    @Override
    public void showData(ViewHolder vHolder, String data, int position) {
        img_bigimgAdp_img.enable();
        img_bigimgAdp_img.setScaleType(ImageView.ScaleType.FIT_CENTER);

        ImageLoadUtils.loadBigImg(context, data, img_bigimgAdp_img);
//        Glide.with(context).load(data).placeholder(R.mipmap.ic_defaultimg).error(R.mipmap.ic_defaultimg).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).dontAnimate().into(img_bigimgAdp_img);
        img_bigimgAdp_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(0, R.anim.a3);

            }
        });

        img_bigimgAdp_img.setTag(tag_url, data);
        img_bigimgAdp_img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                final MyBottomDialog myBottomDialog = new MyBottomDialog(context);
                myBottomDialog.show();
                myBottomDialog.getBottomDialogAdapter().addAll(bottom_item);

                myBottomDialog.getBottomDialogAdapter().setMyOnItemClickListener(new AbsRecycleAdapter.OnMyItemClickListener<String>() {
                    @Override
                    public void clickItem(AbsRecycleViewHolder holder, View view, String o, int position) {
                        myBottomDialog.dismiss();

                        if (position == 0) {


                            PermissionUtils.requestSinglePermission((Activity) context, new PermissionUtils.PermissionGrant() {
                                @Override
                                public void onPermissionGranted(int requestCode) {
                                    String url = (String) v.getTag(tag_url);
                                    saveImageToSysAlbum((PhotoView) v, url);
                                }
                            }, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE);
                        }

                    }
                });

                return false;
            }
        });
    }

    private PhotoView img_bigimgAdp_img;

    private void saveImageToSysAlbum(PhotoView photoView, String url) {
        String save_path = "";
        if (url.endsWith("gif")) {
            GifDrawable drawable = (GifDrawable) photoView.getDrawable();
            byte[] data = FormatTools.Drawable2Bytes(drawable);
            if (data == null) {
                AppUtil.showToastShort("图片还没有下载好");
                return;
            }
            save_path = ImageHelper.saveGif(data, MD5.md5(url) + ".gif");


        } else {
            BitmapDrawable bmpDrawable = (BitmapDrawable) photoView.getDrawable();
            Bitmap bmp = bmpDrawable.getBitmap();
            if (bmp == null) {
                AppUtil.showToastShort("图片还没有下载好");
                return;
            }
            try {
                save_path = ImageHelper.saveBitmapToFile(bmp, MD5.md5(url) + "." + url.substring(url.lastIndexOf(".") + 1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.strIsEmpty(save_path)) {
            AppUtil.showToastShort("保存至" + save_path);
            Uri uri = Uri.fromFile(new File(save_path));
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        }

    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        img_bigimgAdp_img = (PhotoView) viewHolder.getView(R.id.img_bigimgAdp_img);
    }

}
