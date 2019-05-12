package com.example.sushantpaygude.scoutit.Dashboard.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sushantpaygude.scoutit.Dashboard.Adapters.CourseListViewAdapter;
import com.example.sushantpaygude.scoutit.Dashboard.Adapters.CourseRecyclerViewAdapter;
import com.example.sushantpaygude.scoutit.Dashboard.Models.Course;
import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;
import com.example.sushantpaygude.scoutit.Utilities.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushantpaygude on 4/24/19.
 */

public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpandableListView expandableListView;
    private CourseRecyclerViewAdapter courseRecyclerViewAdapter;
    private CourseListViewAdapter courseListViewAdapter;
    private List<Course> courseList = new ArrayList<>();
    private String category;
    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle != null){
            category = getArguments().getString("CATEGORY");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        expandableListView = view.findViewById(R.id.expandableListView);
        if(category == null){
            getAllCourses();
        }
        else{
            getAllCoursesByCategory();
        }
    }




    private void getAllCourses(){
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Utils.GET_ALL_COURSES, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Type listType = new TypeToken<List<Course>>() {}.getType();
                courseList = new Gson().fromJson(response.toString(),listType);
                courseListViewAdapter = new CourseListViewAdapter(courseList,getContext());
                expandableListView.setAdapter(courseListViewAdapter);
                courseListViewAdapter.notifyDataSetChanged();
//                courseRecyclerViewAdapter = new CourseRecyclerViewAdapter(courseList);
//                recyclerView.setAdapter(courseRecyclerViewAdapter);
//                courseRecyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Fetched courses",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Unable to connect to server.",Toast.LENGTH_SHORT).show();

            }
        });

        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void getAllCoursesByCategory(){

        String url = String.format(Utils.GET_COURSES_BY_CATEGORY, category);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Type listType = new TypeToken<List<Course>>() {}.getType();
                courseList = new Gson().fromJson(response.toString(),listType);
                courseListViewAdapter = new CourseListViewAdapter(courseList,getContext());
                expandableListView.setAdapter(courseListViewAdapter);
                courseListViewAdapter.notifyDataSetChanged();
//                courseRecyclerViewAdapter = new CourseRecyclerViewAdapter(courseList);
//                recyclerView.setAdapter(courseRecyclerViewAdapter);
//                courseRecyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Fetched courses",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Unable to connect to server.",Toast.LENGTH_SHORT).show();

            }
        });

        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);



    }
}
