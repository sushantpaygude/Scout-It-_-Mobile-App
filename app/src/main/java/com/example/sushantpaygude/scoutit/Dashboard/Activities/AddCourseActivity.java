package com.example.sushantpaygude.scoutit.Dashboard.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sushantpaygude.scoutit.Dashboard.Models.Course;
import com.example.sushantpaygude.scoutit.Dashboard.Models.UserCourse;
import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;
import com.example.sushantpaygude.scoutit.Utilities.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener
{
    private Spinner spinnerCourse;
    private Spinner spinnerCourseCategory;
    private EditText textCourseName;
    private List<Course> courseList;
    private ArrayAdapter courseAdapter;
    private ArrayAdapter courseCategoryAdapter;
    private ImageView imageNewCourse;
    private Button buttonOffer;
    private boolean newCourse = false;
    private LinearLayout layoutNewCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        spinnerCourse = findViewById(R.id.spinnerCourses);
        spinnerCourseCategory = findViewById(R.id.courseCategory);
        textCourseName = findViewById(R.id.edittextCourseName);
        buttonOffer = findViewById(R.id.buttonOffer);
        courseCategoryAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,Utils.CourseType.values());
        spinnerCourseCategory.setAdapter(courseCategoryAdapter);
        imageNewCourse = findViewById(R.id.imageNewCourse);
        layoutNewCourse = findViewById(R.id.layoutNewCourse);
        layoutNewCourse.setVisibility(View.GONE);
        imageNewCourse.setOnClickListener(this);
        buttonOffer.setOnClickListener(this);
        getExistingCourses();
    }

    private void getExistingCourses(){
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Utils.GET_ALL_EXISTING_COURSES, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type listType = new TypeToken<List<Course>>() {}.getType();
                courseList = new Gson().fromJson(response.toString(),listType);
                courseAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,courseList);
                spinnerCourse.setAdapter(courseAdapter);
                spinnerCourse.setOnItemSelectedListener(new CourseSpinnerListener());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                int statuscode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonOffer:
                postCourse();
                break;
            case R.id.imageNewCourse:
                if(!newCourse){
                    imageNewCourse.setImageDrawable(getResources().getDrawable(R.drawable.icon_minus_white));
                    newCourse = true;
                    layoutNewCourse.setVisibility(View.VISIBLE);
                }
                else{
                    imageNewCourse.setImageDrawable(getResources().getDrawable(R.drawable.icon_plus_white));
                    newCourse = false;
                    layoutNewCourse.setVisibility(View.GONE);
                }

                break;
        }
    }

    private class CourseSpinnerListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Course course = courseList.get(i);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class CourseCategoryAdapter implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private void postCourse(){
        if(newCourse){
            postNewCourse();
        }
        else{
            postExistingCourse();
        }
    }

    private void postNewCourse(){
        Course course = new Course();
        String category = spinnerCourseCategory.getSelectedItem().toString();
        course.setCourseName(textCourseName.getText().toString());
        course.setCategory(category);
        course.setStatus("ACTIVE");
        course.setUserid(2); //TODO changed hardcoded userid.
            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Utils.OFFER_COURSE, new JSONObject(new Gson().toJson(course)), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"Course added successfully",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR",error.toString());
                        //parseVolleyError(error);
                        Toast.makeText(getApplicationContext(),"Unable to add course at this time.",Toast.LENGTH_SHORT).show();
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

    private void postExistingCourse(){
        UserCourse userCourse = new UserCourse();
        Course course =(Course) spinnerCourse.getSelectedItem();
        userCourse.setCourseid(course.getCourseId());
        userCourse.setUserid(2); //TODO changed hardcoded userid.
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Utils.OFFER_EXISTING_COURSE, new JSONObject(new Gson().toJson(userCourse)), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(),"Course added successfully",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR",error.toString());
                    //parseVolleyError(error);
                    Toast.makeText(getApplicationContext(),"Unable to add course at this time.",Toast.LENGTH_SHORT).show();
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

}
