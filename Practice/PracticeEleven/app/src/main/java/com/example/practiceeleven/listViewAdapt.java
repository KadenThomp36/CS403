package com.example.practiceeleven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class listViewAdapt extends BaseAdapter {

    ArrayList<VolleyDemo.Person> people;

    public listViewAdapt(ArrayList<VolleyDemo.Person> people){
        this.people = people;
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView name = convertView.findViewById(R.id.txtNameTen);
        TextView country = convertView.findViewById(R.id.txtCountry);

        ImageView image = convertView.findViewById(R.id.imgPic);
        VolleyDemo.Person person = people.get(position);

        name.setText(person.name);
        country.setText(person.country);

        Picasso.get().load(person.picURL).into(image);

        return convertView;
    }
}
