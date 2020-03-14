package com.example.guestlec;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Homebutton extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Lectures> lecturesList;
    DatabaseReference ref;
    DataSnapshot dataSnapshot3;
    Lectures lectures;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.homebutton,container,false);
        lecturesList=new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference("Lectures");
        recyclerView= view.findViewById(R.id.lectures);
        layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
            ref.addListenerForSingleValueEvent(valueeventlistener);



        return view;


    }

ValueEventListener valueeventlistener= new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        Log.e("size",String.valueOf(dataSnapshot.getChildrenCount())) ;
        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   lectures=new Lectures(String.valueOf(dataSnapshot1.child("Professor").getValue()),
                           String.valueOf(dataSnapshot1.child("LectureName").getValue()),
                           String.valueOf(dataSnapshot1.child("Dates").getValue()),
                           String.valueOf(dataSnapshot1.child("Time").getValue()),String.valueOf(dataSnapshot1.child("Venue").getValue()),
                           String.valueOf(dataSnapshot1.child("LectureDetails").getValue()),String.valueOf(dataSnapshot1.child("Area").getValue()));
                    lecturesList.add(lectures);

                }

                adapter=new CustomRecyleradapter(getContext(),lecturesList);
                recyclerView.setAdapter(adapter);



    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};

}
