package com.example.inputcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class EditTextDemo extends AppCompatActivity {
    AutoCompleteTextView textAuto;
    private String[] countries = new String[]{"Brazil","United States","United Kingdom","Spain","France"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_demo);

        textAuto = findViewById(R.id.autoText);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,countries);
        textAuto.setAdapter(adapter);
    }
}