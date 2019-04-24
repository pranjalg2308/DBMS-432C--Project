package com.kalabhedia.urbanclapclone.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Services.class}, version = 1)
public abstract class ServicesDatabase extends RoomDatabase {

    private static volatile ServicesDatabase INSTANCE;

    static ServicesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ServicesDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ServicesDatabase.class, "service_database")
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract ServicesDao servicesDao();
}
