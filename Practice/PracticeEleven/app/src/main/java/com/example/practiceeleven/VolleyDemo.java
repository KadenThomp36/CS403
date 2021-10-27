package com.example.practiceeleven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class VolleyDemo extends AppCompatActivity {
    ArrayList<Person> people = new ArrayList<>();
    listViewAdapt adapter = new listViewAdapt(people);
    ListView lstPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        lstPerson = findViewById(R.id.lstList);

        lstPerson.setAdapter(adapter);



        String website = "https://randomuser.me/api/?results=10000";



        lstPerson.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastIndex = firstVisibleItem + visibleItemCount;
                if(lastIndex >= people.size()){
                    fetchData(website);
                }
            }
        });




    }

    public void fetchData(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject obj = response;
                JSONArray arr = obj.getJSONArray("results");
                for (int i = 0; i < arr.length(); i++){
                    String name = arr.getJSONObject(i).getJSONObject("name").getString("first") + " " +
                            arr.getJSONObject(i).getJSONObject("name").getString("last");
                    String country = arr.getJSONObject(i).getJSONObject("location").getString("country");
                    String picURL = arr.getJSONObject(i).getJSONObject("picture").getString("large");
                    people.add(new Person(name, country, picURL));
                }
                Log.d("api", people.toString());
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(), "Something went wong", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }

    class Person {
        String name;
        String country;
        String picURL;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    ", picURL='" + picURL + '\'' +
                    '}';
        }

        public Person(String name, String country, String picURL) {
            this.name = name;
            this.country = country;
            this.picURL = picURL;
        }
    }
}