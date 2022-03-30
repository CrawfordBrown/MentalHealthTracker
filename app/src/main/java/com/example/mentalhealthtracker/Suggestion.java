package com.example.mentalhealthtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Suggestion extends AppCompatActivity {

    TextView sugText1, sugText2, sugText3, sugText4, sugText5;
    ImageView iBackBtn, smileImage;
    FirebaseAuth fAuth;
    FirebaseDatabase fDataB;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        fDataB = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();
        sugText1 = findViewById(R.id.SuggestedText);
        sugText2 = findViewById(R.id.suggestedText2);
        sugText3 = findViewById(R.id.suggestedText3);
        sugText4 = findViewById(R.id.suggestedText4);
        sugText5 = findViewById(R.id.suggestedText5);
        iBackBtn = findViewById(R.id.backBtn);
        smileImage = findViewById(R.id.smileImageView);

        iBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });



        DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot.exists()) {

                    long hapResult = documentSnapshot.getLong("HappinessResult");
                    long prodResult = documentSnapshot.getLong("ProductivityResult");
                    long peopResult = documentSnapshot.getLong("PeopleMetResult");


                    if (hapResult <= 60) {

                        sugText1.setText("Your happiness score is less than 60");

                        if (prodResult <= 55) {
                            sugText2.setText("Why not start a new project or finish an old one");
                        }
                        if (prodResult > 55) {
                            sugText2.setText("You have had a productive day :)");
                        }
                        if (peopResult <=5) {
                            sugText3.setText("Go visit a friend or make a new one");
                        }
                        if (peopResult > 5) {
                            sugText3.setText("You have met loads of people today. Good Job");
                        }
                        if (documentSnapshot.getString("OutsideResult").length() == 2) {
                            sugText4.setText("Go for a walk outside");
                        }
                        if (documentSnapshot.getString("OutsideResult").length() == 3) {
                            sugText4.setText("You have been outside today but feel free to go again");
                        }
                        if (documentSnapshot.getString("ExerciseResult").length() == 2) {
                            sugText5.setText("Do a workout or go for a walk");
                        }
                        if (documentSnapshot.getString("ExerciseResult").length() == 2) {
                            sugText5.setText("You have already exercised. Well done");
                        }
                    } else {
                        sugText1.setText("Your happiness score is higher than 60 :)");
                        smileImage.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

    }
}