package com.course.mydietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;
    public TextView todayTextView;
    public EditText contextEditText;
    public FoodDao cFoodDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarview);
        // 월 한글로 변경
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        // 토요일 일요일 색 변경
        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator()
        );
        //점 찍기
        calendarView.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.today())));

        //클릭한 날짜 TextView
        todayTextView = findViewById(R.id.todayTextView);
        contextEditText = findViewById(R.id.contextEditText);
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            int year = date.getYear();
            int month = date.getMonth() + 1;
            int day = date.getDay();

            String shot_Day = year + "/" + month + "/" + day;
            Log.i("shot_Day test", shot_Day + "");
            todayTextView.setText(shot_Day);
        });

        FoodDatabase database = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "MyDietApp")
                .fallbackToDestructiveMigration() //스키마(Database) 변경 가능
                .allowMainThreadQueries()         //main Thread에서 DD에 입출력 가능
                .build();

        cFoodDao = database.foodDao(); //인터페이스 객체 할당

        //데이터 삽입
        Food food = new Food();
        food.setName("피자");
        food.setAmount("1인분");
        food.setTime("점심");
        food.setImage(" ");
        food.setPlace("잠실");
        cFoodDao.setInsertFood(food);

        //데이터 조회
        List<Food> foodList = cFoodDao.getFoodAll();

        for (int i = 0; i < foodList.size(); i++) {
            Log.i("test", foodList.get(i).getName() + "\n" + foodList.get(i).getAmount());
        }

    }


}