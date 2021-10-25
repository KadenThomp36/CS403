package com.example.practiceeleven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Queue;

public class VolleyDemo extends AppCompatActivity {
    TextView txtNameTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);

        txtNameTwo = findViewById(R.id.txtNameTwoElectricBoogaloo);

        RequestQueue queue = Volley.newRequestQueue(this);

        String website = "https://randomuser.me/api";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, website, null, response -> {
            try {
                JSONObject obj = response;
                JSONArray arr = obj.getJSONArray("results");
                String name = arr.getJSONObject(0).getJSONObject("name").getString("first") + " " +
                        arr.getJSONObject(0).getJSONObject("name").getString("last");
                txtNameTwo.setText(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(), "Something went wong", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }
}