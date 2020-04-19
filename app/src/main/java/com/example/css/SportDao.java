package com.example.css;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.Date;
import java.util.List;

@Dao
public interface SportDao {
//    @Query("SELECT * from diary_table ORDER BY eventId ASC")
//    LiveData<List<Sport>> getSport();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSport(Sport sport);

    @Query("DELETE FROM diary_table")
    void deleteAll();

    @Query("SELECT * FROM  diary_table ")
    LiveData<List<Sport>> getAllSports();

    @Query("SELECT * FROM diary_table WHERE eventId = :name")
    List<Sport> findSport(String name);

    @Query("DELETE FROM diary_table WHERE eventId = :name")
    void deleteSport(String name);

    @Update
    void updateSport(Sport sport);

}
