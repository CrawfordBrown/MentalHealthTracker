package com.example.mentalhealthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageView mLogOut, mSetting, mBreathe, mQuestion, mResults, mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogOut = findViewById(R.id.logout);
        mSetting = findViewById(R.id.settings);
        mBreathe = findViewById(R.id.meditateBtn);
        mQuestion = findViewById(R.id.QuestionBtn);
        mResults = findViewById(R.id.Results);
        mInfo = findViewById(R.id.InfoBtn);

        mLogOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Settings.class));
                finish();
            }
        });

        mBreathe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Breathe.class));
                finish();
            }
        });


        mQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Question1.class));
                finish();
            }
        });

        mResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Results.class));
                finish();
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Info.class));
                finish();
            }
        });
    }





}