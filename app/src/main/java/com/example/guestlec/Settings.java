package com.example.guestlec;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Settings extends Fragment {
    private DatabaseReference dref;
    ListView myListView;
    FirebaseAuth fAuth;
    private FirebaseDatabase database;
    ArrayList<String> Lecture_Name = new ArrayList<>();
    ArrayList<String> Lecture_Details=new ArrayList<>();
    ArrayAdapter<String> adapter;
    private String arr[]=new String[500];
   // private String arr[]=new String[500];
    String UserID;
    FirebaseUser newuser;
    String uid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=inflater.inflate(R.layout.user_list_view,container,false);
        fAuth=FirebaseAuth.getInstance();
        ListView myListView=(ListView)view.findViewById(R.id.mylistView);
        dref = database.getReference("User_Lectures").child(fAuth.getCurrentUser().getUid());//gives the uid of the current user

        dref.addValueEventListener(new ValueEventListener() {//used to get the datasnapshot of all the children
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String count = String.valueOf(dataSnapshot.getChildrenCount());
                int counts = Integer.parseInt(count);//check if zero lectures or not
                if(counts==0){
                    Toast.makeText(getActivity(), "No lectures present,So add new lecture!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Load(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
    public void Load(final DataSnapshot dataSnapshot) {
        try {
            int i = 0;
            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                Lecture_Name.add(String.valueOf(ds.child("LectureName").getValue()));
                arr[i]=String.valueOf(ds.child("UserID").getValue());
                Lecture_Details.add(String.valueOf(ds.child("LectureDetails").getValue())) ;//check this
                i++;
            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "DataBase Error\nPlease Try Again After Sometime!", Toast.LENGTH_SHORT).show();
        }
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Lecture_Name);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //  noteID =arr[i];
                String lec_name = String.valueOf(dataSnapshot.child(UserID).child("LectureName").getValue());
                String lec_details = String.valueOf(dataSnapshot.child(UserID).child("LectureDetails").getValue());
                String lec_time = String.valueOf(dataSnapshot.child(UserID).child("Time").getValue());
                String lec_venue = String.valueOf(dataSnapshot.child(UserID).child("Venue").getValue());
                Intent intent = new Intent(getActivity(), Status.class);
                intent.putExtra("LectureName", lec_name);
                intent.putExtra("LectureDetails", lec_details);
                intent.putExtra("noteID", UserID);
                intent.putExtra("Time", lec_time);
                intent.putExtra("Venue", lec_venue);
                startActivity(intent);
            }
        });
    }

}

