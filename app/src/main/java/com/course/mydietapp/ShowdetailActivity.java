package com.course.mydietapp;

import android.content.Intent;
import android.media.ImageWriter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShowdetailActivity extends AppCompatActivity {
    public FoodDao cFoodDao;
    private AnalysisDao mAnalysisDao;
    public ListView list;
    private ImageView foodImage;
    private String postID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);

        foodImage = (ImageView)findViewById(R.id.foodImage);
        Intent intent=getIntent();
        postID =intent.getStringExtra("postID");

        FoodDatabase database = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "MyDietApp")
                .fallbackToDestructiveMigration() //스키마(Database) 변경 가능
                .allowMainThreadQueries()         //main Thread에서 DD에 입출력 가능
                .build();
        cFoodDao = database.foodDao(); //인터페이스 객체 할당
        Food selectedIDfoodList = cFoodDao.loadAllFoodOnID(postID);//해당 postID의 Food data 가져옴

        AnalysisDatabase database2= Room.databaseBuilder(getApplicationContext(), AnalysisDatabase.class, "a")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build(); // 식사 분석 데이터 가져옴.
        mAnalysisDao=database2.analysisDao();

        List<Analysis> analysisList=mAnalysisDao.getAnalysisAll();


        list=findViewById(R.id.list);

        List<String> data=new ArrayList<>();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        Uri uri = Uri.parse(selectedIDfoodList.getImage());
        Glide.with((getApplicationContext())).load(uri).centerCrop().placeholder(R.mipmap.ic_launcher).into((foodImage));
        data.add(selectedIDfoodList.getTime()+"\n"+selectedIDfoodList.getName()+"   "+selectedIDfoodList.getAmount()+"\n"+selectedIDfoodList.getReview()+"\n"+selectedIDfoodList.getPlace());
        adapter.notifyDataSetChanged();

    }

}
