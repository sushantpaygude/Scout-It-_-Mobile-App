package com.example.sushantpaygude.scoutit.Dashboard.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sushantpaygude.scoutit.Dashboard.Models.Product;
import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;
import com.example.sushantpaygude.scoutit.Utilities.VolleySingleton;
import com.google.gson.Gson;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText productName;
    private EditText productPrice;
    private EditText productDescription;
    private Button buttonSubmit;
    private Spinner spinnerProductCategory;
    private ArrayAdapter courseCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
        spinnerProductCategory=findViewById(R.id.spinnerProductCategory);
        courseCategoryAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,Utils.productCategoryEnum.values());
        spinnerProductCategory.setAdapter(courseCategoryAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSubmit:
                Product product = new Product();
                DateTime dateTime = new DateTime();
                //TODO Remove harcoded userid.
                product.setUserid(1);
                product.setProductName(productName.getText().toString());
                product.setProductCost(Integer.valueOf(productPrice.getText().toString()));
                product.setProductDescription(productDescription.getText().toString());
                product.setProductPostDate(dateTime.getMillisOfDay());
                String category = spinnerProductCategory.getSelectedItem().toString();
                product.setCategory(category);
                postProduct(product);
                break;
        }
    }

    private void postProduct(Product product){
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Utils.POST_PRODUCTS, new JSONObject(new Gson().toJson(product)), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(),"Product added successfully",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR",error.toString());
                    //parseVolleyError(error);
                    Toast.makeText(getApplicationContext(),"Unable to add product at this time.",Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected VolleyError parseNetworkError(VolleyError volleyError){
                    if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                        VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                        volleyError = error;
                    }

                    return volleyError;
                }
            };

            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void parseVolleyError(VolleyError error) {
        try {
            String responseBody = new String(error.networkResponse.data, "utf-8");
            JSONObject data = new JSONObject(responseBody);
            JSONArray errors = data.getJSONArray("errors");
            JSONObject jsonMessage = errors.getJSONObject(0);
            String message = jsonMessage.getString("message");
            Log.e("ERROR",":"+message);
            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
        } catch (UnsupportedEncodingException errorr) {
        }
    }
}
