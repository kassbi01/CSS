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
    String url = "https://www.luther.edu/events/?category=455731&no_search=1";;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allSports = appRepository.getAllSports();
    }
    public void insert(Sport sports) {
        appRepository.insert(sports);
    }

    public LiveData<List<Sport>> getAllSports(){
        if (allSports == null) {
            //now populated the database
            allSports = new MutableLiveData<List<Sport>>();
            populateDB(url);
        }

        return allSports;
    }

    private void populateDB(String url) {
        appRepository.populateDB(url);
    }

}
