package com.example.sushantpaygude.scoutit.Registration.Models;

/**
 * Created by sushantpaygude on 4/21/19.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User {

    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}