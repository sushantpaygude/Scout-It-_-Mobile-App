package com.example.sushantpaygude.scoutit.Dashboard.Models;

/**
 * Created by sushantpaygude on 4/24/19.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {

    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("courseId")
    @Expose
    private Integer courseId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("marketplacecourseid")
    @Expose
    private Integer marketplacecourseid;
    @SerializedName("usercourseid")
    @Expose
    private Integer usercourseid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMarketplacecourseid() {
        return marketplacecourseid;
    }

    public void setMarketplacecourseid(Integer marketplacecourseid) {
        this.marketplacecourseid = marketplacecourseid;
    }

    public Integer getUsercourseid() {
        return usercourseid;
    }

    public void setUsercourseid(Integer usercourseid) {
        this.usercourseid = usercourseid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return this.courseName;
    }
}