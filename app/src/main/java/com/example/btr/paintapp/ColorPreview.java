package com.example.btr.paintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ColorPreview extends View {
    private int mColor;


    public ColorPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Rect r = new Rect(0, 0, getWidth(), getHeight());
        // fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mColor);
        canvas.drawRect(r, paint);

        // border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(r, paint);
    }


}
