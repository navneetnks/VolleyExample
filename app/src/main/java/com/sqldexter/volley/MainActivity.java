package com.sqldexter.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hitURL();
    }

    private void hitURL() {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Constants.POPULAR_MOVIE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {
                    JSONObject responseObj = new JSONObject(response);
                    Log.d(TAG,"response text="+responseObj);

                    // Parsing json object response
                    // response will be a json object
//                    boolean error = responseObj.getBoolean("error");
//                    String message = responseObj.getString("message");
//
//                    // checking for error, if not error SMS is initiated
//                    // device should receive it shortly
//                    if (!error) {
//                        // boolean flag saying device is waiting for sms
////                        pref.setIsWaitingForSms(true);
//
//                        // moving the screen to next pager item i.e otp screen
////                        viewPager.setCurrentItem(1);
////                        txtEditMobile.setText(pref.getMobileNumber());
////                        layoutEditMobile.setVisibility(View.VISIBLE);
//
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(getApplicationContext(),
//                                "Error: " + message,
//                                Toast.LENGTH_LONG).show();
//                    }

                    // hiding the progress bar
//                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();

//                    progressBar.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.GONE);
            }
        }) {

            /**
             * Passing user parameters to our server
             * @return
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key", BuildConfig.THE_MOVIE_DB_API_KEY);

                Log.e(TAG, "Posting params: " + params.toString());

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };

        // Adding request to request queue
        MyApplication myApplication=(MyApplication)getApplication();
        myApplication.addToRequestQueue(strReq);
    }
}
