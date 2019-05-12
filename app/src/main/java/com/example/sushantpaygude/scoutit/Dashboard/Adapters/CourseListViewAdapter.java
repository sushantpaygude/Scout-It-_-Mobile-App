package com.example.sushantpaygude.scoutit.Dashboard.Adapters;

/**
 * Created by sushantpaygude on 5/6/19.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.sushantpaygude.scoutit.Dashboard.Models.Course;
import com.example.sushantpaygude.scoutit.R;

public class CourseListViewAdapter extends BaseExpandableListAdapter {

    private Context _context;
    //private List<String> courseNamesList; // header titles
    private List<String[]> courseTitleList;
    private HashMap<String,List<Course>> courseHashMap;
    // child data in format of header title, child title
    //private HashMap<String, List<String>> _listDataChild;

    public CourseListViewAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        //this.courseNamesList = listDataHeader;
        //this._listDataChild = listChildData;
    }

    public CourseListViewAdapter(List<Course> courseList,Context context){
        courseTitleList = new ArrayList<>();
        courseHashMap = new HashMap<>();
        this._context = context;
        for(Course course : courseList){

            if(courseHashMap.containsKey(course.getCourseName())){
                List<Course> courses = courseHashMap.get(course.getCourseName());
                courses.add(course);
                courseHashMap.put(course.getCourseName(),courses);
            }

            else{
                List<Course> courses = new ArrayList<>();
                courses.add(course);
                courseHashMap.put(course.getCourseName(),courses);
                String[] s = new String[2];
                s[0] = course.getCourseName();
                s[1] = course.getCategory();
                courseTitleList.add(s);
            }
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return courseHashMap.get(courseTitleList.get(groupPosition)[0]).get(childPosititon);
//        return this._listDataChild.get(this.courseNamesList.get(groupPosition))
//                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.course_child_view, null);
        }
         TextView textCourseInstructor = convertView.findViewById(R.id.textCourseInstructor);
         TextView textInstructorEmail = convertView.findViewById(R.id.textInstructorEmail);
         Course course = (Course) getChild(groupPosition,childPosition);
         textCourseInstructor.setText(course.getFirstname().concat(" ").concat(course.getLastname()));
         textInstructorEmail.setText(course.getEmail());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return courseHashMap.get(courseTitleList.get(groupPosition)[0]).size();
//        return this._listDataChild.get(this.courseNamesList.get(groupPosition))
//                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.courseTitleList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.courseTitleList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
//        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.course_header, null);
        }

        TextView textItemName = convertView.findViewById(R.id.textItemName);
        TextView textCourseCategory = convertView.findViewById(R.id.textCourseCategory);
        String[] headers = (String[]) getGroup(groupPosition);
        textItemName.setText(headers[0]);
        textCourseCategory.setText(headers[1]);
//
        textItemName.setTypeface(null, Typeface.BOLD);
//        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
