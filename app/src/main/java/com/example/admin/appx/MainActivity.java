package com.example.admin.appx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText mailID;
    private EditText password;
    private Button login;
    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailID = findViewById(R.id.mailID_new);
        password=findViewById(R.id.password_new);
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

    public void signin(EditText mailID, EditText password){
        final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.show();
        auth.signInWithEmailAndPassword(mailID.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),complaintsActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "wrong credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void signup(){
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

}
