package com.example.practiceseven;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.Scale;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int ctr = 0;

    ArrayList<Float> yDir = new ArrayList<>();
    ArrayList<Float> xDir = new ArrayList<>();
    GestureDetector gestureDetector;
    ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(this, new MyZoomDetector());
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        String TAG = "Basic";
//        int action = event.getActionMasked();
//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "User pressed on the screen x: "+event.getX()+ "y: " + event.getY() + "time: " + event.getEventTime());
//                yDir.clear();
//                xDir.clear();
//                yDir.add(event.getY());
//                xDir.add(event.getX());
//                return true;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "User released on the screen x: "+event.getX()+ "y: " + event.getY() + "time: " + event.getEventTime());
//                if(yDir.size() > 10){
//                    if (yDir.get(0) < yDir.get(yDir.size() - 1)){
//                        Toast.makeText(this, "Swiped Down", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(this, "Swiped Up", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                if(xDir.size() > 10){
//                    if (xDir.get(0) < xDir.get(xDir.size() - 1)){
//                        Toast.makeText(this, "Swiped right", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(this, "Swiped left", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "User moved on the screen x: "+event.getX()+ "y: " + event.getY() + "time: " + event.getEventTime());
//                yDir.add(event.getY());
//                xDir.add(event.getX());
//                return true;
//        }
//        //Log.d(TAG, event.toString());
//        return super.onTouchEvent(event);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        String TAG = "Gesture";
        //   @Override
//    public boolean onDown(MotionEvent motionEvent) {
//        Log.d(TAG, "on down was fired");
//        return true;
//    }

//    @Override
//    public void onShowPress(MotionEvent motionEvent) {
//        Log.d(TAG, "on show press was fired");
//    }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            ctr++;
            Log.d(TAG, "on single tap was fired" + ctr);
            return true;
        }

//    @Override
//    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        Log.d(TAG, "on scroll was fired");
//        return true;
//    }

//    @Override
//    public void onLongPress(MotionEvent motionEvent) {
//        Log.d(TAG, "on long press was fired");
//
//    }

//    @Override
//    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//        Log.d(TAG, "on fling was fired");
//        return true;
//    }


//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
//        Log.d(TAG, "on single tap was fired");
//        return true;
//    }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            ctr++;
            Log.d(TAG, "on double tap was fired" + ctr);
            return true;
        }

//    @Override
//    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
//        //Log.d(TAG, "on fling was fired");
//        return true;
//    }
    }

    class MyZoomDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector){
            return true;
        }

//    @Override
//    public void onScaleFinished(ScaleGestureDetector detector){
//
//    }
    }
}

