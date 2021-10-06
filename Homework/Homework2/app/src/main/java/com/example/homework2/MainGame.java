package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainGame extends AppCompatActivity {
    Bundle b;
    int numCommands;
    int currentZone;
    int executedZone;
    boolean gameStart = false;
    boolean advance = false;
    COMMANDS currentCommand;
    COMMANDS executedCommand;
    TextView txtStartGame;
    GestureDetector gestureDetector;
    ArrayList<COMMANDS> listOfCommands = new ArrayList<>();
    enum COMMANDS {
        Tap_Once,
        Tap_Twice,
        Tap_Three_Times,
        Tap_Four_Times,
        Tap_Five_Times,
        Double_Tap,
        Swipe_Up,
        Swipe_Down,
        Swipe_Left,
        Swipe_Right,
        Zoom_In,
        Zoom_Out
    }
    int[] zone1 = new int[4];
    int[] zone2 = new int[4];
    int[] zone3 = new int[4];
    int[] zone4 = new int[4];
    View v;
    String currentGame;
    String Commands;
    ConstraintLayout cl;
    TextView txtZoneOne;
    TextView txtZoneTwo;
    TextView txtZoneThree;
    TextView txtZoneFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        txtStartGame = findViewById(R.id.txtStartGame);
        txtZoneOne = findViewById(R.id.txtOne);
        txtZoneTwo = findViewById(R.id.txtTwo);
        txtZoneThree = findViewById(R.id.txtThree);
        txtZoneFour = findViewById(R.id.txtFour);

        cl = findViewById(R.id.cl);
        v = (View)cl.getParent();

        b = this.getIntent().getExtras();
        Commands = b.getString("Commands");
        numCommands = b.getInt("numCommands");

        for(int i = 0; i < numCommands; i++){
            //TODO: select a random index from "Commands" and use that number to pick a game type
            pickGame();
        }
        pickZone();


    }

    public void computeZones(){
        int left;
        int right;
        int top;
        int bottom;

        left = v.getLeft();
        right = v.getRight();
        top = v.getTop();
        bottom = v.getBottom();
        int offset = getStatusBarHeight();

        //x1 y1 x2 y2

        zone1[0] = left;
        zone1[1] = top;
        zone1[2] = right/2;
        zone1[3] = bottom/2;

        zone2[0] = right/2;
        zone2[1] = top + offset;
        zone2[2] = right;
        zone2[3] = bottom/2;

        zone3[0] = left;
        zone3[1] = bottom/2 + offset;
        zone3[2] = right/2;
        zone3[3] = bottom;

        zone4[0] = right/2;
        zone4[1] = bottom/2 + offset;
        zone4[2] = right;
        zone4[3] = bottom;

        Log.d("Zones", Arrays.toString(zone1));
        Log.d("Zones", Arrays.toString(zone2));
        Log.d("Zones", Arrays.toString(zone3));
        Log.d("Zones", Arrays.toString(zone4));
    }

    public int getStatusBarHeight() {
        int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return (viewTop);
    }

    public void playGame(){
        //if user swipes up then...
        Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
        txtStartGame.setVisibility(View.INVISIBLE);
        computeZones();
        drawCommand(listOfCommands.get(0));

    }

    public void pickZone(){

        currentZone = randomGenerator(1, 4);

        //TODO: find zone bounds and set it to a global variable
    }

    public void pickGame(){

        int rand = randomGenerator(1, Commands.length());

        currentGame = String.valueOf(Commands.charAt(rand-1));

        switch (currentGame){
            case "1":
                playTap();
                break;
            case "2":
                playDoubleTap();
                break;
            case "3":
                playSwipe();
                break;
            case "4":
                playZoom();
                break;
        }


    }

    public void playTap(){
        int tapAmt = randomGenerator(1, 5);
        switch (tapAmt){
            case 1:
                currentCommand = COMMANDS.Tap_Once;
                break;
            case 2:
                currentCommand = COMMANDS.Tap_Twice;
                break;
            case 3:
                currentCommand = COMMANDS.Tap_Three_Times;
                break;
            case 4:
                currentCommand = COMMANDS.Tap_Four_Times;
                break;
            case 5:
                currentCommand = COMMANDS.Tap_Five_Times;
                break;
        }

        listOfCommands.add(currentCommand);
    }

    public void playDoubleTap(){
        listOfCommands.add(COMMANDS.Double_Tap);
    }

    public void playSwipe(){
        int direction = randomGenerator(1, 4);

        switch (direction){
            case 1:
                currentCommand = COMMANDS.Swipe_Up;
                break;
            case 2:
                currentCommand = COMMANDS.Swipe_Right;
                break;
            case 3:
                currentCommand = COMMANDS.Swipe_Down;
                break;
            case 4:
                currentCommand = COMMANDS.Swipe_Left;
                break;
        }

        listOfCommands.add(currentCommand);
    }

    public void playZoom(){
        int zoomDir = randomGenerator(1, 2);

        switch (zoomDir){
            case 1:
                currentCommand = COMMANDS.Zoom_In;
                break;
            case 2:
                currentCommand = COMMANDS.Zoom_Out;
                break;
        }

        listOfCommands.add(currentCommand);
    }

    public int randomGenerator(int min, int max){
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    public void checkMove(){
        currentCommand = listOfCommands.get(0);
        if (!gameStart){
            if(executedCommand == COMMANDS.Swipe_Up){
                playGame();
                gameStart = true;
            } else{
                return;
            }
        }else{
            if (executedCommand == currentCommand && executedZone == currentZone){
                advance = true;
                Toast.makeText(this, "Nice!", Toast.LENGTH_SHORT).show();
            }
        }

        //if (//move that they did == move were looking for)
    }

    public void drawCommand(COMMANDS command){
        switch(currentZone){
            case 1:
                txtZoneOne.setText(command.name().replace("_", " "));
                break;
            case 2:
                txtZoneTwo.setText(command.name().replace("_", " "));
                break;
            case 3:
                txtZoneThree.setText(command.name().replace("_", " "));
                break;
            case 4:
                txtZoneFour.setText(command.name().replace("_", " "));
                break;
        }
    }

    public void determineDirection(MotionEvent start, MotionEvent end){
        //first check to see if its an x or a y event (compare difference in size)
        float X1 = start.getX();
        float X2 = end.getX();
        float Y1 = start.getY();
        float Y2 = end.getY();

        if (Math.abs(X2 - X1) > Math.abs(Y2 - Y1)){
            if(X2 > X1){
                executedCommand = COMMANDS.Swipe_Right;
            } else{
                executedCommand = COMMANDS.Swipe_Left;
            }
        } else {
            if(Y2 > Y1){
                executedCommand = COMMANDS.Swipe_Down;
            } else{
                executedCommand = COMMANDS.Swipe_Up;
            }
        }

        Log.d("Swipe", executedCommand.name());
        determineZone(X1, Y1, X2, Y2);
        Log.d("zone", executedZone+"");
        checkMove();
    }

    public void determineZone(float X1, float Y1, float X2, float Y2){



        if ((X1 > zone1[0]) && (Y1 > zone1[1]) && (X1 < zone1[2]) && ( Y1 < zone1[3])){
            executedZone = 1;
        } else if((X1 > zone2[0]) && (Y1 > zone2[1]) && (X1 < zone2[2]) && ( Y1 < zone2[3])){
            executedZone = 2;
        } else if((X1 > zone3[0]) && (Y1 > zone3[1]) && (X1 < zone3[2]) && ( Y1 < zone3[3])){
            executedZone = 3;
        } else if((X1 > zone4[0]) && (Y1 > zone4[1]) && (X1 < zone4[2]) && ( Y1 < zone4[3])){
            executedZone = 4;
        } else {
            executedZone = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent start, MotionEvent end, float v, float v1) {
            determineDirection(start, end);
            return true;
        }

    }
}

