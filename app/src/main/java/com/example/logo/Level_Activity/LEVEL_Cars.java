package com.example.logo.Level_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.GridView_Adapter.Level_Cars_Gridview_Adapter;
import com.example.logo.ImagePage.LEVEL_Cars_ImagePage;
import com.example.logo.LevelPage_Activity;
import com.example.logo.R;
import com.example.logo.RecyclerView_Activity.Level_Extra_RecyclerView_Activity;
import com.example.logo.SolutionPage.LEVEL_Cars_Solution_Page;
import com.example.logo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_Cars extends AppCompatActivity
{

    ImageView carsImageTitle , carsImageBack;
    TextView carsTextLevel;
    GridView carsGridview;
    ArrayList<String> carsImageArrayList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_cars);

        carsImageTitle = findViewById(R.id.CarsImageTitle);
        carsImageBack = findViewById(R.id.CarsImageBack);
        carsTextLevel = findViewById(R.id.CarsTextLevel);
        carsGridview = findViewById(R.id.CarsGridview);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);   // ----->  Data Store Karava mate
        editor = sharedPreferences.edit();

        carsImageBack.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_Cars.this , Level_Extra_RecyclerView_Activity.class);
            startActivity(Iback);
            finish();

        });


        String[] imageName = null;

        try
        {
            imageName = getAssets().list(config.imagePositionCars[0]);
            carsImageArrayList = new ArrayList<>(Arrays.asList(imageName));

            Level_Cars_Gridview_Adapter level_cars_gridview_adapter = new Level_Cars_Gridview_Adapter
                    (this , carsImageArrayList);
            carsGridview.setAdapter(level_cars_gridview_adapter);


        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        carsGridview.setOnItemClickListener((adapterView, view, i, l) -> {

            String status = sharedPreferences.getString("imageLevelCars" + i , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Cars.this , LEVEL_Cars_Solution_Page.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , carsImageArrayList.get(i));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Cars.this , LEVEL_Cars_ImagePage.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , carsImageArrayList.get(i));
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
        Iback = new Intent(LEVEL_Cars.this , LevelPage_Activity.class);
        startActivity(Iback);
        finish();

    }
}