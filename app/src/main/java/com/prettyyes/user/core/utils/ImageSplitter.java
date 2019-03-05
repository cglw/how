package com.prettyyes.user.core.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengang on 2018/3/27.
 */

public class ImageSplitter {

    public static Bitmap zoomImgWith(Bitmap bm, int newWidth) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scale = ((float) newWidth) / width;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        bm.recycle();
        return newbm;
    }

    /**
     * 将图片切成 , piece *piece
     *
     * @param bitmap
     * @param
     * @return
     */
    public static List<ImagePiece> split(Bitmap bitmap, int row, int coloum) {
        if (row <= 1)
            row = 1;
        if (row <= coloum)
            coloum = 1;

        List<ImagePiece> pieces = new ArrayList<ImagePiece>(row * coloum);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();


        Log.e("TAG", "bitmap Width = " + width + " , height = " + height);
        int pieceWidth = width / coloum;
        int pieceHeight = height / row;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coloum; j++)
            {
                ImagePiece imagePiece = new ImagePiece();
                imagePiece.index = j + i * coloum;
                int xValue = j * pieceWidth;
                int yValue = i * pieceHeight;

                imagePiece.bitmap = Bitmap.createBitmap(bitmap, xValue, yValue,
                        pieceWidth, pieceHeight);
                pieces.add(imagePiece);
            }
        }
        return pieces;
    }

    public static class ImagePiece {
        public int index = 0;
        public Bitmap bitmap = null;
    }
}
