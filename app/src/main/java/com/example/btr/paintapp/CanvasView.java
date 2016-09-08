package com.example.btr.paintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Custom View for drawing on Canves using touch.
 */
public class CanvasView extends View implements ColorPickerDialog.OnColorSelectedListener {

    private CanvasController mController;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mController = new CanvasController(createPaint(), new Path(), new Canvas());
    }

    private Paint createPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20.0f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mController.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeigt) {
        super.onSizeChanged(width, height, oldWidth, oldHeigt);
        mController.handleSizeChanged(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mController.handleTouchDown(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mController.handleTouchMove(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                mController.handleTouchUp();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void clearCanvas() {
        mController.eraseAll();
        invalidate();
    }

    @Override
    public void onColorSelected(int color) {
        mController.setPaintColor(color);
    }
}
