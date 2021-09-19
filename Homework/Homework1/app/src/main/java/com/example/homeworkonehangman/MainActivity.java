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

    //variables for referencing views and holding universal values
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

    //creates the initial view and sets up global references to views. Reads in file containing
    //words that will be used for the hangman game
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

        //read in words
        try {
            for (int i = 0; i < 10000; i++){
                words[i] = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //setup for a new round or initial round
        setupView();

    }

    //helper to call the setupView() same function that is called from startup (this function
    //is called from button click)
    public void startGame(View view){
        setupView();
    }

    //creates the initial setup for hangman, resets variables and chooses a word to use
    public void setupView(){
        String drawUnderscore = "";
        imgGraphic.setImageResource(R.drawable.hangman0);

        txtStatus.setText("Guess History: ");
        txtStatus.setTextColor(Color.BLACK);
        guessHistory = "";
        attemptNumber = 0;
        txtGuess.setEnabled(true);
        btnGo.setEnabled(true);

        //choose a word to use from the list
        randomWord();

        //represent the length of the word with underscores
        for(int i = 0; i < currentWord.length(); i++){
            drawUnderscore += "_";
        }

        txtWord.setText(drawUnderscore);
    }

    //pulls a random word out of the array of 10000 words and makes sure that it is 3 - 6 letters long
    public void randomWord(){
         //loop looking for words until the word we find meets the requirements
        boolean meetsReq = false;
        while (!meetsReq){
            int randWord = (int)(Math.random() *(9999 + 1));
            String checkWord = words[randWord];
            //required length check
            if (checkWord.length() <= 6 && checkWord.length() >= 3){
                currentWord = checkWord; //if it meets it set the word
                meetsReq = true;
            }
        }

    }

    //called when the user clicks go on a letter choice and advances the game forward
    public void guess(View view){
        int guessPosition;
        String word;
        userGuess = txtGuess.getText().toString();

        //checks to see if the guess meets requirements (not a duplicate, not empty, not a number..)
        if (!guessValidation()){
            txtGuess.setText("");
            return;
        }

        //pulls down the current word structure during the guess proces i.e: t e _ _ a
        word = txtWord.getText().toString();

        //stores the user guesses
        guessHistory += userGuess;

        //if the current word has a letter being guessed by the user then....
        if(currentWord.contains(userGuess)) {
            guessPosition = -1; //initial guess position state
            do { //loop to find all instances of the letter that was guessed and replace the
                // "t e _ _ a" with the correct letters like -> "t e s _ a"
                guessPosition = currentWord.indexOf(userGuess, guessPosition + 1);
                word = word.substring(0, guessPosition) + userGuess + word.substring(guessPosition + 1);
                txtWord.setText(word);
            } while (currentWord.indexOf(userGuess, guessPosition + 1) != -1);
        } else { //if the users guess wasn't in the current word then increment the attempt number
            attemptNumber += 1;
        }
        //update the hangman game based on amount of attempts and update the status text
        updateHangman();

        //checks to see if the win con has been met
        checkWin();
    }

    //update the hangman game based on amount of attempts and update the status text
    public void updateHangman() {
        //update guess history and remove the current guessed text
        txtStatus.setText("Guess History: " + guessHistory);
        txtGuess.setText("");

        //switch statement to update the hangman graphic based on attempts made
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
            case 6: imgGraphic.setImageResource(R.drawable.hangman6); //this is the lose state
                btnGo.setEnabled(false);
                txtGuess.setEnabled(false);
                txtStatus.setText("You lost. The correct answer was " + currentWord);
                txtStatus.setTextColor(Color.RED);
                break;
        }
    }

    //checks to see if the win con has been met update view to reflect a win
    public void checkWin() {
        if(!txtWord.getText().toString().contains("_")){
            txtGuess.setEnabled(false);
            btnGo.setEnabled(false);
            txtStatus.setText("Yay! You won with " + (6 - attemptNumber) + " guesses left. Play again?");
            txtStatus.setTextColor(Color.GREEN);
        }
    }

    //check to make sure what the user is guessing is a valid guess
    public boolean guessValidation(){
        userGuess = userGuess.toLowerCase();
        String acceptableInput = "abcdefghijklmnopqrstuvwxyz";

        //empty string
        if(userGuess.equals("")){
            Toast.makeText(this, "blank input ignored", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!acceptableInput.contains(userGuess)){ //is a-z
            Toast.makeText(this, "invalid input ignored", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(guessHistory.contains(userGuess)){ //not already guessed
            Toast.makeText(this, "duplicate input ignored", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}