package com.kalabhedia.urbanclapclone.Api.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceProvider {


    @Expose
    @SerializedName("USER_ID")
    private String userId;

    @Expose
    @SerializedName("PASSWORD")
    private String password;

    @Expose
    @SerializedName("EMAIL")
    private String email;

    @Expose
    @SerializedName("COMPANYNAME")
    private String companyName;

    @Expose
    @SerializedName("CONTACTNO")
    private int contactNo;

    @Expose
    @SerializedName("RATING")
    private int rating;
    @Expose
    @SerializedName("CATEGORY_ID")
    private String categoryId;
    @Expose
    @SerializedName("ADDLINE1")
    private String addLine1;
    @Expose
    @SerializedName("ADDLINE2")
    private String addLine2;
    @Expose
    @SerializedName("CITY")
    private String city;

    public ServiceProvider(String userId, String password, String email, String companyName, int contactNo, int rating, String categoryId, String addLine1, String addLine2, String city) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.companyName = companyName;
        this.contactNo = contactNo;
        this.rating = rating;
        this.categoryId = categoryId;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getContactNo() {
        return contactNo;
    }

    public int getRating() {
        return rating;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public String getCity() {
        return city;
    }

}
