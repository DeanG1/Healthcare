package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HealthyFoodBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact;
    Button btnBooking, btnMenuBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_book);
        edname = findViewById(R.id.editTextHBBFullName);
        edaddress = findViewById(R.id.editTextHBBAddress);
        edcontact = findViewById(R.id.editTextHBBContact);
        btnBooking = findViewById(R.id.buttonBooking);
        btnMenuBack = findViewById(R.id.btnMenuBack);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(),"Your booking is done succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HealthyFoodBookActivity.this, HomeActivity.class));
            }
        });
        btnMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyFoodBookActivity.this, HealthyFood.class));
            }
        });

    }
}