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
import com.example.logo.ImagePage.LEVEL_Fashion_ImagePage;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.Level_Activity.LEVEL_Cars;
import com.example.logo.Level_Activity.LEVEL_Fashion;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_Fashion_Solution_Page extends AppCompatActivity
{
    ImageView fashionImageback,fashionImagelogo,fashionImagetic;
    TextView fashionTextviewname;
    Button fashionButtonnext;

    ArrayList<String> fashionImageArraySolution1;

    int ImagePosition;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_fashion_solution_page);

        fashionImageback = findViewById(R.id.FashionImageBack1);
        fashionImagelogo = findViewById(R.id.FashionImageLogo1);
        fashionImagetic = findViewById(R.id.FashionImageTic1);
        fashionTextviewname  = findViewById(R.id.FashionTextviewName1);
        fashionButtonnext = findViewById(R.id.FashionButtonNext1);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ImagePosition = getIntent().getIntExtra("imagePosition" , 0);

        String[] ImageName = null;
        InputStream inputStream = null;

        try
        {
            ImageName = getAssets().list("fashion_full_photos");
            fashionImageArraySolution1 = new ArrayList<>(Arrays.asList(ImageName));

            inputStream = getAssets().open("fashion_full_photos/" + fashionImageArraySolution1.get(ImagePosition));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            fashionImagelogo.setImageDrawable(drawable);

            int b = fashionImageArraySolution1.get(ImagePosition).indexOf(".");
            fashionTextviewname.setText(fashionImageArraySolution1.get(ImagePosition).substring(0 , b));

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        fashionImageback.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_Fashion_Solution_Page.this , LEVEL_Fashion.class);
            startActivity(Iback);
            finish();

        });

        fashionButtonnext.setOnClickListener(view -> {

            String status = sharedPreferences.getString("imageLevelFashion" + (ImagePosition + 1) , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Fashion_Solution_Page.this , LEVEL_Fashion_Solution_Page.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , fashionImageArraySolution1.get(ImagePosition + 1));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_Fashion_Solution_Page.this , LEVEL_Fashion_ImagePage.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , fashionImageArraySolution1.get(ImagePosition + 1));
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
        Iback = new Intent(LEVEL_Fashion_Solution_Page.this , LEVEL_Fashion.class);
        startActivity(Iback);
        finish();

    }
}