package com.example.mentalhealthtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Question1 extends AppCompatActivity {

    SeekBar seekBar;

    TextView textView;
    Button qSubmitBtn;

    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.rating);
        qSubmitBtn = findViewById(R.id.submitBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();
   //
    int currentValue = seekBar.getProgress();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 50;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;

                textView.setText(progress + "/" + seekBar.getMax());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something here, if you want to do anything at the start of
                // touching the seekbar


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Display the value in textview
                textView.setText(progress + "/" + seekBar.getMax());

            }
        });

        qSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
                Map<String, Object> updated = new HashMap<>();
                updated.put("HappinessResult", seekBar.getProgress());
                documentReference.update(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Question1.this, "Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Question2.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Question1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());
                    }
                });

            }
        });
    }
}


