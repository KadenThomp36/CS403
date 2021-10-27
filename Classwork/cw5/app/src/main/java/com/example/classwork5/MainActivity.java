package com.example.classwork5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestQueue queue = Volley.newRequestQueue(this);

        String website = "https://randomuser.me/api/?results=10";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, website, null, response -> {
            try {

                JSONObject obj = response;
                JSONArray arr = obj.getJSONArray("results");
                for(int i = 0; i < arr.length(); i++) {
                    String name = arr.getJSONObject(i).getJSONObject("name").getString("first") + " "
                            + arr.getJSONObject(i).getJSONObject("name").getString("last");
                    String email = arr.getJSONObject(i).getString("email");
                    String address = arr.getJSONObject(i).getJSONObject("location").getJSONObject("street").getString("number") + " " +
                            arr.getJSONObject(i).getJSONObject("location").getJSONObject("street").getString("name") + ", " +
                            arr.getJSONObject(i).getJSONObject("location").getString("city") + ", " +
                            arr.getJSONObject(i).getJSONObject("location").getString("state") + ", " +
                            arr.getJSONObject(i).getJSONObject("location").getString("postcode");
                    people.add(new Person(name, email, address));
                }
                Log.d("api", people.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }

    class Person{
        String name, email, address;

        public Person(String name, String email, String address) {
            this.name = name;
            this.email = email;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    "}\n";
        }
    }
}