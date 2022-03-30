package com.example.mentalhealthtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Settings extends AppCompatActivity {
    TextView fullName, email, phoneNumber;
    ImageView mBackButton;
    Button EditSettings, resetPassword;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fullName = findViewById(R.id.settingsName);
        email = findViewById(R.id.settingsEmail);
        phoneNumber = findViewById(R.id.settingsPhone);
        EditSettings = findViewById(R.id.EditSettingsBtn);
        mBackButton = findViewById(R.id.backBtn1);
        resetPassword = findViewById(R.id.resetPasswordBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();


        //get user info from Firestore
        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fullName.setText(documentSnapshot.getString("fName"));
                email.setText(documentSnapshot.getString("email"));
                phoneNumber.setText(documentSnapshot.getString("phone"));
            }
        });


        //edit user info
        EditSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EditSettings.class);
                i.putExtra("fName", fullName.getText().toString());
                i.putExtra("email", email.getText().toString());
                i.putExtra("phone", phoneNumber.getText().toString());
                startActivity(i);

            }
        });

        //reset password
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetPassword = new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Password Reset");
                passwordResetDialog.setMessage("Please Enter Your New Password");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("yes",  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String newPassword = resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Settings.this, "Password Reset Successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Settings.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    } });
                passwordResetDialog.create().show();
                }});





        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}