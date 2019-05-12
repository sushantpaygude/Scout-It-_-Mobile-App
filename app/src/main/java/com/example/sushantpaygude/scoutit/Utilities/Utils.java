package com.example.sushantpaygude.scoutit.Utilities;

/**
 * Created by sushantpaygude on 4/20/19.
 */

public class Utils {
    public static final int RC_SIGN_IN = 1;


    //Request APIs
    public static final String BASE_URL = "http://130.85.242.123:8080/scoutit";
    public static final String ADD_USER = BASE_URL + "/users/adduser";
    public static final String GET_ALL_PRODUCTS = BASE_URL +"/products/getallactiveproducts";
    public static final String GET_ALL_PRODUCTS_BY_CATEGORY = BASE_URL +"/products/getproductsbycategory/%s";
    public static final String POST_PRODUCTS = BASE_URL +"/products/addproduct";
    public static final String GET_ALL_COURSES = BASE_URL +"/courses/getallcourses";
    public static final String GET_ALL_EXISTING_COURSES = BASE_URL +"/courses/getallexistingcourses";
    public static final String OFFER_COURSE = BASE_URL +"/courses/offercourse";
    public static final String OFFER_EXISTING_COURSE = BASE_URL +"/courses/offerexistingcourse";
    public static final String GET_COURSES_BY_CATEGORY = BASE_URL +"/courses/getcoursesbycategory/%s";


    public static final int REQUEST_FOR_PRODUCT_FILTER =1;
    public static final int REQUEST_FOR_COURSE_FILTER =2;

    public enum productCategoryEnum
    {
        ELECTRONICS("ELECTRONICS"),
        BOOKS("BOOKS"),
        FURNITURE("FURNITURE");
        private String categoryName;
        productCategoryEnum(String s){
            this.categoryName = s;
        }

        @Override
        public String toString() {
            return categoryName;
        }
    }

    public enum CourseType{
        COMPUTER_SCIENCE("COMPUTER SCIENCE"),
        INFORMATION_SYSTEM("INFORMATION_SYSTEM"),
        COMPUTER_ENGINEERING("COMPUTER_ENGINEERING"),
        ELECTRICAL_ENGINEERING("ELECTRICAL_ENGINEERING");
        private String type;
        CourseType(String type){
            this.type = type;
        }
        @Override
        public String toString() {
            return type;
        }
    }
}
