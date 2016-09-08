package com.example.btr.paintapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

/**
 * Dialog for selecting a color.
 */
public class ColorPickerDialog extends DialogFragment implements SeekBar.OnSeekBarChangeListener, ColorPickerController.IColorView {

    private static final String[] sCOLORS = {"#000000", "#fe0000", "#ff7900", "#ffb900", "#ffde00", "#fcff00", "#d2ff00", "#05c000", "#00c0a7", "#0600ff", "#6700bf", "#9500c0", "#bf0199", "#ffffff"};
    private ColorPickerController mController;
    private OnColorSelectedListener mListener;
    private ColorPreview mColorPreview;
    private SeekBar mColorSizeSeekBar;

    public ColorPickerDialog() {
        mController = new ColorPickerController(sCOLORS, 0, this);
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_color_chooser, null);
        if (dialogView != null) {
            mColorPreview = (ColorPreview) dialogView.findViewById(R.id.color_preview);
            mColorSizeSeekBar = (SeekBar) dialogView.findViewById(R.id.seek_bar_color);
            mColorSizeSeekBar.setOnSeekBarChangeListener(this);
            mController.chooseLastColor();
        }
        builder.setTitle(R.string.color_picker_title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mListener != null) {
                            mListener.onColorSelected(mColorPreview.getColor());
                        }
                        dialog.dismiss();
                    }
                })
                .setView(dialogView);


        return builder.create();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mController.chooseColor(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void setColor(int index, int color) {
        if (mColorSizeSeekBar != null &&  mColorPreview != null) {
            mColorSizeSeekBar.setProgress(index);
            mColorPreview.setColor(color);
        }
    }

    public interface OnColorSelectedListener {

        void onColorSelected(int color);
    }
}
