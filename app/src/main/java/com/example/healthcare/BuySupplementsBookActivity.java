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

public class BuySupplementsBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact;
    Button btnBooking, btnBackSupplements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_supplements_book);
        edname = findViewById(R.id.editTextFullnameBMS);
        edaddress = findViewById(R.id.editTextAddressBMS);
        edcontact = findViewById(R.id.editTextContactBMS);
        btnBooking = findViewById(R.id.buttonBookingSupplement);
        btnBackSupplements = findViewById(R.id.buttonBackSupplement);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),date.toString(),"",Float.parseFloat(price[1].toString()),"supplement");
                db.removeCart(username,"supplement");
                Toast.makeText(getApplicationContext(),"Your booking is done succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuySupplementsBookActivity.this, HomeActivity.class));
            }
        });
        btnBackSupplements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuySupplementsBookActivity.this, BuySupplementsActivity.class));
            }
        });
    }
}