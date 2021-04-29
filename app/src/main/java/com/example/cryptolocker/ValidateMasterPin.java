package com.example.cryptolocker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ValidateMasterPin extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;

    Button buttonValidateMasterPin;
    EditText editTextPin;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_master_pin);

        editTextPin=findViewById(R.id.editTextMasterPin);
        buttonValidateMasterPin =findViewById(R.id.button_ValidateMasterPassword);

        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage(" Validating Pin ...");

        buttonValidateMasterPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                final String pin=editTextPin.getText().toString();
                if(pin.length()<4)
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth = FirebaseAuth.getInstance();
                    user=firebaseAuth.getCurrentUser();
                    databaseReference= FirebaseDatabase.getInstance().getReference("Pin").child(user.getUid());

                    //Fetch current User Pin
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String value= snapshot.getValue(String.class);
                                if(pin.equals(value))
                                {
                                    Toast.makeText(ValidateMasterPin.this, "Pin Validation Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));
                                    progressDialog.dismiss();
                                    finish();
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(ValidateMasterPin.this, "Please Enter Valid Pin", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    //
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}