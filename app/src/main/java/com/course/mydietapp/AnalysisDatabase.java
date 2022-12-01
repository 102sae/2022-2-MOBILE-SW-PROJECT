package com.course.mydietapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Analysis.class}, version = 1)
public abstract class AnalysisDatabase extends RoomDatabase {
    public abstract AnalysisDao analysisDao();
}
