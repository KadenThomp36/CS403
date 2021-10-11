package com.example.classwork4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Recycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle);

        ArrayList<Student> students = new ArrayList<>();
        RecyclerView rv = findViewById(R.id.rv);

        rv = findViewById(R.id.rv);

        for (int i = 0; i < 20; i++){
            students.add(new Student("kaden" + i, 22, 3.4));
        }


        RecyclerAdapter lva = new RecyclerAdapter(students);
        rv.setAdapter(lva);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //rv.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1000);
        rv.setLayoutManager(gridLayoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(getApplicationContext(), "SwipedRight", Toast.LENGTH_SHORT).show();
                int pos = viewHolder.getAdapterPosition();
                students.remove(pos);
                lva.notifyItemRemoved(pos);
            }
        });

        itemTouchHelper.attachToRecyclerView(rv);
    }
}