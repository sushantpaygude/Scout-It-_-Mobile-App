package com.example.sushantpaygude.scoutit.Dashboard.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sushantpaygude.scoutit.Dashboard.Adapters.ItemsRecyclerViewAdapter;
import com.example.sushantpaygude.scoutit.Dashboard.Models.Product;
import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;
import com.example.sushantpaygude.scoutit.Utilities.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemsRecyclerViewAdapter itemsRecyclerViewAdapter;
    private List<Product> productList = new ArrayList<>();
    private String category;
    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if(bundle != null){
            category = getArguments().getString("CATEGORY");
        }

        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        if(category == null){
            getAllProducts();
        }
        else{
            getAllProductsByCategory();
        }

    }

    private void getAllProducts(){
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Utils.GET_ALL_PRODUCTS, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Type listType = new TypeToken<List<Product>>() {}.getType();
                productList = new Gson().fromJson(response.toString(),listType);
                //List<Product> list = new Gson().fromJson(response.toString(),listType);
                itemsRecyclerViewAdapter = new ItemsRecyclerViewAdapter(productList);
                recyclerView.setAdapter(itemsRecyclerViewAdapter);
                itemsRecyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Fetched products",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Unable to connect to server.",Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                int statuscode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }



    private void getAllProductsByCategory(){
        String url = String.format(Utils.GET_ALL_PRODUCTS_BY_CATEGORY,category);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productList.clear();
                Type listType = new TypeToken<List<Product>>() {}.getType();
                productList = new Gson().fromJson(response.toString(),listType);
                //List<Product> list = new Gson().fromJson(response.toString(),listType);
                itemsRecyclerViewAdapter = new ItemsRecyclerViewAdapter(productList);
                recyclerView.setAdapter(itemsRecyclerViewAdapter);
                itemsRecyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Fetched products",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Unable to connect to server.",Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                int statuscode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }
}
