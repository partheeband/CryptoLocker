package com.example.cryptolocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class ViewDataActivity extends AppCompatActivity {
    String getTitle,getSubTitle1,getSubTitle2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        textView=findViewById(R.id.textView3);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            getTitle= (String) bd.get("title");
            getSubTitle1= (String) bd.get("subtitle1");
            getSubTitle2= (String) bd.get("subtitle2");
        }

        textView.setText(getTitle+" "+getSubTitle1+" "+getSubTitle2);
        Toast.makeText(this, getTitle+" "+getSubTitle1+" "+getSubTitle2, Toast.LENGTH_SHORT).show();

    }
}