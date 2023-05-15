package com.example.logo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.RecyclerView_Activity.Level_1_RecyclerView_Activity;
import com.example.logo.RecyclerView_Activity.Level_Extra_RecyclerView_Activity;

public class LevelPage_Activity extends AppCompatActivity
{

    ImageView levelPage_ImageBack;
    TextView levelPage_TextLevel , levelPage_TextExtra , levelPage_TextExpert;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_page);

        levelPage_ImageBack = findViewById(R.id.LevelPageImageBack);
        levelPage_TextLevel = findViewById(R.id.LevelPage_TextLevel);
        levelPage_TextExtra = findViewById(R.id.LevelPage_LevelExtra);
        levelPage_TextExpert = findViewById(R.id.LevelPage_LevelExpert);

        levelPage_ImageBack.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LevelPage_Activity.this , Play_Challenge_Page.class);
            startActivity(Iback);
            finish();

        });

        levelPage_TextLevel.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(LevelPage_Activity.this , Level_1_RecyclerView_Activity.class);
            startActivity(Inext);
            finish();

        });

        levelPage_TextExtra.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(LevelPage_Activity.this , Level_Extra_RecyclerView_Activity.class);
            startActivity(Inext);
            finish();

        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent Iback ;
        Iback = new Intent(LevelPage_Activity.this , Play_Challenge_Page.class);
        startActivity(Iback);
        finish();
    }
}