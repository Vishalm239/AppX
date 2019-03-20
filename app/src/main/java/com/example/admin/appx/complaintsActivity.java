package com.example.admin.appx;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.admin.appx.R;

public class complaintsActivity extends AppCompatActivity {

    FloatingActionButton userMenu=new FloatingActionButton();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
    }
}
