package com.example.classwork4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Student> students = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);

        for (int i = 0; i < 20; i++){
            students.add(new Student("kaden" + i, 22, 3.4));
        }


        ListViewAdapter lva = new ListViewAdapter(students);
        lv.setAdapter(lva);


    }


}

class ListViewAdapter extends BaseAdapter {
    ArrayList<Student> students = new ArrayList<>();

    public ListViewAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtGpa = convertView.findViewById(R.id.txtGpa);
        TextView txtAge = convertView.findViewById(R.id.txtAge);

        txtName.setText("Name: "+students.get(position).name+"");
        txtAge.setText("Age: "+students.get(position).Age+"");
        txtGpa.setText("GPA: "+students.get(position).GPA+"");

        return convertView;

    }
}


class Student{
    String name;
    int Age;
    double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        Age = age;
        this.GPA = GPA;
    }
}