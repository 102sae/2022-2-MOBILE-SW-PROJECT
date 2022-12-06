package com.course.mydietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class ShowdetailActivity extends AppCompatActivity {
    public FoodDao cFoodDao;
    private AnalysisDao mAnalysisDao;
    public TextView detaildate;
    public ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);

        Intent intent=getIntent();
        String day=intent.getStringExtra("shotday");

        FoodDatabase database = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "MyDietApp")
                .fallbackToDestructiveMigration() //스키마(Database) 변경 가능
                .allowMainThreadQueries()         //main Thread에서 DD에 입출력 가능
                .build();
        cFoodDao = database.foodDao(); //인터페이스 객체 할당
        List<Food> foodList = cFoodDao.getFoodAll();

        AnalysisDatabase database2= Room.databaseBuilder(getApplicationContext(), AnalysisDatabase.class, "a")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        mAnalysisDao=database2.analysisDao();
        List<Analysis> analysisList=mAnalysisDao.getAnalysisAll();

        detaildate=findViewById(R.id.textViewdetaildate);
        list=findViewById(R.id.list);

        List<String> data=new ArrayList<>();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        for (int i = 0; i < foodList.size(); i++) {
            if (day.equals(foodList.get(i).getDate()))
            detaildate.setText(foodList.get(i).getDate());

            data.add(foodList.get(i).getTime()+"\n"+foodList.get(i).getName()+"   "+foodList.get(i).getAmount()+"\n"+foodList.get(i).getReview()+"\n"+foodList.get(i).getPlace());
            adapter.notifyDataSetChanged();
        }

    }

}
