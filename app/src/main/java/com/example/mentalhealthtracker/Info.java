package com.example.mentalhealthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Info extends AppCompatActivity {

    ImageView IBackBtn;
    TextView AnxUrlLink, DepUrlLink, SaneUrlLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        AnxUrlLink = findViewById(R.id.anxietyURLtext);
        DepUrlLink = findViewById(R.id.depressionURLtext);
        SaneUrlLink = findViewById(R.id.saneURLtext);

        IBackBtn = findViewById(R.id.backBtn);

        IBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        Linkify.addLinks(AnxUrlLink, Linkify.ALL);
        Linkify.addLinks(DepUrlLink, Linkify.ALL);
        Linkify.addLinks(SaneUrlLink, Linkify.ALL);

    }
}