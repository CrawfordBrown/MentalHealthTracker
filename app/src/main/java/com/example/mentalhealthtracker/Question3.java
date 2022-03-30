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

public class Question3 extends AppCompatActivity {
    SeekBar seekBar3;
    Button qSubmitBtn3;
    TextView textView3;

    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        seekBar3 = findViewById(R.id.seekBar3);
        textView3 = findViewById(R.id.rating3);
        qSubmitBtn3 = findViewById(R.id.submitBtn3);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();


        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 50;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;

                textView3.setText(progress + "/" + seekBar.getMax());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something here, if you want to do anything at the start of
                // touching the seekbar


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Display the value in textview
                textView3.setText(progress + "/" + seekBar.getMax());

            }
        });

        qSubmitBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
                Map<String, Object> updated = new HashMap<>();
                updated.put("PeopleMetResult", seekBar3.getProgress());
                documentReference.update(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Question3.this, "Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Question4.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Question3.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}