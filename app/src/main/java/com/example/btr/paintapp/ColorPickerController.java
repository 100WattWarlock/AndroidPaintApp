package com.example.btr.paintapp;

import android.graphics.Color;

/**
 * Controller for choosing a color. It takes an array of color codes strings and a
 * <code>IColorView</code>. When a color is chosen the <code>IColorView</code> will be updated.
 */
public class ColorPickerController {

    private String[] mColors;
    private int mSelectedColor;
    private int mSelectedIndex;
    private IColorView mColorView;

    ColorPickerController(String[] colors, int selectedColorIndex, IColorView colorView) {
        mColors = colors;
        mColorView = colorView;
        chooseColor(selectedColorIndex);
    }

    public void chooseLastColor() {
        chooseColor(mSelectedIndex);
    }

    public void chooseColor(int index) {
        mSelectedIndex = validateIndex(index);
        mSelectedColor = Color.parseColor(mColors[mSelectedIndex]);
        if (mColorView != null) {
            mColorView.setColor(mSelectedIndex, mSelectedColor);
        }
    }

    private int validateIndex(int index) {
        if (index < 0) {
            return 0;
        } else if (index > mColors.length) {
            return mColors.length - 1;
        } else {
            return index;
        }
    }

    public interface IColorView {
        void setColor(int index, int color);
    }
}
