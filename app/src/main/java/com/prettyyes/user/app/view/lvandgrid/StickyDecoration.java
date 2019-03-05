package com.prettyyes.user.app.view.lvandgrid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.prettyyes.user.R;
import com.prettyyes.user.app.adapter.order.ShipCompanyAdapter;
import com.prettyyes.user.core.utils.AppUtil;

/**
 * Created by tangyangkai on 2016/12/27.
 */

public class StickyDecoration extends RecyclerView.ItemDecoration {


    private TextPaint textPaint;
    private Paint paint;
    private int topHeight;
    ShipCompanyAdapter shipCompantAdapter;

    public void setShipCompantAdapter(ShipCompanyAdapter shipCompantAdapter) {
        this.shipCompantAdapter = shipCompantAdapter;
    }

    public StickyDecoration(Context context) {
        Resources res = context.getResources();

        paint = new Paint();
        paint.setColor(res.getColor(R.color.white_grey));
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(AppUtil.dip2px(18));
        textPaint.setColor(Color.BLACK);
        topHeight = AppUtil.dip2px(30);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (isFirstInGroup(position)) {
            outRect.top = topHeight;
        } else {
            outRect.top = 0;
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (position > shipCompantAdapter.getItemCount() - 1)
                return;
            String textLine = shipCompantAdapter.getItemData(position).getLetter().toUpperCase();
            if (isFirstInGroup(position)) {
                float top = view.getTop() - topHeight;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                c.drawText(textLine, left + AppUtil.dip2px(10), bottom - AppUtil.dip2px(7.5), textPaint);//绘制文本
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        c.drawRect(left, 0, right, topHeight, paint);//绘制红色矩形
        String text = shipCompantAdapter.getItemData(position).getLetter().toUpperCase();
        c.drawText(text, AppUtil.dip2px(10), topHeight - AppUtil.dip2px(7.5), textPaint);//绘制文本
    }

    private boolean isFirstInGroup(int position) {
        boolean isFirst = false;
        if (position <= shipCompantAdapter.getItemCount() - 1) {
            if (position == 0) {
                isFirst = true;
            } else {
                if (shipCompantAdapter.getItemData(position).getLetter().
                        equals(shipCompantAdapter.getItemData(position - 1).getLetter())) {
                    isFirst = false;
                } else {
                    isFirst = true;
                }
            }
        }
        return isFirst;
    }
}
