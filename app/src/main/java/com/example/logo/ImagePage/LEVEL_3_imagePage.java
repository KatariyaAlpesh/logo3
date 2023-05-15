package com.example.logo.ImagePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.logo.SolutionPage.LEVEL_3_Solution_Page;
import com.example.logo.Level_Activity.LEVEL_3;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LEVEL_3_imagePage extends AppCompatActivity implements View.OnClickListener {

    ImageView level3_ImageBack3 , level3_ImageLogo3;
    LinearLayout level3_LinearLayout3;
    Button ansButton3[] , button3[];

    ArrayList<String> allCharacter3 = new ArrayList<>();

    String[] Name = {"aim" , "aoi" , "audio" , "barlie" , "canon" , "cartoonnetwork" , "cbs" , "chanel" , "cnn" , "dhl" ,
                    "dish" , "dreamwork" , "dunlop" , "email" , "esso" , "explore" , "flicker" , "heineken" , "hello_kitty" , "ibm" ,
        "ign" , "intel" , "hajuar" , "kroger" , "lamborghini" , "linkdin" , "loui_vuitton" , "minute_mind" ,"mistubishi" , "nickelodeon" ,
            "nintendo" , "pampers" , "panasonic" , "paypal" , "sap" , "suzuki" , "unicef" , "verizon" , "visa" , "windows"};

    int t = 0;
    int imagePosition2;
    String Photos;

    ArrayList positionList = new ArrayList();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3_image_page);

        level3_ImageBack3 = findViewById(R.id.Level3_ImageBack3);
        level3_ImageLogo3 = findViewById(R.id.Level3_ImageLogo3);
        level3_LinearLayout3 = findViewById(R.id.Level3_LinearLayout3);

        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        imagePosition2 = getIntent().getIntExtra("imagePosition" , 0);
        Photos = getIntent().getStringExtra("Photos" );

        char[] NameArray = Name[imagePosition2].toCharArray();  //------>> Name (STRING) Convert in to Char
        ansButton3 = new Button[NameArray.length];

        for (int i = 0; i < Name[imagePosition2].length(); i++)
        {
            allCharacter3.add("" + NameArray[i]);     //--->>   Add all Charcters in ArrayList = allCharacter
            ansButton3[i] = new Button(this);
            ansButton3[i].setId(i);
            ansButton3[i].setBackgroundResource(R.drawable.bluebutton_background);      //---->>   Set Ansbutton Background Color

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 85, 1);
            params.setMargins(5, 0, 5, 0);

            ansButton3[i].setLayoutParams(params);     //---->>    Set AnsButton Width , height , weight , lMagine
            ansButton3[i].setOnClickListener(this);
            level3_LinearLayout3.addView(ansButton3[i]);        // ---->> Set ansButton in LinearLayout
        }

        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] alphabetArray = alphabet.toCharArray();


        for (int i = 0; i < 14 - Name[imagePosition2].length(); i++)
        {
            Random random = new Random();

            int randomInt = random.nextInt(alphabetArray.length);
            System.out.println(alphabetArray[randomInt]);

            allCharacter3.add("" + alphabetArray[randomInt]);
            System.out.println(allCharacter3);
        }

        Collections.shuffle(allCharacter3);      //  SHUFFLE all Alphabets in 16 Spaces
        System.out.println(allCharacter3);


        button3 = new Button[14];

        for (int i = 0; i < 14; i++)
        {
            int id = getResources().getIdentifier("b" + i, "id", getPackageName());
            button3[i] = findViewById(id);
            button3[i].setText(allCharacter3.get(i));
            button3[i].setOnClickListener(this);
        }

        String Folder;
        Folder = "" + config.imagePosition3[0];


        InputStream inputStream = null;

        try {
            inputStream = getAssets().open(Folder + "/" + Photos);
            System.out.println(Photos);

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Drawable drawable = Drawable.createFromStream(inputStream, null);
        level3_ImageLogo3.setImageDrawable(drawable);


        level3_ImageBack3.setOnClickListener(view ->
        {

            Intent Iback;
            Iback = new Intent(LEVEL_3_imagePage.this , LEVEL_3.class);
            startActivity(Iback);
            finish();

        });


    }

    @Override
    public void onClick(View view)
    {
        for (int i = 0 ; i < 14 ; i++ )    //---->>   16 Button mathi
        {
            if (view.getId() == button3[i].getId())    //---->>   Jeni id Per Clicked thay
            {
                if (t < Name[imagePosition2].length())     //---->> ansButton ma check thay
                {
                    for (int k = 0; k < Name[imagePosition2].length(); k++)    //---->> ansButton ma Value Print thay
                    {
                        if (ansButton3[k].getText().toString().isEmpty())     //---->> Jo ansButton Empty hoy to
                        {
                            Map m = new HashMap();    //---->>   HashMap Acsept Key and Value from Click.

                            ansButton3[k].setBackgroundResource(R.drawable.bluebutton_background);     //--->>   Background Color change karva mate
                            ansButton3[k].setTextColor(getColor(R.color.white));
                            ansButton3[k].setText(button3[i].getText().toString());
                            //button[i].setBackgroundColor(getColor(R.color.button));     //--->>   Background Color change karva mate
                            button3[i].setText("");

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
                        builder.append(ansButton3[k].getText());
//                            System.out.println(" \" This is\"");
//                            System.out.println(builder);
                        String str = builder.toString();
                        System.out.println(str);

                        if (str.equalsIgnoreCase(Name[imagePosition2]))
                        {

                            editor.putString("imageLevel3" + imagePosition2 , "done");
                            editor.commit();

                            Intent Inext;
                            Inext = new Intent(LEVEL_3_imagePage.this , LEVEL_3_Solution_Page.class);
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
            if (view.getId() == ansButton3[i].getId() && !ansButton3[i].getText().toString().isEmpty())
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

                        button3[pos].setText(ansButton3[i].getText());
                        //     button[pos].setBackgroundColor(getColor(R.color.button1));  //--->>   Background Color change karva mate
                        ansButton3[i].setText("");
                        ansButton3[i].setBackgroundColor(getColor(R.color.button));   //--->>   Background Color change karva mate
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
        Iback = new Intent(LEVEL_3_imagePage.this , LEVEL_3.class);
        startActivity(Iback);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}