package com.example.logo.GridView_Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Level_1_GridView_Adapter extends BaseAdapter
{

    LEVEL_1 level_1;
    ArrayList<String> imageArrayList;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Level_1_GridView_Adapter(LEVEL_1 level_1, ArrayList<String> imageArrayList)
    {
        this.level_1 = level_1;
        this.imageArrayList = imageArrayList;

        sharedPreferences = level_1.getSharedPreferences("db" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public int getCount()
    {
        return imageArrayList.size();
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

        view = LayoutInflater.from(level_1).inflate(R.layout.activity_main_image_page, viewGroup , false);

        ImageView imageView = view.findViewById(R.id.ImageData);
        ImageView imageViewTic = view.findViewById(R.id.ImageTic);

        InputStream inputStream;

        try
        {
            inputStream = level_1.getAssets().open("half_photos_1/" + imageArrayList.get(i));
            Drawable drawable = Drawable.createFromStream(inputStream , null);

            imageView.setImageDrawable(drawable);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        String status = sharedPreferences.getString("imageLevel1" + i , "pending");

        String folderName;

        if (status.equalsIgnoreCase("done"))
        {
            folderName = "full_photos_1";

            imageViewTic.setVisibility(View.VISIBLE);
        }
        else
        {
            folderName = "half_photos_1";
        }

        return view;
    }
}
