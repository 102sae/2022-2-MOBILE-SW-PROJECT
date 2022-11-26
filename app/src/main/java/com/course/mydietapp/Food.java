package com.course.mydietapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "food")
public class Food {
    @PrimaryKey(autoGenerate = true)
    private int post_id  = 0; // 하나의 식단에 대한 고유 ID

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="image")
    private String image;

    @ColumnInfo(name="amount")
    private String amount;

    @ColumnInfo(name="time")
    private String time;

    @ColumnInfo(name="place")
    private String place;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
