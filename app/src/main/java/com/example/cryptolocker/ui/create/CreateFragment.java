package com.example.cryptolocker.ui.create;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cryptolocker.Aes256;
import com.example.cryptolocker.Home;
import com.example.cryptolocker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class CreateFragment extends Fragment {
    //    private CreateViewModel createViewModel;
    EditText editTextTitle,editTextSubTitle1,editTextSubTitle2;
    Button buttonSave;
    Spinner spinnerCategory;
    String category,title, subtitle1, subtitle2;
    int spinner_category_position;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;

    Date currentTime;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        createViewModel =
//                new ViewModelProvider(this).get(CreateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create, container, false);
        editTextTitle = root.findViewById(R.id.editTextTitle);
        editTextSubTitle1 = root.findViewById(R.id.editTextSubTitle1);
        editTextSubTitle2 = root.findViewById(R.id.editTextSubTitle2);

        buttonSave = root.findViewById(R.id.buttonSave);

        spinnerCategory=root.findViewById(R.id.spinnerCategory);

        String[] categoryArray={"Select Category","Social Media","Bank","Custom"};
        ArrayAdapter<String> categoryAdapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_item_category,R.id.spinner_textview_category,categoryArray);
        spinnerCategory.setAdapter(categoryAdapter);

//        createViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        firebaseAuth= FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        Toast.makeText(getActivity(), String.valueOf(currentTime), Toast.LENGTH_SHORT).show();

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner_category_position= spinnerCategory.getSelectedItemPosition();
                if (spinner_category_position==1){
                    editTextTitle.setHint("Account Type");
                    editTextSubTitle1.setHint("UserName");
                    editTextSubTitle2.setHint("Password");
                }
                else if (spinner_category_position==2){
                    editTextTitle.setHint("Bank Name");
                    editTextSubTitle1.setHint("Account Number");
                    editTextSubTitle2.setHint("Pin");
                }
                else
                {
                    editTextTitle.setHint("Title");
                    editTextSubTitle1.setHint("SubTitle1");
                    editTextSubTitle2.setHint("SubTitle2");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTime = Calendar.getInstance().getTime();
                saveData_To_Firebase();
            }
        });

        return root;
    }

    private void saveData_To_Firebase() {
        title=String.valueOf(editTextTitle.getText());
        subtitle1 =String.valueOf(editTextSubTitle1.getText());
        subtitle2 =String.valueOf(editTextSubTitle2.getText());

        int spinner_category_position = spinnerCategory.getSelectedItemPosition();

        if (spinner_category_position == 0)
        {
            Toast.makeText(getActivity(), "Please select category", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            category=computeCategory(spinner_category_position);
        }

        //Encryption occurs here:
        title=Aes256.encrypt(title);
        subtitle1=Aes256.encrypt(subtitle1);
        subtitle2=Aes256.encrypt(subtitle2);
        category=Aes256.encrypt(category);
        //

        if (TextUtils.isEmpty(title)||TextUtils.isEmpty(subtitle1)||TextUtils.isEmpty(subtitle2))
        {
            Toast.makeText(getActivity(), "Please enter credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        Home home=new Home(title, subtitle1, subtitle2,category);
        databaseReference.child("Home").child(user.getUid()).child(String.valueOf(currentTime)).setValue(home);

        Toast.makeText(getActivity(), "Data saved to firebase", Toast.LENGTH_SHORT).show();
    }

    private String computeCategory(int spinner_category_position) {
        String category="";
        if(spinner_category_position==1)
        {
            category="social media";
        }
        else if (spinner_category_position==2)
        {
            category="bank";
        }
        else
        {
            category="custom";
        }
        return category;
    }
}