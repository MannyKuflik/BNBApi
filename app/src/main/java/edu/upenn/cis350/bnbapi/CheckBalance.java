package edu.upenn.cis350.bnbapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CheckBalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);
    }

    public void locationRequest(String uk, String longi, int radius){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://bnbapideveloperv1.azurewebsites.net/?userKey=e8k7crKA9S0:APA91bGDZ76NccQkYXIzS5";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CheckBalance.this, "Error retrieving Gyms from server", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}