package edu.upenn.cis350.bnbapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView main;
    Button balance, transfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (TextView) findViewById(R.id.maintext);
        balance = (Button) findViewById(R.id.balance);
        balance.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    getBalance("e8k7crKA9S0:APA91bGDZ76NccQkYXIzS5", "1520468133");
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("ERROR ERROR ERROR");
                }
            }
        });

        transfer = (Button) findViewById(R.id.transfer);
        transfer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    makeTransfer("e8k7crKA9S0:APA91bGDZ76NccQkYXIzS5", "1520468087", "1520468060", "2003", "10", "TEST");
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("ERROR ERROR ERROR");
                }
            }
        });
    }

    public void getBalance(String userkey, String account) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject bal = new JSONObject();
        bal.put("userKey", userkey);
        bal.put("accountNumber", account);
        //bal.put("userKey", userkey);
        String url ="https://bnbapideveloperv1.azurewebsites.net/api/Enterprise/Balance";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, bal, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        main.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error retrieving balance", Toast.LENGTH_SHORT).show();
                    }
                });
        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
    }

    public void makeTransfer(String userkey, String src, String dst, String currency, String amount, String ref) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject bal = new JSONObject();
        bal.put("userKey", userkey);
        bal.put("sourceAccountNumber", src);
        bal.put("destinationAccountNumber", dst);
        bal.put("currency", currency);
        bal.put("ammount", amount);
        bal.put("reference", ref);
        //bal.put("userKey", userkey);
        String url ="https://bnbapideveloperv1.azurewebsites.net/api/Enterprise/Transfer";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, bal, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        main.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error making transfer", Toast.LENGTH_SHORT).show();
                    }
                });
        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
    }
}