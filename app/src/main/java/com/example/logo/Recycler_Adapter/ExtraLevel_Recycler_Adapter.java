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

import com.example.logo.Level_Activity.LEVEL_Cars;
import com.example.logo.Level_Activity.LEVEL_Fashion;
import com.example.logo.Level_Activity.LEVEL_MobileApp;
import com.example.logo.RecyclerView_Activity.Level_Extra_RecyclerView_Activity;
import com.example.logo.R;

public class ExtraLevel_Recycler_Adapter extends RecyclerView.Adapter<ExtraLevel_Recycler_Adapter.Myclass>
{

    Level_Extra_RecyclerView_Activity level_extra_recyclerView_activity;

    public ExtraLevel_Recycler_Adapter(Level_Extra_RecyclerView_Activity level_extra_recyclerView_activity)
    {
        this.level_extra_recyclerView_activity = level_extra_recyclerView_activity;
    }

    public class Myclass extends RecyclerView.ViewHolder
    {
        LinearLayout levelExtra_Level_ImagePage;
        TextView levelExtraText;
        ImageView extraImage1 , extraImage2 , extraImage3 , extraImage4 ;
        SeekBar extraSeekBar;

        public Myclass(@NonNull View itemView)
        {
            super(itemView);

            levelExtra_Level_ImagePage = itemView.findViewById(R.id.LevelExtra_Level_ImagePage);
            levelExtraText = itemView.findViewById(R.id.RecyclerExtraImagePage_LevelText);
            extraImage1 = itemView.findViewById(R.id.RecyclerExtraImagePage_Image1);
            extraImage2 = itemView.findViewById(R.id.RecyclerExtraImagePage_Image2);
            extraImage3 = itemView.findViewById(R.id.RecyclerExtraImagePage_Image3);
            extraImage4 = itemView.findViewById(R.id.RecyclerExtraImagePage_Image4);
            extraSeekBar = itemView.findViewById(R.id.RecyclerExtraImagePage_SeekBar);

        }
    }

    @NonNull
    @Override
    public ExtraLevel_Recycler_Adapter.Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(level_extra_recyclerView_activity).inflate(R.layout.activity_extra_recycler_image_page
                                                                                                    , parent , false);

        Myclass M = new Myclass(view);

        return M;
    }

    @Override
    public void onBindViewHolder(@NonNull ExtraLevel_Recycler_Adapter.Myclass holder, int position)
    {
        int n = position + 1;

        if (position == 0)
        {
            holder.levelExtraText.setText("Mobile App's " + n);
            holder.extraImage1.setImageResource(R.drawable.facebook);
            holder.extraImage2.setImageResource(R.drawable.angry_birds);
            holder.extraImage3.setImageResource(R.drawable.instagram);
            holder.extraImage4.setImageResource(R.drawable.twitter);

            holder.levelExtra_Level_ImagePage.setOnClickListener(view -> {

                Intent Inext;
                Inext = new Intent(level_extra_recyclerView_activity , LEVEL_MobileApp.class);
                level_extra_recyclerView_activity.startActivity(Inext);
                level_extra_recyclerView_activity.finish();

            });
        }
        else if (position == 1)
        {
            holder.levelExtraText.setText("Cars " + n);
            holder.extraImage1.setImageResource(R.drawable.skoda_half);
            holder.extraImage2.setImageResource(R.drawable.mercedes);
            holder.extraImage3.setImageResource(R.drawable.hyundai);
            holder.extraImage4.setImageResource(R.drawable.volkswagen);

            holder.levelExtra_Level_ImagePage.setOnClickListener(view -> {

                Intent Inext;
                Inext = new Intent(level_extra_recyclerView_activity , LEVEL_Cars.class);
                level_extra_recyclerView_activity.startActivity(Inext);
                level_extra_recyclerView_activity.finish();

            });
        }
        else if (position == 2)
        {
            holder.levelExtraText.setText("Fashion App,s " + n);
            holder.extraImage1.setImageResource(R.drawable.addidas);
            holder.extraImage2.setImageResource(R.drawable.ralph_lauren);
            holder.extraImage3.setImageResource(R.drawable.calvin_klein);
            holder.extraImage4.setImageResource(R.drawable.fila);

            holder.levelExtra_Level_ImagePage.setOnClickListener(view -> {

                Intent Inext;
                Inext = new Intent(level_extra_recyclerView_activity , LEVEL_Fashion.class);
                level_extra_recyclerView_activity.startActivity(Inext);
                level_extra_recyclerView_activity.finish();

            });
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
