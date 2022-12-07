package com.course.mydietapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FoodanalysisActivity extends AppCompatActivity {
    private AnalysisDao mAnalysisDao;
    public FoodDao cFoodDao;
    public TextView kcaldate;
    public TextView kcalname;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodanalysis);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date=new Date();
        String day=formatter.format(date);

        kcaldate=findViewById(R.id.textViewdate);
        kcalname=findViewById(R.id.textViewname);
        list=findViewById(R.id.list);

        FoodDatabase database = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "MyDietApp")
                .fallbackToDestructiveMigration() //스키마(Database) 변경 가능
                .allowMainThreadQueries()         //main Thread에서 DD에 입출력 가능
                .build();
        cFoodDao = database.foodDao();

        AnalysisDatabase database2= Room.databaseBuilder(getApplicationContext(), AnalysisDatabase.class, "a")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        mAnalysisDao=database2.analysisDao();

        Analysis analysis=new Analysis();
        analysis.setId(1);
        analysis.setKname("사과");
        analysis.setKcal("57");
        mAnalysisDao.setInsertAnalysis(analysis);

        Analysis analysis2=new Analysis();
        analysis2.setId(2);
        analysis2.setKname("떡볶이");
        analysis2.setKcal("303");
        mAnalysisDao.setInsertAnalysis(analysis2);

        Analysis analysis3=new Analysis();
        analysis3.setId(3);
        analysis3.setKname("바나나");
        analysis3.setKcal("93");
        mAnalysisDao.setInsertAnalysis(analysis3);

        Analysis analysis4=new Analysis();
        analysis4.setId(4);
        analysis4.setKname("삶은달걀");
        analysis4.setKcal("68");
        mAnalysisDao.setInsertAnalysis(analysis4);

        Analysis analysis5=new Analysis();
        analysis5.setId(5);
        analysis5.setKname("고구마");
        analysis5.setKcal("124");
        mAnalysisDao.setInsertAnalysis(analysis5);

        Analysis analysis6=new Analysis();
        analysis6.setId(6);
        analysis6.setKname("삼겹살");
        analysis6.setKcal("331");
        mAnalysisDao.setInsertAnalysis(analysis6);

        Analysis analysis7=new Analysis();
        analysis7.setId(7);
        analysis7.setKname("김밥");
        analysis7.setKcal("318");
        mAnalysisDao.setInsertAnalysis(analysis7);

        Analysis analysis8=new Analysis();
        analysis8.setId(8);
        analysis8.setKname("김치찌개");
        analysis8.setKcal("128");
        mAnalysisDao.setInsertAnalysis(analysis8);

        Analysis analysis9=new Analysis();
        analysis9.setId(9);
        analysis9.setKname("제육볶음");
        analysis9.setKcal("572");
        mAnalysisDao.setInsertAnalysis(analysis9);

        Analysis analysis10=new Analysis();
        analysis10.setId(10);
        analysis10.setKname("닭꼬치");
        analysis10.setKcal("141");
        mAnalysisDao.setInsertAnalysis(analysis10);

        Analysis analysis11=new Analysis();
        analysis11.setId(11);
        analysis11.setKname("케이크");
        analysis11.setKcal("181");
        mAnalysisDao.setInsertAnalysis(analysis11);

        Analysis analysis12=new Analysis();
        analysis12.setId(12);
        analysis12.setKname("우동");
        analysis12.setKcal("610");
        mAnalysisDao.setInsertAnalysis(analysis12);

        Analysis analysis13=new Analysis();
        analysis13.setId(13);
        analysis13.setKname("라면");
        analysis13.setKcal("460");
        mAnalysisDao.setInsertAnalysis(analysis13);

        Analysis analysis14=new Analysis();
        analysis14.setId(14);
        analysis14.setKname("스파게티");
        analysis14.setKcal("690");
        mAnalysisDao.setInsertAnalysis(analysis14);

        List<Analysis> analysisList=mAnalysisDao.getAnalysisAll();
        List<Food> foodList=cFoodDao.getFoodAll();
        List<String> data=new ArrayList<>();
        int total=0;

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, data);
        list.setAdapter(adapter);

        for(int i=0; i<analysisList.size(); i++) {
            for (int j = 0; j < foodList.size(); j++) {
                if ((analysisList.get(i).getKname()).equals(foodList.get(j).getName())){
                    if (day.equals(foodList.get(j).getDate())) {
                        kcaldate.setText(foodList.get(j).getDate());
                        data.add(analysisList.get(i).getKname() + "   " + analysisList.get(i).getKcal() + "kcal");
                        adapter.notifyDataSetChanged();
                        total += Integer.parseInt(analysisList.get(i).getKcal());
                    }
                }

            }
        }

        kcalname.setText("하루 총 칼로리량 : "+Integer.toString(total)+"kcal");

    }
}
