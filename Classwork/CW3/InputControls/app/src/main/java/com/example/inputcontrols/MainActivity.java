package com.example.inputcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    CheckBox check_Ham;
    CheckBox check_Beef;
    CheckBox check_Pepperoni;
    CheckBox check_Mushrooms;

    RadioButton radio_sm;
    RadioButton radio_md;
    RadioButton radio_lg;

    RadioButton rdoPickup;
    RadioButton rdoDelivery;
    RadioButton rdoCurbside;

    SeekBar seekBar_tip;
    Spinner spinner;

    ArrayList<String> toppings;
    String pizza_size;
    String delivery_type;
    int tip_amt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toppings = new ArrayList<>();

        check_Ham = findViewById(R.id.check_Ham);
        check_Beef = findViewById(R.id.check_Beef);
        check_Mushrooms = findViewById(R.id.check_Mushrooms);
        check_Pepperoni = findViewById(R.id.check_Pepperoni);
        radio_sm = findViewById(R.id.radio_sm);
        radio_md = findViewById(R.id.radio_md);
        radio_lg = findViewById(R.id.radio_lg);
        rdoPickup = findViewById(R.id.rdoPickup);
        rdoCurbside = findViewById(R.id.rdoCurbside);
        rdoDelivery = findViewById(R.id.rdoDelivery);

        radio_md.setChecked(true);

        rdoPickup.setChecked(true);
        delivery_type = rdoPickup.getText().toString();

        pizza_size = radio_md.getText().toString();
        seekBar_tip = findViewById(R.id.seekBar_tip);
        seekBar_tip.setProgress(30);
        tip_amt = 30;
        seekBar_tip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Seek",seekBar_tip.getProgress()+"");
                tip_amt = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spinner = findViewById(R.id.spinner);

        //check_Pepperoni.setChecked(true);
    }

    public void checkToppings(View view){
        CheckBox c = (CheckBox) view;

        if (c.isChecked()) {
            toppings.add(c.getText().toString());
            Log.i("toppings", toppings.toString());
        }
        else {
            toppings.remove(c.getText().toString());
            Log.i("toppings", toppings.toString());
        }

//        switch(c.getId()){
//            case R.id.check_Beef:
//                if (c.isChecked())
//                    Log.i("toppings","Beef was selected");
//                else
//                    Log.i("toppings","Beef was removed");
//                break;
//            case R.id.check_Ham:
//                if (c.isChecked())
//                    Log.i("toppings","Ham was selected");
//                else
//                    Log.i("toppings","Ham was removed");
//                break;
//            case R.id.check_Mushrooms:
//                if (c.isChecked())
//                    Log.i("toppings","Mushroom was selected");
//                else
//                    Log.i("toppings","Mushroom was removed");
//                break;
//            case R.id.check_Pepperoni:
//                if (c.isChecked())
//                    Log.i("toppings","Pepperoni was selected");
//                else
//                    Log.i("toppings","Pepperoni was removed");
//                break;
//
//        }

    }

    public void checkSize(View view){
        RadioButton r = (RadioButton) view;

        if (r.isChecked()) {
            pizza_size = r.getText().toString();
            Log.i("size", pizza_size);
        }

    }

    public void checkDelivery(View view){
        RadioButton r = (RadioButton) view;

        if (r.isChecked()) {
            delivery_type = r.getText().toString();
        }
    }

    public void summary(View view){
        Intent intent = new Intent(this, MainActivity2.class);

        ArrayList<String> summary = new ArrayList<>();

        summary.add("Order Summary");
        summary.add("Pizza size: " + pizza_size);
        summary.add("Toppings: " + toppings.toString());
        summary.add("Delivery: " + delivery_type);
        summary.add("Tip: " + tip_amt + "%");
        summary.add("Payment Method: " + spinner.getSelectedItem().toString());
        summary.add("Thanks for your order!");

        intent.putExtra("summary", summary);

        startActivity(intent);


    }
}