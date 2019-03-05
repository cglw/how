package com.prettyyes.user.app.view.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.core.utils.LogUtil;

/**
 * Project Name: priceSelect
 * Package Name: com.prettyyes.priceselect
 * Author: SmileChen
 * Created on: 2016/7/28
 * Description: Nothing
 * 价格选择
 */
public class PriceSelectImageView extends View {

    Paint paint_center;//画中间进度
    Paint paint_all;//画整个进度
    Paint paint_allwithcolor;//画整个进度
    Paint paint_text;
    Paint paint_textRight;
    Paint paint_bitmap;
    private int height;
    private int width;
    private float lastX;
    private float leftmove = 0;
    private float leftlocation = 0;//左边圆的初始圆心位置
    private float rightlocation = 0;//右边圆的初始圆心位置
    private int rightmove = 0;

    private int height_progress = 10;//进度的高度
    private int color_center = getResources().getColor(R.color.theme_yellow);//中间的颜色
    private int color_all = getResources().getColor(R.color.grey_txt);//剩余部分颜色
    private int color_txt = Color.WHITE;//字的颜色
    boolean isfirst = true;//是否是第一次
    private int distance;//两个图片原点距离
    private int padding;
    private float density;
    private int bitmapWithSet = 20;
    private int paddingset = 32;
    private int progressHightSet = 5;
    private int bitmapWith;
    private Bitmap scaledBitmap;
    private int heightButtom;
    private int heightTop;
    private int totalWith;
    private boolean ispressLeft = false;
    private boolean ispressRight = false;


    public PriceSelectImageView(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }

