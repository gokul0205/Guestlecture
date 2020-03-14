package com.example.guestlec;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Lecturedetails extends AppCompatActivity {
  TextView professor,lecture,date,time,venue;
  Button book_lecture;
  private DatabaseReference lecture_database;
  private DatabaseReference user_lecture_database;
  private FirebaseDatabase database;
  String lecture_id;
    String prof;
    String lec;
    String d;
    String t;
    String v;
    FirebaseAuth fAuth;
    String currentFirebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture);


        book_lecture=findViewById(R.id.book);
        professor = findViewById(R.id.lecturer);
        lecture = findViewById(R.id.subject);
        venue = findViewById(R.id.Venue);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        Bundle b = getIntent().getExtras();
        professor.setText("Professor: " + b.getString("professor"));
        lecture.setText("Lecture: " + b.getString("topic"));
        date.setText("Date: " + b.getString("date"));
        venue.setText("Venue: " + b.getString("venue"));
        time.setText("Time: " + b.getString("time"));

        book_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        }


}



