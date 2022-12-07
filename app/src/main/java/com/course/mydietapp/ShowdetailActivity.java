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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShowdetailActivity extends AppCompatActivity {
    public FoodDao cFoodDao;
    private AnalysisDao mAnalysisDao;
    private ImageView foodImage;
    private String postID;
    private TextView title;
    private TextView fTime;
    private TextView fName;
    private TextView fAmount;
    private TextView fReview;
    private TextView fPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);
        fTime = (TextView) findViewById(R.id.fTime);
        fName = (TextView) findViewById(R.id.fName);
        fAmount = (TextView) findViewById(R.id.fAmount);
        fReview = (TextView) findViewById(R.id.fReview);
        fPlace = (TextView) findViewById(R.id.fPlace);

        foodImage = (ImageView)findViewById(R.id.foodImage);
        title = (TextView)findViewById(R.id.title);

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

        title.setText(selectedIDfoodList.getDate());
        Uri uri = Uri.parse(selectedIDfoodList.getImage());
        Glide.with((getApplicationContext())).load(uri).centerCrop().placeholder(R.mipmap.ic_launcher).into((foodImage));
        fTime.setText("먹은 시간: "+ selectedIDfoodList.getTime());
        fName.setText("음식 명: "+ selectedIDfoodList.getName());
        fAmount.setText("먹은 양: " + selectedIDfoodList.getAmount());
        fPlace.setText("먹은 장소: " + selectedIDfoodList.getPlace());
        fReview.setText("맛: "+ selectedIDfoodList.getReview()+"점");


    }

}
