package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HealthyFoodDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnAddToCart,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_food_details);

        tvPackageName = findViewById(R.id.textViewPackageName);
        tvTotalCost = findViewById(R.id.textViewTotalCost);
        edDetails = findViewById(R.id.editTextTextMultiLine);
        btnAddToCart = findViewById(R.id.buttonHFDAddToCart);
        btnBack = findViewById(R.id.buttonHFDBack);

        //Make this not editable
        edDetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyFoodDetailsActivity.this, HealthyFood.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                //Creating database object
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                //Check if product is already in the cart
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already added",Toast.LENGTH_SHORT).show();
                }else{
                    //add to cart output a message and return to the previous page
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Record inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HealthyFoodDetailsActivity.this, HealthyFood.class));
                }
            }
        });
    }
}