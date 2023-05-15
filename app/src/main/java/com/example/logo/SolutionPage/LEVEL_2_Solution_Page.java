package com.example.logo.SolutionPage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.ImagePage.LEVEL_2_imagePage;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.Level_Activity.LEVEL_2;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_2_Solution_Page extends AppCompatActivity
{
    ImageView imageBack2 , imageLogo2 , imageTic2;
    TextView textViewName2;
    Button buttonNext2;

    ArrayList<String> ImageArraySolution2;

    int ImagePosition;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_2_solution_page);

        imageBack2 = findViewById(R.id.Level2_SolutionPage_ImageBack2);
        imageLogo2 = findViewById(R.id.Level2_SolutionPage_ImageLogo2);
        imageTic2 = findViewById(R.id.Level2_SolutionPage_ImageTic2);
        textViewName2 = findViewById(R.id.Level2_SolutionPage_TextviewName2);
        buttonNext2 = findViewById(R.id.Level2_SolutionPage_ButtonNext2);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ImagePosition = getIntent().getIntExtra("imagePosition" , 0);

        String[] ImageName = null;
        InputStream inputStream = null;

        try
        {
            ImageName = getAssets().list("full_photos_2");
            ImageArraySolution2 = new ArrayList<>(Arrays.asList(ImageName));

            inputStream = getAssets().open("full_photos_2/" + ImageArraySolution2.get(ImagePosition));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            imageLogo2.setImageDrawable(drawable);

            int b = ImageArraySolution2.get(ImagePosition).indexOf(".");
            textViewName2.setText(ImageArraySolution2.get(ImagePosition).substring(0 , b));

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        imageBack2.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_2_Solution_Page.this , LEVEL_2.class);
            startActivity(Iback);
            finish();

        });

        buttonNext2.setOnClickListener(view -> {

            String status = sharedPreferences.getString("imageLevel2" + (ImagePosition + 1) , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext ;
                Inext = new Intent(LEVEL_2_Solution_Page.this , LEVEL_2_Solution_Page.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , ImageArraySolution2.get(ImagePosition + 1));
                startActivity(Inext);
                finish();
            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_2_Solution_Page.this , LEVEL_2_imagePage.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , ImageArraySolution2.get(ImagePosition + 1));
                startActivity(Inext);
                finish();
            }

        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent Iback;
        Iback = new Intent(LEVEL_2_Solution_Page.this , LEVEL_2.class);
        startActivity(Iback);
        finish();

    }
}