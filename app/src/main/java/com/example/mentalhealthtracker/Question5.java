package com.example.mentalhealthtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

public class Question5 extends AppCompatActivity {

    Button qSubmitBtn5;
    RadioButton RBtnYes, RbtnNo;

    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        qSubmitBtn5 = findViewById(R.id.submitBtn5);
        RBtnYes = findViewById(R.id.yesRadioButton2);
        RbtnNo = findViewById(R.id.NoRadioBtn2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        qSubmitBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RBtnYes.isChecked()) {
                    DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
                    Map<String, Object> updated = new HashMap<>();
                    updated.put("ExerciseResult", "Yes");
                    documentReference.update(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Question5.this, "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Results.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Question5.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                if(RbtnNo.isChecked()) {
                    DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
                    Map<String, Object> updated = new HashMap<>();
                    updated.put("ExerciseResult", "No");
                    documentReference.update(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Question5.this, "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Results.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Question5.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            else {
                    Toast.makeText(Question5.this, "No answer Selected", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}