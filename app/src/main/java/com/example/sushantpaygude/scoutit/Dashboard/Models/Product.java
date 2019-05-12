package com.example.sushantpaygude.scoutit.Dashboard.Models;

/**
 * Created by sushantpaygude on 4/24/19.
 */

import com.android.volley.toolbox.StringRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("productid")
    @Expose
    private Integer productid;
    @SerializedName("marketplaceproductsid")
    @Expose
    private Integer marketplaceproductsid;
    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productDescription")
    @Expose
    private String productDescription;
    @SerializedName("productCost")
    @Expose
    private Integer productCost;
    @SerializedName("productPostDate")
    @Expose
    private Integer productPostDate;
    @SerializedName("productStatus")
    @Expose
    private String productStatus;
    @SerializedName("category")
    @Expose
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public void setProductCost(Integer productCost) {
        this.productCost = productCost;
    }

    public Integer getProductPostDate() {
        return productPostDate;
    }

    public void setProductPostDate(Integer productPostDate) {
        this.productPostDate = productPostDate;
    }

    public Integer getMarketplaceproductsid() {
        return marketplaceproductsid;
    }

    public void setMarketplaceproductsid(Integer marketplaceproductsid) {
        this.marketplaceproductsid = marketplaceproductsid;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

}
