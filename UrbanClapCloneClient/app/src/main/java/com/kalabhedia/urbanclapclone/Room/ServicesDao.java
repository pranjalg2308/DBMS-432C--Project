package com.kalabhedia.urbanclapclone.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServicesDao {

    @Insert
    void insert(Services... services);

    @Query("DELETE FROM services_table")
    void deleteAll();

    @Query("SELECT * from services_table ORDER BY service_name ASC")
    LiveData<List<Services>> getAllServices();

}
