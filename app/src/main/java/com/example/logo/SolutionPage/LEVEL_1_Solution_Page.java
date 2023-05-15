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

import com.example.logo.ImagePage.LEVEL_1_ImagePage;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LEVEL_1_Solution_Page extends AppCompatActivity
{

    ImageView imageback,imagelogo,imagetic;
    TextView textviewname;
    Button buttonnext;

    ArrayList<String> ImageArraySolution1;

    int ImagePosition;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_solution_page);

        imageback = findViewById(R.id.ImageBack1);
        imagelogo = findViewById(R.id.ImageLogo);
        imagetic = findViewById(R.id.ImageTic);
        textviewname = findViewById(R.id.TextviewName);
        buttonnext = findViewById(R.id.ButtonNext);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        ImagePosition = getIntent().getIntExtra("imagePosition" , 0);

        System.out.println("==>="+ImagePosition);

        String[] ImageName = null;
        InputStream inputStream = null;

        try {
            ImageName = getAssets().list("full_photos_1");
            ImageArraySolution1 = new ArrayList<>(Arrays.asList(ImageName));

            inputStream = getAssets().open("full_photos_1/" + ImageArraySolution1.get(ImagePosition));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            imagelogo.setImageDrawable(drawable);

            int b = ImageArraySolution1.get(ImagePosition).indexOf(".");
            textviewname.setText(ImageArraySolution1.get(ImagePosition).substring(0 , b));

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        imageback.setOnClickListener(view -> {

            Intent Iback;
            Iback = new Intent(LEVEL_1_Solution_Page.this , LEVEL_1.class);
            startActivity(Iback);
            finish();

        });


        buttonnext.setOnClickListener(view -> {

            String status = sharedPreferences.getString("imageLevel1" + (ImagePosition + 1) , "pending" );

            if (status.equalsIgnoreCase("done"))
            {
                Intent Inext;
                Inext = new Intent(LEVEL_1_Solution_Page.this , LEVEL_1_Solution_Page.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);

//--->   enter Full name and get Solved image clicked on next and get next new Half photo //////////////////////////////////////

                Inext.putExtra("Photos" , ImageArraySolution1.get(ImagePosition + 1) );
                startActivity(Inext);

                finish();
            }
            else
            {
                Intent Inext;
                Inext = new Intent(LEVEL_1_Solution_Page.this , LEVEL_1_ImagePage.class);
                Inext.putExtra("imagePosition" , ImagePosition + 1);

//--->   enter Full name and get Solved image clicked on next and get next new Half photo //////////////////////////////////////

                Inext.putExtra("Photos" , ImageArraySolution1.get(ImagePosition + 1) );

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
        Iback = new Intent(LEVEL_1_Solution_Page.this , LEVEL_1.class);
        startActivity(Iback);
        finish();

    }
}