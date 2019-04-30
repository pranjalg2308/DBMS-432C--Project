package com.kalabhedia.urbanclapclone.Api.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Booking {

    @SerializedName("ORDER_ID")
    @Expose
    private String ORDER_ID;

    @SerializedName("USER_ID")
    @Expose
    private String USER_ID;

    @SerializedName("SERVICE_ID")
    @Expose
    private String SERVICE_ID;

    @SerializedName("DATE_OF_ORDER")
    @Expose
    private int DATE_OF_ORDER;

    @SerializedName("COMPLETED")
    @Expose
    private int COMPLETED;

    @SerializedName("RATING")
    @Expose
    private int RATING;

    @SerializedName("DURATION")
    @Expose
    private int DURATION;

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public String getSERVICE_ID() {
        return SERVICE_ID;
    }

    public int getDATE_OF_ORDER() {
        return DATE_OF_ORDER;
    }

    public int getCOMPLETED() {
        return COMPLETED;
    }

    public int getRATING() {
        return RATING;
    }

    public int getDURATION() {
        return DURATION;
    }

    public long getSTART_TIME() {
        return START_TIME;
    }

    public int getDELETED() {
        return DELETED;
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

    public Booking(String ORDER_ID, String USER_ID, String SERVICE_ID, int DATE_OF_ORDER, int COMPLETED, int RATING, int DURATION, long START_TIME, int DELETED, String NAME, String DESCRIPTION, int CHARGE, int PERHOUR, int COMMISSION) {
        this.ORDER_ID = ORDER_ID;
        this.USER_ID = USER_ID;
        this.SERVICE_ID = SERVICE_ID;
        this.DATE_OF_ORDER = DATE_OF_ORDER;
        this.COMPLETED = COMPLETED;
        this.RATING = RATING;
        this.DURATION = DURATION;
        this.START_TIME = START_TIME;
        this.DELETED = DELETED;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.CHARGE = CHARGE;
        this.PERHOUR = PERHOUR;
        this.COMMISSION = COMMISSION;
    }

    @SerializedName("START_TIME")
    @Expose
    private long START_TIME;

    @SerializedName("DELETED")
    @Expose
    private int DELETED;

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


}
