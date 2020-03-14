package com.example.guestlec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    FrameLayout frameLayout;

    @Override
    protected void onPause() {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
GoogleSignInClient googleSignInClient=GoogleSignIn.getClient(this,gso);
googleSignInClient.signOut();
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottomnavbar);

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
   frameLayout=findViewById(R.id.fragmentcontainer);


    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    Fragment fragment=null;


                    switch (item.getItemId()) {
                        case R.id.settings:
                            fragment=new frag_settings();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragment).commit();
                            return true;
                        case R.id.search:
                            fragment=new Search();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragment).commit();
                            return true;
                        case R.id.home:
                            fragment=new Homebutton();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragment).commit();
                            return true;
                        case R.id.profile:
                            fragment=new Profile();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragment).commit();
                            return true;
                        case R.id.selected:
                            fragment=new Selected();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,fragment).commit();
                            return true;
                    }
                    return false;
                }
            };


}
