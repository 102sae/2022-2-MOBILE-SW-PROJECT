package com.course.mydietapp;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnalysisDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE) //중복 대체
    void setInsertAnalysis(Analysis analysis);

    @Delete
    void setDeleteAnalysis(Analysis analysis);

    @Query("SELECT * FROM Analysis")
    List<Analysis> getAnalysisAll();
}
