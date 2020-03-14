package com.example.guestlec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class Profile extends Fragment {
    TextView name,email,id;
    ImageView photo;
    Button sign_out;
    GoogleSignInClient mGoogleSignInClient;
    Button book_lecture;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.profile,container,false);
        name=view.findViewById(R.id.Name);
        book_lecture=view.findViewById(R.id.Addlecture);
        email=view.findViewById(R.id.email);

        book_lecture=view.findViewById((R.id.book_lecture));
        intent = new Intent(getActivity(), AddLecture.class);

     //   id=view.findViewById(R.id.username);
        photo=view.findViewById(R.id.profilephoto);
     //   sign_out=view.findViewById(R.id.button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
         //   String personId = acct.getId();
            String personPhoto = acct.getPhotoUrl().toString();
            name.setText(personName);
            email.setText(personEmail);
        //    id.setText(personId);

            Glide
                    .with(getContext()).load(personPhoto)
                    .override(450, 400)
                    .centerCrop()
                    .into(photo);

        }else{
            SharedPreferences preferences=this.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);

            String personEmail = preferences.getString("email","");
            String personName = preferences.getString(personEmail,"");
            name.setText(personName);
            email.setText(personEmail);

        }
        book_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

return view;
    }
}
