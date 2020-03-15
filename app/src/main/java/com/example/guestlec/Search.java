package com.example.guestlec;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Search extends Fragment {
    EditText LectureName,LectureDetails,Venue,Time,Professor,Dates,Area;
    private DatabaseReference dref;
    Button Addlectures;
    FirebaseAuth firebaseAuth;
    String uid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search, container, false);


        LectureName = (EditText)view.findViewById(R.id.lecture_lecture);
        LectureDetails = (EditText) view.findViewById(R.id.details_lecture);
        Venue = (EditText) view.findViewById(R.id.venue_lecture);
        Time = (EditText) view.findViewById(R.id.time_lecture);
        Professor = (EditText) view.findViewById(R.id.professor_lecture);
        Dates = (EditText) view.findViewById(R.id.date_lecture);
        Area=(EditText) view.findViewById(R.id.area_lecture);
        // lecture_id = dref.push().getKey();
        Addlectures = (Button) view.findViewById(R.id.Addlecture);
        firebaseAuth = FirebaseAuth.getInstance();
        dref = FirebaseDatabase.getInstance().getReference().child("Lectures");



        Addlectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference newLectureref = dref;
                uid=dref.push().getKey();
                final Map notemap = new HashMap();

                notemap.put("Professor", Professor.getText().toString());
                notemap.put("LectureName",LectureName.getText().toString());
                notemap.put("LectureDetails",LectureDetails.getText().toString());
                notemap.put("Venue",Venue.getText().toString());
                notemap.put("Time",Time.getText().toString());
                notemap.put("Dates",Dates.getText().toString());
                notemap.put("Area",Area.getText().toString());
                //newLectureref.child(notemap.get("LectureName").toString()).setValue(notemap);
                Thread makeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newLectureref.push().setValue(notemap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Lecture Added Sucessfully", Toast.LENGTH_SHORT).show();
                                     getActivity().finish();

                                } else {
                                    Toast.makeText(getContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    //   Toast.makeText(add_note_activity.this, "Error : "+fAuth.getCurrentUser(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                makeThread.start();
            }
        });

     return view;
    }


}

