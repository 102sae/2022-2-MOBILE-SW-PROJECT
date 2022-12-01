package com.course.mydietapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToCalendarBtn = (Button)findViewById(R.id.calendarBtn);
        goToCalendarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(intent);
            }

        });

        Button menuPlusBtn = (Button)findViewById(R.id.menuAddBtn);
        menuPlusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),AddfoodActivity.class);
                startActivity(intent);
            }
        });

        Button gotoMealBtn = (Button)findViewById(R.id.mealAnalysis);
        gotoMealBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),FoodanalysisActivity.class);
                startActivity(intent);
            }
        });
    }
}