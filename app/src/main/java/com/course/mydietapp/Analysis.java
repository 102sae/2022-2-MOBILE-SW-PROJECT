package com.course.mydietapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Analysis {
    @PrimaryKey(autoGenerate = true)
    private int id=0; //고유 ID

    private String kname; //식사 이름

    private String kcal; //칼로리

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }
}
