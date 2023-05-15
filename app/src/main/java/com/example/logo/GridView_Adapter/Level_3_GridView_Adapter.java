package com.example.logo.GridView_Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.logo.Level_Activity.LEVEL_3;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Level_3_GridView_Adapter extends BaseAdapter
{

    LEVEL_3 level_3;
    ArrayList<String> imageArrayList3;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Level_3_GridView_Adapter(LEVEL_3 level_3, ArrayList<String> imageArrayList3)
    {
        this.level_3 = level_3;
        this.imageArrayList3 = imageArrayList3;

        sharedPreferences = level_3.getSharedPreferences("db" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public int getCount()
    {
        return imageArrayList3.size();
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = LayoutInflater.from(level_3).inflate(R.layout.activity_main_image_page , viewGroup , false);

        ImageView imageViewTic = view.findViewById(R.id.ImageTic);
        ImageView imageView = view.findViewById(R.id.ImageData);

        InputStream inputStream;

        try
        {
            inputStream = level_3.getAssets().open("half_photos_3/" + imageArrayList3.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            imageView.setImageDrawable(drawable);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        String status = sharedPreferences.getString("imageLevel3" + i , "pending");

        String folderName;

        if (status.equalsIgnoreCase("done"))
        {
            folderName = "full_photos_3";

            imageViewTic.setVisibility(View.VISIBLE);
        }
        else
        {
            folderName = "half_photos_3";
        }


        return view;
    }
}
