package com.course.mydietapp;


import static com.course.mydietapp.RealPathUtil.getRealPath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;

import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;
    public TextView todayTextView;
    public TextView selectDayFoodAmount;
    public TextView selectDayFoodText;
    public TextView selectDayFoodTime;
    public ImageView selectDayFoodImage;
    public FoodDao cFoodDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        FoodDatabase database = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "MyDietApp")
                .fallbackToDestructiveMigration() //스키마(Database) 변경 가능
                .allowMainThreadQueries()         //main Thread에서 DD에 입출력 가능
                .build();
        cFoodDao = database.foodDao(); //인터페이스 객체 할당
        List<Food> foodList = cFoodDao.getFoodAll(); //Food 가져오기


        calendarView = findViewById(R.id.calendarview);
        // 월 한글로 변경
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        // 토요일 일요일 색 변경
        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator()
        );

        //클릭한 날짜 TextView
        todayTextView = findViewById(R.id.todayTextView);

        //클릭한 날짜에 입력된 음식 TextView, Image View
        selectDayFoodText = findViewById(R.id.selectDayFoodNameText);
        selectDayFoodAmount = findViewById(R.id.selectDayFoodAmountText);
        selectDayFoodTime = findViewById(R.id.selectDayFoodTimeText);
        selectDayFoodImage = findViewById(R.id.selectDayFoodImage);

        //데이터 있는 데에 마다 점 찍기
        for (int i = 0; i < foodList.size(); i++) {
            //점 찍기
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = formatter.parse(foodList.get(i).getDate());
                CalendarDay day = CalendarDay.from(date);
                calendarView.addDecorators(new EventDecorator(Color.RED, Collections.singleton(day)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //캘린더의 날짜가 클릭되었을 때
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            int year = date.getYear();
            int month = date.getMonth() + 1;
            int day = date.getDay();
            String shot_Day;
            if(day<10){
                shot_Day = year + "-" + month + "-0" + day;
            }
            else {
                //선택된 날짜 String 으로 변경
                shot_Day = year + "-" + month + "-" + day;
            }
            todayTextView.setText(shot_Day);

            selectDayFoodText.setVisibility(View.VISIBLE); //food data 보이도록
            selectDayFoodAmount.setVisibility(View.VISIBLE);
            selectDayFoodTime.setVisibility(View.VISIBLE);
            selectDayFoodImage.setVisibility(View.VISIBLE);

            List<Food> selectedFoodList = cFoodDao.loadAllFoodOnDate(shot_Day); // 선택된 날짜 Food 가져오기
            if (cFoodDao.loadAllFoodOnDate(shot_Day).size() == 0) {
                selectDayFoodText.setVisibility(View.INVISIBLE); //food data 안보이게
                selectDayFoodAmount.setVisibility(View.INVISIBLE);
                selectDayFoodTime.setVisibility(View.INVISIBLE);
                selectDayFoodImage.setVisibility(View.VISIBLE);
            } else {
                //선택된 날짜에 저장된 food data 작게 불러오기
                for (int i = 0; i < cFoodDao.loadAllFoodOnDate(shot_Day).size(); i++) {
                    // food data base 가져오기
                    selectDayFoodText.setText(selectedFoodList.get(i).getName());
                    selectDayFoodAmount.setText(selectedFoodList.get(i).getAmount());
                    selectDayFoodTime.setText(selectedFoodList.get(i).getTime());

                    try{
                        InputStream in = getContentResolver().openInputStream( Uri.parse(selectedFoodList.get(i).getImage()));
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        selectDayFoodImage.setImageBitmap(bitmap);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });//캘린더 날짜 클릭 이벤트

    } //onClick

}