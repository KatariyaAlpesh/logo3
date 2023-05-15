package com.example.logo.RecyclerView_Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.logo.LevelPage_Activity;
import com.example.logo.R;
import com.example.logo.Recycler_Adapter.Level_Recycler_Adapter;

public class Level_1_RecyclerView_Activity extends AppCompatActivity
{

    ImageView levelGrid_ImageBack;
    RecyclerView levelGrid_RecyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_recycler_view);

        levelGrid_ImageBack = findViewById(R.id.LevelGrid_ImageBack);
        levelGrid_RecyclerView = findViewById(R.id.LevelGrid_RecyclerView);

        levelGrid_ImageBack.setOnClickListener(view -> {

            Intent Iback ;
            Iback = new Intent(Level_1_RecyclerView_Activity.this , LevelPage_Activity.class);
            startActivity(Iback);
            finish();
        });

        Level_Recycler_Adapter level_recycler_adapter = new Level_Recycler_Adapter(Level_1_RecyclerView_Activity.this);
        levelGrid_RecyclerView.setAdapter(level_recycler_adapter);

        GridLayoutManager GM = new GridLayoutManager(Level_1_RecyclerView_Activity.this , 1 ,
                                                            GridLayoutManager.VERTICAL , false);

        levelGrid_RecyclerView.setLayoutManager(GM);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent Iback;
        Iback = new Intent(Level_1_RecyclerView_Activity.this , LevelPage_Activity.class);
        startActivity(Iback);
        finish();
    }
}