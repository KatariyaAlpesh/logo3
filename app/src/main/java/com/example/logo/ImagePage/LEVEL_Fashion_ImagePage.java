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

import com.example.logo.Level_Activity.LEVEL_Cars;
import com.example.logo.Level_Activity.LEVEL_Fashion;
import com.example.logo.R;
import com.example.logo.SolutionPage.LEVEL_Cars_Solution_Page;
import com.example.logo.SolutionPage.LEVEL_Fashion_Solution_Page;
import com.example.logo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LEVEL_Fashion_ImagePage extends AppCompatActivity implements View.OnClickListener {

    ImageView fashionImageBack2 , fashionImagelogo2;
    LinearLayout fashionLinearLayout2;
    Button ansButtonFashion[] , buttonFashion[];
    ArrayList<String> allCharacter = new ArrayList<>();

    String[] Name = {"addidas" , "american_eagle" , "atticus" , "barlie" , "burberry_london" , "calvin_klein" , "cartier" , "dolce_gabbana" ,
            "fila" , "giorgio_armani" , "gucci" , "hermes" , "lacoste" , "louis_vuitton" , "mammut" , "michael_jorden" , "puma" ,
            "ralph_lauren" , "rayban" , "royal_brand" , "versace" , "yves_saint_laurent"};

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
        setContentView(R.layout.activity_level_fashion_image_page);

        fashionImageBack2 = findViewById(R.id.FashionImageBack2);
        fashionImagelogo2 = findViewById(R.id.FashionImageLogo2);
        fashionLinearLayout2 = findViewById(R.id.FashionLinearLayout2);


        sharedPreferences = getSharedPreferences("db" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        imagePosition2 = getIntent().getIntExtra("imagePosition", 0);
        Photos = getIntent().getStringExtra("Photos");


        char[] NameArray = Name[imagePosition2].toCharArray();
        ansButtonFashion = new Button[NameArray.length];

        for (int i = 0 ; i < Name[imagePosition2].length() ; i++)
        {
            allCharacter.add("" + NameArray[i]);
            ansButtonFashion[i] = new Button(this);
            ansButtonFashion[i].setId(i);
            ansButtonFashion[i].setBackgroundResource(R.drawable.bluebutton_background);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 85, 1);
            params.setMargins(5, 0, 5, 0);

            ansButtonFashion[i].setLayoutParams(params);
            ansButtonFashion[i].setOnClickListener(this);
            fashionLinearLayout2.addView(ansButtonFashion[i]);
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


        buttonFashion = new Button[14];

        for (int i = 0; i < 14; i++)
        {
            int id = getResources().getIdentifier("b" + i, "id", getPackageName());
            buttonFashion[i] = findViewById(id);
            buttonFashion[i].setText(allCharacter.get(i));
            buttonFashion[i].setOnClickListener(this);
        }

        String Folder;
        Folder = "" + config.imagePositionFashion[0];

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
        fashionImagelogo2.setImageDrawable(drawable);


        fashionImageBack2.setOnClickListener(view ->
        {

            Intent Iback;
            Iback = new Intent(LEVEL_Fashion_ImagePage.this , LEVEL_Fashion.class);
            startActivity(Iback);
            finish();

        });

    }

    @Override
    public void onClick(View view)
    {
        for (int i = 0 ; i < 14 ; i++ )    //---->>   16 Button mathi
        {
            if (view.getId() == buttonFashion[i].getId())    //---->>   Jeni id Per Clicked thay
            {
                if (t < Name[imagePosition2].length())     //---->> ansButton ma check thay
                {
                    for (int k = 0; k < Name[imagePosition2].length(); k++)    //---->> ansButton ma Value Print thay
                    {
                        if (ansButtonFashion[k].getText().toString().isEmpty())     //---->> Jo ansButton Empty hoy to
                        {
                            Map m = new HashMap();    //---->>   HashMap Acsept Key and Value from Click.

                            ansButtonFashion[k].setBackgroundResource(R.drawable.bluebutton_background);     //--->>   Background Color change karva mate
                            ansButtonFashion[k].setTextColor(getColor(R.color.white));
                            ansButtonFashion[k].setText(buttonFashion[i].getText().toString());
                            //button[i].setBackgroundColor(getColor(R.color.button));     //--->>   Background Color change karva mate
                            buttonFashion[i].setText("");

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
                        builder.append(ansButtonFashion[k].getText());
//                            System.out.println(" \" This is\"");
//                            System.out.println(builder);
                        String str = builder.toString();
                        System.out.println(str);

                        if (str.equalsIgnoreCase(Name[imagePosition2]))
                        {

                            editor.putString("imageLevelFashion" + imagePosition2 , "done");
                            editor.commit();

                            Intent Inext;
                            Inext = new Intent(LEVEL_Fashion_ImagePage.this , LEVEL_Fashion_Solution_Page.class);
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
            if (view.getId() == ansButtonFashion[i].getId() && !ansButtonFashion[i].getText().toString().isEmpty())
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

                        buttonFashion[pos].setText(ansButtonFashion[i].getText());
                        //     button[pos].setBackgroundColor(getColor(R.color.button1));  //--->>   Background Color change karva mate
                        ansButtonFashion[i].setText("");
                        ansButtonFashion[i].setBackgroundColor(getColor(R.color.button));   //--->>   Background Color change karva mate
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
        Iback = new Intent(LEVEL_Fashion_ImagePage.this , LEVEL_Fashion.class);
        startActivity(Iback);
        finish();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {
        super.onPointerCaptureChanged(hasCapture);
    }

}