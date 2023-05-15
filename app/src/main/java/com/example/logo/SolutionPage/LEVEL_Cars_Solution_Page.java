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

import com.example.logo.ImagePage.LEVEL_Cars_ImagePage;
import com.example.logo.LevelPage_Activity;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.Level_Activity.LEVEL_Cars;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_Cars_Solution_Page extends AppCompatActivity
{

    ImageView carsImageback,carsImagelogo,carsImagetic;
    TextView carsTextviewname;
    Button carsButtonnext;

    ArrayList<String> carsImageArraySolution1;

    int ImagePosition;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_cars_solution_page);

        carsImageback = findViewById(R.id.CarsImageBack1);
        carsImagelogo = findViewById(R.id.CarsImageLogo1);
        carsImagetic = findViewById(R.id.CarsImageTic1);
        carsTextviewname  = findViewById(R.id.CarsTextviewName1);
        carsButtonnext = findViewById(R.id.CarsButtonNext1);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ImagePosition = getIntent().getIntExtra("imagePosition" , 0);

        String[] ImageName = null;
        InputStream inputStream = null;

        try
        {
            ImageName = getAssets().list("cars_full_photos");
            carsImageArraySolution1 = new ArrayList<>(Arrays.asList(ImageName));

            inputStream = getAssets().open("cars_full_photos/" + carsImageArraySolution1.get(ImagePosition));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            carsImagelogo.setImageDrawable(drawable);

            int b = carsImageArraySolution1.get(ImagePosition).indexOf(".");
            carsTextviewname.setText(carsImageArraySolution1.get(ImagePosition).substring(0 , b));

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        carsImageback.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_Cars_Solution_Page.this , LEVEL_Cars.class);
            startActivity(Iback);
            finish();

        });

        carsButtonnext.setOnClickListener(view -> {

            String status = sharedPreferences.getString("imageLevelCars" + (ImagePosition + 1) , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Cars_Solution_Page.this , LEVEL_Cars_Solution_Page.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , carsImageArraySolution1.get(ImagePosition + 1));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Cars_Solution_Page.this , LEVEL_Cars_ImagePage.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , carsImageArraySolution1.get(ImagePosition + 1));
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
        Iback = new Intent(LEVEL_Cars_Solution_Page.this , LEVEL_Cars.class);
        startActivity(Iback);
        finish();

    }
}