package com.example.mobiledevfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutMePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutmepage);
        getSupportActionBar().setTitle("About Me");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}