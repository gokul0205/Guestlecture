package com.example.guestlec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Homebutton extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Lectures> lecturesList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.homebutton,container,false);
        lecturesList=new ArrayList<>();
lecturesList.add(new Lectures("Indra Kumar","Subject topic","11th February","9:00 AM","Amrita"));
        lecturesList.add(new Lectures("Vikram C","Subject topic","12th February","9:00 AM","Amrita"));

        lecturesList.add(new Lectures("Arjun R","Subject topic","13th February","9:00 AM","Amrita"));
        lecturesList.add(new Lectures("Thejaswaghiri","Subject topic","14th February","9:00 AM","Amrita"));
        lecturesList.add(new Lectures("Lochan","Subject topic","15th February","9:00 AM","Amrita"));

        recyclerView= view.findViewById(R.id.lectures);
         layoutManager= new LinearLayoutManager(getContext());
recyclerView.setLayoutManager(layoutManager);
adapter=new CustomRecyleradapter(getContext(),lecturesList);
recyclerView.setAdapter(adapter);




        return view;


    }


}
