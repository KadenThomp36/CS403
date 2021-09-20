package com.example.practicefive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = findViewById(R.id.txtInput);
    }

    public void startSecond(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void startThird(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("msg", txtMessage.getText().toString());
        startActivity(intent);
    }
    public void startFourth(View view){
        Intent intent = new Intent(this, FourthActivity.class);
//        //intent.putExtra("msg", txtMessage.getText().toString());
//        Bundle b = new Bundle();
//        b.putString("name", "John");
//        b.putInt("age", 22);
//        b.putStringArray("grades", new String[]{"A","B","C"});
//        intent.putExtras(b);
        Student john = new Student("John", 22, 3.6);
        intent.putExtra("john", john);
        startActivity(intent);
    }

    public void startImplicit(View view){
//        Uri uri = Uri.parse("tel:"+txtMessage.getText());
//        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "dog videos");
        startActivity(intent);
    }

}

class Student implements Serializable {
    String name;
    int age;
    double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'';
    }
}