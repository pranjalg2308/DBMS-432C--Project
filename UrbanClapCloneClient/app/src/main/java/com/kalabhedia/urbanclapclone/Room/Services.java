package com.kalabhedia.urbanclapclone.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "services_table")
public class Services {

    @PrimaryKey(autoGenerate = true)
    private int serviceId;

    @ColumnInfo(name = "service_name")
    private String serviceName;

    @ColumnInfo(name = "service_url")
    private String serviceUrl;

    public Services(String serviceName, String serviceUrl) {
        this.serviceId = 0;
        this.serviceName = serviceName;
        this.serviceUrl = serviceUrl;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }
}
