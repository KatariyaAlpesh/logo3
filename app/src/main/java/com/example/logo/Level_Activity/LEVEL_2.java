
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
import com.example.logo.SolutionPage.LEVEL_2_Solution_Page;
import com.example.logo.ImagePage.LEVEL_2_imagePage;
import com.example.logo.RecyclerView_Activity.Level_1_RecyclerView_Activity;
import com.example.logo.GridView_Adapter.Level_2_GridView_Adapter;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_2 extends AppCompatActivity
{

    ImageView level2_imageBack , level2_imageTitle;
    TextView level2_levelText;
    GridView level2_GridView;

    ArrayList<String> imageArrayList2 = new ArrayList<>();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_2);

        level2_imageBack = findViewById(R.id.Level_2_ImageBack);
        level2_imageTitle = findViewById(R.id.Level_2_ImageTitle);
        level2_levelText = findViewById(R.id.Level_2_TextLevel);
        level2_GridView = findViewById(R.id.Level_2_Gridview);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        level2_imageBack.setOnClickListener(view -> {

            Intent Iback ;
            Iback = new Intent(LEVEL_2.this , Level_1_RecyclerView_Activity.class);
            startActivity(Iback);
            finish();

        });

        String[] imageName = null;

        try
        {
            imageName = getAssets().list(config.imagePosition2[0]);
            imageArrayList2 = new ArrayList<>(Arrays.asList(imageName));

            Level_2_GridView_Adapter level_2_gridView_adapter = new Level_2_GridView_Adapter(LEVEL_2.this , imageArrayList2);
            level2_GridView.setAdapter(level_2_gridView_adapter);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        level2_GridView.setOnItemClickListener((adapterView, view, i, l) -> {

            String ststus = sharedPreferences.getString("imageLevel2" + i , "pending");
            if (ststus.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_2.this , LEVEL_2_Solution_Page.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , imageArrayList2.get(i));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_2.this , LEVEL_2_imagePage.class);
                Inext.putExtra("imagePosition" , i);
                Inext.putExtra("Photos" , imageArrayList2.get(i));
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
        Iback = new Intent(LEVEL_2.this , LevelPage_Activity.class);
        startActivity(Iback);
        finish();

    }
}