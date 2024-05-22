package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome " + username,Toast.LENGTH_SHORT).show();
        CardView exit = findViewById(R.id.cardExit);
           exit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.apply();
                    //After we click on logout we clear everything which is stored in the local memory and then go back to login page
                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));
               }
           });
           CardView findTrainer = findViewById(R.id.cardFindTrainer);
        findTrainer.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(HomeActivity.this, FindTrainer.class));
               }
           });
           CardView labTest = findViewById(R.id.cardHealthyFood);
            labTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, HealthyFood.class));
                }
            });
            CardView orderDetails = findViewById(R.id.cardOrderDetails);
            orderDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
                }
            });
            CardView buySupplements = findViewById(R.id.cardBuySupplements);
        buySupplements.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, BuySupplementsActivity.class));
                }
            });

            CardView fitness = findViewById(R.id.cardFitnessArticle);
            fitness.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, FitnessArticlesActivity.class));
                }
            });
            finish();
            }
        }