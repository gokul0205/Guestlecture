package com.example.guestlec;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddLecture extends AppCompatActivity {
    EditText LectureName,LectureDetails,Venue,Time,Professor,Dates,Area;
    private DatabaseReference dref;
    Button Addlectures;
    FirebaseAuth firebaseAuth;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_lecture);
        LectureName = (EditText) findViewById(R.id.lecture_lecture);
        LectureDetails = (EditText) findViewById(R.id.details_lecture);
        Venue = (EditText) findViewById(R.id.venue_lecture);
        Time = (EditText) findViewById(R.id.time_lecture);
        Professor = (EditText) findViewById(R.id.professor_lecture);
        Dates = (EditText) findViewById(R.id.date_lecture);
        Area=(EditText) findViewById(R.id.area_lecture);
        // lecture_id = dref.push().getKey();
        Addlectures = (Button) findViewById(R.id.Addlecture);
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



                Thread makeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newLectureref.child(uid).setValue(notemap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(AddLecture.this, "Lecture Added Sucessfully", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    Toast.makeText(AddLecture.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    //   Toast.makeText(add_note_activity.this, "Error : "+fAuth.getCurrentUser(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                makeThread.start();
            }
        });
    }
}
