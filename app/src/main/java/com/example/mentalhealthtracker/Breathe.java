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
    TextView breathText, breathText2, breathText3;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        breathSpiral = findViewById(R.id.breatheImage);
        breathText = findViewById(R.id.breathGuide);
        breathText2 = findViewById(R.id.breathGuide2);
        breathText3 = findViewById(R.id.breathText);
        startBtn = findViewById(R.id.startBtn);
        mBackButton = findViewById(R.id.backBtn2);

        startBreathingTextIntro();

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
                        breathText.setVisibility(View.VISIBLE);
                        breathText2.setVisibility(View.VISIBLE);
                        breathText.setText("Inhale");
                        breathText2.setText("Exhale");
                        startBtn.setVisibility(View.INVISIBLE);
                        breathText3.setVisibility(View.INVISIBLE);



                    }
                })
                .decelerate()
                .duration(1000)
                .thenAnimate(breathSpiral)
                .scale(0.02f, 1.5f, 0.02f)
                .rotation(180)
                .repeatCount(9)
               // .accelerate()
                .duration(6500)

                .andAnimate(breathText)
                .scale(.02f, 1.2f, 0.2f)
                .duration(6500)


                .andAnimate(breathText2)
                .scale(1.2f, 0.2f, 1.2f)
                .duration(6500)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        breathText.setVisibility(View.INVISIBLE);
                        breathText2.setVisibility(View.INVISIBLE);
                        breathSpiral.setScaleX(1.0f);
                        breathSpiral.setScaleY(1.0f);
                        startBtn.setVisibility(View.VISIBLE);
                        startBtn.setText("Breathe again");
                        breathText3.setText("Well done!");
                        breathText3.setVisibility(View.VISIBLE);


                    }
                })

                .start();


    }

//    private void startBreathingText() {
//
//        ViewAnimator
//                .animate(breathText)
//                .scale(0f, 1f, 0f)
//                .duration(5000);
//
//    }

    private void startBreathingTextIntro() {

        ViewAnimator
                .animate(breathText3)
                .scale(0f, 1f)
                .duration(1500)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        breathText3.setText("Lets take 10 breaths");
                    }
                })
                .start();
    }


}