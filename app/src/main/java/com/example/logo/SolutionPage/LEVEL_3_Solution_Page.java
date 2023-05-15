package com.example.logo.SolutionPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logo.ImagePage.LEVEL_3_imagePage;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.Level_Activity.LEVEL_3;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_3_Solution_Page extends AppCompatActivity
{
    ImageView imageBack3 , imageLogo3 , imageTic3;
    TextView textViewName3;
    Button buttonNext3;

    ArrayList<String> ImageArraySolution3;

    int ImagePosition;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3_solution_page);

        imageBack3 = findViewById(R.id.Level3_SolutionPage_ImageBack3);
        imageLogo3 = findViewById(R.id.Level3_SolutionPage_ImageLogo3);
        imageTic3 = findViewById(R.id.Level3_SolutionPage_ImageTic3);
        textViewName3 = findViewById(R.id.Level3_SolutionPage_TextviewName3);
        buttonNext3 = findViewById(R.id.Level3_SolutionPage_ButtonNext3);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ImagePosition = getIntent().getIntExtra("imagePosition" , 0);

        String[] ImageName = null;
        InputStream inputStream = null;

        try
        {
            ImageName = getAssets().list("full_photos_3");
            ImageArraySolution3 = new ArrayList<>(Arrays.asList(ImageName));

            inputStream = getAssets().open("full_photos_3/" + ImageArraySolution3.get(ImagePosition));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            imageLogo3.setImageDrawable(drawable);

            int b = ImageArraySolution3.get(ImagePosition).indexOf(".");
            textViewName3.setText(ImageArraySolution3.get(ImagePosition).substring(0 , b));

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        imageBack3.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_3_Solution_Page.this , LEVEL_3.class);
            startActivity(Iback);
            finish();

        });

        buttonNext3.setOnClickListener(view -> {

            String status = sharedPreferences.getString("imageLevel3" + (ImagePosition + 1) , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext ;
                Inext = new Intent(LEVEL_3_Solution_Page.this , LEVEL_3_Solution_Page.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , ImageArraySolution3.get(ImagePosition + 1));
                startActivity(Inext);
                finish();
            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_3_Solution_Page.this , LEVEL_3_imagePage.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , ImageArraySolution3.get(ImagePosition + 1));
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
        Iback = new Intent(LEVEL_3_Solution_Page.this , LEVEL_3.class);
        startActivity(Iback);
        finish();

    }
}