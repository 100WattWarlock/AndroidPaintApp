package com.example.btr.paintapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CanvasControllerTest {

    Paint mockPaint;
    Path mockPath;
    Canvas mockSurface;

    @Before
    public void setUp() {
         mockPaint = mock(Paint.class);
        mockPath = mock(Path.class);
        mockSurface = mock(Canvas.class);
    }

    @Test
    public void testDraw() {
        Canvas mockCanvas = mock(Canvas.class);
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.draw(mockCanvas);

        verify(mockCanvas).drawBitmap(any(Bitmap.class), eq(0.0f), eq(0.0f), isA(Paint.class));
        verify(mockCanvas).drawPath(mockPath, mockPaint);
    }

    @Test
    public void testHandleSizeChange() {
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.handleSizeChanged(123, 456);

        verify(mockSurface).setBitmap(any(Bitmap.class));
    }

    @Test
    public void testHandleTouchDown() {
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.handleTouchDown(123.0f, 456.0f);

        verify(mockPath).moveTo(eq(123.0f), eq(456.0f));
    }

    @Test
    public void testHandleTouchUp() {
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.handleTouchUp();

        verify(mockSurface).drawPath(mockPath, mockPaint);
        verify(mockPath).reset();
    }

    @Test
    public void testHandleTouchMove() {
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.handleTouchMove(123.0f, 456.0f);

        verify(mockPath).lineTo(eq(123.0f), eq(456.0f));
    }

    @Test
    public void testEraseAll() {
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.eraseAll();

        verify(mockPath).reset();
        verify(mockSurface).drawColor(eq(Color.WHITE));
    }

    @Test
    public void testSetColor() {
        CanvasController controller = new CanvasController(mockPaint, mockPath, mockSurface);

        controller.setPaintColor(Color.GREEN);

        verify(mockPaint).setColor(eq(Color.GREEN));
    }
}
