package com.course.mydietapp;

import androidx.room.Dao;
//import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
//import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void setInsertFood(Food food);
    /*
    @Update
    void setUpdateFood(Food food);

    @Delete
    void setDeleteFood(Food food);
     */

    @Query("SELECT * FROM food")
    List<Food> getFoodAll();


}
