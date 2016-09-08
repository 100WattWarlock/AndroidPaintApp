package com.example.btr.paintapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private CanvasView mView;
    private ColorPickerDialog mColorPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = (CanvasView) findViewById(R.id.custom_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        initColorPicker();
    }

    private void initColorPicker() {
        mColorPickerDialog = new ColorPickerDialog();
        mColorPickerDialog.setOnColorSelectedListener(mView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete:
                mView.clearCanvas();
                break;
            case R.id.action_color:
                mColorPickerDialog.show(getFragmentManager(), "color_picker");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
