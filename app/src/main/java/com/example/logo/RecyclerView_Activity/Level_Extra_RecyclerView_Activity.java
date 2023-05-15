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
import com.example.logo.Recycler_Adapter.ExtraLevel_Recycler_Adapter;

public class Level_Extra_RecyclerView_Activity extends AppCompatActivity
{

    ImageView levelExtra_ImageBack;
    RecyclerView levelExtra_RecyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_recycler_view);

        levelExtra_ImageBack = findViewById(R.id.LevelExtra_ImageBack);
        levelExtra_RecyclerView = findViewById(R.id.LevelExtra_RecyclerView);

        levelExtra_ImageBack.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(Level_Extra_RecyclerView_Activity.this , LevelPage_Activity.class);
            startActivity(Inext);
            finish();

        });

        ExtraLevel_Recycler_Adapter extraLevel_recycler_adapter = new ExtraLevel_Recycler_Adapter(Level_Extra_RecyclerView_Activity.this);
        levelExtra_RecyclerView.setAdapter(extraLevel_recycler_adapter);


        GridLayoutManager GM = new GridLayoutManager(Level_Extra_RecyclerView_Activity.this , 1 ,
                GridLayoutManager.VERTICAL , false);

        levelExtra_RecyclerView.setLayoutManager(GM);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent Iback;
        Iback = new Intent(Level_Extra_RecyclerView_Activity.this , LevelPage_Activity.class);
        startActivity(Iback);
        finish();
    }
}