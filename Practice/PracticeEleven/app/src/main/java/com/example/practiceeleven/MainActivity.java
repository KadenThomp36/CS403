package com.example.practiceeleven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtNameTen);

        String website = "https://randomuser.me/api";
        new FetchData().execute(website);
    }

    class FetchData extends AsyncTask<String,Void,String> {
        HttpURLConnection conn;
        InputStream is;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                is = conn.getInputStream();
                String content = processData(is);
                Log.d("fetch", content);
                return content;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public String processData(InputStream is) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = "";
            String line = br.readLine();
            while (line!=null){
                result += line+"\n";
                line = br.readLine();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray arr = obj.getJSONArray("results");
                String name = arr.getJSONObject(0).getJSONObject("name").getString("first") + " " +
                        arr.getJSONObject(0).getJSONObject("name").getString("last");
                txtName.setText(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}