package com.kalabhedia.urbanclapclone.Api.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {


    @SerializedName("USER_ID")
    @Expose
    private String userID;

    @SerializedName("PASSWORD")
    @Expose
    private String password;

    @SerializedName("EMAIL")
    @Expose
    private String email;

    @SerializedName("FIRSTNAME")
    @Expose
    private String firstName;

    @SerializedName("LASTNAME")
    @Expose
    private String lastName;

    @SerializedName("GENDER")
    @Expose
    private String gender;

    @SerializedName("AGE")
    @Expose
    private int age;
    @SerializedName("CONTACTNO")
    @Expose
    private long contactNo;
    @SerializedName("ADDLINE1")
    @Expose
    private String addLine1;
    @SerializedName("ADDLINE2")
    @Expose
    private String addLine2;
    @SerializedName("CITY")
    @Expose
    private String city;

    public Users(String userID, String password, String email, String firstName, String lastName, String gender, int age, int contactNo, String addLine1, String addLine2, String city) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.contactNo = contactNo;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.city = city;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public long getContactNo() {
        return contactNo;
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
