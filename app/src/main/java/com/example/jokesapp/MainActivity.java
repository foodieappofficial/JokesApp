package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvJoke;

    RequestQueue mRequestQueue;
    RequestQueue mArrayRequestQueue;
    List<JSONObject> jokesList ;
    List<Joke> jokes ;

    Joke joke = new Joke();
    StringBuilder stringBuilder = new StringBuilder();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJoke = findViewById(R.id.tv_joke);
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        mArrayRequestQueue = Volley.newRequestQueue(MainActivity.this);
        jokesList = new ArrayList<>();
        jokes = new ArrayList<>();

        // request for getting single json object

        /*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_joke", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                //JSONArray jsonArray = response.getJSONArray()
                try {
                    String setup = response.getString("setup");
                    tvJoke.setText(setup);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(jsonObjectRequest);

         */




        // request for getting an Array of json objects.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_ten", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jo;
                try {

                    // get extract json objects from json array and put it in a list
                    for(int i = 0; i < response.length(); i++) {

                        jo = response.getJSONObject(i);
                        jokesList.add(jo);

                    }

                    // map all json objects to model class and print json objects from the model objects list
                    for(int i = 0; i < jokesList.size(); i++){

                        Gson gson = new GsonBuilder().create();
                        joke =  gson.fromJson(""  + jokesList.get(i), Joke.class);
                        jokes.add(joke);

                        stringBuilder.append(jokes.get(i).getId() + "\n");
                        stringBuilder.append(jokes.get(i).getType() + "\n");
                        stringBuilder.append(jokes.get(i).getSetup() + "\n");
                        stringBuilder.append(jokes.get(i).getPunchline() + "\n\n\n");


                        tvJoke.setText(stringBuilder);

                        //Toast.makeText(MainActivity.this, ""+ jokes.get(i).getPunchline(), Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        mArrayRequestQueue.add(jsonArrayRequest);



    }
}