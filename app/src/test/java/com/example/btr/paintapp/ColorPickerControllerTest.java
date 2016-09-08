package com.example.btr.paintapp;

import android.graphics.Color;

import org.junit.Test;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class ColorPickerControllerTest {

    //Black, Red, Green, Blue, White
    private static final String[] sCOLORS = {"#000000", "#ff0000", "#00ff00", "#0000ff", "#ffffff"};

    @Test
    public void test_ChooseColor_ValidIndex() {
        ColorPickerController.IColorView mockColorView = mock(ColorPickerController.IColorView.class);
        ColorPickerController controller = new ColorPickerController(sCOLORS, 0, mockColorView);

        controller.chooseColor(0);

        verify(mockColorView, times(2)).setColor(eq(0), eq(Color.parseColor("green")));
    }

    @Test
    public void test_ChooseColor_NegativeIndex() {
        ColorPickerController.IColorView mockColorView = mock(ColorPickerController.IColorView.class);
        ColorPickerController controller = new ColorPickerController(sCOLORS, 0, mockColorView);

        controller.chooseColor(-10);

        verify(mockColorView, times(2)).setColor(eq(0), eq(Color.parseColor("black")));
    }

    @Test
    public void test_ChooseColor_LargeIndex() {
        ColorPickerController.IColorView mockColorView = mock(ColorPickerController.IColorView.class);
        ColorPickerController controller = new ColorPickerController(sCOLORS, 0, mockColorView);

        controller.chooseColor(100);

        verify(mockColorView).setColor(eq(4), eq(Color.parseColor("white")));
    }

    @Test
    public void test_ChooseLastColor() {
        ColorPickerController.IColorView mockColorView = mock(ColorPickerController.IColorView.class);
        ColorPickerController controller = new ColorPickerController(sCOLORS, 0, mockColorView);

        controller.chooseColor(3);

        controller.chooseLastColor();
        verify(mockColorView, times(2)).setColor(eq(3), eq(Color.parseColor("blue")));
    }
}