    public PriceSelectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        density = getResources().getDisplayMetrics().density;
        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_ask_priceselect);
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (density * 20), (int) (density * 20), false);


    }

    public PriceSelectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        initFirst();
        initPaint();
        drawall(canvas);
        drawNewall(canvas);
        drawLeftRight(canvas);
        //  drawCenter(canvas);
        drawBitmap(canvas);
        drawText(canvas);
        super.onDraw(canvas);
    }


    private float lastLeft = 0;
    private float lastRight = 0;

    private void drawText(Canvas canvas) {


        if (ispressLeft) {
            paint_text.setTextSize(15 * density);
            paint_textRight.setTextSize(10 * density);

        } else if (ispressRight) {
            paint_text.setTextSize(10 * density);
            paint_textRight.setTextSize(15 * density);
        } else {
            paint_text.setTextSize(10 * density);
            paint_textRight.setTextSize(10 * density);
        }
        String textleft = getPrice((int) (100 * (leftlocation - padding) / totalWith));
        String textright = getPrice((int) (100 * (rightlocation - padding) / totalWith));
        float textLeftWidth = (int) paint_text.measureText(textleft, 0, textleft.length());
        float textRightWidth = (int) paint_textRight.measureText(textright, 0, textright.length());
        if ((leftlocation + textLeftWidth / 2) <= (rightlocation - textRightWidth / 2)) {
            canvas.drawText(textleft, leftlocation - textLeftWidth / 2, 3 * height / 8, paint_text);
            canvas.drawText(textright, rightlocation - textRightWidth / 2, 3 * height / 8, paint_textRight);
            lastLeft = leftlocation - textLeftWidth / 2;
            lastRight = rightlocation - textRightWidth / 2;
        } else {
            if (ispressLeft) {
                canvas.drawText(textleft, leftlocation - textLeftWidth / 2 - ((leftlocation + textLeftWidth / 2) - (rightlocation - textRightWidth / 2)), 3 * height / 8, paint_text);
                canvas.drawText(textright, rightlocation - textRightWidth / 2, 3 * height / 8, paint_textRight);
                lastLeft = leftlocation - textLeftWidth / 2 - ((leftlocation + textLeftWidth / 2) - (rightlocation - textRightWidth / 2));
                lastRight = rightlocation - textRightWidth / 2;

            } else if (ispressRight) {
                canvas.drawText(textleft, leftlocation - textLeftWidth / 2, 3 * height / 8, paint_text);
                canvas.drawText(textright, rightlocation - textRightWidth / 2 + (leftlocation + textLeftWidth / 2) - (rightlocation - textRightWidth / 2), 3 * height / 8, paint_textRight);
                lastLeft = leftlocation - textLeftWidth / 2;
                lastRight = rightlocation - textRightWidth / 2 + (leftlocation + textLeftWidth / 2) - (rightlocation - textRightWidth / 2);
            } else {
                canvas.drawText(textleft, lastRight - textLeftWidth, 3 * height / 8, paint_text);
                canvas.drawText(textright, lastRight, 3 * height / 8, paint_textRight);
            }

        }
    }

    private String getPrice(int progress) {
        if (progress >= 0 && progress <= 50) {
            return progress * 20 + "元";
        } else if (progress > 50 && progress < 100) {
            return 1000 + (progress - 50) * 40 + "元";
        } else if (progress == 100) {
            return 3000 + "+元";
        }
        return "0元";
    }

    private void drawBitmap(Canvas canvas) {
        canvas.drawBitmap(scaledBitmap, leftlocation - density * bitmapWithSet / 2, heightButtom - density * bitmapWithSet / 2, paint_bitmap);
        canvas.drawBitmap(scaledBitmap, rightlocation - density * bitmapWithSet / 2, heightButtom - density * bitmapWithSet / 2, paint_bitmap);
    }

    private void initFirst() {
        if (isfirst) {
            width = getWidth();
            height = getHeight();
            height_progress = (int) (progressHightSet * density);

            if (!ishaveset) {
                rightlocation = (int) (getWidth() - paddingset * density);
                leftlocation = (int) (paddingset * density);//圆环加圆
            }
            isfirst = false;
            distance = (int) (3 * bitmapWithSet / 2 * density);
            padding = (int) (paddingset * density);
            bitmapWith = (int) (bitmapWithSet * density);
            heightTop = height * 1 / 4;
            heightButtom = height * 3 / 4;
            totalWith = width - padding * 2;
        }
    }

    private void drawall(Canvas canvas) {
        RectF rect = new RectF();

        rect.set(padding, heightButtom - height_progress / 2, width - padding, heightButtom + height_progress / 2);
        canvas.drawRoundRect(rect, height_progress / 2, height_progress / 2, paint_all);
    }

    private void drawNewall(Canvas canvas) {
        RectF rect = new RectF();
        rect.set(padding, heightButtom - height_progress / 2, width - padding, heightButtom + height_progress / 2);
        if (true) {
            int colors[] = new int[3];
            float positions[] = new float[3];

            // 第1个点
            colors[0] = 0xFFEEC23E;
            positions[0] = 0;

            // 第2个点
            colors[1] = 0xFFF5AD4A;
            positions[1] = 0.5f;

            // 第3个点
            colors[2] = 0xFFFC6767;
            positions[2] = 1;

            LinearGradient shader = new LinearGradient(
                    padding, heightButtom - height_progress / 2, width - padding, heightButtom + height_progress / 2,
                    colors,
                    positions,
                    Shader.TileMode.MIRROR);
            paint_allwithcolor.setShader(shader);
        }


        canvas.drawRoundRect(rect, height_progress / 2, height_progress / 2, paint_allwithcolor);
    }

    private void drawCenter(Canvas canvas) {
        RectF rect = new RectF();
        rect.set(leftlocation, heightButtom - height_progress / 2, rightlocation, heightButtom + height_progress / 2);
        canvas.drawRoundRect(rect, height_progress / 2, height_progress / 2, paint_center);
    }

    private void drawLeftRight(Canvas canvas) {
        RectF rect = new RectF();
        rect.set(padding, heightButtom - height_progress / 2, leftlocation, heightButtom + height_progress / 2);
        canvas.drawRoundRect(rect, height_progress / 2, height_progress / 2, paint_all);
        rect.set(rightlocation, heightButtom - height_progress / 2, width - padding, heightButtom + height_progress / 2);
        canvas.drawRoundRect(rect, height_progress / 2, height_progress / 2, paint_all);
    }

    private void initPaint() {
        if (paint_text == null) {
            paint_text = new Paint();
            paint_text.setAntiAlias(true);
            paint_text.setColor(color_txt);
            paint_text.setTextSize(10 * density);
        }
        if (paint_textRight == null) {
            paint_textRight = new Paint();
            paint_textRight.setAntiAlias(true);
            paint_textRight.setColor(color_txt);
            paint_textRight.setTextSize(10 * density);


        }
        if (paint_center == null) {
            paint_center = new Paint();
            paint_center.setAntiAlias(true);
            paint_center.setStyle(Paint.Style.FILL);
            paint_center.setStrokeWidth(height_progress);
            paint_center.setColor(color_center);
        }
        if (paint_all == null) {
            paint_all = new Paint();
            paint_all.setAntiAlias(true);
            paint_all.setStyle(Paint.Style.FILL);
            paint_all.setStrokeWidth(height_progress);
            paint_all.setColor(color_all);
        }
        if (paint_allwithcolor == null) {
            paint_allwithcolor = new Paint();
            paint_allwithcolor.setAntiAlias(true);
            paint_allwithcolor.setStyle(Paint.Style.FILL);
            paint_allwithcolor.setStrokeWidth(height_progress);
        }
        if (paint_bitmap == null) {
            paint_bitmap = new Paint();
            paint_bitmap.setAntiAlias(true);
        }
    }

    private int tag = 2;//左边


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = event.getX();
            if (lastX <= leftlocation + bitmapWith && lastX >= leftlocation - bitmapWith) {
                tag = 0;
                ispressLeft = true;
                ispressRight = false;

            } else if (lastX <= rightlocation + bitmapWith && lastX >= rightlocation - bitmapWith) {
                tag = 1;
                ispressRight = true;
                ispressLeft = false;
            } else {
                tag = 2;
                ispressRight = false;
                ispressLeft = false;
            }
            leftmove = 0;
            rightmove = 0;
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (tag == 2) {
                return true;

            } else {
                if (tag == 0) {
                    leftmove = (event.getX() - lastX);
                    rightmove = 0;
                } else if (tag == 1) {
                    rightmove = (int) (event.getX() - lastX);
                    leftmove = 0;
                }
                lastX = event.getX();
                if (rightlocation + rightmove - (leftlocation + leftmove) >= distance) {
                    if (rightlocation + rightmove <= width - padding && leftlocation + leftmove >= padding) {
                        leftlocation += leftmove;
                        rightlocation += rightmove;
                        invalidate();

                    } else {
                        leftmove = 0;
                        rightmove = 0;
                        invalidate();
                    }
                } else {
                    leftmove = 0;
                    rightmove = 0;
                    invalidate();
                }
            }

            float deltaY = y - y;
            float deltaX = x - lastX;
            if (Math.abs(deltaX) < Math.abs(deltaY)) {
                this.getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                this.getParent().requestDisallowInterceptTouchEvent(true);
            }
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            ispressLeft = false;
            ispressRight = false;
            leftmove = 0;
            rightmove = 0;
            invalidate();
        }


        return false;
    }

    private void setMaxPrice(float price) {
        float progress = 0f;
        if (price <= 1000) {
            progress = (price * 1.0f / 20);
        } else {
            progress = (50 + (price - 1000) * 1.0f / 40);
        }
        rightlocation = (totalWith * progress * 1.0f / 100 + padding);
    }

    private void setMinPrice(float price) {
        float progress = 0f;
        if (price <= 1000) {
            progress = (price * 1.0f / 20);
        } else {
            progress = 50 + (price - 1000) * 1.0f / 40;
        }
        leftlocation = (totalWith * progress * 1.0f / 100 + padding);
    }

    public void setPrice(float Max, float Min) {
//        invalidate();
//        setMaxPrice(Max);
//        setMinPrice(Min);
        //      ishaveset = true;
        //       invalidate();
        setMaxPrice(Max);
        setMinPrice(Min);
        invalidate();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                invalidate();


            }
        }
    };
    boolean ishaveset = false;

    public float getMinPrice() {
        return getRealPrice((int) (100 * (leftlocation - padding) / totalWith));
    }

    public float getMaxPrice() {
        return getRealPrice((int) (100 * (rightlocation - padding) / totalWith));
    }

    private float getRealPrice(int progress) {
        if (progress >= 0 && progress <= 50) {
            return progress * 20;
        } else if (progress > 50 && progress < 100) {
            return 1000 + (progress - 50) * 40;
        } else if (progress == 100) {
            return 3000;
        }
        return 0;
    }

    public boolean iscanScrollview = false;

    public interface CallBackPro {
        public void progress(int left, int right);
    }

    public CallBackPro callbak;

    public void setOnCallbackProListener(CallBackPro callbackPro) {
        this.callbak = callbackPro;
    }


}
