package com.course.mydietapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private TextView todayDateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy--MM-dd");
        //todayDateText = findViewById(R.id.todayDate);
        //todayDateText.setText(formatter.format(date));


        //캘린더 보기 버튼
        Button goToCalendarBtn = (Button)findViewById(R.id.calendarBtn);
        goToCalendarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(intent);
            }

        });
        //식사 입력 버튼
        Button menuPlusBtn = (Button)findViewById(R.id.menuAddBtn);
        menuPlusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        //식사 분석 버튼
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