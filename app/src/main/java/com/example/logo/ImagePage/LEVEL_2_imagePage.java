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

import com.example.logo.SolutionPage.LEVEL_2_Solution_Page;
import com.example.logo.Level_Activity.LEVEL_2;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LEVEL_2_imagePage extends AppCompatActivity implements View.OnClickListener {

    ImageView level2_ImageBack2 , level2_ImageLogo2;
    LinearLayout level2_LinearLayout2;
    Button ansButton2[] , button2[];

    ArrayList<String> allCharacter2 = new ArrayList<>();

    String[] Name = {"addidas" , "american_eagle" , "android" , "bentley" , "blockbuster" ,"bmw" , "campbell's" ,"chrome" , "cisco" ,
            "cocacola" , "corona_xtea" , "disney" , "dolby" , "ebay" , "espn" , "expedia" , "ferrari" , "firefox" , "fox" ,
            "gillette" , "hershey" , "levi's" , "mcdonald's" , "microsoft" , "motorola" , "msn" , "myspace" , "nissan" , "nivea" ,
            "olay" , "pantene" , "porsche" , "pringles" , "reebok" , "sega" , "shell" , "skype" , "sony" , "tommy_hilfiger" , "twitter" };

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
        setContentView(R.layout.level_2_image_page);

        level2_ImageBack2 = findViewById(R.id.Level2_ImageBack2);
        level2_ImageLogo2 = findViewById(R.id.Level2_ImageLogo2);
        level2_LinearLayout2 = findViewById(R.id.Level2_LinearLayout2);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        imagePosition2 = getIntent().getIntExtra("imagePosition" , 0);
        Photos = getIntent().getStringExtra("Photos" );

        char[] NameArray = Name[imagePosition2].toCharArray();
        ansButton2 = new Button[NameArray.length];


        for (int i = 0; i < Name[imagePosition2].length(); i++)
        {
            allCharacter2.add("" + NameArray[i]);
            ansButton2[i] = new Button(this);
            ansButton2[i].setId(i);
            ansButton2[i].setBackgroundResource(R.drawable.bluebutton_background);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 85, 1);
            params.setMargins(5, 0, 5, 0);

            ansButton2[i].setLayoutParams(params);
            ansButton2[i].setOnClickListener(this);
            level2_LinearLayout2.addView(ansButton2[i]);
        }

        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] alphabetArray = alphabet.toCharArray();


        for (int i = 0; i < 14 - Name[imagePosition2].length(); i++)
        {
            Random random = new Random();

            int randomInt = random.nextInt(alphabetArray.length);
            System.out.println(alphabetArray[randomInt]);

            allCharacter2.add("" + alphabetArray[randomInt]);
            System.out.println(allCharacter2);
        }

        Collections.shuffle(allCharacter2);
        System.out.println(allCharacter2);


        button2 = new Button[14];

        for (int i = 0; i < 14; i++)
        {
            int id = getResources().getIdentifier("b" + i, "id", getPackageName());
            button2[i] = findViewById(id);
            button2[i].setText(allCharacter2.get(i));
            button2[i].setOnClickListener(this);
        }

        String Folder;
        Folder = "" + config.imagePosition2[0];


        InputStream inputStream = null;

        try {
            inputStream = getAssets().open(Folder + "/" + Photos);
            System.out.println(Photos);

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Drawable drawable = Drawable.createFromStream(inputStream, null);
        level2_ImageLogo2.setImageDrawable(drawable);


        level2_ImageBack2.setOnClickListener(view ->
        {

            Intent Iback;
            Iback = new Intent(LEVEL_2_imagePage.this , LEVEL_2.class);
            startActivity(Iback);
            finish();

        });


    }

    @Override
    public void onClick(View view)
    {
        for (int i = 0 ; i < 14 ; i++ )    //---->>   16 Button mathi
        {
            if (view.getId() == button2[i].getId())    //---->>   Jeni id Per Clicked thay
            {
                if (t < Name[imagePosition2].length())     //---->> ansButton ma check thay
                {
                    for (int k = 0; k < Name[imagePosition2].length(); k++)    //---->> ansButton ma Value Print thay
                    {
                        if (ansButton2[k].getText().toString().isEmpty())     //---->> Jo ansButton Empty hoy to
                        {
                            Map m = new HashMap();    //---->>   HashMap Acsept Key and Value from Click.

                            ansButton2[k].setBackgroundResource(R.drawable.bluebutton_background);     //--->>   Background Color change karva mate
                            ansButton2[k].setTextColor(getColor(R.color.white));
                            ansButton2[k].setText(button2[i].getText().toString());
                            //button[i].setBackgroundColor(getColor(R.color.button));     //--->>   Background Color change karva mate
                            button2[i].setText("");

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
                        builder.append(ansButton2[k].getText());
//                            System.out.println(" \" This is\"");
//                            System.out.println(builder);
                        String str = builder.toString();
                        System.out.println(str);

                        if (str.equalsIgnoreCase(Name[imagePosition2]))
                        {

                            editor.putString("imageLevel2" + imagePosition2 , "done");
                            editor.commit();

                            Intent Inext;
                            Inext = new Intent(LEVEL_2_imagePage.this , LEVEL_2_Solution_Page.class);
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
            if (view.getId() == ansButton2[i].getId() && !ansButton2[i].getText().toString().isEmpty())
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

                        button2[pos].setText(ansButton2[i].getText());
                        //     button[pos].setBackgroundColor(getColor(R.color.button1));  //--->>   Background Color change karva mate
                        ansButton2[i].setText("");
                        ansButton2[i].setBackgroundColor(getColor(R.color.button));   //--->>   Background Color change karva mate
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
        Iback = new Intent(LEVEL_2_imagePage.this , LEVEL_2.class);
        startActivity(Iback);
        finish();

    }
}