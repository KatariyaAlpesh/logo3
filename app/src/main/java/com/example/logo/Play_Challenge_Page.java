package com.example.logo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Play_Challenge_Page extends AppCompatActivity
{

    TextView playButton , challengeButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_challenge_page);

        playButton = findViewById(R.id.PlayText);
        challengeButton = findViewById(R.id.ChallengeText);

        playButton.setOnClickListener(view -> {

            Intent Inext;
            Inext = new Intent(Play_Challenge_Page.this , LevelPage_Activity.class);
            startActivity(Inext);
            finish();

        });
    }


}