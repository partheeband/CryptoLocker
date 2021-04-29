package com.example.cryptolocker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetUpMasterPin extends AppCompatActivity {
    Button buttonSetMasterPin;
    EditText editTextPin;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_master_pin);
        editTextPin=findViewById(R.id.editTextMasterPin);
        buttonSetMasterPin =findViewById(R.id.button_setMasterPassword);

        buttonSetMasterPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin=editTextPin.getText().toString();
                if(pin.length()<4)
                {
                    Toast.makeText(SetUpMasterPin.this, "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth = FirebaseAuth.getInstance();
                    user=firebaseAuth.getCurrentUser();
                    databaseReference= FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Pin").child(user.getUid()).setValue(pin).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SetUpMasterPin.this, "Your Pin has been set successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));
                            finish();
                        }
                    });
                }

            }
        });
    }
}