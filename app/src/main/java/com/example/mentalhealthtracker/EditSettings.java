package com.example.mentalhealthtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditSettings extends AppCompatActivity {

    EditText sFullName, sEmail, sPhone;
    Button saveBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_settings);

        Intent data = getIntent();
        String fullName = data.getStringExtra("fName");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        sFullName = findViewById(R.id.editName);
        sEmail = findViewById(R.id.editEmail);
        sPhone = findViewById(R.id.editPhone);
        saveBtn = findViewById(R.id.saveSettingBtn);

        sFullName.setText(fullName);
        sEmail.setText(email);
        sPhone.setText(phone);


        //update database
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sFullName.getText().toString().isEmpty() || sEmail.getText().toString().isEmpty() || sPhone.getText().toString().isEmpty()){
                    Toast.makeText(EditSettings.this, "One or more fields empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String email = sEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef = fStore.collection("Users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("fName", sFullName.getText().toString());
                        edited.put("email", sEmail);
                        edited.put("phone", sPhone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditSettings.this, "Settings Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Settings.class));
                              //  finish();
                            }
                        });
                        Toast.makeText(EditSettings.this, "Email is updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditSettings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}