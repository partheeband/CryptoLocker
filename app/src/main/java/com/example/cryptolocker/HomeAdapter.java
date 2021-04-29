package com.example.cryptolocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<com.example.cryptolocker.HomeAdapter.HomeViewHolder>{
    private Context mCtx;
    private List<com.example.cryptolocker.Home> homeList;
    private onNoteListener mOnNoteListener;

    public HomeAdapter(Context mCtx, List<com.example.cryptolocker.Home> homeList,onNoteListener onNoteListener) {
        this.mCtx = mCtx;
        this.homeList = homeList;
        this.mOnNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_layout_home, parent, false);
        return new HomeViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        com.example.cryptolocker.Home home=homeList.get(position);


        String title,subTitle1,subTitle2,category;
        title=home.getTitle();
        subTitle1=home.getSubTitle1();
        subTitle2=home.getSubTitle2();
        category=home.getCategory();




//        //Decryption occurs here:
//        String title,subTitle1,subTitle2,category;
//        decryptedData=Aes256.decrypt(home.getTitle(),home.getSubTitle1(),home.getSubTitle2(),home.getCategory());
//
//        title=decryptedData.get(0);
//        subTitle1=decryptedData.get(1);
//        subTitle2=decryptedData.get(2);
//        category=decryptedData.get(3);
//        //

        holder.textViewTitle.setText(title);
        holder.textViewSubTitle1.setText(subTitle1);
        holder.textViewSubTitle2.setText(subTitle2);


        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(home.getImage()));


        //to populate image based on data
        if (category.equals("social media")){
            holder.imageView_category.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.icon_social_media));
        }
        else if(category.equals("bank"))
        {
            holder.imageView_category.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.icon_bank));
        }
        else
        {
            holder.imageView_category.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.icon_custom));
        }

    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView_category,imageView_ViewData;
        TextView textViewTitle,textViewSubTitle1,textViewSubTitle2;

        onNoteListener onNoteListener;
        public HomeViewHolder(@NonNull View itemView, onNoteListener mOnNoteListener) {
            super(itemView);

            imageView_category =(ImageView)itemView.findViewById(R.id.imageView_home_category);
            textViewTitle=(TextView)itemView.findViewById(R.id.textView_home_title);
            textViewSubTitle1=(TextView)itemView.findViewById(R.id.textView_home_subtitle1);
            textViewSubTitle2=(TextView)itemView.findViewById(R.id.textView_home_subtitle2);
            imageView_ViewData=(ImageView)itemView.findViewById(R.id.imageView_ViewData);

            this.onNoteListener=mOnNoteListener;
            imageView_ViewData.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String title,subTitle1,subTitle2;
            title= (String) textViewTitle.getText();
            subTitle1= (String) textViewSubTitle1.getText();
            subTitle2= (String) textViewSubTitle2.getText();
            onNoteListener.onNoteClick(getAdapterPosition(),title,subTitle1,subTitle2);
        }
    }
    public interface onNoteListener{
        void onNoteClick(int position, String s, String subTitle1, String title);
    }
}
