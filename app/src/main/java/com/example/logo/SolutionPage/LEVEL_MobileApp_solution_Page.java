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

import com.example.logo.ImagePage.LEVEL_MobileApp_ImagePage;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.Level_Activity.LEVEL_MobileApp;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_MobileApp_solution_Page extends AppCompatActivity
{

    ImageView mobileAppImageback,mobileAppImagelogo,mobileAppImagetic;
    TextView mobileAppTextviewname;
    Button mobileAppButtonnext;

    ArrayList<String> mobileAppImageArraySolution1;

    int ImagePosition;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_mobile_app_solution_page);

        mobileAppImageback = findViewById(R.id.MobileAppImageBack1);
        mobileAppImagelogo = findViewById(R.id.MobileAppImageLogo1);
        mobileAppImagetic = findViewById(R.id.MobileAppImageTic1);
        mobileAppTextviewname  = findViewById(R.id.MobileAppTextviewName1);
        mobileAppButtonnext = findViewById(R.id.MobileAppButtonNext1);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ImagePosition = getIntent().getIntExtra("imagePosition" , 0);

        String[] ImageName = null;
        InputStream inputStream = null;

        try
        {
            ImageName = getAssets().list("mobile_app_full_photos");
            mobileAppImageArraySolution1 = new ArrayList<>(Arrays.asList(ImageName));

            inputStream = getAssets().open("mobile_app_full_photos/" + mobileAppImageArraySolution1.get(ImagePosition));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            mobileAppImagelogo.setImageDrawable(drawable);

            int b = mobileAppImageArraySolution1.get(ImagePosition).indexOf(".");
            mobileAppTextviewname.setText(mobileAppImageArraySolution1.get(ImagePosition).substring(0 , b));

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        mobileAppImageback.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_MobileApp_solution_Page.this , LEVEL_MobileApp.class);
            startActivity(Iback);
            finish();

        });

        mobileAppButtonnext.setOnClickListener(view -> {

            String status = sharedPreferences.getString("imageLevelMobileApp" + (ImagePosition + 1) , "pending");

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_MobileApp_solution_Page.this , LEVEL_MobileApp_solution_Page.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , mobileAppImageArraySolution1.get(ImagePosition + 1));
                startActivity(Inext);
                finish();

            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_MobileApp_solution_Page.this , LEVEL_MobileApp_ImagePage.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);
                Inext.putExtra("Photos" , mobileAppImageArraySolution1.get(ImagePosition + 1));
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
        Iback = new Intent(LEVEL_MobileApp_solution_Page.this , LEVEL_MobileApp.class);
        startActivity(Iback);
        finish();

    }
}