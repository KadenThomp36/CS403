package com.example.classwork2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private float mScaleFactor = 1.f;
    private ScaleGestureDetector mScaleDetector;
    TextView txtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHello = findViewById(R.id.txtHello);
        mScaleDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        return true;
    }
    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector){
            if(detector.getScaleFactor() > 1){
                txtHello.setText("Zoom out");
            } else
                txtHello.setText("Zoom in");

            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            txtHello.setText("Hello World!");
            super.onScaleEnd(detector);
        }
    }
}

