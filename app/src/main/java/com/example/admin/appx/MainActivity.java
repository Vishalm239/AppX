package com.example.admin.appx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    public FirebaseAuth auth;
    public EditText mailID;
    public EditText password;
    public Button login;
    public Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailID = findViewById(R.id.mailID);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signUp=findViewById(R.id.signUp);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signin(mailID,password);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }
    public void signin(EditText mailID,EditText password){
        auth.signInWithEmailAndPassword(mailID.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),complaintsActivity.class));
                        }
                        else
                           Toast.makeText(getApplicationContext(), "wrong credentials", Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void signup(){
        auth.createUserWithEmailAndPassword(mailID.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
    }

}
