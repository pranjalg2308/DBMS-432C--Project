package com.kalabhedia.urbanclapclone.Api.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {


    @SerializedName("status")
    @Expose
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public Status(boolean status) {
        this.status = status;
    }
}
