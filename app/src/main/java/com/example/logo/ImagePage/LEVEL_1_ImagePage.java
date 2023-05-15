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

import com.example.logo.SolutionPage.LEVEL_1_Solution_Page;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.R;
import com.example.logo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LEVEL_1_ImagePage extends AppCompatActivity implements View.OnClickListener
{

    ImageView imageBack2 , imagelogo2;
    LinearLayout linearLayout2;
    Button ansButton[] , button[];
    ArrayList<String> allCharacter = new ArrayList<>();

    String[] Name = {"amazon" , "apple" , "bbc" , "best buy" , "burger king" , "dove" , "dr pepper" , "facebook" , "fanta" , "fedex" ,
            "ford" , "gap" , "google" , "hp" , "htc" , "ikea" , "kfc" , "lays" , "lego" , "lg" ,
            "mtv" , "nasa" , "nba" , "netflix" , "nick" , "pepsi" , "pixar" , "pizza" , "puma" , "redbull" ,
            "sprite" , "tousrus" , "ups" , "walmart" , "youtube" };

    int t = 0;
    int imagePosition2;
    String Photos;


    ArrayList positionList = new ArrayList();

    SharedPreferences sharedPreferences;   //--->>  Data save kari ticMark lava mate
    SharedPreferences.Editor editor;    //--->>  Data save kari ticMark lava mate



    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);

        setContentView(R.layout.level_1_image_page);

        imageBack2 = findViewById(R.id.ImageBack2);
        imagelogo2 = findViewById(R.id.ImageLogo2);
        linearLayout2 = findViewById(R.id.LinearLayout2);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);   // ----->  Data Store Karava mate
        editor = sharedPreferences.edit();          // ----->  Data Store Karava mate


        imagePosition2 = getIntent().getIntExtra("imagePosition", 0);
        Photos = getIntent().getStringExtra("Photos");

//        System.out.println(Photos);

        char[] NameArray = Name[imagePosition2].toCharArray();  //------>> Name (STRING) Convert in to Char
        ansButton = new Button[NameArray.length];    //----->>  AnsButton no Array Litho Name ni size no

        for (int i = 0; i < Name[imagePosition2].length(); i++)
        {
            allCharacter.add("" + NameArray[i]);     //--->>   Add all Charcters in ArrayList = allCharacter
            ansButton[i] = new Button(this);
            ansButton[i].setId(i);
            ansButton[i].setBackgroundResource(R.drawable.bluebutton_background);      //---->>   Set Ansbutton Background Color

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 85, 1);
            params.setMargins(5, 0, 5, 0);

            ansButton[i].setLayoutParams(params);     //---->>    Set AnsButton Width , height , weight , lMagine
            ansButton[i].setOnClickListener(this);
            linearLayout2.addView(ansButton[i]);        // ---->> Set ansButton in LinearLayout
        }


        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] alphabetArray = alphabet.toCharArray();      //---->>   Set all Alphabet in CharArray( alphabetArray )


 //---->>   Select Logo Name and Other Character from alphabetArray         ///////////////////////


        for (int i = 0; i < 14 - Name[imagePosition2].length(); i++)
        {
            Random random = new Random();

            int randomInt = random.nextInt(alphabetArray.length);
            System.out.println(alphabetArray[randomInt]);

            allCharacter.add("" + alphabetArray[randomInt]);
            System.out.println(allCharacter);
        }

        Collections.shuffle(allCharacter);      //  SHUFFLE all Alphabets in 16 Spaces
        System.out.println(allCharacter);


 //----->>>     FindViewById for Every 16 Button        /////////////////////////////////////////////////

        button = new Button[14];

        for (int i = 0; i < 14; i++)
        {
            int id = getResources().getIdentifier("b" + i, "id", getPackageName());
            button[i] = findViewById(id);
            button[i].setText(allCharacter.get(i));
            button[i].setOnClickListener(this);
        }


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String Folder;
        Folder = "" + config.imagePosition[0];

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
        imagelogo2.setImageDrawable(drawable);


        imageBack2.setOnClickListener(view ->
        {

            Intent Iback;
            Iback = new Intent(LEVEL_1_ImagePage.this , LEVEL_1.class);
            startActivity(Iback);
            finish();
        });


    }


    //--->> HashMap no use karine Key & Value ni position Set Kareli che       ///////////////////////////


    @Override
    public void onClick(View view)
        {
            for (int i = 0 ; i < 14 ; i++ )    //---->>   16 Button mathi
            {
                if (view.getId() == button[i].getId())    //---->>   Jeni id Per Clicked thay
                {
                    if (t < Name[imagePosition2].length())     //---->> ansButton ma check thay
                    {
                        for (int k = 0; k < Name[imagePosition2].length(); k++)    //---->> ansButton ma Value Print thay
                        {
                            if (ansButton[k].getText().toString().isEmpty())     //---->> Jo ansButton Empty hoy to
                            {
                                Map m = new HashMap();    //---->>   HashMap Acsept Key and Value from Click.

                                ansButton[k].setBackgroundResource(R.drawable.bluebutton_background);     //--->>   Background Color change karva mate
                                ansButton[k].setTextColor(getColor(R.color.white));
                                ansButton[k].setText(button[i].getText().toString());
                          //button[i].setBackgroundColor(getColor(R.color.button));     //--->>   Background Color change karva mate
                                button[i].setText("");

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
                            builder.append(ansButton[k].getText());
//                            System.out.println(" \" This is\"");
//                            System.out.println(builder);
                            String str = builder.toString();
                            System.out.println(str);

                            if (str.equalsIgnoreCase(Name[imagePosition2]))
                            {

                                editor.putString("imageLevel1" + imagePosition2 , "done");
                                editor.commit();

                                Intent Inext;
                                Inext = new Intent(LEVEL_1_ImagePage.this , LEVEL_1_Solution_Page.class);
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
                if (view.getId() == ansButton[i].getId() && !ansButton[i].getText().toString().isEmpty())
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

                            button[pos].setText(ansButton[i].getText());
                       //     button[pos].setBackgroundColor(getColor(R.color.button1));  //--->>   Background Color change karva mate
                            ansButton[i].setText("");
                            ansButton[i].setBackgroundColor(getColor(R.color.button));   //--->>   Background Color change karva mate
                            t--;
                            positionList.remove(m1);
                        }
                    }
                    System.out.println(positionList);
                }
            }
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent Iback;
        Iback = new Intent(LEVEL_1_ImagePage.this , LEVEL_1.class);
        startActivity(Iback);
        finish();

    }
}
