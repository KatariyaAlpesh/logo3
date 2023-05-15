package com.example.logo.Level_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.LevelPage_Activity;
import com.example.logo.SolutionPage.LEVEL_1_Solution_Page;
import com.example.logo.RecyclerView_Activity.Level_Extra_RecyclerView_Activity;
import com.example.logo.GridView_Adapter.Level_MobileApp_Gridview_Adapter;
import com.example.logo.ImagePage.LEVEL_MobileApp_ImagePage;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_MobileApp extends AppCompatActivity
{
    ImageView mobileAppImageTitle , mobileAppImageBack;
    TextView mobileAppTextLevel;
    GridView mobileAppGridview;
    ArrayList<String> mobileAppImageArrayList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_mobile_app);

        mobileAppImageTitle = findViewById(R.id.MobileAppImageTitle);
        mobileAppImageBack = findViewById(R.id.MobileAppImageBack);
        mobileAppTextLevel = findViewById(R.id.MobileAppTextLevel);
        mobileAppGridview = findViewById(R.id.MobileAppGridview);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);   // ----->  Data Store Karava mate
        editor = sharedPreferences.edit();

        mobileAppImageBack.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_MobileApp.this , Level_Extra_RecyclerView_Activity.class);
            startActivity(Iback);
            finish();

        });


        String[] imageName = null;

        try
        {
            imageName = getAssets().list(config.imagePositionMobileApp[0]);
            mobileAppImageArrayList = new ArrayList<>(Arrays.asList(imageName));

            Level_MobileApp_Gridview_Adapter level_mobileApp_gridview_adapter = new Level_MobileApp_Gridview_Adapter
                                                                    (this , mobileAppImageArrayList);
            mobileAppGridview.setAdapter(level_mobileApp_gridview_adapter);


        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        mobileAppGridview.setOnItemClickListener((adapterView, view, i, l) -> {

            String status = sharedPreferences.getString("imageLevelMobileApp" + i , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_MobileApp.this , LEVEL_1_Solution_Page.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , mobileAppImageArrayList.get(i));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_MobileApp.this , LEVEL_MobileApp_ImagePage.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , mobileAppImageArrayList.get(i));
                startActivity(Inext);
                finish();

            }

        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent Iback ;
        Iback = new Intent(LEVEL_MobileApp.this , LevelPage_Activity.class);
        startActivity(Iback);
        finish();

    }
}