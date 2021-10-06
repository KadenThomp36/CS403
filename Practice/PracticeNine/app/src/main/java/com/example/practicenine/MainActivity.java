package com.example.studentcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StudentAdapter studentAdapter;
    ArrayList<Student> studentList;
    ListView listView;
    EditText editName;
    EditText editAge;
    EditText editGpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editNewName);
        editAge = findViewById(R.id.editNewAge);
        editGpa = findViewById(R.id.editNewGpa);


        studentList = new ArrayList<>();
        for(int i=0;i<100;i++)
            studentList.add(new Student("John Doe "+i,23,3.5));

        studentAdapter = new StudentAdapter(this,studentList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(studentAdapter);
    }

    public void addNewStudent(View v) {
        com.example.studentcrud.Student s = new com.example.studentcrud.Student(editName.getText() + "", Integer.parseInt(editAge.getText() + ""), Double.parseDouble(editGpa.getText() + ""));
        studentList.add(0, s);
        studentAdapter.notifyDataSetChanged();

    }

    class StudentAdapter extends BaseAdapter{

    }
}