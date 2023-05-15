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

import com.example.logo.Level_Activity.LEVEL_MobileApp;
import com.example.logo.R;
import com.example.logo.SolutionPage.LEVEL_MobileApp_solution_Page;
import com.example.logo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LEVEL_MobileApp_ImagePage extends AppCompatActivity implements View.OnClickListener {
    ImageView MobileAppImageBack2 , MobileAppImagelogo2;
    LinearLayout MobileAppLinearLayout2;
    Button ansButtonMobileApp[] , buttonMobileApp[];
    ArrayList<String> allCharacter = new ArrayList<>();

    String[] Name = {"amazon" , "angry_birds" , "apple_tv" , "burger_king" , "candy_crush" , "cartoonnetwork" , "chrome" , "disney" ,
            "dominoz" , "email" , "facebook" , "firefox" , "gmail" , "google_drive" , "google_file" , "google_photos" ,
            "google_plus" , "instagram" , "kfc" , "mcdonald's" , "netflix" , "paypal" , "pinterest" , "pizza_hut" , "pokemon" ,
            "prime_video" , "spotify" , "telegram" , "temple_run" , "tictoc" , "visa" , "whatsapp" , "youtube" , "zoom"};

    int t = 0;
    int imagePosition2;
    String Photos;


    ArrayList positionList = new ArrayList();

    SharedPreferences sharedPreferences;   //--->>  Data save kari ticMark lava mate
    SharedPreferences.Editor editor;    //--->>  Data save kari ticMark lava mate



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_mobile_app_image_page);

        MobileAppImageBack2 = findViewById(R.id.MobileAppImageBack2);
        MobileAppImagelogo2 = findViewById(R.id.MobileAppImageLogo2);
        MobileAppLinearLayout2 = findViewById(R.id.MobileAppLinearLayout2);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        imagePosition2 = getIntent().getIntExtra("imagePosition", 0);
        Photos = getIntent().getStringExtra("Photos");


        char[] NameArray = Name[imagePosition2].toCharArray();
        ansButtonMobileApp = new Button[NameArray.length];

        for (int i = 0 ; i < Name[imagePosition2].length() ; i++)
        {
            allCharacter.add("" + NameArray[i]);
            ansButtonMobileApp[i] = new Button(this);
            ansButtonMobileApp[i].setId(i);
            ansButtonMobileApp[i].setBackgroundResource(R.drawable.bluebutton_background);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 85, 1);
            params.setMargins(5, 0, 5, 0);

            ansButtonMobileApp[i].setLayoutParams(params);
            ansButtonMobileApp[i].setOnClickListener(this);
            MobileAppLinearLayout2.addView(ansButtonMobileApp[i]);
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


        buttonMobileApp = new Button[14];

        for (int i = 0; i < 14; i++)
        {
            int id = getResources().getIdentifier("b" + i, "id", getPackageName());
            buttonMobileApp[i] = findViewById(id);
            buttonMobileApp[i].setText(allCharacter.get(i));
            buttonMobileApp[i].setOnClickListener(this);
        }

        String Folder;
        Folder = "" + config.imagePositionMobileApp[0];

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
        MobileAppImagelogo2.setImageDrawable(drawable);


        MobileAppImageBack2.setOnClickListener(view ->
        {

            Intent Iback;
            Iback = new Intent(LEVEL_MobileApp_ImagePage.this , LEVEL_MobileApp.class);
            startActivity(Iback);
            finish();

        });

    }

    @Override
    public void onClick(View view)
    {
        for (int i = 0 ; i < 14 ; i++ )    //---->>   16 Button mathi
        {
            if (view.getId() == buttonMobileApp[i].getId())    //---->>   Jeni id Per Clicked thay
            {
                if (t < Name[imagePosition2].length())     //---->> ansButton ma check thay
                {
                    for (int k = 0; k < Name[imagePosition2].length(); k++)    //---->> ansButton ma Value Print thay
                    {
                        if (ansButtonMobileApp[k].getText().toString().isEmpty())     //---->> Jo ansButton Empty hoy to
                        {
                            Map m = new HashMap();    //---->>   HashMap Acsept Key and Value from Click.

                            ansButtonMobileApp[k].setBackgroundResource(R.drawable.bluebutton_background);     //--->>   Background Color change karva mate
                            ansButtonMobileApp[k].setTextColor(getColor(R.color.white));
                            ansButtonMobileApp[k].setText(buttonMobileApp[i].getText().toString());
                            //button[i].setBackgroundColor(getColor(R.color.button));     //--->>   Background Color change karva mate
                            buttonMobileApp[i].setText("");

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
                        builder.append(ansButtonMobileApp[k].getText());
//                            System.out.println(" \" This is\"");
//                            System.out.println(builder);
                        String str = builder.toString();
                        System.out.println(str);

                        if (str.equalsIgnoreCase(Name[imagePosition2]))
                        {

                            editor.putString("imageLevelMobileApp" + imagePosition2 , "done");
                            editor.commit();

                            Intent Inext;
                            Inext = new Intent(LEVEL_MobileApp_ImagePage.this , LEVEL_MobileApp_solution_Page.class);
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
            if (view.getId() == ansButtonMobileApp[i].getId() && !ansButtonMobileApp[i].getText().toString().isEmpty())
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

                        buttonMobileApp[pos].setText(ansButtonMobileApp[i].getText());
                        //     button[pos].setBackgroundColor(getColor(R.color.button1));  //--->>   Background Color change karva mate
                        ansButtonMobileApp[i].setText("");
                        ansButtonMobileApp[i].setBackgroundColor(getColor(R.color.button));   //--->>   Background Color change karva mate
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
        Iback = new Intent(LEVEL_MobileApp_ImagePage.this , LEVEL_MobileApp.class);
        startActivity(Iback);
        finish();

    }
}