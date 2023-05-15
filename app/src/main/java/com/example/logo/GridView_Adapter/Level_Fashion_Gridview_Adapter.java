package com.example.logo.GridView_Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.logo.Level_Activity.LEVEL_Fashion;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Level_Fashion_Gridview_Adapter extends BaseAdapter
{
    LEVEL_Fashion level_fashion;
    ArrayList<String> fashionImageArrayList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Level_Fashion_Gridview_Adapter(LEVEL_Fashion level_fashion, ArrayList<String> fashionImageArrayList)
    {
        this.level_fashion = level_fashion;
        this.fashionImageArrayList = fashionImageArrayList;

        sharedPreferences = level_fashion.getSharedPreferences("db" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    public int getCount()
    {
        return fashionImageArrayList.size();
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
        view = LayoutInflater.from(level_fashion).inflate(R.layout.activity_main_image_page, viewGroup , false);

        ImageView mobileAppImageView = view.findViewById(R.id.ImageData);
        ImageView mobileAppImageViewTic = view.findViewById(R.id.ImageTic);

        InputStream inputStream;

        try
        {
            inputStream = level_fashion.getAssets().open("fashion_half_photos/" + fashionImageArrayList.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            mobileAppImageView.setImageDrawable(drawable);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        String status = sharedPreferences.getString("imageLevelFashion" + i , "pending");

        String folderName;

        if (status.equalsIgnoreCase("done"))
        {
            folderName = "fashion_full_photos";

            mobileAppImageViewTic.setVisibility(View.VISIBLE);
        }
        else
        {
            folderName = "fashion_half_photos";
        }

        return view;
    }
}
