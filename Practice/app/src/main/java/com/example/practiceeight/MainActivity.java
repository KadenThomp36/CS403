package com.example.practiceeight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    SharedPreferences sharedPref;
    ListView lstSimp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        String words[] = {"Earth", "Mars", "Venus", "Saturn"};
        sharedPref = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, words);
        spinner.setAdapter(adapter);
        if(sharedPref.contains("planet"))
               spinner.setSelection(sharedPref.getInt("planet", 0));



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), words[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String items[] = "hi my name is kaden this is an example string".split(" ");
        lstSimp = findViewById(R.id.lstSimp);
        ArrayAdapter<String> simpAdapt = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, items);
        lstSimp.setAdapter(simpAdapt);

        ArrayList<Student> sList = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            sList.add(new Student("John" + i, 12, 4.0));
        }

        StudentAdapter studentAdapter = new StudentAdapter(sList);
        lstSimp.setAdapter(studentAdapter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("planet", spinner.getSelectedItemPosition());
        editor.commit();
    }

    class Student{
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
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gpa=" + gpa +
                    '}';
        }
    }

    class StudentAdapter extends BaseAdapter {
        ArrayList<Student> sList;
        public StudentAdapter(ArrayList<Student> sList){
            this.sList = sList;
        }
        @Override
        public int getCount() {
            return sList.size();
        }

        @Override
        public Object getItem(int i) {
            return sList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.layout_student_item,parent,false);
            TextView txtName = view.findViewById(R.id.txtName);
            TextView txtAge = view.findViewById(R.id.txtAge);
            TextView txtGPA = view.findViewById(R.id.txtGPA);

            txtName.setText(sList.get(position).name);
            txtAge.setText(sList.get(position).age+"");
            txtGPA.setText(sList.get(position).gpa+"");


            return view;
        }
    }
}