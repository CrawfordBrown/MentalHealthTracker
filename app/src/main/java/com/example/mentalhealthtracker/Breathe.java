package com.example.mentalhealthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

public class Breathe extends AppCompatActivity {
    ImageView breathSpiral, mBackButton;
    TextView breathText;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        breathSpiral = findViewById(R.id.breatheImage);
        breathText = findViewById(R.id.breathGuide);
        startBtn = findViewById(R.id.startBtn);
        mBackButton = findViewById(R.id.backBtn2);

        startBreahingTextIntro();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBreathingImage();
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    private void startBreathingImage() {

        ViewAnimator
                .animate(breathSpiral)
                .alpha(0, 1)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        breathText.setText("Inhale...Exhale");
                        startBtn.setVisibility(View.INVISIBLE);
                    }
                })
                .decelerate()
                .duration(1000)
                .thenAnimate(breathSpiral)
                .scale(0.02f, 1.5f, 0.02f)
                .rotation(180)
                .repeatCount(1)
               // .accelerate()
                .duration(5000)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        breathText.setText("Well done");
                        breathSpiral.setScaleX(1.0f);
                        breathSpiral.setScaleY(1.0f);
                        startBtn.setVisibility(View.VISIBLE);
                        startBtn.setText("Breathe again");

                    }
                })
                .start();


    }

    private void startBreahingText() {

    }

    private void startBreahingTextIntro() {

        ViewAnimator
                .animate(breathText)
                .scale(0f, 1f)
                .duration(1500)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        breathText.setText("Lets take 10 breaths");
                    }
                })
                .start();
    }


}