package com.example.css;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Sport.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    abstract SportDao sportDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ApplicationDatabase")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.

                SportDao dao = INSTANCE.sportDao();
                dao.deleteAll();

                Sport sport = new Sport();
                sport.setSport("CANCELED: Tedx", "Apr 25 202", "Center for Faith and Life, Recital Hall");
                dao.insertSport(sport);

                Sport sport1 = new Sport();
                sport1.setSport("CANCELED: Biology Colloquium", "Apr 30 202", "Valders Hall of Science, Room 206");
                dao.insertSport(sport1);

                Sport sport2 = new Sport();
                sport2.setSport("CANCELED: J-Term 2021 Off-Campus Programs \"Meet & Greet\"", "May 10 202", "Valders Hall of Science, Room 206");
                dao.insertSport(sport2);

                Sport sport3 = new Sport();
                sport3.setSport("Music Senior Project Showcase", "May 12 202", "Noble Recital Hall, Choir Room, Room 123: Jenson-Noble Hall of Music");
                dao.insertSport(sport3);

                Sport sport4 = new Sport();
                sport4.setSport("Emeriti Colloquium: Jodi Enos-Berlage: \"What's new in biological research at Luther\"", "May 14 202", "Shirley Baker Commons");
                dao.insertSport(sport4);

            });
        }
    };

}


