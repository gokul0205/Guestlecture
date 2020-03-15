package com.example.guestlec;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class Lecturedetails extends AppCompatActivity {
    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4","Person 8", "Person 9", "Person 10", "Person 11"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4,R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4));
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
    Button add_comment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture);

        add_comment=findViewById(R.id.add_comment);
        add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialogBuilder = new AlertDialog.Builder(Lecturedetails.this).create();
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);

                final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);
                Button button1 = (Button) dialogView.findViewById(R.id.buttonSubmit);
                Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // DO SOMETHINGS
                        dialogBuilder.dismiss();
                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();

            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(Lecturedetails.this, personNames,personImages);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

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



