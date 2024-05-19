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
           CardView findDoctor = findViewById(R.id.cardFindDoctor);
           findDoctor.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(HomeActivity.this, FindDoctor.class));
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
            CardView buyMedicine = findViewById(R.id.cardBuyMedicine);
            buyMedicine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
                }
            });

            CardView health = findViewById(R.id.cardHealthDoctor);
            health.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this,HealthArticlesActivity.class));
                }
            });

            }
        }

