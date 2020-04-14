package com.example.css;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppRepository {
    private SportDao dao;
    private String htmlContentInStringFormat;
    String url;

    private LiveData<List<Sport>> allSports;

    public AppRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        dao = database.sportDao();
        allSports = dao.getSport();
    }

    public void insert(Sport sports) {

        InsertSportAsyncTask task = new InsertSportAsyncTask(dao);
        task.execute(sports);

    }

    public void populateDB(String url) {
        PopulateDBAsyncTask task = new PopulateDBAsyncTask(dao, url);
        task.execute();
    }

    public void update(Sport sports) {

        UpdateSportAsyncTask task = new UpdateSportAsyncTask(dao);
        task.execute(sports);

    }

//    public void delete(Sport sports) {
//
//        new DeleteSportAsyncTask(dao).execute();
//
//    }

    public void deleteAll(){

        DeleteAllSportAsyncTask task = new DeleteAllSportAsyncTask(dao);
        task.execute();

    }

    public LiveData<List<Sport>> getAllSports() {
        return allSports;
    }

    private static class InsertSportAsyncTask extends AsyncTask<Sport, Void, Void> {
        private SportDao myDao;

        private InsertSportAsyncTask(SportDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            myDao.insertSport(sports[0]);
            return null;
        }
    }

//    private static class DeleteSportAsyncTask extends AsyncTask<Sport, Void, Void> {
//        private SportDao myDao;
//
//        private DeleteSportAsyncTask(SportDao myDao) {
//            this.myDao = myDao;
//        }
//
//        @Override
//        protected Void doInBackground(Sport... sports) {
//            myDao.deleteSport(sports);
//            return null;
//        }
//    }

    private static class UpdateSportAsyncTask extends AsyncTask<Sport, Void, Void> {
        private SportDao myDao;

        private UpdateSportAsyncTask(SportDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            myDao.updateSport(sports[0]);
            return null;
        }
    }

    private static class DeleteAllSportAsyncTask extends AsyncTask<Void, Void, Void> {
        private SportDao myDao;

        private DeleteAllSportAsyncTask(SportDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDao.deleteAll();
            return null;
        }
    }


    private static class PopulateDBAsyncTask extends AsyncTask<String, Void, Void> {
        private SportDao myDao;
        String url;

        private PopulateDBAsyncTask (SportDao myDao, String url) {
            this.url = url;
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(String... params) {

            String url = params[0];
            ///ArticleModel articleModel = null;

            ArrayList<String> eventNames = new ArrayList<String>();
            ArrayList<String> eventDates = new ArrayList<String>();
            ArrayList<String> eventLocations = new ArrayList<String>();


            ArrayList<Sport> sports = new ArrayList<Sport>();

            String name;
            String time;
            String location;


            Document pageDocument;
            Elements element1;
            Elements element2;
            Elements element3;


            try {
                pageDocument = Jsoup.connect(url).get();

                //elements = pageDocument.select("#body-content");
                //articleElements = pageDocument.select(".wrap .cols .col-1of2 p");

                element1 = pageDocument.select("div.title");
                element2 = pageDocument.select("div.time");
                element3 = pageDocument.select("div.location");


                //find all the titles
                for(Element element: element1) {
                    eventNames.add(element.text());
                }

                //event time
                for(Element element: element2) {
                    eventDates.add(element.text());
                }

                //event location
                for(Element element: element3) {
                    eventLocations.add(element.text());
                }

                //ArrayList<Sport> listSports = new ArrayList<Sport>();


                Sport sport = new Sport();
                for (int i = 0; i < eventNames.size(); i ++) {
                    name = eventNames.get(i);
                    time = eventDates.get(i);
                    location = eventLocations.get(i);

                    sport.setEventName(name);
                    sport.setDate(time);
                    sport.setEventLocation(location);

                    myDao.insertSport(sport);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}

