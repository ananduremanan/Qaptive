package com.example.qaptive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mName, mEmail , mUsername, mPass, mPhone;
    private TextView mTextView;
    private Button signUpBtn;

    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mUsername = findViewById(R.id.user_name);
        mPass = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mTextView = findViewById(R.id.signuplink);
        signUpBtn = findViewById(R.id.signupbtn);

        mAuth = FirebaseAuth.getInstance();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , SigninActivity.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }
    private void createUser()
    {
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String user = mUsername.getText().toString();
        String pass = mPass.getText().toString();
        String phone = mPhone.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            if (!pass.isEmpty())
            {
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(MainActivity.this, "Hurray..You're Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this , SigninActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Oops Registration Error Occurred!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    }
                });
            }
            else
            {
                mPass.setError("Password Can't Be Empty!!");
            }
        }
        else if(name.isEmpty())
        {
            mName.setError("Name Can't be Empty");
        }
        else if(email.isEmpty())
        {
            mEmail.setError("Email Can't be Empty");
        }
        else if(user.isEmpty())
        {
            mUsername.setError("User Name Can't be Empty");
        }
        else if(phone.isEmpty())
        {
            mPhone.setError("Phone Number Can't be Empty");
        }
        else
        {
            mEmail.setError("Pleas Enter Correct Email");
        }
    }
}
