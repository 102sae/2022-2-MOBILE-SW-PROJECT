package com.course.mydietapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class AddfoodActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    EditText add;
    EditText count;
    TimePicker time;
    EditText place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        ratingBar=findViewById(R.id.ratingBar);
        add=findViewById(R.id.add);
        count=findViewById(R.id.Count);
        time=findViewById(R.id.Time);
        place=findViewById(R.id.Place);


    }
}