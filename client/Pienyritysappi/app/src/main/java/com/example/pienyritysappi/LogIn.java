package com.example.pienyritysappi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    private String logInUrl = "http://mobiilisovellus.therozor.com:5000/login";
    private String logInEmail = "";
    private String logInPassword = "";
    private EditText etEmail;
    private EditText etPassword;
    private String apikey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etEmail = findViewById(R.id.emailEditText);
        etPassword = findViewById(R.id.passwordEditText);
    }

    public void logInButtonClicked(View view)
    {
        logInEmail = etEmail.getText().toString();
        logInPassword = etPassword.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("user_email", logInEmail);
            postData.put("user_password", logInPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, logInUrl, postData,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    apikey = response.getString("apikey");
                    System.out.println(apikey);

                    Intent intent = new Intent(getApplicationContext(),Services.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void registerCustomerButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }

    public void registerCompanyButtonClicked(View view) {
        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }
}