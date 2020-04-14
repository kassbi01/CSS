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
@TypeConverters({Converters.class})
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
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

//    }
//
//    /**
//     * Override the onOpen method to populate the database.
//     * For this sample, we clear the database every time it is created or opened.
//     *
//     * If you want to populate the database only when the database is created for the 1st time,
//     * override RoomDatabase.Callback()#onCreate
//     */
//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//
//                SportDao dao = INSTANCE.sportDao();
//                dao.deleteAll();
//
//                Sport sport = new Sport();
//                sport.setDate(11/12/13);
//                sport.setSportOpponent("Luther Vs Loras");
//                sport.setGameLocation("Luther college");
//                sport.setGameDay(12);
//
//                dao.insertSport(sport);
//            });
//        }
//    };

    }
}

