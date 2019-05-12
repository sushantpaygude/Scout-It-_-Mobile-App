package com.example.sushantpaygude.scoutit.Dashboard.Adapters;

/**
 * Created by sushantpaygude on 4/24/19.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.scoutit.Dashboard.Models.Course;
import com.example.sushantpaygude.scoutit.Dashboard.Models.Product;
import com.example.sushantpaygude.scoutit.R;

import java.util.List;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.scoutit.Dashboard.Models.Product;
import com.example.sushantpaygude.scoutit.R;

import java.util.List;

/**
 * Created by sushantpaygude on 4/24/19.
 */

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ItemsViewHolder> {

    private List<Course> courseList;
    public CourseRecyclerViewAdapter(List<Course> courseList){
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_course_item,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.textItemName.setText(courseList.get(position).getCourseName());
        holder.textCourseCategory.setText(courseList.get(position).getCategory());
        holder.textCourseInstructor.setText(courseList.get(position).getFirstname().concat(courseList.get(position).getLastname()));
        holder.textInstructorEmail.setText(courseList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{

        private TextView textItemName;
        private TextView textCourseCategory;
        private TextView textPrice;
        private TextView buttonContact;
        private TextView textCourseInstructor;
        private TextView textInstructorEmail;
        private ItemsViewHolder(View itemView) {
            super(itemView);
            textItemName = itemView.findViewById(R.id.textItemName);
            textCourseCategory = itemView.findViewById(R.id.textCourseCategory);
            textCourseInstructor = itemView.findViewById(R.id.textCourseInstructor);
            textInstructorEmail = itemView.findViewById(R.id.textInstructorEmail);
            //textPrice = itemView.findViewById(R.id.textPrice);
            buttonContact = itemView.findViewById(R.id.buttonContact);
        }
    }
}

