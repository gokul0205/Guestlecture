package com.example.guestlec;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeatDetails extends AppCompatActivity {
    Button confirm;
    static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_details);
        confirm=findViewById(R.id.confirm_seat);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SeatDetails.this,String.valueOf(i),Toast.LENGTH_SHORT).show();


            }
        });
    }
}
