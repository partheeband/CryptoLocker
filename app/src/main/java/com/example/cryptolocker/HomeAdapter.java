package com.example.cryptolocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<com.example.cryptolocker.HomeAdapter.HomeViewHolder>{
    private Context mCtx;
    private List<com.example.cryptolocker.Home> homeList;

    public HomeAdapter(Context mCtx, List<com.example.cryptolocker.Home> homeList) {
        this.mCtx = mCtx;
        this.homeList = homeList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_layout_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        com.example.cryptolocker.Home home=homeList.get(position);
        holder.textViewTitle.setText(home.getTitle());
        holder.textViewSubTitle1.setText(home.getSubTitle1());
        holder.textViewSubTitle2.setText(home.getSubTitle2());


        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(home.getImage()));


        //to populate image based on data
        if (home.getCategory().equals("social media")){
            holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.icon_social_media));
        }
        else if(home.getCategory().equals("bank"))
        {
            holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.icon_bank));
        }
        else
        {
            holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.icon_custom));
        }

    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle,textViewSubTitle1,textViewSubTitle2;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =(ImageView)itemView.findViewById(R.id.imageView_home_category);
            textViewTitle=(TextView)itemView.findViewById(R.id.textView_home_title);
            textViewSubTitle1=(TextView)itemView.findViewById(R.id.textView_home_subtitle1);
            textViewSubTitle2=(TextView)itemView.findViewById(R.id.textView_home_subtitle2);
        }
    }
}
