package com.example.logo.GridView_Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.logo.Level_Activity.LEVEL_2;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Level_2_GridView_Adapter extends BaseAdapter
{

    LEVEL_2 level_2;
    ArrayList<String> imageArrayList2;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Level_2_GridView_Adapter(LEVEL_2 level_2, ArrayList<String> imageArrayList2)
    {

        this.level_2 = level_2;
        this.imageArrayList2 = imageArrayList2;

        sharedPreferences = level_2.getSharedPreferences("db" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    public int getCount() {
        return imageArrayList2.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        view = LayoutInflater.from(level_2).inflate(R.layout.activity_main_image_page , viewGroup , false);

        ImageView imageView = view.findViewById(R.id.ImageData);
        ImageView imageViewTic = view.findViewById(R.id.ImageTic);

        InputStream inputStream;

        try
        {
            inputStream = level_2.getAssets().open("half_photos_2/" + imageArrayList2.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            imageView.setImageDrawable(drawable);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        String status = sharedPreferences.getString("imageLevel2" + i , "pending");

        String folderName;

        if (status.equalsIgnoreCase("done"))
        {
            folderName = "full_photos_2";

            imageViewTic.setVisibility(View.VISIBLE);
        }
        else
        {
            folderName = "half_photos_2";
        }


        return view;
    }
}
