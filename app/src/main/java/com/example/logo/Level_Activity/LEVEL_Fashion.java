package com.example.logo.Level_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.GridView_Adapter.Level_Fashion_Gridview_Adapter;
import com.example.logo.ImagePage.LEVEL_Fashion_ImagePage;
import com.example.logo.LevelPage_Activity;
import com.example.logo.R;
import com.example.logo.RecyclerView_Activity.Level_Extra_RecyclerView_Activity;
import com.example.logo.SolutionPage.LEVEL_Fashion_Solution_Page;
import com.example.logo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_Fashion extends AppCompatActivity
{

    ImageView fashionImageTitle , fashionImageBack;
    TextView fashionTextLevel;
    GridView fashionGridview;
    ArrayList<String> fashionImageArrayList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_fashion);

        fashionImageTitle = findViewById(R.id.FashionImageTitle);
        fashionImageBack = findViewById(R.id.FashionImageBack);
        fashionTextLevel = findViewById(R.id.FashionTextLevel);
        fashionGridview = findViewById(R.id.FashionGridview);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);   // ----->  Data Store Karava mate
        editor = sharedPreferences.edit();

        fashionImageBack.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_Fashion.this , Level_Extra_RecyclerView_Activity.class);
            startActivity(Iback);
            finish();

        });

        String[] imageName = null;

        try
        {
            imageName = getAssets().list(config.imagePositionFashion[0]);
            fashionImageArrayList = new ArrayList<>(Arrays.asList(imageName));

            Level_Fashion_Gridview_Adapter level_fashion_gridview_adapter = new Level_Fashion_Gridview_Adapter
                    (this , fashionImageArrayList);
            fashionGridview.setAdapter(level_fashion_gridview_adapter);


        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        fashionGridview.setOnItemClickListener((adapterView, view, i, l) -> {

            String status = sharedPreferences.getString("imageLevelFashion" + i , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Fashion.this , LEVEL_Fashion_Solution_Page.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , fashionImageArrayList.get(i));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Fashion.this , LEVEL_Fashion_ImagePage.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , fashionImageArrayList.get(i));
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
        Iback = new Intent(LEVEL_Fashion.this , Level_Extra_RecyclerView_Activity.class);
        startActivity(Iback);
        finish();

    }
}