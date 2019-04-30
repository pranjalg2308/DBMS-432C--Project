package com.kalabhedia.urbanclapclone.Api.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesOffered {

    @SerializedName("SERVICE_ID")
    @Expose
    private String SERVICE_ID;

    @SerializedName("NAME")
    @Expose
    private String NAME;

    @SerializedName("DESCRIPTION")
    @Expose
    private String DESCRIPTION;

    @SerializedName("CHARGE")
    @Expose
    private int CHARGE;

    @SerializedName("PERHOUR")
    @Expose
    private int PERHOUR;

    @SerializedName("COMMISSION")
    @Expose
    private int COMMISSION;

    @SerializedName("PROVIDER_ID")
    @Expose
    private String PROVIDER_ID;

    public ServicesOffered(String SERVICE_ID, String NAME, String DESCRIPTION, int CHARGE, int PERHOUR, int COMMISSION, String PROVIDER_ID) {
        this.SERVICE_ID = SERVICE_ID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.CHARGE = CHARGE;
        this.PERHOUR = PERHOUR;
        this.COMMISSION = COMMISSION;
        this.PROVIDER_ID = PROVIDER_ID;
    }

    public String getSERVICE_ID() {
        return SERVICE_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public int getCHARGE() {
        return CHARGE;
    }

    public int getPERHOUR() {
        return PERHOUR;
    }

    public int getCOMMISSION() {
        return COMMISSION;
    }

    public String getPROVIDER_ID() {
        return PROVIDER_ID;
    }
}
