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

public class Question4 extends AppCompatActivity {

    Button qSubmitBtn4;

    RadioButton RBtnYes, RbtnNo;

    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        qSubmitBtn4 = findViewById(R.id.submitBtn4);

        RBtnYes = findViewById(R.id.yesRadioButton);
        RbtnNo = findViewById(R.id.NoRadioBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        qSubmitBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RBtnYes.isChecked()) {
                DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
                Map<String, Object> updated = new HashMap<>();
                updated.put("OutsideResult", "Yes");
                documentReference.update(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Question4.this, "Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Question5.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Question4.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
                if(RbtnNo.isChecked()) {
                    DocumentReference documentReference = fStore.collection("Users").document(userID).collection("Results").document("Results");
                    Map<String, Object> updated = new HashMap<>();
                    updated.put("OutsideResult", "No");
                    documentReference.update(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Question4.this, "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Question5.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Question4.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Toast.makeText(Question4.this, "No answer Selected", Toast.LENGTH_SHORT).show();
            }
            }
            });


}
}