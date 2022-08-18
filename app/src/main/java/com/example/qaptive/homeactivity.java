package com.example.qaptive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class homeactivity extends AppCompatActivity {

    private Button signoutbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        signoutbtn = findViewById(R.id.signoutbtn);
        mAuth = FirebaseAuth.getInstance();

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(homeactivity.this, SigninActivity.class));
                finish();
            }
        });

    }
}