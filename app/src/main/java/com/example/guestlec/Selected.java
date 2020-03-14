package com.example.guestlec;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Selected extends Fragment {
    private DatabaseReference dref;
    ListView myListView;
    //FirebaseAuth fAuth;
    private FirebaseDatabase database;
    ArrayList<String> Lecture_Name = new ArrayList<>();
    ArrayAdapter<String> adapter;
    //private String arr[]=new String[500];
    String noteID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.list_display,container,false);

        //fAuth=FirebaseAuth.getInstance();

        myListView=(ListView) view.findViewById(R.id.lecture_list);

        //dref = database.getReference("User_Lectures").child(fAuth.getCurrentUser().getUid());//gives the uid of the current user

        dref = FirebaseDatabase.getInstance().getReference("User_Lectures").child("ABCD");
        dref.addValueEventListener(new ValueEventListener() {//used to get the datasnapshot of all the children
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getActivity(), dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
                String count = String.valueOf(dataSnapshot.getChildrenCount());
                int counts = Integer.parseInt(count);//check if zero lectures or not
                if(counts==0){
                    Toast.makeText(getActivity(), "Please book a new lecture", Toast.LENGTH_SHORT).show();
                }
                else{
                   // Toast.makeText(getActivity(), "Please dont book a new lecture", Toast.LENGTH_SHORT).show();
                    Log.d("tagh", "onDataChange: ");
                    for(int i = 1; i<4; i++)
                    Lecture_Name.add(String.valueOf(dataSnapshot.child(String.valueOf(i)).getValue()));
                    myListView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, Lecture_Name));

                    //Load(dataSnapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    return view;
    }
    public void Load(final DataSnapshot dataSnapshot){
        try{
            int i=1;
            for (DataSnapshot ds: dataSnapshot.getChildren())
            {
                String t = String.valueOf(i);
                Lecture_Name.add(String.valueOf(ds.child(t).getValue()));
                Log.d("iol",String.valueOf(ds.child(t).getValue()));

                i++;
            }
        }catch (Exception e){

            Toast.makeText(getActivity(), "DataBase Error\nPlease Try Again After Sometime!", Toast.LENGTH_SHORT).show();
        }


        myListView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, Lecture_Name));

    }
       // return super.onCreateView(inflater, container, savedInstanceState);
    }




