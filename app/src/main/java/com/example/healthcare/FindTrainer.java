package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindTrainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trainer);

        CardView exit = findViewById(R.id.cardFTBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindTrainer.this,HomeActivity.class));
            }
        });
        CardView fitnessTrainer = findViewById(R.id.cardFTFitnessTrainer);
        fitnessTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindTrainer.this, TrainerDetailsActivity.class);
                it.putExtra("title","Fitness Trainer");
                startActivity(it);
            }
        });
        CardView lifestyleCoach = findViewById(R.id.cardFTLifestyleCoach);
        lifestyleCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindTrainer.this, TrainerDetailsActivity.class);
                it.putExtra("title","Lifestyle Coach");
                startActivity(it);
            }
        });
        CardView sportsCoach = findViewById(R.id.cardFTSportsCoach);
        sportsCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindTrainer.this, TrainerDetailsActivity.class);
                it.putExtra("title","Sports Coach");
                startActivity(it);
            }
        });
        CardView personalTrainer = findViewById(R.id.cardFTPersonalTrainer);
        personalTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindTrainer.this, TrainerDetailsActivity.class);
                it.putExtra("title","Personal trainer");
                startActivity(it);
            }
        });
        CardView athleticTrainer = findViewById(R.id.cardFTAthleticTrainer);
        athleticTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindTrainer.this, TrainerDetailsActivity.class);
                it.putExtra("title","Athletic trainer");
                startActivity(it);
            }
        });
        finish();
    }
}