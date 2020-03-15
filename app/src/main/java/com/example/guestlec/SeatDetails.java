package com.example.guestlec;

import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SeatDetails extends AppCompatActivity {
    Button confirm;
    static int i=0;
    private DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_details);
        confirm=findViewById(R.id.confirm_seat);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map notemap = new HashMap();

                Bundle b=getIntent().getExtras();
                dref=FirebaseDatabase.getInstance().getReference().child("User_Lectures");
                notemap.put("User UID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                notemap.put("LectureName",b.getString("LectureName").toString());

                //newLectureref.child(notemap.get("LectureName").toString()).setValue(notemap);
                Thread makeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dref.push().setValue(notemap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Lecture Added Sucessfully", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
