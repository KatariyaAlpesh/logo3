package com.example.logo.Recycler_Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logo.RecyclerView_Activity.Level_1_RecyclerView_Activity;
import com.example.logo.Level_Activity.LEVEL_1;
import com.example.logo.Level_Activity.LEVEL_2;
import com.example.logo.Level_Activity.LEVEL_3;
import com.example.logo.R;

public class Level_Recycler_Adapter extends RecyclerView.Adapter<Level_Recycler_Adapter.Myclass>
{

    Level_1_RecyclerView_Activity level_1_recyclerView_activity;

    public Level_Recycler_Adapter(Level_1_RecyclerView_Activity level_gridView_activity)
    {
        this.level_1_recyclerView_activity = level_gridView_activity;
    }

    public class Myclass extends RecyclerView.ViewHolder
    {
        LinearLayout levelGrid_Level_ImagePage;
        TextView levelText;
        ImageView image1 , image2 , image3 , image4 ;
        SeekBar seekBar;

        public Myclass(@NonNull View itemView)
        {
            super(itemView);

            levelGrid_Level_ImagePage = itemView.findViewById(R.id.LevelGrid_Level_ImagePage);
            levelText = itemView.findViewById(R.id.RecyclerImagePage_LevelText);
            image1 = itemView.findViewById(R.id.RecyclerImagePage_Image1);
            image2 = itemView.findViewById(R.id.RecyclerImagePage_Image2);
            image3 = itemView.findViewById(R.id.RecyclerImagePage_Image3);
            image4 = itemView.findViewById(R.id.RecyclerImagePage_Image4);
            seekBar = itemView.findViewById(R.id.RecyclerImagePage_SeekBar);

        }
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(level_1_recyclerView_activity).inflate(R.layout.level_1_recycler_image_page, parent , false);

        Myclass M = new Myclass(view);

        return M;
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position)
    {
        int n = position + 1;

        if (position == 0) {
            holder.levelText.setText("Level - " + n);
            holder.image1.setImageResource(R.drawable.apple);
            holder.image2.setImageResource(R.drawable.google);
            holder.image3.setImageResource(R.drawable.pepsi);
            holder.image4.setImageResource(R.drawable.kfc);

            holder.levelGrid_Level_ImagePage.setOnClickListener(view -> {

                Intent Inext;
                Inext = new Intent(level_1_recyclerView_activity , LEVEL_1.class);
                level_1_recyclerView_activity.startActivity(Inext);
                level_1_recyclerView_activity.finish();

            });
        }
        else if (position == 1)
        {
            holder.levelText.setText("Level - " + n);
            holder.image1.setImageResource(R.drawable.mcdonalds);
            holder.image2.setImageResource(R.drawable.twitter);
            holder.image3.setImageResource(R.drawable.disney);
            holder.image4.setImageResource(R.drawable.bmw);

            holder.levelGrid_Level_ImagePage.setOnClickListener(view -> {

                Intent Inext;
                Inext = new Intent(level_1_recyclerView_activity , LEVEL_2.class);
                level_1_recyclerView_activity.startActivity(Inext);
                level_1_recyclerView_activity.finish();

            });
        }
        else if (position == 2)
        {
            holder.levelText.setText("Level - " + n);
            holder.image1.setImageResource(R.drawable.jaguar);
            holder.image2.setImageResource(R.drawable.dhl);
            holder.image3.setImageResource(R.drawable.intel);
            holder.image4.setImageResource(R.drawable.email);

            holder.levelGrid_Level_ImagePage.setOnClickListener(view -> {

                Intent Inext;
                Inext = new Intent(level_1_recyclerView_activity , LEVEL_3.class);
                level_1_recyclerView_activity.startActivity(Inext);
                level_1_recyclerView_activity.finish();

            });
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
