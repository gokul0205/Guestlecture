package com.example.guestlec;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search extends Fragment {
    private FirebaseDatabase database;
    AutoCompleteTextView searchtext;
    DatabaseReference myRef;
    String [] array ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search, container, false);
        searchtext = view.findViewById(R.id.search);
         myRef=FirebaseDatabase.getInstance().getReference("Lectures");
         myRef.addListenerForSingleValueEvent(valueeventlistener);
// Write a message to the database
     return view;
    }

ValueEventListener valueeventlistener =new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        if(dataSnapshot.exists()){
            array=new String[Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()))];
            int i=0;
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                array[i]=String.valueOf(dataSnapshot1.child("LectureName").getValue());

                        i+=1;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.select_dialog_item,array);
            searchtext.setAdapter(adapter);

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};

}

