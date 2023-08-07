package com.example.logo.Level_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.ImagePage.LEVEL_1_ImagePage;
import com.example.logo.LevelPage_Activity;
import com.example.logo.SolutionPage.LEVEL_1_Solution_Page;
import com.example.logo.GridView_Adapter.Level_1_GridView_Adapter;
import com.example.logo.RecyclerView_Activity.Level_1_RecyclerView_Activity;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_1 extends AppCompatActivity
{

    ImageView imageTitle , imageBack;
    TextView textLevel;
    GridView gridview;
    ArrayList<String> imageArrayList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1);

        imageTitle = findViewById(R.id.ImageTitle);
        imageBack = findViewById(R.id.ImageBack);
        textLevel = findViewById(R.id.TextLevel);
        gridview = findViewById(R.id.Gridview);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);   // ----->  Data Store Karava mate
        editor = sharedPreferences.edit();          // ----->  Data Store Karava mate

        imageBack.setOnClickListener(view -> {

            Intent Iback ;
            Iback = new Intent(LEVEL_1.this , Level_1_RecyclerView_Activity.class);
            startActivity(Iback);
            finish();

        });


////////////---->>>   Get the photos from Assets --->>    its in config Activity     ////////////////////////////////////////////////


        String[] imageName = null;
        try
        {
            imageName = getAssets().list(config.imagePosition[0]);
            imageArrayList = new ArrayList<>(Arrays.asList(imageName));

            Level_1_GridView_Adapter level_1_gridView_adapter = new Level_1_GridView_Adapter(this , imageArrayList);
            gridview.setAdapter(level_1_gridView_adapter);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


////////---->>  Click on GridView and get Any company logo ---> if clicked photo is get ("done") tickmark then go in next HalfImage


        gridview.setOnItemClickListener((adapterView, view, i, l) -> {

            String ststus = sharedPreferences.getString("imageLevel1" + i , "pending");
            if (ststus.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_1.this , LEVEL_1_Solution_Page.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , imageArrayList.get(i));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_1.this , LEVEL_1_ImagePage.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , imageArrayList.get(i));
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
        Iback = new Intent(LEVEL_1.this , Level_1_RecyclerView_Activity.class);
        startActivity(Iback);
        finish();

    }
}