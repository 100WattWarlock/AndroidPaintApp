package com.example.btr.paintapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Controller for drawing on Canves using touch.
 */
public class CanvasController {

    private Paint mDrawPaint;
    private Path mDrawPath;
    private Canvas mDrawCanvas;
    private Bitmap mCanvasBitmap;

    public CanvasController(Paint paint, Path path, Canvas drawCanvas) {
        mDrawPaint = paint;
        mDrawPath = path;
        mDrawCanvas = drawCanvas;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(mCanvasBitmap, 0, 0, new Paint(Paint.DITHER_FLAG));
        canvas.drawPath(mDrawPath, mDrawPaint);
    }

    public void handleSizeChanged(int width, int height) {
        mCanvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mDrawCanvas.setBitmap(mCanvasBitmap);
    }

    public void handleTouchDown(float touchX, float touchY) {
        mDrawPath.moveTo(touchX, touchY);
    }

    public void handleTouchUp() {
        mDrawCanvas.drawPath(mDrawPath, mDrawPaint);
        mDrawPath.reset();
    }

    public void handleTouchMove(float touchX, float touchY) {
        mDrawPath.lineTo(touchX, touchY);
    }

    public void eraseAll() {
        mDrawPath.reset();
        mDrawCanvas.drawColor(Color.WHITE);
    }

    public void setPaintColor(int color) {
        mDrawPaint.setColor(color);
    }
}
