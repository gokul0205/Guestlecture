package com.example.guestlec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
AutoCompleteTextView search;
String [] array;
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
       search=view.findViewById(R.id.search);


        return view;


    }

ValueEventListener valueeventlistener= new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
dataSnapshot3=dataSnapshot;
        Log.e("size",String.valueOf(dataSnapshot.getChildrenCount())) ;
        int i=0;
        array=new String[Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()))];
        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   lectures=new Lectures(String.valueOf(dataSnapshot1.child("Professor").getValue()),
                           String.valueOf(dataSnapshot1.child("LectureName").getValue()),
                           String.valueOf(dataSnapshot1.child("Dates").getValue()),
                           String.valueOf(dataSnapshot1.child("Time").getValue()),String.valueOf(dataSnapshot1.child("Venue").getValue()),
                           String.valueOf(dataSnapshot1.child("LectureDetails").getValue()),String.valueOf(dataSnapshot1.child("Area").getValue()));
                   array[i]= String.valueOf(dataSnapshot1.child("LectureName").getValue());
                   i+=1;
                   lecturesList.add(lectures);

                }

                adapter=new CustomRecyleradapter(getContext(),lecturesList);
                recyclerView.setAdapter(adapter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.select_dialog_item,array  );
        search.setAdapter(adapter);
        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String lecture_name= String.valueOf(parent.getItemAtPosition(position));


                for(DataSnapshot dataSnapshot1:dataSnapshot3.getChildren()){

                    if(lecture_name==String.valueOf(dataSnapshot1.child("LectureName").getValue())){
                        Intent b =new Intent(view.getContext(),Lecturedetails.class);

                        b.putExtra("professor",String.valueOf(dataSnapshot1.child("Professor").getValue()));
                        b.putExtra("date",String.valueOf(dataSnapshot1.child("Dates").getValue()));
                        b.putExtra("time",String.valueOf(dataSnapshot1.child("Time").getValue()));
                        b.putExtra("venue",String.valueOf(dataSnapshot1.child("Venue").getValue()));
                        b.putExtra("topic",String.valueOf(dataSnapshot1.child("LectureName").getValue()));
                        view.getContext().startActivity(b);

                    }


                }


            }
        });



    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};

}
