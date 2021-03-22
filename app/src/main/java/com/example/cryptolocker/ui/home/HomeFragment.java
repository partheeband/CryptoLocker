package com.example.cryptolocker.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cryptolocker.Home;
import com.example.cryptolocker.HomeAdapter;
import com.example.cryptolocker.R;
import com.example.cryptolocker.ViewDataActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeAdapter.onNoteListener{

//    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    HomeAdapter adapter;
    List<Home> homeList;

    DatabaseReference dbHome;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("This is home fragment");

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        homeList=new ArrayList<>();

        adapter =new HomeAdapter(getActivity(),homeList,this);
        recyclerView.setAdapter(adapter);

        dbHome = FirebaseDatabase.getInstance().getReference("Home");
        dbHome.addListenerForSingleValueEvent(valueEventListener);


        return root;
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            homeList.clear();

            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Home home = snapshot.getValue(Home.class);
                    homeList.add(home);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    public void onNoteClick(int position) {
        Home home=homeList.get(position);
        Toast.makeText(getActivity(), "CardView Position: "+String.valueOf(position)+" Title:"+home.getTitle(), Toast.LENGTH_SHORT).show();
        Log.d("cardviewPosition", String.valueOf(position));

        Intent i=new Intent(getActivity(), ViewDataActivity.class);
        i.putExtra("title",home.getTitle());
        i.putExtra("subtitle1",home.getSubTitle1());
        i.putExtra("subtitle2",home.getSubTitle2());
        startActivity(i);

    }
}