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

public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;
    public TextView todayTextView;
    public EditText contextEditText;
    public String selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarview);
        // 월 한글로 변경
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));

        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator()
        );

        calendarView.addDecorators(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.today())));


        todayTextView = findViewById(R.id.todayTextView);
        contextEditText = findViewById(R.id.contextEditText);
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            int year = date.getYear();
            int month = date.getMonth()+1;
            int day = date.getDay();

            String shot_Day = year + "/" + month + "/" + day;
            Log.i("shot_Day test", shot_Day + "");
            todayTextView.setText(shot_Day);

        });
    }





}