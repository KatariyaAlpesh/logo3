package com.example.logo.ImagePage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.logo.Level_Activity.LEVEL_Cars;
import com.example.logo.R;
import com.example.logo.SolutionPage.LEVEL_Cars_Solution_Page;
import com.example.logo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LEVEL_Cars_ImagePage extends AppCompatActivity implements View.OnClickListener {
    ImageView carsImageBack2 , carsImagelogo2;
    LinearLayout carsLinearLayout2;
    Button ansButtonCars[] , buttonCars[];
    ArrayList<String> allCharacter = new ArrayList<>();

    String[] Name = {"abarth" , "audio" , "bentley" , "bmw" , "cadillac" , "chery" , "chevrolet" , "chrysler" , "dodge" , "ferrari" ,
          "ford" , "geely" , "great_wall" , "hyundai" , "jaguar" , "kia" , "mahindra" , "mahindra_univeils" , "mazda" , "mercedes_banz" ,
            "nissan" , "opel" , "porsche" , "skoda" , "suzuki" , "tata_motors" , "toyota" , "volkswagen" , "volvo" , "yamaha"};

    int t = 0;
    int imagePosition2;
    String Photos;


    ArrayList positionList = new ArrayList();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_care_image_page);

        carsImageBack2 = findViewById(R.id.CarsImageBack2);
        carsImagelogo2 = findViewById(R.id.CarsImageLogo2);
        carsLinearLayout2 = findViewById(R.id.CarsLinearLayout2);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        imagePosition2 = getIntent().getIntExtra("imagePosition", 0);
        Photos = getIntent().getStringExtra("Photos");


        char[] NameArray = Name[imagePosition2].toCharArray();
        ansButtonCars = new Button[NameArray.length];

        for (int i = 0 ; i < Name[imagePosition2].length() ; i++)
        {
            allCharacter.add("" + NameArray[i]);
            ansButtonCars[i] = new Button(this);
            ansButtonCars[i].setId(i);
            ansButtonCars[i].setBackgroundResource(R.drawable.bluebutton_background);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 85, 1);
            params.setMargins(5, 0, 5, 0);

            ansButtonCars[i].setLayoutParams(params);
            ansButtonCars[i].setOnClickListener(this);
            carsLinearLayout2.addView(ansButtonCars[i]);
        }

        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] alphabetArray = alphabet.toCharArray();


        for (int i = 0 ; i < 14 - Name[imagePosition2].length() ; i++)
        {
            Random random = new Random();

            int randomInt = random.nextInt(alphabetArray.length);
            System.out.println(alphabetArray[randomInt]);

            allCharacter.add("" + alphabetArray[randomInt]);
            System.out.println(allCharacter);
        }

        Collections.shuffle(allCharacter);
        System.out.println(allCharacter);


        buttonCars = new Button[14];

        for (int i = 0; i < 14; i++)
        {
            int id = getResources().getIdentifier("b" + i, "id", getPackageName());
            buttonCars[i] = findViewById(id);
            buttonCars[i].setText(allCharacter.get(i));
            buttonCars[i].setOnClickListener(this);
        }

        String Folder;
        Folder = "" + config.imagePositionCars[0];

//        String ststus = sharedPreferences.getString("imageLevel1" + imagePosition2 , "pending");
//        if (ststus.equalsIgnoreCase("done"))
//        {
//            Folder = config.imagePosition[0];
//        }
//        else
//        {
//            Folder = config.imagePosition[1];
//        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        InputStream inputStream = null;

        try {
            inputStream = getAssets().open(Folder + "/" + Photos);
            System.out.println(Photos);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Drawable drawable = Drawable.createFromStream(inputStream, null);
        carsImagelogo2.setImageDrawable(drawable);


        carsImageBack2.setOnClickListener(view ->
        {

            Intent Iback;
            Iback = new Intent(LEVEL_Cars_ImagePage.this , LEVEL_Cars.class);
            startActivity(Iback);
            finish();

        });

    }

    @Override
    public void onClick(View view)
    {
        for (int i = 0 ; i < 14 ; i++ )    //---->>   16 Button mathi
        {
            if (view.getId() == buttonCars[i].getId())    //---->>   Jeni id Per Clicked thay
            {
                if (t < Name[imagePosition2].length())     //---->> ansButton ma check thay
                {
                    for (int k = 0; k < Name[imagePosition2].length(); k++)    //---->> ansButton ma Value Print thay
                    {
                        if (ansButtonCars[k].getText().toString().isEmpty())     //---->> Jo ansButton Empty hoy to
                        {
                            Map m = new HashMap();    //---->>   HashMap Acsept Key and Value from Click.

                            ansButtonCars[k].setBackgroundResource(R.drawable.bluebutton_background);     //--->>   Background Color change karva mate
                            ansButtonCars[k].setTextColor(getColor(R.color.white));
                            ansButtonCars[k].setText(buttonCars[i].getText().toString());
                            //button[i].setBackgroundColor(getColor(R.color.button));     //--->>   Background Color change karva mate
                            buttonCars[i].setText("");

                            m.put(k, i);    //----->>  ansButton K means Key    &    button I means Value
                            System.out.println(m);
                            positionList.add(m);
                            System.out.println(positionList);

                            t++;
                            break;
                        }
                    }
                    //---->>    StringBuilder Silected Value ne One by One Print Kare che
                    // ---->>    for Ex =  click "A" next "H" next "F" that means    builder = AHF
                    StringBuilder builder = new StringBuilder();
                    for (int k = 0; k < Name[imagePosition2].length(); k++)
                    {
                        builder.append(ansButtonCars[k].getText());
//                            System.out.println(" \" This is\"");
//                            System.out.println(builder);
                        String str = builder.toString();
                        System.out.println(str);

                        if (str.equalsIgnoreCase(Name[imagePosition2]))
                        {

                            editor.putString("imageLevelCars" + imagePosition2 , "done");
                            editor.commit();

                            Intent Inext;
                            Inext = new Intent(LEVEL_Cars_ImagePage.this , LEVEL_Cars_Solution_Page.class);
                            Inext.putExtra("imagePosition" , imagePosition2);
                            startActivity(Inext);
                            finish();

                        }
                    }
                }
            }
        }

        for (int i = 0 ; i < Name[imagePosition2].length() ; i++)
        {
            if (view.getId() == ansButtonCars[i].getId() && !ansButtonCars[i].getText().toString().isEmpty())
            {
                System.out.println(i);
                // System.out.println(positionList.get(i));
                for (int k = 0 ; k < positionList.size() ; k++)
                {
                    Map m1 = (Map) positionList.get(k);
                    if (m1.containsKey(i))
                    {
                        Map m = m1;
                        int pos = (int) m.get(i);
                        System.out.println(pos);

                        buttonCars[pos].setText(ansButtonCars[i].getText());
                        //     button[pos].setBackgroundColor(getColor(R.color.button1));  //--->>   Background Color change karva mate
                        ansButtonCars[i].setText("");
                        ansButtonCars[i].setBackgroundColor(getColor(R.color.button));   //--->>   Background Color change karva mate
                        t--;
                        positionList.remove(m1);
                    }
                }
                System.out.println(positionList);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent Iback;
        Iback = new Intent(LEVEL_Cars_ImagePage.this , LEVEL_Cars.class);
        startActivity(Iback);
        finish();

    }
}