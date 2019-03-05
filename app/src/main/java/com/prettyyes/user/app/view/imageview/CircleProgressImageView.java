package com.prettyyes.user.app.view.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.prettyyes.user.R;

/**
 * Created by cc on 2015/12/30.
 */
public class CircleProgressImageView extends ImageView {

    private int maxProgress = 100;
    private int progress = 0;
    private int progressStrokeWidth = 4;
    private String path = "";

    RectF oval;
    Paint paint;
    public CircleProgressImageView(Context context) {
        super(context);
        oval = new RectF();
        paint = new Paint();
    }
    public CircleProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oval = new RectF();
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {

        // TODO 32自动生成的方法存根
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();
        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }
        maxProgress = 100;

        paint.setAntiAlias(true); // 设置画笔为抗锯齿
        paint.setColor(Color.WHITE); // 设置画笔颜色
        canvas.drawColor(Color.TRANSPARENT); // 白色背景
        paint.setStrokeWidth(progressStrokeWidth); //线宽
        paint.setStyle(Paint.Style.STROKE);

        if (progress == 0) {

            Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.img_del);
            Bitmap mScaledBitmap = Bitmap.createScaledBitmap(bitmap, getWidth(), getHeight(), false);

            canvas.drawBitmap(mScaledBitmap, 0, 0, paint);
            bitmap.recycle();
            bitmap=null;
        } else if (progress > 0 && progress < 100) {
            oval.left = width / 4 + progressStrokeWidth / 2;
            oval.top = height / 4 + progressStrokeWidth / 2;
            oval.right = width * 3 / 4 - progressStrokeWidth / 2;
            oval.bottom = height * 3 / 4 - progressStrokeWidth / 2;
            canvas.drawArc(oval, -90, 360, false, paint);
            paint.setColor(Color.rgb(0x57, 0x87, 0xb6));
            canvas.drawArc(oval, -90, ((float) progress / maxProgress) * 360, false, paint);
            paint.setStrokeWidth(1);
            String text = progress + "%";
            int textHeight = height / 8;
            paint.setTextSize(textHeight);
            int textWidth = (int) paint.measureText(text, 0, text.length());
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + textHeight / 2, paint);
        } else if (progress == 100) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.success);
            int bitheight = bitmap.getHeight();
            int bitWidth = bitmap.getWidth();
            canvas.drawBitmap(bitmap, width - bitWidth, 0, paint);
            bitmap.recycle();
        }
    }

    public int getMaxProgress() {
        return maxProgress;
    }
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
    public void setProgress(int progress) {
        this.progress = progress;
        this.invalidate();

    }
    public int getProgress() {
        return progress;
    }

    public void setProgressNotInUiThread(int progress) {
        this.progress = progress;
        this.postInvalidate();
    }

    public void setPtah(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
