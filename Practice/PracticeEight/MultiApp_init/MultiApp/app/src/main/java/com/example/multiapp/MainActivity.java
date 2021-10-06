package com.example.multiapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    EditText txtMessage;
    ActivityResultLauncher<Intent> resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = findViewById(R.id.txtMessage);
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->  {

                Intent it = result.getData();
                int code = result.getResultCode();
                Toast.makeText(getApplicationContext(), "code: " + code+ " Extras: " + it.getStringExtra("msg"), Toast.LENGTH_SHORT).show();

        });

    }

    public void startSecondActivity(View v){
        Intent intent = new Intent(this,SecondActivity.class);
        //startActivity(intent);
        resultLauncher.launch(intent);
    }

    public void startThirdActivity(View v){
        String txt = txtMessage.getText().toString();
        Intent intent = new Intent(this,ThirdActivity.class);
        intent.putExtra("msg",txt);
        startActivity(intent);

    }


    public void startFourthActivity(View v){
        Student s   = new Student("John",23,3.5);
        Intent intent = new Intent(this,FourthActivity.class);
        intent.putExtra("john",s);

        String j = new Gson().toJson(s);

        intent.putExtra("json_str",j);
        System.out.println(j);
        startActivity(intent);

    }

    public void startImplicit(View v){
        Uri uri = Uri.parse("http://www.google.com");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
//
//        Uri uri = Uri.parse("tel:"+txtMessage.getText());
//        Intent it = new Intent(Intent.ACTION_DIAL,uri);

//        Uri uri = Uri.parse("mailto:amukher1@svsu.edu");
//        Intent it = new Intent(Intent.ACTION_SENDTO,uri);


//        Intent it = new Intent(Intent.ACTION_SEND);
//        it.setType("text/plain");
//        it.putExtra(Intent.EXTRA_TEXT,"dog videos");
        startActivity(it);
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

}

/*
 copy to build.gradle (Module)
dependencies {
  implementation 'com.google.code.gson:gson:2.8.8'
}
 */