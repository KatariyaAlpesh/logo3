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
import com.example.logo.SolutionPage.LEVEL_3_Solution_Page;
import com.example.logo.ImagePage.LEVEL_3_imagePage;
import com.example.logo.RecyclerView_Activity.Level_1_RecyclerView_Activity;
import com.example.logo.GridView_Adapter.Level_3_GridView_Adapter;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_3 extends AppCompatActivity
{

    ImageView level3_imageBack , level3_imageTitle;
    TextView level3_levelText;
    GridView level3_GridView;

    ArrayList<String> imageArrayList3 = new ArrayList<>();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3);

        level3_imageBack = findViewById(R.id.Level_3_ImageBack);
        level3_imageTitle = findViewById(R.id.Level_3_ImageTitle);
        level3_levelText = findViewById(R.id.Level_3_TextLevel);
        level3_GridView = findViewById(R.id.Level_3_Gridview);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        level3_imageBack.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_3.this , Level_1_RecyclerView_Activity.class);
            startActivity(Iback);
            finish();

        });

        String[] imageName = null;

        try
        {
            imageName = getAssets().list(config.imagePosition3[0]);
            imageArrayList3 = new ArrayList<>(Arrays.asList(imageName));

            Level_3_GridView_Adapter level_3_gridView_adapter = new Level_3_GridView_Adapter(LEVEL_3.this , imageArrayList3);
            level3_GridView.setAdapter(level_3_gridView_adapter);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        level3_GridView.setOnItemClickListener((adapterView, view, i, l) -> {

            String ststus = sharedPreferences.getString("imageLevel3" + i , "pending");
            if (ststus.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_3.this , LEVEL_3_Solution_Page.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , imageArrayList3.get(i));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_3.this , LEVEL_3_imagePage.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , imageArrayList3.get(i));
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
        Iback = new Intent(LEVEL_3.this , Level_1_RecyclerView_Activity.class);
        startActivity(Iback);
        finish();

    }
}