package com.kalabhedia.urbanclapclone.Api.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ServiceCategory {


    @SerializedName("category_id")
    @Expose
    private String categoryId;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("monthlyPayment")
    @Expose
    private int monthlyPayement;

    public ServiceCategory(String categoryId, String category, int monthlyPayement) {
        this.categoryId = categoryId;
        this.category = category;
        this.monthlyPayement = monthlyPayement;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategory() {
        return category;
    }

    public int getMonthlyPayement() {
        return monthlyPayement;
    }
}
