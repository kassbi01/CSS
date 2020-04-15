package com.example.css;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private LiveData<List<Sport>> allSports;
    private Sport sport;
    String url;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allSports = appRepository.getAllSports();

    }

    public void populateDB(String url) {
        appRepository.populateDB(url);
    }

//    public void insert(Sport sports) {
//        appRepository.insert(sports);
//    }
//
//    public void update(Sport sports) {
//        appRepository.update(sports);
//    }

//    public void delete(Sport sports) {
//        appRepository.delete(sports);
//    }

//    public void deleteAll() {
//        appRepository.deleteAll();
//    }

    public LiveData<List<Sport>> getAllSports(){
        if (allSports == null) {
            //now populated the database
            allSports = new MutableLiveData<List<Sport>>();
            populateDB();
        }

        return allSports;
    }

    private void populateDB() {
        appRepository.populateDB(url);
    }
}
