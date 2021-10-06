package com.example.studentcrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    ArrayList<Student> list;
    Context context;

    public StudentAdapter(Context context,ArrayList<Student> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.student_list_item, parent, false);
        }
            TextView txtName = view.findViewById(R.id.txtName);
            TextView txtAge = view.findViewById(R.id.txtAge);
            TextView txtGpa = view.findViewById(R.id.txtGpa);
            Button btnEdit = view.findViewById(R.id.btnEdit);
            Button btnDelete = view.findViewById(R.id.btnDelete);
            EditText editName = view.findViewById(R.id.editName);
            ImageView image = view.findViewById(R.id.imgPhoto);


            txtName.setText(((Student) getItem(pos)).name);
            txtAge.setText("Age: " + ((Student) getItem(pos)).age);
            txtGpa.setText("GPA: " + ((Student) getItem(pos)).gpa);
            image.setImageResource(R.drawable.avatar2);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(pos);
                    System.out.println(list);
                    notifyDataSetChanged();
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(btnEdit.getText().toString().equalsIgnoreCase("edit")){
                        txtName.setVisibility(View.INVISIBLE);
                        editName.setVisibility(View.VISIBLE);
                        editName.setText(txtName.getText());
                        btnEdit.setText("Save");
                    }
                    else{
                        txtName.setVisibility(View.VISIBLE);
                        list.get(pos).name = editName.getText()+"";
                        editName.setVisibility(View.INVISIBLE);
                        btnEdit.setText("Edit");
                        notifyDataSetChanged();
                    }



                }
            });



        return view;
    }
}
