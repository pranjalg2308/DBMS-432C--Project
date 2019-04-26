package com.kalabhedia.urbanclapclone.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ServicesRepository {

    private ServicesDao servicesDao;
    private LiveData<List<Services>> mAllService;

    ServicesRepository(Application application) {
        ServicesDatabase db = ServicesDatabase.getDatabase(application);
        servicesDao = db.servicesDao();
        mAllService = servicesDao.getAllServices();
    }

    LiveData<List<Services>> getmAllServices() {
        return mAllService;
    }

    public void deleteAll(){new deleteAsyncTask(servicesDao).execute();}

    public void insert(Services services) {
        new insertAsyncTask(servicesDao).execute(services);
    }

    private static class insertAsyncTask extends AsyncTask<Services, Void, Void> {

        private ServicesDao mAsyncTaskDao;

        insertAsyncTask(ServicesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Services... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void>{

        private ServicesDao mAsyncTaskDao;

        deleteAsyncTask(ServicesDao servicesDao) {
            mAsyncTaskDao = servicesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
