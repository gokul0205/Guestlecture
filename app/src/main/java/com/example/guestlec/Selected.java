package com.example.guestlec;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Selected extends Fragment {
    private DatabaseReference dref;
    ListView myListView;
    //FirebaseAuth fAuth;
    private FirebaseDatabase database;
    ArrayList<String> lecture_name;
    ArrayAdapter<String> adapter;
    //private String arr[]=new String[500];
    String noteID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.list_display,container,false);
        lecture_name=new ArrayList<>();

        myListView= view.findViewById(R.id.lecture_list);


        dref = FirebaseDatabase.getInstance().getReference("User_Lectures");
        dref.addValueEventListener(new ValueEventListener() {//used to get the datasnapshot of all the children
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
if(getContext()!=null){
                SharedPreferences pref=getContext().getSharedPreferences("emailprefs", Context.MODE_PRIVATE);
                String email=pref.getString("email","");
          //    Toast.makeText(getContext(),email,Toast.LENGTH_LONG).show();

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    if(email.equals(String.valueOf(dataSnapshot1.child("Email").getValue()))){
                    //    Toast.makeText(getContext(),String.valueOf(dataSnapshot1.child("Email").getValue()),Toast.LENGTH_LONG).show();

                         lecture_name.add(String.valueOf(dataSnapshot1.child("LectureName").getValue()));
                      //  Toast.makeText(getContext(),String.valueOf(dataSnapshot1.child("LectureName").getValue()),Toast.LENGTH_LONG).show();


                    }

                    myListView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, lecture_name));



                }


            }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    return view;
    }





       // return super.onCreateView(inflater, container, savedInstanceState);
    }




