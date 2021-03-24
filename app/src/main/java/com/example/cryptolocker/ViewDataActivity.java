package com.example.cryptolocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class ViewDataActivity extends AppCompatActivity implements View.OnClickListener{
    String getTitle,getSubTitle1,getSubTitle2,getCategory;
    EditText editTextTitle,editTextSubTitle1,editTextSubTitle2;
    TextView textViewTitle,textViewSubTitle1,textViewSubTitle2;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        textViewTitle=findViewById(R.id.textViewTitle);
        textViewSubTitle1=findViewById(R.id.textViewSubtitle1);
        textViewSubTitle2=findViewById(R.id.textViewSubtitle2);

        editTextTitle=findViewById(R.id.editTextTitle);
        editTextSubTitle1=findViewById(R.id.editTextSubTitle1);
        editTextSubTitle2=findViewById(R.id.editTextSubTitle2);

        editTextTitle.setOnClickListener(this);
        editTextSubTitle1.setOnClickListener(this);
        editTextSubTitle2.setOnClickListener(this);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            getTitle= (String) bd.get("title");
            getSubTitle1= (String) bd.get("subtitle1");
            getSubTitle2= (String) bd.get("subtitle2");
            getCategory= (String) bd.get("category");
        }

        editTextTitle.setText(getTitle);
        editTextSubTitle1.setText(getSubTitle1);
        editTextSubTitle2.setText(getSubTitle2);

        editTextTitle.setKeyListener(null);
        editTextSubTitle1.setKeyListener(null);
        editTextSubTitle2.setKeyListener(null);



        if (getCategory.equals("social media"))
        {
            //Toast.makeText(this, getTitle+" "+getSubTitle1+" "+getSubTitle2, Toast.LENGTH_SHORT).show();

            textViewTitle.setText("Account");
            textViewSubTitle1.setText("Username");
            textViewSubTitle2.setText("Password");
        }
        else if (getCategory.equals("bank"))
        {
            textViewTitle.setText("Bank Name");
            textViewSubTitle1.setText("Account Number");
            textViewSubTitle2.setText("Pin");
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editTextTitle:
                myClip = ClipData.newPlainText("text", getTitle);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editTextSubTitle1:
                myClip = ClipData.newPlainText("text", getSubTitle1);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editTextSubTitle2:
                myClip = ClipData.newPlainText("text", getSubTitle2);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}