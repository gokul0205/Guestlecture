package com.example.guestlec;

import android.content.Intent;
import android.net.Uri;
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
  Button book_lecture,locate_venue;
  private DatabaseReference lecture_database;
  private DatabaseReference user_lecture_database;
  private FirebaseDatabase database;
  String lecture_id;
  Bundle b;
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
        locate_venue=findViewById(R.id.locate_venue);
        b = getIntent().getExtras();
        professor.setText("Professor: " + b.getString("professor"));
        lecture.setText("Lecture: " + b.getString("topic"));
        date.setText("Date: " + b.getString("date"));
        venue.setText("Venue: " + b.getString("venue"));
        time.setText("Time: " + b.getString("time"));

        book_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Lecturedetails.this,SeatDetails.class);
                i.putExtra("LectureName",lecture.getText().toString());
                startActivity(i);


            }
        });

        locate_venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String place_code=b.getString("venue").toLowerCase();
                String coordinates="0,0";
                switch(place_code)
                {
                    case "ab1":
                        coordinates="10.9005357,76.9023769";
                        break;
                    case "ab2":
                        coordinates="10.9039061,76.8987575";
                        break;
                    case "ab3":
                        coordinates="10.9039061,76.8987575";
                        break;
                    default:

                        System.out.println("no match");
                }
                String param ="google.navigation:q="+coordinates;

                Uri gmmIntentUri = Uri.parse(param);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        }


}



