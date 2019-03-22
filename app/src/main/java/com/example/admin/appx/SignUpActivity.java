package com.example.admin.appx;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private Button signup;
    private TextView mailID;
    private TextView password;
    private TextView name;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup=findViewById(R.id.signup);
        mailID=findViewById(R.id.mailID_new);
        password=findViewById(R.id.password_new);
        name=findViewById(R.id.name);
        auth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress=new ProgressDialog(getApplicationContext());
                progress.setMessage("Loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();
                if((mailID.getText().length()>0)&&(password.getText().length()>0)&&(name.getText().length()>0)){
                    auth.createUserWithEmailAndPassword(mailID.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progress.dismiss();
                                    if(task.isSuccessful()){
                                        currentUser=auth.getCurrentUser();
                                        startActivity(new Intent(getApplicationContext(),complaintsActivity.class) );
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Authentication failed! Please Try Again Later!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else{
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"Please fill all the fields!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
