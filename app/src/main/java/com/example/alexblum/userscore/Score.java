package com.example.alexblum.userscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Score extends Activity implements View.OnClickListener{

    private Button bLogout;
    private Button bUpdateScore;
    private EditText editScore;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        bLogout = (Button) findViewById(R.id.bLogout);
        bUpdateScore = (Button) findViewById(R.id.bUpdateScore);
        editScore = (EditText) findViewById(R.id.editScore);


        bUpdateScore.setOnClickListener(this);
        bLogout.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out
                }
                // ...
            }
        };
    }



    @Override
    public void onClick(View v) {


        if (v == bUpdateScore) {
            Toast.makeText(this, "Score updating.",Toast.LENGTH_SHORT).show();


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("scores");

            FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

            Toast.makeText(this, "Current user:" + currentuser.getEmail(), Toast.LENGTH_SHORT).show();
            String newscore = editScore.getText().toString();

            User user = new User(newscore,currentuser.getEmail());
            myRef.child(currentuser.getUid().toString()).setValue(user);

            //Login -> Go to Score Activity
        }
        else if (v == bLogout) {
            //Logout
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }
    }

}

