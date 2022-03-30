package com.example.mentalhealthtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class Results extends AppCompatActivity {

    ImageView rBackBtn;
    DatabaseReference dbRef;
    TextView happinessText,peopleMetText, productiveText, outsideText, exerciseText;
    Button sugestBtn;

    FirebaseAuth fAuth;

    FirebaseDatabase fDataB;

    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        rBackBtn = findViewById(R.id.backBtn);

        happinessText = findViewById(R.id.getText1);
        productiveText = findViewById(R.id.getText2);
        peopleMetText = findViewById(R.id.getText3);
        outsideText = findViewById(R.id.outsideText);
        exerciseText = findViewById(R.id.exerciseText);
        sugestBtn = findViewById(R.id.suggestionBtn);

        fDataB = FirebaseDatabase.getInstance();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();


        rBackBtn.setOnClickListener(new View.OnClickListener() {
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
                    happinessText.setText(documentSnapshot.getLong("HappinessResult").toString());
                    productiveText.setText(documentSnapshot.getLong("ProductivityResult").toString());
                    peopleMetText.setText(documentSnapshot.getLong("PeopleMetResult").toString());
                    if(documentSnapshot.getString("ExerciseResult").length() == 3) {
                        exerciseText.setText("You have exercised today");
                    }
                    if(documentSnapshot.getString("ExerciseResult").length() == 2)
                        {
                        exerciseText.setText("You have not exercised today");
                    }
                    if(documentSnapshot.getString("OutsideResult").length() == 3) {
                        outsideText.setText("You have been outside today");
                    }
                    if(documentSnapshot.getString("OutsideResult").length() == 2)
                        {
                        outsideText.setText("You have not been outside today");
                    }
                }

            }
        });

        sugestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Suggestion.class));
                finish();
            }
        });

    }
}