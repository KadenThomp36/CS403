package com.example.homeworkonehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String[] words = new String[10000];
    String userGuess;
    String currentWord;
    String guessHistory = "";
    int attemptNumber;
    ImageView imgGraphic;
    TextView txtGuess;
    TextView txtStatus;
    TextView txtWord;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGraphic = findViewById(R.id.imgGraphic);
        txtGuess = findViewById(R.id.txtGuess);
        txtStatus = findViewById(R.id.txtStatus);
        txtWord = findViewById(R.id.txtWord);
        btnGo = findViewById(R.id.btnGo);

        InputStream is = getResources().openRawResource(R.raw.wordlist10000);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            for (int i = 0; i < 10000; i++){
                words[i] = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupView();

    }

    public void startGame(View view){
        setupView();
    }

    public void setupView(){
        String drawUnderscore = "";
        imgGraphic.setImageResource(R.drawable.hangman0);

        txtStatus.setText("Guess History: ");
        txtStatus.setTextColor(Color.BLACK);
        guessHistory = "";
        attemptNumber = 0;
        txtGuess.setEnabled(true);
        btnGo.setEnabled(true);
        randomWord();

        for(int i = 0; i < currentWord.length(); i++){
            drawUnderscore += "_";
        }

        txtWord.setText(drawUnderscore);
    }

    public void randomWord(){
        boolean meetsReq = false;
        while (!meetsReq){
            int randWord = (int)(Math.random() *(9999 + 1));
            String checkWord = words[randWord];
            if (checkWord.length() <= 6 && checkWord.length() >= 3){
                currentWord = checkWord;
                meetsReq = true;
            }
        }

    }

    public void guess(View view){
        int guessPosition;
        String word;
        userGuess = txtGuess.getText().toString();
        if (!guessValidation()){
            txtGuess.setText("");
            return;
        }
        word = txtWord.getText().toString();

        guessHistory += userGuess;
        if(currentWord.contains(userGuess)) {
            guessPosition = -1;
            do {
                guessPosition = currentWord.indexOf(userGuess, guessPosition + 1);
                word = word.substring(0, guessPosition) + userGuess + word.substring(guessPosition + 1);
                txtWord.setText(word);
            } while (currentWord.indexOf(userGuess, guessPosition + 1) != -1);
        } else {
            attemptNumber += 1;
        }
        updateHangman();

        checkWin();
    }

    public void updateHangman() {
        txtStatus.setText("Guess History: " + guessHistory);
        txtGuess.setText("");
        switch (attemptNumber){
            case 0: imgGraphic.setImageResource(R.drawable.hangman0);
                break;
            case 1: imgGraphic.setImageResource(R.drawable.hangman1);
                break;
            case 2: imgGraphic.setImageResource(R.drawable.hangman2);
                break;
            case 3: imgGraphic.setImageResource(R.drawable.hangman3);
                break;
            case 4: imgGraphic.setImageResource(R.drawable.hangman4);
                break;
            case 5: imgGraphic.setImageResource(R.drawable.hangman5);
                break;
            case 6: imgGraphic.setImageResource(R.drawable.hangman6);
                btnGo.setEnabled(false);
                txtGuess.setText("");
                txtGuess.setEnabled(false);
                txtStatus.setText("You lost. The correct answer was " + currentWord);
                txtStatus.setTextColor(Color.RED);
                break;
        }
    }

    public void checkWin() {
        if(!txtWord.getText().toString().contains("_")){
            txtGuess.setEnabled(false);
            btnGo.setEnabled(false);
            txtStatus.setText("Yay! You won with " + (6 - attemptNumber) + " guesses left. Play again?");
            txtStatus.setTextColor(Color.GREEN);
        }
    }

    public boolean guessValidation(){
        userGuess = userGuess.toLowerCase();
        String acceptableInput = "abcdefghijklmnopqrstuvwxyz";

        if(userGuess.equals("")){
            Toast.makeText(this, "blank input ignored", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!acceptableInput.contains(userGuess)){
            Toast.makeText(this, "invalid input ignored", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(guessHistory.contains(userGuess)){
            Toast.makeText(this, "duplicate input ignored", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}