package com.course.mydietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class ShowdetailActivity extends AppCompatActivity {
    public FoodDao cFoodDao;
    private AnalysisDao mAnalysisDao;
    public TextView detaildate;
    public TextView detailtime;
    public TextView detailname;
    public TextView detailrat;
    public TextView detailplace;
    public ImageView imageView;
    public Button button;
    public TextView detailkcal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);

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
        detailtime=findViewById(R.id.textViewdetailtime);
        detailname=findViewById(R.id.textViewdetailname);
        detailrat=findViewById(R.id.textViewdetailrat);
        detailplace=findViewById(R.id.textViewdetailplace);
        imageView=findViewById(R.id.imageView2);
        button=findViewById(R.id.button);
        detailkcal=findViewById(R.id.textViewdetailkcal);

        for (int i = 0; i < foodList.size(); i++) {
            detaildate.setText(foodList.get(i).getDate());
            //imageView.setImageURI();
            detailtime.setText("식사 시간 "+foodList.get(i).getTime());
            detailname.setText("식사 "+foodList.get(i).getName()+" "+foodList.get(i).getAmount());
            detailrat.setText("식사평 "+String.valueOf(foodList.get(i).getReview()));
            detailplace.setText("식사 위치 "+foodList.get(i).getPlace());
            for (int j = 0; j < analysisList.size(); j++) {
                if ((analysisList.get(j).getKname()).equals(foodList.get(i).getName()))
                    detailkcal.setText("칼로리 "+analysisList.get(j).getKcal()+"kcal");
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FoodanalysisActivity.class);
                startActivity(intent);
            }
        });

    }

}
