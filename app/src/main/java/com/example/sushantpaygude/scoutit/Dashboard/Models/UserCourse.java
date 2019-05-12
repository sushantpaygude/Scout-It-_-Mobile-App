package com.example.sushantpaygude.scoutit.Dashboard.Models;

/**
 * Created by sushantpaygude on 5/6/19.
 */

public class UserCourse {
    private int usercourseid;
    private int courseid;
    private int userid;
    private String status;

    public int getUsercourseid() {
        return usercourseid;
    }

    public void setUsercourseid(int usercourseid) {
        this.usercourseid = usercourseid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
